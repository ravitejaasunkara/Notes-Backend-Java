package com.notes.saver.core.dto;

public class NoteDTO {
	private int id;
	private String title;
	private String description;

	public NoteDTO() {
	}

	public NoteDTO(int id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NoteDTO [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

}
