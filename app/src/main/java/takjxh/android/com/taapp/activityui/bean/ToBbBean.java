package takjxh.android.com.taapp.activityui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:12
 * @Description:
 **/
public class ToBbBean {

    public ToBbBean(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String code;
    public String value;
    public List<TaskListBean.ReportTasksBean> reportTasks=new ArrayList<>();

}
