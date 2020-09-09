package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.Learner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Learner> mLearners;



    public SkillsAdapter(Context context, List<Learner> learners) {
        mContext = context;
        mLearners = learners;

        mLayoutInflater = LayoutInflater.from( mContext );
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate( R.layout.skills_card, parent, false );
        return new ViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learner learner = mLearners.get( position );
        String msg;
        String info;
        String name = learner.getName();
        String country = learner.getCountry();
        String badgeUrl = learner.getBadgeUrl();
        int score = learner.getScore();
        msg = " Skill IQ score, ";
        info = score+ msg+ country;
        holder.mNameText.setText( name );
        holder.mInfoText.setText( info );

    }

    @Override
    public int getItemCount() {
        return mLearners == null ? 0 : mLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mNameText;
        private final TextView mInfoText;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            mNameText = (TextView) itemView.findViewById( R.id.learner_name_txt );
            mInfoText = (TextView) itemView.findViewById( R.id.learner_info_txt );

        }

    }




}
