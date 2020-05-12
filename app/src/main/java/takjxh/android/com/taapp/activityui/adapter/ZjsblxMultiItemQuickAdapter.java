package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.dialog.DateDialog;
import takjxh.android.com.taapp.activityui.dialog.DateDialogSet;
import takjxh.android.com.taapp.adapter.AddAttachmentAdapter;
import takjxh.android.com.taapp.view.CustomGridView;
import takjxh.android.com.taapp.view.CustomSpinner;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-07 21:13
 * @Description:
 **/
public class ZjsblxMultiItemQuickAdapter extends BaseMultiItemQuickAdapter<PolicyapplyaddBean, BaseViewHolder> {

    FragmentActivity activity;
    int positionNum;

    public ZjsblxMultiItemQuickAdapter(FragmentActivity activity, List<PolicyapplyaddBean> data,  int position) {
        super(data);
        this.activity = activity;
        this.positionNum=position;
        addItemType(1, R.layout.item_recycler_zjsblx_item_1);
        addItemType(2, R.layout.item_recycler_zjsblx_item_1);
        addItemType(3, R.layout.item_recycler_zjsblx_item_1);

        addItemType(4, R.layout.item_recycler_zjsblx_item_4);
        addItemType(5, R.layout.item_recycler_zjsblx_item_3);

        addItemType(6, R.layout.item_recycler_zjsblx_item_2);

        addItemType(7, R.layout.item_recycler_zjsblx_item_5);

    }

