package takjxh.android.com.taapp.activityui.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.utils.OpenFileUtil;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-14 12:30
 * @Description:
 **/
public class QuickAdapter1 extends BaseQuickAdapter<UploadFileBean, BaseViewHolder> {
    public QuickAdapter1() {
        super(R.layout.adapter_grid_item_attachment1);
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, UploadFileBean item) {
        String name=getFileName(item.getFileName());

        if (!TextUtils.isEmpty(item.getFileName()) && item.getFileName().contains(".pdf")) {
            viewHolder.getView(R.id.im_pdf).setVisibility(View.VISIBLE);
            viewHolder.getView(R.id.im_tp).setVisibility(View.GONE);
            viewHolder.getView(R.id.pdfName).setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.pdfName, name+".pdf");
        } else {
            viewHolder.getView(R.id.im_pdf).setVisibility(View.GONE);
            viewHolder.getView(R.id.im_tp).setVisibility(View.VISIBLE);
            viewHolder.getView(R.id.pdfName).setVisibility(View.GONE);
            ImageWrapper.setImage(viewHolder.getView(R.id.im_tp), item.getFilePath(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

        }

        viewHolder.getView(R.id.im_pdf).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


        viewHolder.getView(R.id.im_tp).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


    }


    public String getFileName(String pathandname){
        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if (start!=-1 && end!=-1) {
            return pathandname.substring(start+1, end);
        }
        else {
            return null;
        }
    }


}