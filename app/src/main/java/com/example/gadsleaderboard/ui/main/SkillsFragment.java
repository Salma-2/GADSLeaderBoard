package com.example.gadsleaderboard.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.models.Learner;
import com.example.gadsleaderboard.LearnerAdapter;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.services.LearningService;
import com.example.gadsleaderboard.services.ServiceBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsFragment extends Fragment {

    private LinearLayoutManager mLinearLayoutManager;
    private LearnerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    public static final int SKILL_CODE = 123;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final Context context = getContext();
        View root = inflater.inflate( R.layout.learning_fragment, container, false );


        mRecyclerView = root.findViewById( R.id.item_list );
        mLinearLayoutManager = new LinearLayoutManager( getContext() );
        mRecyclerView.setLayoutManager( mLinearLayoutManager );

        LearningService learningService = ServiceBuilder.buildService( LearningService.class );
        Call<List<Learner>> request = learningService.getSkill();

        request.enqueue( new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                if(response.isSuccessful()){
                    mAdapter = new LearnerAdapter( getContext(), response.body(),SKILL_CODE );
                    mRecyclerView.setAdapter( mAdapter );
                }
                else if (response.code() == 401) {
                    Toast.makeText( context, "Your session has expired",
                            Toast.LENGTH_LONG ).show();
                } else {
                    Toast.makeText( context, "Failed to retrieve skills.",
                            Toast.LENGTH_LONG ).show();
                }

            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {
                //connection error
                if (t instanceof IOException) {
                    Toast.makeText( context, "A connection error occured",
                            Toast.LENGTH_LONG ).show();

                } else {
                    Toast.makeText( context, "Failed to retrieve skills.",
                            Toast.LENGTH_LONG ).show();
                }

            }
        } );




        return root;
    }

}
