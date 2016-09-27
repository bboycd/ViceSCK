package com.example.sungwon.vicenews;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends CursorRecyclerViewAdapter<RecyclerViewAdapter.ViewHolder> {

    public RecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView, textDetailView;
        public ImageView imageView;
        public CardView cardView;


        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textView);
            textDetailView = (TextView) view.findViewById(R.id.textDetailView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        viewHolder.textView.setText(cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_TITLE)));
        viewHolder.textDetailView.setText(cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_BODY)));
        //TODO: WILL THIS WORK?
        viewHolder.imageView.setImageURI(Uri.parse(ViceDBHelper.VICENEWS_THUMBNAIL));
    }

}
