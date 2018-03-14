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

public class MEIZU implements PermissionPage {
    private final Context context;

    public MEIZU(Context context) {
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
        Intent intent = DeviceUtils.getIntentForActivity(context, "com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        if (intent != null) {
            intent.putExtra("packageName", context.getPackageName());
            intents.add(intent);
        }
        return intents;
    }
}
