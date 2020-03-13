package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-18 16:29
 * @Description:
 **/
public class QafaqListBean<T> extends BaseComResponse {

    public int faqSum;
    public List<FaqsBean> faqs;


    public static class FaqsBean {
        /**
         * id : b48c3f1b4aaf103882a7b81541065d33
         * title : 为什么我已经收到产品，但是订单仍显示正在配货
         * answer : 由于不同快递公司物流信息更新时间略有不同，当聚美订单发货后，部分快递信息可能会延迟返回给聚美。
         * cover : http://106.53.78.24:8080/Takjxh/images/img3515969469ee8.jpg
         */

        private String id;
        private String title;
        private String answer;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
