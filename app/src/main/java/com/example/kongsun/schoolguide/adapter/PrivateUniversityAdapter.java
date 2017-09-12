package com.example.kongsun.schoolguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kongsun.schoolguide.OnRecyclerViewItemClickListener;
import com.example.kongsun.schoolguide.R;
import com.example.kongsun.schoolguide.accessor.PrivateUniversity;
import com.example.kongsun.schoolguide.accessor.PublicUniversity;
import com.example.kongsun.schoolguide.singleton.MySingleTon;

import java.util.List;

/**
 * Created by kongsun on 9/11/17.
 */

public class PrivateUniversityAdapter extends RecyclerView.Adapter<PrivateUniversityAdapter.ViewHolder> {
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private List<PrivateUniversity> privateUniversities;
    private Context context;
    public PrivateUniversityAdapter(List<PrivateUniversity> privateUniversities, Context context) {
        this.privateUniversities = privateUniversities;
        this.context = context;
    }

    public void setPrivateUniversity(List<PrivateUniversity> privateUniversity) {
        this.privateUniversities = privateUniversity;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_university, parent, false);
        ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PrivateUniversity privateUniversity = privateUniversities.get(position);
        holder.kh_name.setText(privateUniversity.getKh_Name());
        holder.en_name.setText(privateUniversity.getEn_Name());
        holder.logo.setImageUrl(privateUniversity.getLogoUrl(), MySingleTon.getInstance(context).getImageLoader());
        holder.photoBackground.setImageUrl(privateUniversity.getPhotoUrl(), MySingleTon.getInstance(context).getImageLoader());

    }

    @Override
    public int getItemCount() {
        return privateUniversities.size();
    }

    public PrivateUniversity getPrivateUniversity(int position) {
        return privateUniversities.get(position);
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
                    Log.d("schoolguide", "on view holder click");
                    onRecyclerViewItemClickListener.OnRecyclerViewItemClickListener(getAdapterPosition());
                }
            });

        }
    }
}
