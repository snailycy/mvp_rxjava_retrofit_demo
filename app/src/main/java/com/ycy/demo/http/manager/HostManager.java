package com.ycy.demo.http.manager;

import com.ycy.demo.config.AppConfig;

/**
 * Created by YCY.
 */
public class HostManager {
    private static boolean isDebugHost = AppConfig.isDebug();
    //正式环境host
    private static String hostRelease = "http://demo.api.ycy.com/demoapi/";

    //测试环境host
    private static String hostDebug = "http://testdemo.api.ycy.com/demoapi/";

    public static String getHost() {
        return isDebugHost ? hostDebug : hostRelease;
    }
}
