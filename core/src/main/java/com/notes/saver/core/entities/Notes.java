package com.notes.saver.core.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes_data")
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noteId;
	private String noteTitle;
	private String noteDescription;
	private Boolean isFavourite = false;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Auth auth;
	@CreationTimestamp
	private LocalDateTime creationTime = LocalDateTime.now();
	
	public Notes() {}
	
	public Notes(Integer noteId, String noteTitle, String noteDescription, Boolean isFavourite,Auth auth,LocalDateTime creationTime) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteDescription = noteDescription;
		this.isFavourite = isFavourite;
		this.auth = auth;
		this.creationTime = creationTime;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public Boolean getIsFavourite() {
		return isFavourite;
	}

	public void setIsFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Notes [noteId=" + noteId + ", noteTitle=" + noteTitle + ", noteDescription=" + noteDescription
				+ ", isFavourite=" + isFavourite + ", creationTime=" + creationTime + "]";
	}



}
