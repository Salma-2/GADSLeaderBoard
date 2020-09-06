package com.example.gadsleaderboard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.DataManager;
import com.example.gadsleaderboard.Learner;
import com.example.gadsleaderboard.LearnerAdapter;
import com.example.gadsleaderboard.R;

import java.util.List;

public class LearningFragment extends Fragment {
    List<Learner> learners = DataManager.getInstance().initializeLearner();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.learning_fragment, container, false );
        RecyclerView recyclerView = root.findViewById( R.id.item_list );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        LearnerAdapter adapter = new LearnerAdapter( getContext(), learners );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( adapter );
        return root;
    }
}
