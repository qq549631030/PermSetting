package cn.hx.permissionsetting.manufacturer;

import android.content.Intent;

import java.util.List;

/**
 * @author huangx
 * @date 2018/3/12
 */

public interface PermissionPage {
    /**
     * 可能的权限设置页面，按优先级从高到低排序
     *
     * @return
     */
    List<Intent> possibleIntents();
}
