package com.ezequielbolzi.rest.Repository;

import com.ezequielbolzi.rest.Model.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Modifying
    @Query("update Note n set n.archived = ?1 where n.id = ?2")
    @Transactional
    void setArchivedStatusById(boolean status, Long id);

    List<Note> findByArchivedFalse();

    List<Note> findByArchivedTrue();
}

