package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-21 13:41
 * @Description:
 **/
public class ExamIndexBean<T> extends BaseComResponse {


    /**
     * score : 3
     * examNum : 0
     * types : [{"code":"01","value":"日阶段考试","des":"太阳每天都是新的"}]
     */

    private int score;
    private int examNum;
    private List<TypesBean> types;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getExamNum() {
        return examNum;
    }

    public void setExamNum(int examNum) {
        this.examNum = examNum;
    }

    public List<TypesBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypesBean> types) {
        this.types = types;
    }

    public static class TypesBean {
        /**
         * code : 01
         * value : 日阶段考试
         * des : 太阳每天都是新的
         */

        private String code;
        private String value;
        private String des;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
