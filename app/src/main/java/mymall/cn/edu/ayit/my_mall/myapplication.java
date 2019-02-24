package mymall.cn.edu.ayit.my_mall;


import android.app.Application;

/**
 * Created by Administrator on 2017/12/31 0031.
 */

public class myapplication extends Application {
    private static String sessionid;
    public static String ip="http://192.168.0.120:8080/mymall/";

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionid()
    {
        return this.sessionid;
    }

}
