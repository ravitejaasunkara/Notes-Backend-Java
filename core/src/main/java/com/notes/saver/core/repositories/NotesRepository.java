package com.notes.saver.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notes.saver.core.entities.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {
	List<Notes> findByIsFavourite(Boolean isFavourite);
}
