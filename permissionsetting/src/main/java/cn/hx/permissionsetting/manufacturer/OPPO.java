package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import cn.hx.permissionsetting.DeviceUtils;
import cn.hx.permissionsetting.Version;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class OPPO implements PermissionPage {
    private final Context context;

    public OPPO(Context context) {
        this.context = context;
    }

    /**
     * 可能的权限设置页面，按优先级从高到低排序
     *
     * @return
     */
    @Override
    public List<Intent> possibleIntents() {
        List<Intent> intents = new ArrayList<>();
        String colorOsVersion = DeviceUtils.getSystemProperty("ro.build.version.opporom");
        Version version = new Version(colorOsVersion);
        if (version.isLowerThan("3.0")) {//ColorOS3.0以下
            //ColorOS 2.1
            Intent intent = DeviceUtils.getIntentForActivity(context, "com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
            if (intent != null) {
                intent.putExtra("package", context.getPackageName());
                intents.add(intent);
            }
            //ColorOS 2.0
            intent = DeviceUtils.getIntentForActivity(context, "com.oppo.safe", "com.oppo.safe.permission.PermissionSettingsActivity");
            if (intent != null) {
                intent.putExtra("package", context.getPackageName());
                intents.add(intent);
            }
        } else {//ColorOS3.0及以上
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                //优先看具体应用权限管理
                Intent intent = DeviceUtils.getIntentForActivity(context, "com.coloros.safecenter", "com.coloros.safecenter.permission.singlepage.PermissionSinglePageActivity");
                if (intent != null) {
                    intent.putExtra("package", context.getPackageName());
                    intents.add(intent);
                }
                //如果不能进入具体应用权限管理则到上一级，所有应用权限管理
                intent = DeviceUtils.getIntentForActivity(context, "com.coloros.safecenter", "com.coloros.safecenter.permission.PermissionManagerActivity");
                if (intent != null) {
                    intent.putExtra("package", context.getPackageName());
                    intents.add(intent);
                }
            } else {
                // Android6.0 以上进入应用详情页面的权限管理更合适
            }
        }
        return intents;
    }
}
