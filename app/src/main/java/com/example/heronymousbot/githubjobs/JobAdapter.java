package com.example.heronymousbot.githubjobs;


import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<Job> jobsList;
    private Context context;
    public JobAdapter(Context context, List<Job> jobsList){
        this.context = context;
        this.jobsList = jobsList;
    }

    public JobAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder( JobViewHolder holder, int position) {
        Job currentJob = jobsList.get(position);
        holder.jobTitle.setText(currentJob.getJobTitle());
        holder.jobType.setText(currentJob.getJobType());
        holder.jobLocation.setText(" || " + currentJob.getJobLocation());

        if(currentJob.getCompanyLogo() == null){
           Picasso.get().load("https://i.redd.it/erdjqzlmlv111.png").fit().into(holder.companyLogo);
        }
        else{
            Picasso.get().load(currentJob.getCompanyLogo()).fit().into(holder.companyLogo);
        }


    }

    @NonNull
    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder{
        public ImageView companyLogo;
        public TextView jobTitle;
        public TextView jobType;
        public TextView jobLocation;

        public JobViewHolder(View itemView){
            super(itemView);
            companyLogo = itemView.findViewById(R.id.company_logo);
            jobTitle = itemView.findViewById(R.id.job_title);
            jobLocation = itemView.findViewById(R.id.job_location);
            jobType = itemView.findViewById(R.id.job_type);
        }
    }
}
