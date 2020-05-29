package com.disablity.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.disablity.app.data.JobProfile;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class AppUtil {


    public static final String jobs = "user.jobs";
    public static ArrayList<JobProfile> getjobs(Activity activity)
    {
        ArrayList<JobProfile> list= new ArrayList<>();
        SharedPreferences pref = activity.getSharedPreferences("users", Context.MODE_PRIVATE);
        if (pref.contains(jobs)){
            String listData = pref.getString(jobs, "");
            JobProfile[] jobProfiles = gson.fromJson(listData, JobProfile[].class);
            list.addAll(Arrays.asList(jobProfiles));
        }
        return  list;
    }
    public static   void saveJob(Activity activity,JobProfile profile){
        SharedPreferences pref = activity.getSharedPreferences("users", Context.MODE_PRIVATE);
        ArrayList<JobProfile> getjobs = getjobs(activity);
        getjobs.add(profile);
        pref.edit().putString(jobs, gson.toJson(getjobs)).apply();
    }

    public static final String USER_ID ="user.name";
    public static final String USER_OBJ ="user.obj";
    public static final Gson gson = new Gson();

}
