package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2020/3/9.
 */

public class HealthListBean<T> extends BaseComResponse {
/*
	"": "否", //是否发烧咳嗽
            "": "否", //是否接触过确诊或疑似病例
            "": "36.9℃", //体温
            "": "2020-03-06"  //上报时间*/


    public int page;
    public int pageSize;
    public T healthListVos;

    public static class HealthListVosBean {
        private String isFsks;
        private String isJcbl;
        private String temperature;
        private String time;

        public String getIsFsks() {
            return isFsks;
        }

        public void setIsFsks(String isFsks) {
            this.isFsks = isFsks;
        }

        public String getIsJcbl() {
            return isJcbl;
        }

        public void setIsJcbl(String isJcbl) {
            this.isJcbl = isJcbl;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }


}
