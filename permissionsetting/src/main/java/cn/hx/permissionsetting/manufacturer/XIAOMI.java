package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.hx.permissionsetting.DeviceUtils;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class XIAOMI implements PermissionPage {
    private final Context context;

    public XIAOMI(Context context) {
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
        String miuiVersion = DeviceUtils.getSystemProperty("ro.miui.ui.version.name");
        if (miuiVersion.contains("5") || miuiVersion.contains("6") || miuiVersion.contains("7")) {//MIUI 8 以下(5以下太老，不做考虑)
            Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            intent.putExtra("extra_pkgname", context.getPackageName());
            intents.add(intent);
        } else {//MIUI 8及以上
            //先看具体应用权限管理页面
            //MIUI 8.0.1.0 以上
            Intent intent = DeviceUtils.getIntentForActivity(context, "com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            if (intent != null) {
                intent.putExtra("extra_pkgname", context.getPackageName());
                intents.add(intent);
            }
            //MIUI 8.1.3.0 (具体应用权限管理页面无效向前找所有应用权限管理页)
            intent = DeviceUtils.getIntentForActivity(context, "com.miui.securitycenter", "com.miui.permcenter.permissions.RealAppPermissionsEditorActivity");
            if (intent != null) {
                intent = DeviceUtils.getIntentForActivity(context, "com.miui.securitycenter", "com.miui.permcenter.MainAcitivty");
                intent.putExtra("extra_pkgname", context.getPackageName());
                intents.add(intent);
            }
            //MIUI 8.0.1.0
            intent = DeviceUtils.getIntentForActivity(context, "com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            if (intent != null) {
                intent.putExtra("extra_pkgname", context.getPackageName());
                intents.add(intent);
            }
        }
        return intents;
    }
}
