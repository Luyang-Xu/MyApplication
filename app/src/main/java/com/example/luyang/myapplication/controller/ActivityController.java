package com.example.luyang.myapplication.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyang on 2017/9/26.
 */

public class ActivityController {
    public static List<Activity> activities=new ArrayList<Activity>();

    //增加活动
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    //移除活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    //关闭所有活动退出程序
    public  static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
