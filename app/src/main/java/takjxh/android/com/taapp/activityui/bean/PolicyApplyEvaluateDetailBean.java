package takjxh.android.com.taapp.activityui.bean;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 9:02
 * @Description:
 **/
public class PolicyApplyEvaluateDetailBean<T> extends BaseComResponse {


    /**
     * detailVo : {"helpId":"3e2e2f48569e103896b87a6795b7d300","organName":"以江管理咨询","linkman":"张三"}
     */

    public T detailVo;


    public static class DetailVoBean {
        /**
         * helpId : 3e2e2f48569e103896b87a6795b7d300
         * organName : 以江管理咨询
         * linkman : 张三
         */

        private String helpId;
        private String organName;
        public String des;
        private String linkman;

        public String getHelpId() {
            return helpId;
        }

        public void setHelpId(String helpId) {
            this.helpId = helpId;
        }

        public String getOrganName() {
            return organName;
        }

        public void setOrganName(String organName) {
            this.organName = organName;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }
    }
}
