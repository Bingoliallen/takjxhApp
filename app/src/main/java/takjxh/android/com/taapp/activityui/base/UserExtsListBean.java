package takjxh.android.com.taapp.activityui.base;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtsListBean<T> extends BaseComResponse {

    public int page;
    public int pageSize;
    public T sysUserExtListVos;

    public static class UserExtListVosBean {
        /* "id":"036ee5d548d811eaaca800ff0bcb9432", //扩展用户信息编号
                 "unitName":"333", //单位名称
                 "unitCode":"111111111111111111", //单位代码
                 "linkmanPhone":"1111", //联系电话
                 "status":"01", //审核状态
                 "showStatus":"待审核", //审核状态，显示用
                 "type": "01", //用户类型
                 "showType": "政府会员", //用户类型，显示用
                 "isDefaultType":true, //是否为默认
                 "showIsDefaultType":"是" //是否为默认，显示用*/

        private String type;
        private String showType;

        private String id;
        private String unitName;
        private String unitCode;
        private String linkmanPhone;
        private String status;
        private String showStatus;
        private boolean isDefaultType;
        private String showIsDefaultType;

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

        public boolean isDefaultType() {
            return isDefaultType;
        }

        public void setDefaultType(boolean defaultType) {
            isDefaultType = defaultType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getLinkmanPhone() {
            return linkmanPhone;
        }

        public void setLinkmanPhone(String linkmanPhone) {
            this.linkmanPhone = linkmanPhone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public boolean getIsDefaultType() {
            return isDefaultType;
        }

        public void setIsDefaultType(boolean isDefaultType) {
            this.isDefaultType = isDefaultType;
        }

        public String getShowIsDefaultType() {
            return showIsDefaultType;
        }

        public void setShowIsDefaultType(String showIsDefaultType) {
            this.showIsDefaultType = showIsDefaultType;
        }
    }
}
