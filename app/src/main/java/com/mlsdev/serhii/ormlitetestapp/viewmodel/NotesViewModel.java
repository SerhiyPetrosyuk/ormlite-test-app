package com.mlsdev.serhii.ormlitetestapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.mlsdev.serhii.ormlitetestapp.view.activity.NoteDetailsActivity;

public class NotesViewModel extends BaseObservable {
    private Context context;

    public NotesViewModel(Context context) {
        this.context = context;
    }

    public void onClickEditNoteDetails(View view) {
        Intent intent = new Intent(context, NoteDetailsActivity.class);
        context.startActivity(intent);
    }

}
