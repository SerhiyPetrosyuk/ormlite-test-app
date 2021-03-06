package com.mlsdev.serhii.ormlitetestapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.j256.ormlite.dao.Dao;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.util.DateUtils;

import org.antlr.v4.runtime.misc.NotNull;

import java.sql.SQLException;

/**
 * Created by serhii on 19.04.16.
 */
public class NoteDetailsViewModel extends BaseViewModel {
    private Note note;
    private boolean isUpdating = false;
    private boolean isNoteDeleted = false;
    public final ObservableField<String> title;
    public final ObservableField<String> description;
    public final ObservableField<String> lastEditingDate;

    public NoteDetailsViewModel(Context context) {
        super(context);
        title = new ObservableField<>();
        description = new ObservableField<>();
        lastEditingDate = new ObservableField<>();
    }

    public void setData(@NotNull Note note) {
        isUpdating = note != null;
        this.note = note;
        if (isUpdating) {
            title.set(note.getTitle());
            description.set(note.getDescription());
            lastEditingDate.set(DateUtils.getFormattedDate(note.getLastEditDate()));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (note == null)
            lastEditingDate.set(DateUtils.getFormattedDate(DateUtils.getCurrentDateTime()));
    }

    public void onPause(String title, String description) {
        insertUpdateNewNote(title, description);
    }

    public void onDeleteNote() {
        if (note != null && note.getId() > 0) {
            isNoteDeleted = true;
            try {
                Dao<Note, Integer> notesDao = getDatabaseHelper().getNotesDao();
                notesDao.delete(note);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void shareNote() {
        Intent sharingIntent = new Intent();
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, description.get());
        sharingIntent.putExtra(Intent.EXTRA_TITLE, title.get());
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, title.get());
        sharingIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sharingIntent, "Share to:"));
    }

    private void insertUpdateNewNote(String title, String description) {
        if (isNoteDeleted)
            return;

        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setDescription(description);

        try {
            Dao<Note, Integer> notesDao = getDatabaseHelper().getNotesDao();
            boolean atListOneFieldNotEmpty = !TextUtils.isEmpty(title) || !TextUtils.isEmpty(description);
            boolean isDataChanged = !title.equals(this.title.get()) || !description.equals(this.description.get());

            if (isUpdating && atListOneFieldNotEmpty) {

                if (isDataChanged) {
                    newNote.setId(note.getId());
                    newNote.setLastEditDate(DateUtils.getCurrentDateTime());
                    newNote.setCreateDate(note.getCreateDate());
                    notesDao.update(newNote);
                }
            } else if (atListOneFieldNotEmpty) {
                newNote.setCreateDate(DateUtils.getCurrentDateTime());
                newNote.setLastEditDate(newNote.getCreateDate());
                notesDao.create(newNote);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
