package com.notes.saver.core.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notes.saver.core.dto.ApiResponse;
import com.notes.saver.core.entities.Notes;
import com.notes.saver.core.services.NotesService;

@RestController
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@GetMapping(path = "/notes/{userId}")
	public ResponseEntity<ApiResponse> getAllNotesByUserId(@PathVariable int userId) {
		ResponseEntity<ApiResponse> allNotes = notesService.getAllNotes(userId);
		return allNotes;
	}
	
	@GetMapping(path = "/notes/{userId}/{noteId}")
	public ResponseEntity<ApiResponse> getSingleNote(@PathVariable int userId,@PathVariable int noteId) {
		ResponseEntity<ApiResponse> noteByNoteId = notesService.getNoteByNoteId(userId, noteId);
		return noteByNoteId;
	}
	
	@PostMapping(path = "/notes/{userId}")
	public ResponseEntity<ApiResponse> saveNewNote(@RequestBody Notes note,@PathVariable int userId) {
		ResponseEntity<ApiResponse> saveNewNote = notesService.saveNewNote(note, userId);
		return saveNewNote;
	}
	
	@DeleteMapping(path = "/notes/{userId}/{noteId}")
	public ResponseEntity<ApiResponse> deleteNote(@PathVariable int userId,@PathVariable int noteId) {
		ResponseEntity<ApiResponse> deleteNoteById = notesService.deleteNoteById(noteId, userId);
		return deleteNoteById;
	}
	
	@PatchMapping(path = "/notes/{userId}/{noteId}")
	public ResponseEntity<ApiResponse> updateNote(@PathVariable int userId,@PathVariable int noteId,@RequestBody Notes note) {
		ResponseEntity<ApiResponse> updateNote = notesService.updateNote(note, userId, noteId);
		return updateNote;
	}
	
	@PatchMapping(path = "/notes/{userId}/{noteId}/updateFav")
	public ResponseEntity<ApiResponse> favNoteUpdate(@PathVariable int userId,@PathVariable int noteId) {
		ResponseEntity<ApiResponse> changeFavourite = notesService.changeFavourite(userId, noteId);
		return changeFavourite;
	}
	
	@GetMapping(path = "/notes/{userId}/favourites")
	public ResponseEntity<ApiResponse> getAllFavouriteNotesOfUser(@PathVariable int userId) {
		ResponseEntity<ApiResponse> allFavNotes = notesService.getAllFavNotes(userId);
		return allFavNotes;
	}
}
