package com.ezequielbolzi.rest.Service;

import com.ezequielbolzi.rest.Model.Note;
import com.ezequielbolzi.rest.Repository.NoteRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    public Optional<Note> findNoteById(long id){
        return noteRepository.findById(id);
    }

    public void deleteNote(Note note){
        noteRepository.delete(note);
    }

    public List<Note> getActiveNotes(){
        return noteRepository.findByArchivedFalse();
    }

    public List<Note> getArchivedNotes(){
        return noteRepository.findByArchivedTrue();
    }

    public void archiveOrUnarchiveNote(long noteId, boolean status){
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        note.setArchived(status);
        noteRepository.save(note);
    }
}
