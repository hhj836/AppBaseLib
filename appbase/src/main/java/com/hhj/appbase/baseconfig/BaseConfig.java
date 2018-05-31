package com.hhj.appbase.baseconfig;

/**
 * Created by hhj on 2018/3/22.
 */

public class BaseConfig  implements Cloneable{
   public  static   BaseConfig  DEFAULT=new BaseConfig();
    public static void setDefaultListConfig(BaseConfig config){
        DEFAULT = config;
    }
    /**
     * title 颜色
     */
    public int colorPrimary;
    public int titleBarLeftResId;
    public int titleBarRightResId;
    /**
     * 背景色
     */
    public int colorContent;

    @Override
    public BaseConfig clone(){
        try {
            return (BaseConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new BaseConfig();
    }
}
