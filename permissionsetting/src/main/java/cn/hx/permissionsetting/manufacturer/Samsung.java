package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class Samsung implements PermissionPage {
    private final Context context;

    public Samsung(Context context) {
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            intents.add(new Intent(Settings.ACTION_SETTINGS));//到设置找应用程序许可
        }
        return intents;
    }
}
