package com.example.gadsleaderboard.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.DataManager;
import com.example.gadsleaderboard.Learner;
import com.example.gadsleaderboard.LearnerAdapter;
import com.example.gadsleaderboard.QueryUtils;
import com.example.gadsleaderboard.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LearningFragment extends Fragment {
    private static final String USGS_REQUEST_URL= "https://gadsapi.herokuapp.com/api/hours";
    public static final int LEARN_CODE = 42;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private LearnerAdapter mAdapter;
    List<Learner> learners = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(!hasInternet(getContext()))
        {

            Snackbar.make( container,"No Internet Connection",
                    BaseTransientBottomBar.LENGTH_LONG  );

        }
        else {

            LearnerAsyncTask task = new LearnerAsyncTask();
            task.execute(USGS_REQUEST_URL);
        }

        View root = inflater.inflate( R.layout.learning_fragment, container, false );
        mRecyclerView = root.findViewById( R.id.item_list );
        mLinearLayoutManager = new LinearLayoutManager( getContext() );
        mRecyclerView.setLayoutManager( mLinearLayoutManager );
        mAdapter = new LearnerAdapter( getContext(), learners, R.drawable.skill_iq );
        mRecyclerView.setAdapter( mAdapter );
        return root;
    }


    //Determine whether you have an internet connection
    private static boolean hasInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private class LearnerAsyncTask extends AsyncTask<String,Void, ArrayList<Learner>>
    {
        @Override
        protected ArrayList<Learner> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Learner> learners= QueryUtils.fetchEarthquakeData(urls[0], LEARN_CODE );
            return learners;
        }
        @Override
        protected void onPostExecute(final ArrayList<Learner> learners) {
            if (learners == null) {
                return;
            }


            mAdapter = new LearnerAdapter( getContext(), learners, R.drawable.top_learner );
            mRecyclerView.setAdapter( mAdapter );
        }

    }

}
