package com.mlsdev.serhii.ormlitetestapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.j256.ormlite.dao.Dao;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.view.activity.NoteDetailsActivity;
import com.mlsdev.serhii.ormlitetestapp.view.adapter.NotesAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends BaseViewModel {
    private NotesAdapter notesAdapter;
    private List<Note> noteList;

    public NotesViewModel(Context context) {
        super(context);
        noteList = new ArrayList<>();
    }

    public void onClickEditNoteDetails(View view) {
        openNoteDetails(null);
    }

    public void onEditNote(Note note) {
        openNoteDetails(note);
    }

    private void openNoteDetails(@Nullable Note note) {
        Intent intent = new Intent(context, NoteDetailsActivity.class);

        if (note != null)
            intent.putExtra(EXTRA_NOTE, (Parcelable) note);

        context.startActivity(intent);
    }

    @Override
    public void onStart() {
        loadNotes();
    }

    public void setNotesAdapter(NotesAdapter notesAdapter) {
        this.notesAdapter = notesAdapter;
    }

    private void loadNotes() {
        try {
            Dao<Note, Integer> notesDao = getDatabaseHelper().getNotesDao();
            noteList = notesDao.queryBuilder().orderBy(Note.CREATED_DATE, false).query();
            notesAdapter.setData(noteList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
