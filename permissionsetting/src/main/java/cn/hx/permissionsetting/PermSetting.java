package cn.hx.permissionsetting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.hx.permissionsetting.manufacturer.HUAWEI;
import cn.hx.permissionsetting.manufacturer.MEIZU;
import cn.hx.permissionsetting.manufacturer.OPPO;
import cn.hx.permissionsetting.manufacturer.PermissionPage;
import cn.hx.permissionsetting.manufacturer.Samsung;
import cn.hx.permissionsetting.manufacturer.VIVO;
import cn.hx.permissionsetting.manufacturer.XIAOMI;
import cn.hx.permissionsetting.manufacturer.ZTE;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class PermSetting {

    public static final String MANUFACTURER_HUAWEI = "HUAWEI";
    public static final String MANUFACTURER_XIAOMI = "XIAOMI";
    public static final String MANUFACTURER_OPPO = "OPPO";
    public static final String MANUFACTURER_VIVO = "vivo";
    public static final String MANUFACTURER_MEIZU = "meizu";
    public static final String MANUFACTURER_SMARTISAN = "smartisan";
    public static final String MANUFACTURER_SAMSUNG = "samsung";
    public static final String MANUFACTURER_ZTE = "ZTE";
    public static final String manufacturer = Build.MANUFACTURER;

    /**
     * @param context
     * @param requestCode
     * @return 权限设置页面的启动模式
     */
    public static int gotoPermSetting(Context context, int requestCode) {
        List<Intent> intents = getIntents(context);
        for (Intent intent : intents) {
            try {
                int launchMode = intent.getIntExtra(DeviceUtils.LAUNCH_MODE, -1);
                context.startActivity(intent);
                return launchMode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * @param activity
     * @param requestCode
     * @return 权限设置页面的启动模式
     */
    public static int gotoPermSetting(Activity activity, int requestCode) {
        List<Intent> intents = getIntents(activity);
        for (Intent intent : intents) {
            try {
                int launchMode = intent.getIntExtra(DeviceUtils.LAUNCH_MODE, -1);
                activity.startActivityForResult(intent, requestCode);
                return launchMode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * @param fragment
     * @param requestCode
     * @return 权限设置页面的启动模式
     */
    public static int gotoPermSetting(Fragment fragment, int requestCode) {
        List<Intent> intents = getIntents(fragment.getContext());
        for (Intent intent : intents) {
            try {
                int launchMode = intent.getIntExtra(DeviceUtils.LAUNCH_MODE, -1);
                fragment.startActivityForResult(intent, requestCode);
                return launchMode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * @param fragment
     * @param requestCode
     * @return 权限设置页面的启动模式
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static int gotoPermSetting(android.app.Fragment fragment, int requestCode) {
        List<Intent> intents = getIntents(fragment.getContext());
        for (Intent intent : intents) {
            try {
                int launchMode = intent.getIntExtra(DeviceUtils.LAUNCH_MODE, -1);
                fragment.startActivityForResult(intent, requestCode);
                return launchMode;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static List<Intent> getIntents(Context context) {
        List<Intent> intents = new ArrayList<>();
        PermissionPage permissionsPage = null;
        if (MANUFACTURER_HUAWEI.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new HUAWEI(context);
        } else if (MANUFACTURER_OPPO.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new OPPO(context);
        } else if (MANUFACTURER_VIVO.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new VIVO(context);
        } else if (MANUFACTURER_XIAOMI.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new XIAOMI(context);
        } else if (MANUFACTURER_MEIZU.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new MEIZU(context);
        } else if (MANUFACTURER_SAMSUNG.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new Samsung(context);
        } else if (MANUFACTURER_ZTE.equalsIgnoreCase(manufacturer)) {
            permissionsPage = new ZTE(context);
        }
        if (permissionsPage != null) {
            List<Intent> possibleIntents = permissionsPage.possibleIntents();
            if (possibleIntents != null && !possibleIntents.isEmpty()) {
                intents.addAll(possibleIntents);
            }
        }
        intents.add(getDefaultPermSetting(context));
        return intents;
    }


    public static Intent getDefaultPermSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        return intent;
    }
}
