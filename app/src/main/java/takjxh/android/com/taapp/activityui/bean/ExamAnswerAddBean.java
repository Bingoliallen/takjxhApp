package takjxh.android.com.taapp.activityui.bean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-22 9:08
 * @Description:
 **/
public class ExamAnswerAddBean {
    public ExamAnswerAddBean(String questionId, String itemId, String inputContent) {
        this.questionId = questionId;
        this.itemId = itemId;
        this.inputContent = inputContent;
    }

    /**
     * questionId : 53f296be5d8e103896b87a6795b7d300
     * itemId : 540c22635d8e103896b87a6795b7d300
     * inputContent :
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
