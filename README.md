# 国产手机权限设置页面
由于国内产商都在原生Android系统上做了定制化，所以每个厂商的手机权限管理都不同，本项目不是关于权限如何判断的，而是关于没有权限后时引导用户去打开权限，具体应该跳转到哪里的。关于权限判断在另外一个项目里


### 使用方法  
1. 添加依赖：
```groovy
compile 'cn.hx.permissionsetting:permissionsetting:0.0.1'
```

2. 使用：

```java
int launchMode = PermSetting.gotoPermSetting(MainActivity.this, REQUEST_CODE);
```

返回值 launchMode用来判断用户何时设置完返回来，接着可以做其他处理（如再次判断权限有没有设置好）  
具体用法参考[MainActivity](app/src/main/java/cn/hx/permsetting/MainActivity.java)
