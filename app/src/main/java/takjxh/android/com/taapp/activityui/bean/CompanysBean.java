package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 11:52
 * @Description:
 **/
public class CompanysBean<T> extends BaseComResponse {


    /**
     * companys : [{"id":"6a35f09f4c10103896b87a6795b7d306","name":"中国国旅(软件园二期门市部)","cover":"http://106.53.78.24:8080/Takjxh/images/company-1.jpg","category":"01","area":"湖里","address":"软件园二期望海路73号102室","des":"中国国旅(软件园二期门市部)","lng":"118.194093","lat":"24.493333"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T companys;


    public static class CompanyBean {
        /**
         * id : 6a35f09f4c10103896b87a6795b7d306
         * name : 中国国旅(软件园二期门市部)
         * cover : http://106.53.78.24:8080/Takjxh/images/company-1.jpg
         * category : 01
         * area : 湖里
         * address : 软件园二期望海路73号102室
         * des : 中国国旅(软件园二期门市部)
         * lng : 118.194093
         * lat : 24.493333
         */

        public boolean isSelect=false;
        private String id;
        private String name;
        private String cover;
        private String category;
        private String area;
        private String address;
        private String des;
        private String lng;
        private String lat;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}
