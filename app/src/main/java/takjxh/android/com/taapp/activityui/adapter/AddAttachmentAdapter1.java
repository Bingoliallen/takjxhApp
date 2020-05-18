package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.view.CustomGridView;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-14 11:41
 * @Description:
 **/
public class AddAttachmentAdapter1  extends BaseAdapter {

    private Context context;
    private List<UploadFileBean> list = new ArrayList<UploadFileBean>();
    private LayoutInflater layoutInflater;
    CustomGridView mGridView;

    public AddAttachmentAdapter1(Context context, List<UploadFileBean> list,CustomGridView mGridView) {
        this.context = context;
        this.list = list;
        this.mGridView=mGridView;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomGridView getmGridView() {
        return mGridView;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (getCount()>0) {
            getmGridView().setVisibility(View.VISIBLE);
        }else{
            getmGridView().setVisibility(View.GONE);
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= getCount()) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_grid_item_attachment1, null);
            viewHolder.im_tp = (ImageView) convertView.findViewById(R.id.im_tp);
            viewHolder.im_pdf = (ImageView) convertView.findViewById(R.id.im_pdf);
            viewHolder.pdfName = (TextView) convertView.findViewById(R.id.pdfName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = list.get(position).getFileName();

        viewHolder.pdfName.setVisibility(View.GONE);

        if(!TextUtils.isEmpty(name) && name.contains(".pdf")){
            if(!TextUtils.isEmpty(name) && name.contains("http")){
                viewHolder.pdfName.setText(getFileName(name)+".pdf");
            }else{
                viewHolder.pdfName.setText(name);
            }
            viewHolder.pdfName.setVisibility(View.VISIBLE);
            viewHolder.im_pdf.setVisibility(View.VISIBLE);
            viewHolder.im_tp.setVisibility(View.GONE);
        }else{
            viewHolder.im_pdf.setVisibility(View.GONE);
            viewHolder.im_tp.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(list.get(position).getFilePath()) && list.get(position).getFilePath().contains("http")){
                ImageWrapper.setImage(viewHolder.im_tp, list.get(position).getFilePath(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

            }else{
                if(!TextUtils.isEmpty(list.get(position).getFilePath()) && list.get(position).getFilePath().contains("//")){
                    String replaceStr =  list.get(position).getFilePath().replace("//","/");
                    ImageWrapper.setImage(viewHolder.im_tp, HttpConfig.HOST1+replaceStr, R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

                }else{
                    ImageWrapper.setImage(viewHolder.im_tp, HttpConfig.HOST1+list.get(position).getFilePath(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

                }

            }

        }

        /*viewHolder.im_pdf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


        viewHolder.im_tp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });*/

        return convertView;
    }

    class ViewHolder {
        private ImageView im_tp;
        private ImageView im_pdf;
        private TextView pdfName;
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