package cn.hx.permissionsetting.manufacturer;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangx
 * @date 2018/3/14
 */

public class ZTE implements PermissionPage {
    private final Context context;

    public ZTE(Context context) {
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
        Intent intent = new Intent();
        intent.setAction("com.zte.heartyservice.intent.action.startActivity.PERMISSION_SCANNER");
        intents.add(intent);
        return intents;
    }
}
