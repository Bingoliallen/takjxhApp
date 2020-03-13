package takjxh.android.com.taapp.activityui.bean;

import android.text.TextUtils;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 8:55
 * @Description:
 **/
public class PolicyApplyHelpDetailBean<T> extends BaseComResponse {


    /**
     * help : {"id":"3e2e2f48569e103896b87a6795b7d300","cover":"http://106.53.78.24:8080/Takjxh/images/LOGO.jpg","linkman":"陈海彬","linkmanPhne":"15080342451","desc":"民营企业税后利润转增资、再投资奖励","companyName":"厦门睿天科技有限公司","lng":"118.122505","lat":"24.478849","time":"2019年11月12日 21:12","tip":"求助者已经等待了17.06小时，请您尽快予以帮助!"}
     */

    public T help;


    public static class HelpBean {
        /**
         * id : 3e2e2f48569e103896b87a6795b7d300
         * cover : http://106.53.78.24:8080/Takjxh/images/LOGO.jpg
         * linkman : 陈海彬
         * linkmanPhne : 15080342451
         * desc : 民营企业税后利润转增资、再投资奖励
         * companyName : 厦门睿天科技有限公司
         * lng : 118.122505
         * lat : 24.478849
         * time : 2019年11月12日 21:12
         * tip : 求助者已经等待了17.06小时，请您尽快予以帮助!
         */

        private String id;
        private String cover;
        private String linkman;
        private String linkmanPhne;
        private String desc;
        private String companyName;
        private String lng;
        private String lat;
        private String time;
        private String tip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinkmanPhne() {
            return linkmanPhne;
        }

        public void setLinkmanPhne(String linkmanPhne) {
            this.linkmanPhne = linkmanPhne;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getLng() {
            if(TextUtils.isEmpty(lng)){
                lng="0.0";
            }
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            if(TextUtils.isEmpty(lat)){
                lat="0.0";
            }
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
