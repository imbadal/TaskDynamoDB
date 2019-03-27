package com.example.taskdynamodb;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<NewsDo> list;

    public Adapter(List<NewsDo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.textViewTitle.setText(list.get(i).getTitle());

        viewHolder.textViewTimeStamp.setText(new TimeStampToTime().getDateTime(list.get(i).getTimeStamp()));

        if (list.get(i).isReviewed())
            viewHolder.textViewReview.setText("Verified");
        else
            viewHolder.textViewReview.setText("Not Verified");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewTimeStamp;
        public TextView textViewReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title);
            textViewTimeStamp = itemView.findViewById(R.id.time);
            textViewReview = itemView.findViewById(R.id.review);

        }
    }

}
