package com.steven.schooldelivery.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 22340 on 2017/4/5.
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        // activityList.stream().filter(activity -> !activity.isFinishing()).forEach(Activity::finish);
    }
}
