package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-07 8:45
 * @Description:
 **/
public class ScoreRuleBean<T> extends BaseComResponse {

    /**
     * content : 会计理论考试不可在累积积分
     */

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
