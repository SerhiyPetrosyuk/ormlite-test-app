package com.mlsdev.serhii.ormlitetestapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mlsdev.serhii.ormlitetestapp.data.DatabaseHelper;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.view.activity.NoteDetailsActivity;
import com.mlsdev.serhii.ormlitetestapp.view.adapter.NotesAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends BaseObservable implements ViewModel {
    private Context context;
    private NotesAdapter notesAdapter;
    private DatabaseHelper databaseHelper;
    private List<Note> noteList;

    public NotesViewModel(Context context) {
        this.context = context;
        noteList = new ArrayList<>();
    }

    public void onClickEditNoteDetails(View view) {
        Intent intent = new Intent(context, NoteDetailsActivity.class);
        context.startActivity(intent);
    }

    private DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null)
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

        return databaseHelper;
    }

    @Override
    public void onStart() {
        loadNotes();
    }

    @Override
    public void onStop() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    public void setNotesAdapter(NotesAdapter notesAdapter) {
        this.notesAdapter = notesAdapter;
    }

    private void loadNotes() {
        try {
            Dao<Note, Integer> notesDao = getDatabaseHelper().getNotesDao();
            noteList = notesDao.queryBuilder().orderBy(Note.LAST_EDITED_DATE, false).query();
            notesAdapter.setData(noteList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
