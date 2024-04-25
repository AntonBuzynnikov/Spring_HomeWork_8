package ru.buzynnikov.notemanager.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteDTO {
    private String title;
    private String description;
}
