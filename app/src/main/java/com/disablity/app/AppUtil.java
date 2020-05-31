package com.disablity.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.disablity.app.data.JobApplication;
import com.disablity.app.data.JobProfile;
import com.disablity.app.data.User;
import com.disablity.app.ui.RegisterActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppUtil
{



    public static ArrayList<JobProfile> getJobs(Context activity, String recuiter)
    {
        ArrayList<JobProfile> jobs = getJobs(activity);
        for (int i = 0; i < jobs.size(); i++) {
            JobProfile profile = jobs.get(i);
            if (!profile.getRecuruiter_id().equals(recuiter))
                jobs.remove((int) i);
        }
        return jobs;
    }


    public static ArrayList<JobProfile> getJobs(Context activity)
    {
        ArrayList<JobProfile> list = new ArrayList<>();
        SharedPreferences pref = getPrefences(activity);
        if (pref.contains(jobs)) {
            String listData = pref.getString(jobs, "");
            JobProfile[] jobProfiles = gson.fromJson(listData, JobProfile[].class);
            list.addAll(Arrays.asList(jobProfiles));
        }
        return list;
    }

    public static void saveJob(Context activity, JobProfile profile)
    {
        SharedPreferences pref = getPrefences(activity);
        ArrayList<JobProfile> getjobs = getJobs(activity);
        for (int i = 0; i < getjobs.size(); i++) {
            JobProfile p = getjobs.get(i);
            if (p.getId().equals(profile.getId())) {
                getjobs.remove((int) i);
                break;
            }
        }
        getjobs.add(profile);
        pref.edit().putString(jobs, gson.toJson(getjobs)).apply();
    }



    public static void addToList(Context activity,User user){
        List<User> users = getUsers(activity);
        users.add(user);
        SharedPreferences pref = getPrefences(activity);
        pref.edit().putString(USER_LIST,gson.toJson(users)).apply();
    }

    public static ArrayList<User> getUsers(Context activity)
    {
        SharedPreferences pref = getPrefences(activity);
        ArrayList<User> userList = new ArrayList<>();
        if (pref.contains(USER_LIST)) {
            String listData = pref.getString(USER_LIST, "");
            User[] users = gson.fromJson(listData, User[].class);
            userList.addAll(Arrays.asList(users));
        }
        return userList;
    }

    private static SharedPreferences getPrefences(Context activity)
    {
        return activity.getSharedPreferences("users", Context.MODE_PRIVATE);
    }

    public  static ArrayList<JobApplication> getApplications(Context activity){
        SharedPreferences pref = getPrefences(activity);
        ArrayList<JobApplication>  jobApplicationArrayList= new ArrayList<>();
        if (pref.contains(USER_APPLICATIONS)){
            String listData = pref.getString(USER_APPLICATIONS, "");
            JobApplication[] arra=gson.fromJson(listData,JobApplication[].class);
            jobApplicationArrayList.addAll(Arrays.asList(arra));
        }
        return  jobApplicationArrayList;
    }

    public static void addApplication(Context activity,JobApplication application){
        SharedPreferences pref = getPrefences(activity);
        ArrayList<JobApplication> applications = getApplications(activity);
        for (int i = 0; i < applications.size(); i++) {
            JobApplication a=applications.get(i);
            if (a.getId()==application.getId())
                applications.remove(a);
        }
        applications.add(application);
        pref.edit().putString(USER_APPLICATIONS,gson.toJson(applications)).apply();
    }

    public static final String jobs = "user.jobs";
    public static final String USER_ID = "user.name";
    public static final String USER_OBJ = "user.obj";
    public static final  String USER_LIST = "user.list";
    public static final String USER_APPLICATIONS="user.application";

    //    public static final String  R
    public static final Gson gson = new Gson();

}
