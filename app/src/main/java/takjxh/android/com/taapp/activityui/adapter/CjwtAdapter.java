package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.TwDetailActivity;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.QafaqListBean;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;

/**
 * 类名称：常见问题列表-网格
 *
 * @Author: libaibing
 * @Date: 2019-10-17 16:41
 * @Description:
 **/
public class CjwtAdapter  extends BaseRecyclerAdapter<QafaqListBean.FaqsBean> {

    public CjwtAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_cjwt);

    }


    @Override
    public void onBind(ViewHolder holder, final QafaqListBean.FaqsBean item, final int position) {

        holder.setText(R.id.tv_name, item.getTitle());

        ImageView iv_icon = (ImageView) holder.getView(R.id.iv_icon);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }


        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TwDetailActivity.lunch((Activity)mContext,item.getId());*/
                new MessageDialog.Builder((FragmentActivity)mContext)
                        // 标题可以不用填写
                        .setTitle("提示")
                        // 内容必须要填写
                        .setMessage(item.getAnswer())
                        // 确定按钮文本
                        .setConfirm("知道了")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                // toast("确定了");

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
            }
        });

    }
}