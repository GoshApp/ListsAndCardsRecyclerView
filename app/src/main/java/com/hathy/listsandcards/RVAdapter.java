package com.hathy.listsandcards;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ChannelViewHolder> {

    private CustomItemClickListener listener;
    private final ImageLoader IMAGE_LOADER;
    private final ArrayList<HashMap<String,String>> DATA;

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        TextView mTxtPublishedAt;
        ImageView personPhoto;

        ChannelViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.txtTitle);
            personAge = (TextView)itemView.findViewById(R.id.txtDuration);
            personPhoto = (ImageView)itemView.findViewById(R.id.imgThumbnail);
            mTxtPublishedAt = (TextView) itemView.findViewById(R.id.txtPublishedAt);
        }
    }

    List<Person> persons;

    RVAdapter(Context context, ArrayList<HashMap<String, String>> list, CustomItemClickListener listener){
        IMAGE_LOADER = MySingleton.getInstance(context).getImageLoader();
        DATA = list;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video_list, viewGroup, false);

        final ChannelViewHolder pvh = new ChannelViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, pvh.getPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder personViewHolder, int i) {
        HashMap<String, String> item;
        item = DATA.get(i);
        personViewHolder.personName.setText(item.get(Utils.KEY_TITLE));
        personViewHolder.personAge.setText(item.get(Utils.KEY_DURATION));
        personViewHolder.mTxtPublishedAt.setText(item.get(Utils.KEY_PUBLISHEDAT));
        // Set image to imageview

        IMAGE_LOADER.get(item.get((Utils.KEY_URL_THUMBNAILS)),ImageLoader.getImageListener((personViewHolder.personPhoto),
                        R.drawable.empty_photo, R.drawable.empty_photo));
    }

    @Override
    public int getItemCount() {
        return DATA.size();
    }
}
