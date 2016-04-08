package com.mlsdev.serhii.ormlitetestapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.serhii.ormlitetestapp.R;
import com.mlsdev.serhii.ormlitetestapp.databinding.NoteItemBinding;
import com.mlsdev.serhii.ormlitetestapp.model.Note;
import com.mlsdev.serhii.ormlitetestapp.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> noteList;

    public NotesAdapter() {
        noteList = new ArrayList<>();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note item = noteList.get(position);
        holder.noteItemBinding.tvNoteTitle.setText(item.getTitle());
        holder.noteItemBinding.tvNoteShortDescription.setText(item.getDescription());
        holder.noteItemBinding.tvNoteTime.setText(DateUtils.getFormattedDate(item.getLastEditDate()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding noteItemBinding;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteItemBinding = DataBindingUtil.getBinding(itemView);
        }
    }
}
