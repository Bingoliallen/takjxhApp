package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;


import org.greenrobot.eventbus.EventBus;

import java.io.File;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.OrgansBean;
import takjxh.android.com.taapp.activityui.chat.activity.ChatActivity;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-24 14:38
 * @Description:
 **/
public class ZxjgAdapter extends BaseRecyclerAdapter<OrgansBean.OrganListBean> {

    public ZxjgAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_qzjg_list);

    }


    @Override
    public void onBind(ViewHolder holder, final OrgansBean.OrganListBean item, final int position) {
 /* 以江管理咨询，是一家由全球合伙人联合所有和运营的有限合伙企业，由来自中国、美国、英国等地的团队为客户提供咨询服务。
        电话：021-5629 7756
        E-mail: hrd@yijiangglobal.com
        地址：上海市长宁区虹桥路2535号永融国际中心8楼*/

        holder.setText(R.id.tv_sj, item.getName());
        holder.setText(R.id.tv_zt, item.getWorkTime());
        String msg = item.getDes() + "\n"
                + "联系人：" + item.getLinkman() + "\n"
                + "电话：" + item.getLinkmanPhone() + "\n"
                + "E-mail:" + item.getEmail() + "\n"
                + "地址：" + item.getAddress() + "\n";
        holder.setText(R.id.tvcontent, msg);


        ImageView iv_icon = (ImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

        }


        holder.setText(R.id.tv_pj, "马上咨询");
        holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  ChatActivity.startAction((Activity) mContext,item.getName());*/
                UserInfo info = JMessageClient.getMyInfo();
                if (null != info) {
                    JMessageClient.getUserInfo(item.getAdminUserId(), new GetUserInfoCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                            if (responseCode == 0) {
                                UserInfo mUserInfo = info;
                                Intent intent = new Intent();
                                intent.setClass((Activity) mContext, ChatActivity.class);
                                //创建会话
                                intent.putExtra("targetId", mUserInfo.getUserName());
                                intent.putExtra("targetAppKey", mUserInfo.getAppKey());
                                String notename = mUserInfo.getNotename();
                                if (TextUtils.isEmpty(notename)) {
                                    notename = mUserInfo.getNickname();
                                    if (TextUtils.isEmpty(notename)) {
                                        notename = mUserInfo.getUserName();
                                    }
                                }
                                intent.putExtra("conv_title", notename);
                           /* Conversation conv = JMessageClient.getSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey());
                            //如果会话为空，使用EventBus通知会话列表添加新会话
                            if (conv == null) {
                                conv = Conversation.createSingleConversation(mUserInfo.getUserName(), mUserInfo.getAppKey());
                                EventBus.getDefault().post(new Event.Builder()
                                        .setType(EventType.createConversation)
                                        .setConversation(conv)
                                        .build());
                            }*/
                                ((Activity) mContext).startActivity(intent);
                                ((Activity) mContext).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }

                        }
                    });
                }else {
                    ToastUtil.showToast(mContext, "用户无菜单操作权限，请联系后台管理员");
                }



            }
        });

    }
}
