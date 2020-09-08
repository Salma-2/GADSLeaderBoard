package com.example.gadsleaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gadsleaderboard.services.LearningService;
import com.example.gadsleaderboard.services.ServiceBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmitActivity extends AppCompatActivity {

    private Button mSubmitBtn;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mProjectLink;
    private LinearLayout mConfirmLayout;
    private Button mConfirmBtn;
    private ImageButton mCloseBtn;
    private ImageView mBackBtn;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = getBaseContext();
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_submit );

        //views
        mFirstName = (EditText) findViewById( R.id.firstname_txt );
        mLastName = (EditText) findViewById( R.id.lastname_txt );
        mEmail = (EditText) findViewById( R.id.email_txt );
        mProjectLink = (EditText) findViewById( R.id.github_link_txt );

        mSubmitBtn = (Button) findViewById( R.id.submit_btn );
        mBackBtn = (ImageView) findViewById( R.id.back_btn );

        View root= createDialog();
        mConfirmLayout = (LinearLayout) findViewById( R.id.confirm_layout );

        mConfirmBtn = (Button) root.findViewById( R.id.confirm_btn );
        mCloseBtn =(ImageButton) root.findViewById( R.id.close_btn );


        mBackBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( context, MainActivity.class ) );
            }
        } );


        mSubmitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAlertDialog.show();
            }
        } );

        //exit without submit
       mCloseBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        } );



        mConfirmBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                LearningService learningService = ServiceBuilder.buildPostService( LearningService.class );
                Call<Void> request = learningService.pushCode(
                        mFirstName.getText().toString(),
                        mLastName.getText().toString(),
                        mEmail.getText().toString(),
                        mProjectLink.getText().toString() );

                request.enqueue( new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText( context, "Submission Successful",
                                    Toast.LENGTH_LONG );
                        } else if (response.code() == 401) {
                            Toast.makeText( context, "Your session has expired",
                                    Toast.LENGTH_LONG ).show();
                        } else {
                            Toast.makeText( context, "Submission is not Successful",
                                    Toast.LENGTH_LONG ).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (t instanceof IOException) {
                            Toast.makeText( context, "A connection error occured",
                                    Toast.LENGTH_LONG ).show();

                        } else {
                            Toast.makeText( context, "Submission is not Successful",
                                    Toast.LENGTH_LONG ).show();
                        }

                    }
                } );
            }
        } );

    }

    private View createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder( SubmitActivity.this );
        ViewGroup viewGroup = findViewById( android.R.id.content );
        View dialogView = LayoutInflater.from( getBaseContext() ).inflate( R.layout.confirm_dialog,
                viewGroup, false );
        builder.setView( dialogView );
        mAlertDialog = builder.create();
        return dialogView;
    }


}