package com.example.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gadsleaderboard.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( this, getSupportFragmentManager() );
        ViewPager viewPager = findViewById( R.id.view_pager );
        viewPager.setAdapter( sectionsPagerAdapter );
        TabLayout tabs = findViewById( R.id.tabs );
        tabs.setupWithViewPager( viewPager );

        mSubmitBtn = (Button) findViewById( R.id.submit_btn );
        mSubmitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getBaseContext(), SubmitActivity.class ) );
            }
        } );


    }
}