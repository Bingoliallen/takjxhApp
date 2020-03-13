package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 14:42
 * @Description:
 **/
public class BannnersBean<T> extends BaseComResponse {

    public T banners;
/*    private List<BannersBean> banners;*/



    public static class BannersBean {
        /**
         * id : e11c82d6f70711e9b39fb2e6695c91c3
         * title : 人民日报钟声：中美良性互动多多益善
         * cover : /images/banner.jpg
         * type : 01
         * url : null
         */

        private String id;
        private String title;
        private String cover;
        private String type;
        private String url;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
