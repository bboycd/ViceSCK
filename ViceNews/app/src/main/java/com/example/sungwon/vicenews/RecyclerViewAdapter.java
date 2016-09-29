package com.example.sungwon.vicenews;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends CursorRecyclerViewAdapter<RecyclerViewAdapter.ViewHolder> {
    Context context;

    public RecyclerViewAdapter(Context context, Cursor cursor) {

        super(context, cursor);
        this.context = context;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public CardView cardView;


        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.textView);
//            textDetailView = (TextView) view.findViewById(R.id.textDetailView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {

        viewHolder.textView.setText(cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_TITLE)));
//        viewHolder.textDetailView.setText(cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_BODY)));
        //TODO: WILL THIS WORK?
        Picasso.with(context).load(cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_THUMBNAIL))).into(viewHolder.imageView);



        final Integer position = cursor.getPosition();
        viewHolder.cardView.setTag(position);
        //INTENT TO DETAIL VIEW
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                Cursor cursor = getCursor();
                int position = ((Integer) view.getTag()).intValue();
                cursor.moveToPosition(position);
                String title = cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_TITLE));
                String body = cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_BODY));
                String image = cursor.getString(cursor.getColumnIndex(ViceDBHelper.VICENEWS_THUMBNAIL));
                intent.putExtra("title", title);
                intent.putExtra("body", body);
                intent.putExtra("image", image);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    View image2 = view.findViewById(R.id.imageView);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(((Activity) view.getContext()), image2, "imageView");
                    view.getContext().startActivity(intent, options.toBundle());
                }else{
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

}
