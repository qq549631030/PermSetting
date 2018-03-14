package cn.hx.permissionsetting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author huangx
 * @date 2018/3/12
 */

public class DeviceUtils {

    public static final String LAUNCH_MODE = "launchMode";

    /**
     * 获取系统属性值
     *
     * @param propertyName
     * @return
     */
    public static String getSystemProperty(String propertyName) {
        String line = "";
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propertyName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    /**
     * 获取指定Activity信息
     *
     * @param context
     * @param packageName
     * @param activityName
     * @return
     */
    public static ActivityInfo getActivityInfo(Context context, String packageName, String activityName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_ACTIVITIES);
            for (ActivityInfo activityInfo : pi.activities) {
                if (activityInfo.name.equals(activityName)) {
                    return activityInfo;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 获取跳转到目标Activity的Intent
     *
     * @param context
     * @param packageName
     * @param activityName
     * @return
     */
    public static Intent getIntentForActivity(Context context, String packageName, String activityName) {
        ActivityInfo activityInfo = getActivityInfo(context, packageName, activityName);
        if (activityInfo != null) {
            Intent intent = new Intent();
            intent.putExtra(LAUNCH_MODE, activityInfo.launchMode);
            ComponentName comp = new ComponentName(packageName, activityName);
            intent.setComponent(comp);
            return intent;
        }
        return null;
    }
}
