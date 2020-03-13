package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.JlztHdListActivity;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;
import takjxh.android.com.taapp.activityui.dialog.InputTextMsgDialog;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 11:09
 * @Description:
 **/
public class JlztDetailAdapter extends BaseRecyclerAdapter<CommQuestionBean.CommQuestionsBean> {
    private String topicId;

    public JlztDetailAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_jlzt_detail_list);
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public void onBind(ViewHolder holder, final CommQuestionBean.CommQuestionsBean item, final int position) {
        holder.setText(R.id.tvName, item.getName());
        holder.setText(R.id.tv_extra1, item.getTime());
        holder.setText(R.id.tv_extra, ""+item.getCommentNum());
        holder.setText(R.id.tvContent, item.getContent());


        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getUserCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon, item.getUserCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }

        holder.getView(R.id.ivpl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTextMsgDialog inputTextMsgDialog = new InputTextMsgDialog(mContext, R.style.dialog_center);
                inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
                    @Override
                    public void onTextSend(String msg) {
                        //点击发送按钮后，回调此方法，msg为输入的值
                        if (mOnTextSendListener != null) {
                            mOnTextSendListener.onTextSend(msg, item, position);
                        }
                    }
                });
                inputTextMsgDialog.show();
            }
        });

        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JlztHdListActivity.lunch((Activity) mContext, topicId, item.getId());
            }
        });
    }

    private OnJlztTextSendListener mOnTextSendListener;

    public interface OnJlztTextSendListener {

        void onTextSend(String msg, final CommQuestionBean.CommQuestionsBean item, final int position);
    }

    public void setmOnTextSendListener(OnJlztTextSendListener mOnTextSendListener) {
        this.mOnTextSendListener = mOnTextSendListener;
    }
}