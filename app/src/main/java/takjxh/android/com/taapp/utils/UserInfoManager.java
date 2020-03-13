package takjxh.android.com.taapp.utils;

import java.util.ArrayList;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.QbApplication;

/***
 * 名称定义
 *
 * @Param:
 * @return:
 * @Author: libaibing
 * @Date: 2019/2/28
 */
public class UserInfoManager {

    public static void delete() {
        UserInfo info = JMessageClient.getMyInfo();
        if(null != info){
            JMessageClient.logout();
        }
        QbApplication.mBaseApplication.delConversation = null;
        QbApplication.mBaseApplication.forwardMsg.clear();
        QbApplication.mBaseApplication.alreadyRead.clear();
        QbApplication.mBaseApplication.unRead.clear();
        QbApplication.mBaseApplication.isAtMe.clear();
        QbApplication.mBaseApplication.isAtall.clear();
        QbApplication.mBaseApplication.ids.clear();
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_userId");

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_userPassword");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_cached_avatar_path");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_isShowCheck");


        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "station");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isTeacher");

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "userId");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "userName");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "name");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "mobilePhone");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "company");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "type");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "score");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isOpenMsg");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isOpenVoice");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "avatar");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "token");
        QbApplication.mBaseApplication.userExts=new ArrayList<>();
    }

}
