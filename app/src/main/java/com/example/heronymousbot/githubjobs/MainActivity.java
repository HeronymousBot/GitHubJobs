package com.example.heronymousbot.githubjobs;

import android.content.Intent;
import android.graphics.Movie;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mQueryLocation;
    private EditText mQueryDescription;
    private Button mSearchButton;
    private RecyclerView mRecyclerView;
    private JobAdapter mAdapter;
    private ProgressBar mLoadingIndicator;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueryDescription = findViewById(R.id.job_description_ed);
        mQueryLocation = findViewById(R.id.job_location_ed);
        mSearchButton = findViewById(R.id.my_search_button);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mRecyclerView = findViewById(R.id.rv_job_list);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                String description = mQueryDescription.getText().toString();
                String location = mQueryLocation.getText().toString();
                mLoadingIndicator.setVisibility(View.VISIBLE);
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<Job>> call = service.getAllJobs(description, location);
                call.enqueue(new Callback<List<Job>>() {

                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        mLoadingIndicator.setVisibility(View.GONE);
                        generateDataList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        mLoadingIndicator.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void generateDataList(List<Job> jobsList) {
        mRecyclerView = findViewById(R.id.rv_job_list);
        mAdapter = new JobAdapter(this, jobsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