    @Override
    protected void convert(BaseViewHolder helper, PolicyapplyaddBean item) {

        switch (helper.getItemViewType()) {
            case 1:

                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());


                EditText tv_GSMC = (EditText) helper.getView(R.id.tv_GSMC);
                tv_GSMC.setInputType(InputType.TYPE_CLASS_TEXT);
                if (item.getRuleText() != null && !TextUtils.isEmpty(item.getRuleText().toString())) {
                    double num = Double.valueOf(item.getRuleText().toString());
                    tv_GSMC.setMaxLines((int) Math.floor(num));
                }



                if (tv_GSMC.getTag() != null && tv_GSMC.getTag() instanceof TextWatcher) {
                    tv_GSMC.removeTextChangedListener((TextWatcher) tv_GSMC.getTag());
                }
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                       //getData().get(getData().indexOf(item)).putKey = s.toString();
                        if(mOnDataChangeListener!=null){
                            mOnDataChangeListener.onClick(positionNum,getData().indexOf(item),s.toString());
                        }
                    }
                };
                tv_GSMC.addTextChangedListener(textWatcher);
                tv_GSMC.setTag(textWatcher);
                tv_GSMC.setText(item.getValue());

                break;
            case 2:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());
                EditText tv_GSMC2 = (EditText) helper.getView(R.id.tv_GSMC);
                tv_GSMC2.setInputType(InputType.TYPE_CLASS_NUMBER);
                int min = 0;
                int max = 999999999;
                if (item.getMinNum() != null && !TextUtils.isEmpty(item.getMinNum().toString())) {
                    min = (int) Math.floor(Double.valueOf(item.getMinNum().toString()));
                }
                if (item.getMaxNum() != null && !TextUtils.isEmpty(item.getMaxNum().toString())) {
                    max = (int) Math.floor(Double.valueOf(item.getMaxNum().toString()));

                }
                int aM = min;
                int bM = max;
                tv_GSMC2.setFilters(new InputFilter[]{new InputFilter() {

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        try {
                            int input = Integer.parseInt(dest.toString() + source.toString());
                            if (isInRange(aM, bM, input)) {
                                return null;
                            }
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                        return "";
                    }

                    private boolean isInRange(int a, int b, int c) {
                        return b > a ? c >= a && c <= b : c >= b && c <= a;
                    }
                }});

                if (tv_GSMC2.getTag() != null && tv_GSMC2.getTag() instanceof TextWatcher) {
                    tv_GSMC2.removeTextChangedListener((TextWatcher) tv_GSMC2.getTag());
                }
                TextWatcher textWatcher2 = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                       // getData().get(getData().indexOf(item)).putKey = s.toString();
                        if(mOnDataChangeListener!=null){
                            mOnDataChangeListener.onClick(positionNum,getData().indexOf(item),s.toString());
                        }
                    }
                };
                tv_GSMC2.addTextChangedListener(textWatcher2);
                tv_GSMC2.setTag(textWatcher2);
                tv_GSMC2.setText(item.getValue());


                break;
            case 3:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());
                EditText tv_GSMC3 = (EditText) helper.getView(R.id.tv_GSMC);
                tv_GSMC3.setInputType(InputType.TYPE_CLASS_DATETIME);
                if (item.getRuleNumb() != null && !TextUtils.isEmpty(item.getRuleNumb().toString())) {

                    /**
                     * 保留小数点后多少位，默认两位
                     */
                    int mDecimalEndNumber = (int) Math.floor(Double.valueOf(item.getRuleNumb().toString()));
                    tv_GSMC3.setFilters(new InputFilter[]{new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                            String lastInputContent = dest.toString();

                               /* LogUtils.d("source->" + source + "--start->" + start + " " +
                                        "--lastInputContent->" + lastInputContent + "--dstart->" + dstart + "--dend->" + dend);*/


                            if (source.equals(".") && lastInputContent.length() == 0) {
                                return "0.";
                            }

                            if (!source.equals(".") && !source.equals("") && lastInputContent.equals("0")) {
                                return ".";
                            }

                            if (source.equals(".") && lastInputContent.contains(".")) {
                                return "";
                            }

                            if (lastInputContent.contains(".")) {
                                int index = lastInputContent.indexOf(".");
                                if (dend - index >= mDecimalEndNumber + 1) {
                                    return "";
                                }
                            } else {
                               /* if (!source.equals(".") && lastInputContent.length() >= mDecimalStarNumber) {
                                    return "";
                                }*/
                            }

                            return null;
                        }
                    }});
                }

                if (tv_GSMC3.getTag() != null && tv_GSMC3.getTag() instanceof TextWatcher) {
                    tv_GSMC3.removeTextChangedListener((TextWatcher) tv_GSMC3.getTag());
                }
                TextWatcher textWatcher3 = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //getData().get(getData().indexOf(item)).putKey = s.toString();
                        if(mOnDataChangeListener!=null){
                            mOnDataChangeListener.onClick(positionNum,getData().indexOf(item),s.toString());
                        }
                    }
                };
                tv_GSMC3.addTextChangedListener(textWatcher3);
                tv_GSMC3.setTag(textWatcher3);
                tv_GSMC3.setText(item.getValue());

                break;
            case 4:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());

                TextView tv_czjg = (TextView) helper.getView(R.id.tv_czjg);
                CustomSpinner sp_register2 = (CustomSpinner) helper.getView(R.id.sp_register1);
                List<String> mList2 = new ArrayList<>();
                mList2.clear();
                if (!TextUtils.isEmpty(item.getSelectVal())) {
                    List<String> list = Arrays.asList(item.getSelectVal().split("\\[sp\\]"));
                    if (list != null) {
                        mList2.addAll(list);
                    }
                }

                tv_czjg.setText(item.getValue());
                MyArrayAdapter2 adapterResult2 = new MyArrayAdapter2(mContext, mList2);
                adapterResult2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_register2.setAdapter(adapterResult2);
                sp_register2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                        String mBean = mList2.get(arg2);
                        tv_czjg.setText(mBean);
                       // getData().get(getData().indexOf(item)).putKey = mBean;
                        if(mOnDataChangeListener!=null){
                            mOnDataChangeListener.onClick(positionNum,getData().indexOf(item),mBean);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }


                });
                sp_register2.setSelection(-1, true);
                break;
            case 5:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());

                TextView edSCFJ = (TextView) helper.getView(R.id.edSCFJ);
                TextView edSCFJ1 = (TextView) helper.getView(R.id.edSCFJ1);
                TextView tv_Mark = (TextView) helper.getView(R.id.tv_Mark);
                if (item.getTemplate() != null && !TextUtils.isEmpty(item.getTemplate().toString())) {
                    edSCFJ1.setVisibility(View.GONE);//?
                } else {
                    edSCFJ1.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(item.getMark())) {
                    tv_Mark.setVisibility(View.VISIBLE);
                    tv_Mark.setText(item.getMark());
                } else {
                    tv_Mark.setVisibility(View.GONE);
                }
                CustomGridView mGridView = (CustomGridView) helper.getView(R.id.noScrollgridview);
                ArrayList<UploadFileBean> urls = new ArrayList<>();
                AddAttachmentAdapter mAdapter = new AddAttachmentAdapter(activity, urls);
                mGridView.setAdapter(mAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(mWJSCItemClickListener!=null){
                            mWJSCItemClickListener.onClick(i);
                        }
                        /*positionSelected = i;
                        dialogDelete.show();*/

                    }
                });
                edSCFJ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnWJSCClickListener!=null){
                            mOnWJSCClickListener.onClick(mAdapter,positionNum,getData().indexOf(item) , urls);
                        }
                    }
                });

                break;
            case 6:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());
                TextView edZCSJ = (TextView) helper.getView(R.id.edZCSJ);
                edZCSJ.setText(item.getValue());
                edZCSJ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        long mStart = 0;
                        long mEnd = 0;
                        if (item.getStartTime() != null && !TextUtils.isEmpty(item.getStartTime().toString())) {
                            mStart = Long.valueOf(item.getStartTime().toString());
                        }
                        if (item.getEndTime() != null && !TextUtils.isEmpty(item.getEndTime().toString())) {
                            mEnd = Long.valueOf(item.getEndTime().toString());
                        }
                        // 日期选择对话框
                        new DateDialogSet.Builder(activity, mStart, mEnd)
                                .setTitle("请选择日期")
                                // 确定按钮文本
                                .setConfirm("确定")
                                // 设置 null 表示不显示取消按钮
                                .setCancel("取消")
                                // 设置日期
                                //.setDate("2018-12-31")
                                //.setDate("20181231")
                                //.setDate(1546263036137)
                                // 设置年份
                                //.setYear(2018)
                                // 设置月份
                                //.setMonth(2)
                                // 设置天数
                                //.setDay(20)
                                // 不选择天数
                                //.setIgnoreDay()
                                .setListener(new DateDialogSet.OnListener() {
                                    @Override
                                    public void onSelected(BaseDialog dialog, int year, int month, int day) {

                                        String mmonth = "";
                                        if (month < 10) {
                                            mmonth = "0" + month;
                                        } else {
                                            mmonth = "" + month;
                                        }
                                        String mday = "";
                                        if (day < 10) {
                                            mday = "0" + day;
                                        } else {
                                            mday = "" + day;
                                        }

                                /*String mhour="";
                                if(hour<10){
                                    mhour="0"+hour;
                                }else{
                                    mhour=""+hour;
                                }

                                String mminute="";
                                if(minute<10){
                                    mminute="0"+minute;
                                }else{
                                    mminute=""+minute;
                                }

                                String msecond="";
                                if(second<10){
                                    msecond="0"+second;
                                }else{
                                    msecond=""+second;
                                }*/
                                        edZCSJ.setText(year + activity.getString(R.string.common_year) + mmonth + activity.getString(R.string.common_month) + mday + activity.getString(R.string.common_day)
                                        );//+" "+mhour+":"+mminute+":"+msecond
                                        String mZCSJ = year + "-" + mmonth + "-" + mday;//+ "" +""+mhour+""+mminute+""+msecond
                                        //getData().get(getData().indexOf(item)).putKey=mZCSJ;
                                        if(mOnDataChangeListener!=null){
                                            mOnDataChangeListener.onClick(positionNum,getData().indexOf(item),mZCSJ);
                                        }

                                        // 如果不指定时分秒则默认为现在的时间
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.set(Calendar.YEAR, year);
                                        // 月份从零开始，所以需要减 1
                                        calendar.set(Calendar.MONTH, month - 1);
                                        calendar.set(Calendar.DAY_OF_MONTH, day);
                                        //     toast("时间戳：" + calendar.getTimeInMillis());
                                        //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                                    }

                                    @Override
                                    public void onCancel(BaseDialog dialog) {
                                        // toast("取消了");
                                    }
                                })
                                .show();
                    }
                });

                break;
            case 7:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());
                TextView tv_GSMC1 = (TextView) helper.getView(R.id.tv_GSMC);
                tv_GSMC1.setText(item.getValue());
                break;
        }
    }


    public class MyArrayAdapter2 extends ArrayAdapter<String> {

        private List<String> mList;

        public MyArrayAdapter2(Context context, List<String> objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
            mList = objects;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);

            String mBean = mList.get(position);
            tvName.setText(mBean);

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            TextView text = (TextView) convertView
                    .findViewById(android.R.id.text1);
            String mBean = mList.get(position);
            text.setText(mBean);
            return convertView;
        }

    }

    public interface OnDataChangeListener {
        void onClick(int positionNum, int position,String putKey);
    }
    private OnDataChangeListener mOnDataChangeListener;
    public void setOnDataChangeListener(OnDataChangeListener mClickListener) {
        this.mOnDataChangeListener = mClickListener;
    }



    public interface OnWJSCClickListener {
        void onClick(AddAttachmentAdapter mAdapter,int positionNum, int position, ArrayList<UploadFileBean> urls);
    }
    private OnWJSCClickListener mOnWJSCClickListener;
    public void setOnWJSCClickListener(OnWJSCClickListener mClickListener) {
        this.mOnWJSCClickListener = mClickListener;
    }



    public interface OnWJSCItemClickListener {
        void onClick(int position);
    }
    private OnWJSCItemClickListener mWJSCItemClickListener;
    public void setOnWJSCItemClickListener(OnWJSCItemClickListener mClickListener) {
        this.mWJSCItemClickListener = mClickListener;
    }


}
