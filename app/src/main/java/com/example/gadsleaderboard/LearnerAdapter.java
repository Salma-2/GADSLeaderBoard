package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Learner> mLearners;
    private int mImageUrl;


    public LearnerAdapter(Context context, List<Learner> learners, int imageUrl) {
        mContext = context;
        mLearners= learners;
        mImageUrl = imageUrl;
        mLayoutInflater = LayoutInflater.from( mContext );
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate( R.layout.learner_card, parent, false );
        return new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learner learner = mLearners.get( position );
        holder.mNameText.setText( learner.getName() );
        holder.mInfoText.setText( learner.getInfo() );
        holder.mBadgeImage.setImageResource( mImageUrl );
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameText;
        private final TextView mInfoText;
        private final ImageView mBadgeImage;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mNameText = (TextView) itemView.findViewById( R.id.learner_name_txt );
            mInfoText = (TextView) itemView.findViewById( R.id.learner_info_txt );
            mBadgeImage = (ImageView) itemView.findViewById( R.id.badge_image );
        }
    }



}
