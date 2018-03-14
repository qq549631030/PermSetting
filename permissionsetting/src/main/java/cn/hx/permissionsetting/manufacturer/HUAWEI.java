package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.hx.permissionsetting.DeviceUtils;
import cn.hx.permissionsetting.Version;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class HUAWEI implements PermissionPage {
    private final Context context;

    public HUAWEI(Context context) {
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
        String emuiVersion = DeviceUtils.getSystemProperty("ro.build.version.emui");
        Version version = new Version(emuiVersion);
        if (version.isLowerThan("4.0")) {//4.0以下到权限管理页面
            Intent intent = DeviceUtils.getIntentForActivity(context, "com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            if (intent != null) {
                intent.putExtra("package", context.getPackageName());
                intents.add(intent);
            }
        } else {
            //4.x就到默认应用详情页面，到自带的权限页面没有存储权限设置 坑
            //5.0以上就到默认应用详情页面
        }
        return intents;
    }
}
