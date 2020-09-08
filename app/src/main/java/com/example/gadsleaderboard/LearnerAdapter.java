package com.example.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.models.Learner;
import com.example.gadsleaderboard.ui.main.LearningFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Learner> mLearners;
   private int mCode;


    public LearnerAdapter(Context context, List<Learner> learners, int code) {
        mContext = context;
        mLearners = learners;
        mCode= code;
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
        String msg;
        String info;

        String name = learner.getName();
        String country = learner.getCountry();

        if(mCode == LearningFragment.LEARN_CODE){
            int hours= learner.getHours();
            msg = " Learning hours, ";
            info = hours+ msg+ country;
        }
        else{
            int score = learner.getScore();
            msg = " Skill IQ score, ";
            info = score+ msg+ country;
        }

        String badgeUrl = learner.getBadgeUrl();

        holder.mNameText.setText( name );
        holder.mInfoText.setText( info );
        holder.showImage( badgeUrl );
    }

    @Override
    public int getItemCount() {
        return mLearners == null ? 0 : mLearners.size();
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
        public void showImage(String url){
            if(url != null && !url.isEmpty() ){
                Picasso.get().load( url ).resize( 280,280 ).centerCrop().into( mBadgeImage );

            }
        }
    }




}
