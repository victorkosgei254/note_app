package com.example.note_app.views.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app.R;
import com.example.note_app.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;
    OnViewItemClickListener listener;

    public NoteAdapter(Context context, List<Note> data)
    {
        inflater = LayoutInflater.from(context);
        notes = data;
    }
    public void setItems(List<Note> items)
    {
        this.notes = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTitle.setText(note.getNote_title());
        holder.noteDesc.setText(note.getNote_desc());
    }

    @Override
    public int getItemCount() {
        if (notes == null)
        {
            Log.d("NoteAdapter", "getItemCount: is null");
            return 0;
        }
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView noteTitle, noteDesc;

        public ViewHolder(@NonNull View view) {
            super(view);
            noteTitle = view.findViewById(R.id.note_title);
            noteDesc = view.findViewById(R.id.note_desc);
            view.setOnClickListener(vw->{
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION)
                {
                    listener.onViewItemClicked(notes.get(position));
                }

            });

        }
    }

    public interface OnViewItemClickListener{
        void onViewItemClicked(Note note);
    }

    public void setOnViewItemClickListener(OnViewItemClickListener listener){
        this.listener = listener;
    }
}
