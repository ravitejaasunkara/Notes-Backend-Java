package com.notes.saver.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.notes.saver.core.dto.ApiResponse;
import com.notes.saver.core.dto.NoteDTO;
import com.notes.saver.core.entities.Auth;
import com.notes.saver.core.entities.Notes;
import com.notes.saver.core.repositories.AuthRepository;
import com.notes.saver.core.repositories.NotesRepository;

@Service
public class NotesService {
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	public ResponseEntity<ApiResponse> getAllNotes(int userId) {
		Optional<Auth> findById = authRepository.findById(userId);
		if(findById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"No user found."));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true,findById.get().getNotes(),"Data found!"));
	}
	
	public ResponseEntity<ApiResponse> getNoteByNoteId(int userId,int noteId) {
		Optional<Notes> note = notesRepository.findById(noteId);
		Optional<Auth> user = authRepository.findById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,String.format("User not found with UserId : ", userId)));
		}
		//here what we are doing is we are checking the userId attatched to the noteId we got,if both were
		//same then only returning the note
		//that means the note belongs to that user only.
		if(note.isEmpty() || note.get().getAuth().getId() != userId) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,String.format("Note not found with NoteId for this UserId : ", userId)));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true,note,"Data found."));
	}
	
	public ResponseEntity<ApiResponse> saveNewNote(Notes note,int userId) {
		Optional<Auth> user = authRepository.findById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"User not found."));
		}
		note.setAuth(user.get());
		Notes save = notesRepository.save(note);
		NoteDTO obj = new NoteDTO();
		obj.setId(note.getNoteId());
		obj.setTitle(note.getNoteTitle());
		obj.setDescription(note.getNoteDescription());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(true,obj,"Entry created successfully"));
	}
	
	public ResponseEntity<ApiResponse> updateNote(Notes note,int userId,int noteId) {
		Optional<Auth> user = authRepository.findById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"User not found."));
		}
		Optional<Notes> notee = notesRepository.findById(noteId);
		if(notee.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"Note not exist to update."));
		}
		if(note.getNoteTitle() != null)
			notee.get().setNoteTitle(note.getNoteTitle());
		if(note.getNoteDescription() != null)
			notee.get().setNoteDescription(note.getNoteDescription());
		notee.get().setCreationTime(LocalDateTime.now());
		notesRepository.save(notee.get());
		NoteDTO obj = new NoteDTO(notee.get().getNoteId(),note.getNoteTitle(),note.getNoteDescription());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true,obj,"Note updated succesfully."));
	}
	
	public ResponseEntity<ApiResponse> deleteNoteById(int noteId,int userId) {
		Optional<Auth> user = authRepository.findById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"User not found."));
		}
		Optional<Notes> note = notesRepository.findById(noteId);
		if(note.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"Note not found."));
		}
		notesRepository.deleteById(noteId);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse(true,null,"Note deleted."));
	}
	
	public ResponseEntity<ApiResponse> changeFavourite(int userId,int noteId) {
		Optional<Auth> user = authRepository.findById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"User not found."));
		}
		Optional<Notes> note = notesRepository.findById(noteId);
		if(note.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,"Note not found."));
		}
		Boolean noteStatus = !note.get().getIsFavourite();
		note.get().setIsFavourite(noteStatus);
		note.get().setCreationTime(LocalDateTime.now());
		notesRepository.save(note.get());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse(true,note.get(),"Note favourite status updated."));
	}
	
	public ResponseEntity<ApiResponse> getAllFavNotes(int userId) {
		List<Notes> allFavNotes = notesRepository.findByIsFavourite(true);
		List<Notes> favnotes = new ArrayList<>();
		for(Notes item:allFavNotes) {
			if(item.getAuth().getId() == userId) {
				favnotes.add(item);
			}
		}
		if(favnotes.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false,null,String.format("No favourite notes found for UserId :%d ", userId)));
		}
		return ResponseEntity.status((HttpStatus.OK))
				.body(new ApiResponse(true,favnotes,"data found."));
	}
}
