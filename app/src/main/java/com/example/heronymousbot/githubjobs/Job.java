package com.example.heronymousbot.githubjobs;

import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("title")
    private String jobTitle;
    @SerializedName("type")
    private String jobType;
    @SerializedName("location")
    private String jobLocation;
    @SerializedName("description")
    private String jobDescription;
    @SerializedName("company")
    private String company;
    @SerializedName("company_logo")
    private String companyLogo;

    public Job(String jobTitle, String jobType, String jobLocation, String jobDescription, String company, String companyLogo){
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.company = company;
        this.companyLogo = companyLogo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getCompany() {
        return company;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
