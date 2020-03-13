package takjxh.android.com.taapp.activityui.bean;

import java.util.List;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-17 11:21
 * @Description:
 **/
public class KsnrBean<T> extends BaseComResponse {

    public List<ExamQuestionsBean> examQuestions;

    public String examId;

    public static class ExamQuestionsBean {
        /**
         * id : 36792f9b5d86103896b87a6795b7d300
         * title : 风雨送春归，飞雪迎春到。（
         * ），犹有花枝俏。
         * type : 01
         * showType : 输入框
         * img : null
         * video : null
         * knowledgePoint : 卜算子·咏梅，作者：毛泽东
         * 风雨送春归，飞雪迎春到。
         * 已是悬崖百丈冰，犹有花枝俏。
         * 俏也不争春，只把春来报。
         * 待到山花烂漫时，她在丛中笑。
         * items : [{"id":"36b170c15d86103896b87a6795b7d300","des":"瀚海阑干百丈冰","rightAnser":null,"isRight":false},{"id":"36a23f905d86103896b87a6795b7d300","des":"已是黄昏独自愁","rightAnser":null,"isRight":false}]
         */

        private String id;
        private String title;
        private String type;
        private String showType;
        private String img;
        private String video;
        private String knowledgePoint;
        private List<ItemsBean> items;
        public String inputContent = "";

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getKnowledgePoint() {
            return knowledgePoint;
        }

        public void setKnowledgePoint(String knowledgePoint) {
            this.knowledgePoint = knowledgePoint;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 36b170c15d86103896b87a6795b7d300
             * des : 瀚海阑干百丈冰
             * rightAnser : null
             * isRight : false
             */

            private String id;
            private String des;
            private String rightAnser;
            private boolean isRight;

            public boolean isCheck = false;

            public String inputContent = "";


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getRightAnser() {
                return rightAnser;
            }

            public void setRightAnser(String rightAnser) {
                this.rightAnser = rightAnser;
            }

            public boolean isIsRight() {
                return isRight;
            }

            public void setIsRight(boolean isRight) {
                this.isRight = isRight;
            }
        }
    }
}
