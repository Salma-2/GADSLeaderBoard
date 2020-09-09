package com.example.gadsleaderboard.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.services.LearningService;
import com.example.gadsleaderboard.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmitActivity extends AppCompatActivity {

    private Button mSubmitBtn;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mProjectLink;
    private Button mConfirmBtn;
    private ImageButton mCloseBtn;
    private ImageView mBackBtn;


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


        mBackBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( context, MainActivity.class ) );
            }
        } );


        mSubmitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notEmpty()){
                    createConfirmDialog( v );
                }
               else
                   Toast.makeText( context,"Field cannot be " +
                        "left blank.",Toast.LENGTH_LONG ).show();

            }
        } );

    }


    private void createFailDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View confirmDialogView =
                LayoutInflater.from(view.getContext()).inflate(R.layout.fail_dialog,
                        viewGroup, false);
        builder.setView(confirmDialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void createSuccessDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View confirmDialogView =
                LayoutInflater.from(view.getContext()).inflate(R.layout.success_dialog,
                        viewGroup, false);
        builder.setView(confirmDialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void createConfirmDialog(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View confirmDialogView =
                LayoutInflater.from(view.getContext()).inflate(R.layout.confirm_dialog,
                        viewGroup, false);
        builder.setView(confirmDialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        mCloseBtn =(ImageButton) confirmDialogView.findViewById( R.id.close_btn );
        //exit without submit
        mCloseBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        } );

        mConfirmBtn = (Button) confirmDialogView.findViewById( R.id.confirm_btn );
        //Confirm submit
        mConfirmBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                alertDialog.dismiss();
                submit( v );
            }
        } );


    }
    private void submit(final View v){
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
                    createSuccessDialog( v );
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                createFailDialog( v );
            }
        } );
    }
    private Boolean notEmpty(){
        return (!TextUtils.isEmpty(mFirstName.getText().toString()) &&
                !TextUtils.isEmpty( mLastName.getText().toString()) &&
                !TextUtils.isEmpty( mEmail.getText().toString()) &&
                !TextUtils.isEmpty( mProjectLink.getText().toString()))? true: false;


    }

}