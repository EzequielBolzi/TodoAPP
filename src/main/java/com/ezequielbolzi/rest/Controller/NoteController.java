package com.ezequielbolzi.rest.Controller;// Import necessary packages
import com.ezequielbolzi.rest.Model.Note;
import com.ezequielbolzi.rest.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Enable Cross-Origin Resource Sharing (CORS) for all origins
@CrossOrigin("*")
// Define this class as a REST controller
@RestController
public class NoteController {
    // Autowired the NoteService so it can be used in this class
    @Autowired
    private NoteService noteService;

    // Map GET requests onto /notes to this method
    @GetMapping(value = "/notes")
    public List<Note> getNote(){
        // Return all notes
        return noteService.getNotes();
    }

    // Map POST requests onto /savenote to this method
    @PostMapping(value = "/savenote")
    public String saveNote(@RequestBody Note note){
        // Save the provided note
        noteService.saveNote(note);
        // Return a success message
        return "Saved Note";
    }

    // Map PUT requests onto /update/{id} to this method
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateNote(@PathVariable long id, @RequestBody Note note){
        // Try to find the note with the given ID
        Optional<Note> optionalNote = noteService.findNoteById(id);
        if (optionalNote.isPresent()) {
            // If the note exists, update its title and save it
            Note updateNote = optionalNote.get();
            updateNote.setTitle(note.getTitle());
            noteService.saveNote(updateNote);
            // Return a success message
            return new ResponseEntity<>("Updated Note", HttpStatus.OK);
        } else {
            // If the note doesn't exist, return an error message
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
        }
    }

    // Map DELETE requests onto /delete/{id} to this method
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable long id){
        // Try to find the note with the given ID
        Optional<Note> optionalNote = noteService.findNoteById(id);
        if (optionalNote.isPresent()) {
            // If the note exists, delete it
            Note deletedNote = optionalNote.get();
            noteService.deleteNote(deletedNote);
            // Return a success message
            return new ResponseEntity<>("Deleted Note", HttpStatus.OK);
        } else {
            // If the note doesn't exist, return an error message
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
        }
    }

    // Map PUT requests onto /archive/{noteId} to this method
    @PutMapping("/archive/{noteId}")
    public String archiveNote(@PathVariable long noteId) {
        // Archive the note with the given ID
        noteService.archiveOrUnarchiveNote(noteId, true);
        // Return a success message
        return "Archived Note";
    }

    // Map PUT requests onto /unarchive/{noteId} to this method
    @PutMapping("/unarchive/{noteId}")
    public String unarchiveNote(@PathVariable long noteId) {
        // Unarchive the note with the given ID
        noteService.archiveOrUnarchiveNote(noteId, false);
        // Return a success message
        return "Unarchived Note";
    }

    // Map GET requests onto /activeNotes to this method
    @GetMapping(value = "/activeNotes")
    public List<Note> getActiveNotes(){
        // Return all active notes
        return noteService.getActiveNotes();
    }

    // Map GET requests onto /archivedNotes to this method
    @GetMapping(value = "/archivedNotes")
    public List<Note> getArchivedNotes(){
        // Return all archived notes
        return noteService.getArchivedNotes();
    }
}
