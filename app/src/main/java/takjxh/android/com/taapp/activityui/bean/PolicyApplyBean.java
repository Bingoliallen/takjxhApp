package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 11:35
 * @Description:
 **/
public class PolicyApplyBean<T> extends BaseComResponse {


    /**
     * applyInfos : [{"id":"ad87d04f535c103896b87a6795b7d333","time":"2019年11月12日 11:14","title":"关于民营企业股权转让收益税收奖励申请","status":"审核通过","desc":"申请企业名称：厦门喆洋出行汽车租赁有限公司<br/>组织机构代码：11212121212121313<br/>申请金额：¥100000"}]
     * page : 1
     * pageSize : 5
     */

    public int page;
    public int pageSize;
    public T applyInfos;


    public static class ApplyInfosBean {
        /**
         * id : ad87d04f535c103896b87a6795b7d333
         * time : 2019年11月12日 11:14
         * title : 关于民营企业股权转让收益税收奖励申请
         * status : 审核通过
         * desc : 申请企业名称：厦门喆洋出行汽车租赁有限公司<br/>组织机构代码：11212121212121313<br/>申请金额：¥100000
         */
        /*{
            "id": "c8a028e18f6011ea9653e86a6477f339", //政策申报编号
                "time": "2020年05月06日 14:14", //政策申报时间
                "title": "关于同安区减免租金出租方房土两税扶持资金（第一季度）申请", //政策申报标题
                "status": "01",  //政策申报状态
                "showStatus": "申报审核中", //政策申报状态（显示用）
                "desc": null,
                "checkDes": null, //政策申报审核描述（当申报状态为03显示）
                "typeStatus": "02", //政策申报类型
                "isOpenDown": true //是否提供模板下载（当申请状态为02并且isOpenDown=true的时候显示申报表下载、收据下载链接）
        }*/


        private String showStatus;
        private String checkDes;
        private String typeStatus;
        private boolean isOpenDown;


        private String id;
        private String time;
        private String title;
        private String status;
        private String desc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getCheckDes() {
            return checkDes;
        }

        public void setCheckDes(String checkDes) {
            this.checkDes = checkDes;
        }

        public String getTypeStatus() {
            return typeStatus;
        }

        public void setTypeStatus(String typeStatus) {
            this.typeStatus = typeStatus;
        }

        public boolean isOpenDown() {
            return isOpenDown;
        }

        public void setOpenDown(boolean openDown) {
            isOpenDown = openDown;
        }
    }
}
