package ru.buzynnikov.notemanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.buzynnikov.notemanager.dto.NoteDTO;
import ru.buzynnikov.notemanager.exceptions.NotFoundNoteException;
import ru.buzynnikov.notemanager.models.Note;
import ru.buzynnikov.notemanager.services.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    /*
        Возвращаем в формате json список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }
    /*
        Создаём заметку из тела запроса
        Пример:
        {
            "title":"for example title",
            "description":"for example description"
        }
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteDTO note){
        return new ResponseEntity<>(noteService.saveNote(note), HttpStatus.CREATED);
    }
    /*
        Поиск заметки по id
        В случае, если заметка отсутсвует отправляем ответ с описание ошибки
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@RequestParam Long id){
        try{
            Note note = noteService.getNoteById(id).orElseThrow(NotFoundNoteException::new);
            return new ResponseEntity<>(note,HttpStatus.OK);
        } catch (NotFoundNoteException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Note not found", e);
        }
    }
    /*
        Обновляем заметку по id
     */
    @PostMapping("/update/{id}")
    public ResponseEntity<Note> updateNot(@PathVariable Long id, @RequestBody NoteDTO noteDTO){
        return new ResponseEntity<>(noteService.updateNote(id,noteDTO),HttpStatus.OK);
    }
    /*
        Удаляем заметку по id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
