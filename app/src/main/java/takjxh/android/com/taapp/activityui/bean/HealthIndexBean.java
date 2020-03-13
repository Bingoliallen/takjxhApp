package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * Created by Administrator on 2020/3/9.
 */

public class HealthIndexBean<T> extends BaseComResponse {
    /*"isFsks": 0,  //是否发烧咳嗽,0-没有，1-有
            "isJcbl": 0,  //是否接触过确诊或疑似病例,0-没有，1-有
            "temperature": 36.9 //今天体温[20200311]*/
    public int isFsks;
    public int isJcbl;
    public int temperature;

    public String deptId; //机构ID
    public String deptName; //机构名称
    public String name;//当前用户姓名
    public String phone;//当前用户手机号
    public boolean isSubmit;//今日是否有提交（true-提交过，false-未提交），如果有的话，进入页面后的提交按钮要改成“已提交，重新提交”

}
