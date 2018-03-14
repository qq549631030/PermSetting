package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.hx.permissionsetting.DeviceUtils;
import cn.hx.permissionsetting.Version;

/**
 * @author huangx
 * @date 2018/3/12
 */

public class VIVO implements PermissionPage {

    private final Context context;

    public VIVO(Context context) {
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
        String funtouchOsVersion = DeviceUtils.getSystemProperty("ro.vivo.os.version");
        Version version = new Version(funtouchOsVersion);
        if (version.isLowerThan("3.0")) {//FuntouchOs 3.0以下
            Intent intent = DeviceUtils.getIntentForActivity(context, "com.iqoo.secure", "com.iqoo.secure.safeguard.SoftPermissionDetailActivity");
            if (intent != null) {
                intent.putExtra("packagename", context.getPackageName());
                intents.add(intent);
            }
        } else {//FuntouchOs 3.0及以上
            Intent intent = DeviceUtils.getIntentForActivity(context, "com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
            if (intent != null) {
                intent.putExtra("packagename", context.getPackageName());
                intents.add(intent);
            }
        }
        return intents;
    }
}
