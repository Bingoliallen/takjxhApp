package takjxh.android.com.taapp.activityui.bean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 13:37
 * @Description:
 **/
public class AnswerAddBean {
    public AnswerAddBean(String questionId, String itemId, String inputContent) {
        this.questionId = questionId;
        this.itemId = itemId;
        this.inputContent = inputContent;
    }

    /**
     * questionId : f2681d255692103896b87a6795b7d399
     * itemId : f2681d255692103896b87a6795b7900
     * inputContent : 测试输入框填写
     */

    private String questionId;
    private String itemId;
    private String inputContent;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }
}
