package com.example.kongsun.schoolguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.schoolguide.OnRecyclerViewItemClickListener;
import com.example.kongsun.schoolguide.R;
import com.example.kongsun.schoolguide.accessor.PublicUniversity;
import com.example.kongsun.schoolguide.singleton.MySingleTon;

import java.util.List;

/**
 * Created by kongsun on 9/7/17.
 */

public class PublicUniversityAdapter extends RecyclerView.Adapter<PublicUniversityAdapter.ViewHolder> {
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private List<PublicUniversity> publicUniversities;
    private Context context;
    public PublicUniversityAdapter(List<PublicUniversity> publicUniversities, Context context) {
        this.publicUniversities = publicUniversities;
        this.context = context;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void setUniversities(List<PublicUniversity> publicUniversities) {
        this.publicUniversities = publicUniversities;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_university, parent, false);
        ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PublicUniversity publicUniversity = publicUniversities.get(position);
        holder.kh_name.setText(publicUniversity.getKh_Name());
        holder.en_name.setText(publicUniversity.getEn_Name());
        holder.logo.setImageUrl(publicUniversity.getLogoUrl(), MySingleTon.getInstance(context).getImageLoader());
        holder.photoBackground.setImageUrl(publicUniversity.getPhotoUrl(), MySingleTon.getInstance(context).getImageLoader());

    }

    @Override
    public int getItemCount() {
        return publicUniversities.size();
    }

    public PublicUniversity getPublicUniversity(int position) {
        return publicUniversities.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView kh_name;
        private TextView en_name;
        private NetworkImageView logo;
        private NetworkImageView photoBackground;

        public ViewHolder(View itemView) {
            super(itemView);

            kh_name = (TextView) itemView.findViewById(R.id.txt_khName);
            en_name = (TextView) itemView.findViewById(R.id.txt_enName);
            logo = (NetworkImageView) itemView.findViewById(R.id.img_logo);
            photoBackground = (NetworkImageView) itemView.findViewById(R.id.img_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.OnRecyclerViewItemClickListener(getAdapterPosition());
                }
            });

        }
    }

}
