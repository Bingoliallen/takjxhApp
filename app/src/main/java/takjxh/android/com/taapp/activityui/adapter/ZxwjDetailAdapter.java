package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.SurveyDetailBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-15 16:39
 * @Description:
 **/
public class ZxwjDetailAdapter extends BaseRecyclerAdapter<SurveyDetailBean.Question1Bean> {


    public ZxwjDetailAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zxwj_detail_list);

    }


    @Override
    public void onBind(ViewHolder holder, final SurveyDetailBean.Question1Bean item, final int position) {

        holder.setText(R.id.tvtitle, (position + 1) + "." + item.getTitle());

        View line1 = (View) holder.getView(R.id.line1);
        View line2 = (View) holder.getView(R.id.line2);
        View line3 = (View) holder.getView(R.id.line3);
        View line4 = (View) holder.getView(R.id.line4);
        View line5 = (View) holder.getView(R.id.line5);
        View line6 = (View) holder.getView(R.id.line6);
        View line7 = (View) holder.getView(R.id.line7);
        View line8 = (View) holder.getView(R.id.line8);
        View line9 = (View) holder.getView(R.id.line9);
        View line10 = (View) holder.getView(R.id.line10);
        View line11 = (View) holder.getView(R.id.line11);
        View line12 = (View) holder.getView(R.id.line12);
        View line13 = (View) holder.getView(R.id.line13);
        View line14 = (View) holder.getView(R.id.line14);
        View line15 = (View) holder.getView(R.id.line15);

        View line20 = (View) holder.getView(R.id.line20);
        View line21 = (View) holder.getView(R.id.line21);
        View line22 = (View) holder.getView(R.id.line22);
        View line23 = (View) holder.getView(R.id.line23);
        View line24 = (View) holder.getView(R.id.line24);
        View line25 = (View) holder.getView(R.id.line25);
        View line26 = (View) holder.getView(R.id.line26);
        View line27 = (View) holder.getView(R.id.line27);
        View line28 = (View) holder.getView(R.id.line28);
        View line29 = (View) holder.getView(R.id.line29);
        View line30 = (View) holder.getView(R.id.line30);
        View line31 = (View) holder.getView(R.id.line31);
        View line32 = (View) holder.getView(R.id.line32);
        View line33 = (View) holder.getView(R.id.line33);
        View line34 = (View) holder.getView(R.id.line34);


        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        line4.setVisibility(View.GONE);
        line5.setVisibility(View.GONE);
        line6.setVisibility(View.GONE);
        line7.setVisibility(View.GONE);
        line8.setVisibility(View.GONE);
        line9.setVisibility(View.GONE);
        line10.setVisibility(View.GONE);
        line11.setVisibility(View.GONE);
        line12.setVisibility(View.GONE);
        line13.setVisibility(View.GONE);
        line14.setVisibility(View.GONE);
        line15.setVisibility(View.GONE);


        line20.setVisibility(View.GONE);
        line21.setVisibility(View.GONE);
        line22.setVisibility(View.GONE);
        line23.setVisibility(View.GONE);
        line24.setVisibility(View.GONE);
        line25.setVisibility(View.GONE);
        line26.setVisibility(View.GONE);
        line27.setVisibility(View.GONE);
        line28.setVisibility(View.GONE);
        line29.setVisibility(View.GONE);
        line30.setVisibility(View.GONE);
        line31.setVisibility(View.GONE);
        line32.setVisibility(View.GONE);
        line33.setVisibility(View.GONE);
        line34.setVisibility(View.GONE);



        RadioButton radioButton1 = (RadioButton) holder.getView(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) holder.getView(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton) holder.getView(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton) holder.getView(R.id.radioButton4);
        RadioButton radioButton5 = (RadioButton) holder.getView(R.id.radioButton5);
        RadioButton radioButton6 = (RadioButton) holder.getView(R.id.radioButton6);
        RadioButton radioButton7 = (RadioButton) holder.getView(R.id.radioButton7);
        RadioButton radioButton8 = (RadioButton) holder.getView(R.id.radioButton8);
        RadioButton radioButton9 = (RadioButton) holder.getView(R.id.radioButton9);
        RadioButton radioButton10 = (RadioButton) holder.getView(R.id.radioButton10);
        RadioButton radioButton11 = (RadioButton) holder.getView(R.id.radioButton11);
        RadioButton radioButton12 = (RadioButton) holder.getView(R.id.radioButton12);
        RadioButton radioButton13 = (RadioButton) holder.getView(R.id.radioButton13);
        RadioButton radioButton14 = (RadioButton) holder.getView(R.id.radioButton14);
        RadioButton radioButton15 = (RadioButton) holder.getView(R.id.radioButton15);

        radioButton1.setVisibility(View.GONE);
        radioButton2.setVisibility(View.GONE);
        radioButton3.setVisibility(View.GONE);
        radioButton4.setVisibility(View.GONE);
        radioButton5.setVisibility(View.GONE);
        radioButton6.setVisibility(View.GONE);
        radioButton7.setVisibility(View.GONE);
        radioButton8.setVisibility(View.GONE);
        radioButton9.setVisibility(View.GONE);
        radioButton10.setVisibility(View.GONE);
        radioButton11.setVisibility(View.GONE);
        radioButton12.setVisibility(View.GONE);
        radioButton13.setVisibility(View.GONE);
        radioButton14.setVisibility(View.GONE);
        radioButton15.setVisibility(View.GONE);

        CheckBox cb_1 = (CheckBox) holder.getView(R.id.cb_1);
        CheckBox cb_2 = (CheckBox) holder.getView(R.id.cb_2);
        CheckBox cb_3 = (CheckBox) holder.getView(R.id.cb_3);
        CheckBox cb_4 = (CheckBox) holder.getView(R.id.cb_4);
        CheckBox cb_5 = (CheckBox) holder.getView(R.id.cb_5);
        CheckBox cb_6 = (CheckBox) holder.getView(R.id.cb_6);
        CheckBox cb_7 = (CheckBox) holder.getView(R.id.cb_7);
        CheckBox cb_8 = (CheckBox) holder.getView(R.id.cb_8);
        CheckBox cb_9 = (CheckBox) holder.getView(R.id.cb_9);
        CheckBox cb_10 = (CheckBox) holder.getView(R.id.cb_10);
        CheckBox cb_11 = (CheckBox) holder.getView(R.id.cb_11);
        CheckBox cb_12 = (CheckBox) holder.getView(R.id.cb_12);
        CheckBox cb_13 = (CheckBox) holder.getView(R.id.cb_13);
        CheckBox cb_14 = (CheckBox) holder.getView(R.id.cb_14);
        CheckBox cb_15 = (CheckBox) holder.getView(R.id.cb_15);


        cb_1.setVisibility(View.GONE);
        cb_2.setVisibility(View.GONE);
        cb_3.setVisibility(View.GONE);
        cb_4.setVisibility(View.GONE);
        cb_5.setVisibility(View.GONE);
        cb_6.setVisibility(View.GONE);
        cb_7.setVisibility(View.GONE);
        cb_8.setVisibility(View.GONE);
        cb_9.setVisibility(View.GONE);
        cb_10.setVisibility(View.GONE);
        cb_11.setVisibility(View.GONE);
        cb_12.setVisibility(View.GONE);
        cb_13.setVisibility(View.GONE);
        cb_14.setVisibility(View.GONE);
        cb_15.setVisibility(View.GONE);

        String type=item.getType();
        String code="";
        if(QbApplication.mBaseApplication.questiontype!=null &&QbApplication.mBaseApplication.questiontype.size()>0){
            for(SysParamBean.ParamsBean.ExamtypeBean bean :QbApplication.mBaseApplication.questiontype){
                if("单选题".equals(bean.getValue()) && bean.getCode().equals(type)){
                    code="01";
                }else if("多选题".equals(bean.getValue()) && bean.getCode().equals(type) ){
                    code="02";
                }else if("文本域".equals(bean.getValue()) && bean.getCode().equals(type) ){
                    code="03";
                }
            }
        }


        if ("01".equals(code)) {
            holder.getView(R.id.mLdx).setVisibility(View.VISIBLE);
            holder.getView(R.id.mLrx).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.mL3).setVisibility(View.GONE);

            if (item.getItems() != null) {
                for (int a = 0; a < item.getItems().size(); a++) {
                    int num = a;
                    if (a == 0) {
                        radioButton1.setChecked(item.getItems().get(a).isCheck);

                        radioButton1.setText(item.getItems().get(0).getDes());
                        radioButton1.setTag(item.getItems().get(0).getId());
                        radioButton1.setVisibility(View.VISIBLE);
                        radioButton1.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line1.setVisibility(View.VISIBLE);

                    } else if (a == 1) {
                        radioButton2.setChecked(item.getItems().get(a).isCheck);

                        radioButton2.setText(item.getItems().get(1).getDes());
                        radioButton2.setTag(item.getItems().get(1).getId());
                        radioButton2.setVisibility(View.VISIBLE);
                        radioButton2.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line2.setVisibility(View.VISIBLE);
                    } else if (a == 2) {
                        radioButton3.setChecked(item.getItems().get(a).isCheck);

                        radioButton3.setText(item.getItems().get(2).getDes());
                        radioButton3.setTag(item.getItems().get(2).getId());
                        radioButton3.setVisibility(View.VISIBLE);
                        radioButton3.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line3.setVisibility(View.VISIBLE);

                    } else if (a == 3) {
                        radioButton4.setChecked(item.getItems().get(a).isCheck);

                        radioButton4.setText(item.getItems().get(3).getDes());
                        radioButton4.setTag(item.getItems().get(3).getId());
                        radioButton4.setVisibility(View.VISIBLE);
                        radioButton4.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line4.setVisibility(View.VISIBLE);
                    } else if (a == 4) {
                        radioButton5.setChecked(item.getItems().get(a).isCheck);

                        radioButton5.setText(item.getItems().get(4).getDes());
                        radioButton5.setTag(item.getItems().get(4).getId());
                        radioButton5.setVisibility(View.VISIBLE);
                        radioButton5.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line5.setVisibility(View.VISIBLE);
                    } else if (a == 5) {
                        radioButton6.setChecked(item.getItems().get(a).isCheck);
                        radioButton6.setText(item.getItems().get(5).getDes());
                        radioButton6.setTag(item.getItems().get(5).getId());
                        radioButton6.setVisibility(View.VISIBLE);
                        radioButton6.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line6.setVisibility(View.VISIBLE);
                    } else if (a == 6) {
                        radioButton7.setChecked(item.getItems().get(a).isCheck);

                        radioButton7.setText(item.getItems().get(6).getDes());
                        radioButton7.setTag(item.getItems().get(6).getId());
                        radioButton7.setVisibility(View.VISIBLE);
                        radioButton7.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line7.setVisibility(View.VISIBLE);
                    } else if (a == 7) {
                        radioButton8.setChecked(item.getItems().get(a).isCheck);

                        radioButton8.setText(item.getItems().get(7).getDes());
                        radioButton8.setTag(item.getItems().get(7).getId());
                        radioButton8.setVisibility(View.VISIBLE);
                        radioButton8.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line8.setVisibility(View.VISIBLE);
                    } else if (a == 8) {
                        radioButton9.setChecked(item.getItems().get(a).isCheck);


                        radioButton9.setText(item.getItems().get(8).getDes());
                        radioButton9.setTag(item.getItems().get(8).getId());
                        radioButton9.setVisibility(View.VISIBLE);
                        radioButton9.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line9.setVisibility(View.VISIBLE);
                    } else if (a == 9) {
                        radioButton10.setChecked(item.getItems().get(a).isCheck);

                        radioButton10.setText(item.getItems().get(9).getDes());
                        radioButton10.setTag(item.getItems().get(9).getId());
                        radioButton10.setVisibility(View.VISIBLE);
                        radioButton10.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line10.setVisibility(View.VISIBLE);
                    } else if (a == 10) {
                        radioButton11.setChecked(item.getItems().get(a).isCheck);


                        radioButton11.setText(item.getItems().get(10).getDes());
                        radioButton11.setTag(item.getItems().get(10).getId());
                        radioButton11.setVisibility(View.VISIBLE);
                        radioButton11.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line11.setVisibility(View.VISIBLE);
                    } else if (a == 11) {
                        radioButton12.setChecked(item.getItems().get(a).isCheck);

                        radioButton12.setText(item.getItems().get(11).getDes());
                        radioButton12.setTag(item.getItems().get(11).getId());
                        radioButton12.setVisibility(View.VISIBLE);
                        radioButton12.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line12.setVisibility(View.VISIBLE);
                    } else if (a == 12) {
                        radioButton13.setChecked(item.getItems().get(a).isCheck);

                        radioButton13.setText(item.getItems().get(12).getDes());
                        radioButton13.setTag(item.getItems().get(12).getId());
                        radioButton13.setVisibility(View.VISIBLE);
                        radioButton13.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line13.setVisibility(View.VISIBLE);
                    } else if (a == 13) {
                        radioButton14.setChecked(item.getItems().get(a).isCheck);

                        radioButton14.setText(item.getItems().get(13).getDes());
                        radioButton14.setTag(item.getItems().get(13).getId());
                        radioButton14.setVisibility(View.VISIBLE);
                        radioButton14.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line14.setVisibility(View.VISIBLE);
                    } else if (a == 14) {
                        radioButton15.setChecked(item.getItems().get(a).isCheck);
                        radioButton15.setText(item.getItems().get(14).getDes());
                        radioButton15.setTag(item.getItems().get(14).getId());
                        radioButton15.setVisibility(View.VISIBLE);
                        radioButton15.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                radioButtonL(item, position, num);
                            }
                        });
                        line15.setVisibility(View.VISIBLE);
                    }
                }

            }

        } else if ("02".equals(code)) {
            holder.getView(R.id.mLdx).setVisibility(View.GONE);
            holder.getView(R.id.mLrx).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.mL3).setVisibility(View.GONE);

            if (item.getItems() != null) {
                for (int a = 0; a < item.getItems().size(); a++) {
                    int num = a;
                    if (a == 0) {
                        cb_1.setChecked(item.getItems().get(a).isCheck);

                        cb_1.setText(item.getItems().get(0).getDes());
                        cb_1.setTag(item.getItems().get(0).getId());
                        cb_1.setVisibility(View.VISIBLE);

                        cb_1.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line20.setVisibility(View.VISIBLE);
                    } else if (a == 1) {
                        cb_2.setChecked(item.getItems().get(a).isCheck);

                        cb_2.setText(item.getItems().get(1).getDes());
                        cb_2.setTag(item.getItems().get(1).getId());
                        cb_2.setVisibility(View.VISIBLE);

                        cb_2.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line21.setVisibility(View.VISIBLE);
                    } else if (a == 2) {
                        cb_3.setChecked(item.getItems().get(a).isCheck);

                        cb_3.setText(item.getItems().get(2).getDes());
                        cb_3.setTag(item.getItems().get(2).getId());
                        cb_3.setVisibility(View.VISIBLE);
                        cb_3.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line22.setVisibility(View.VISIBLE);
                    } else if (a == 3) {
                        cb_4.setChecked(item.getItems().get(a).isCheck);

                        cb_4.setText(item.getItems().get(3).getDes());
                        cb_4.setTag(item.getItems().get(3).getId());
                        cb_4.setVisibility(View.VISIBLE);
                        cb_4.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line23.setVisibility(View.VISIBLE);
                    } else if (a == 4) {
                        cb_5.setChecked(item.getItems().get(a).isCheck);

                        cb_5.setText(item.getItems().get(4).getDes());
                        cb_5.setTag(item.getItems().get(4).getId());
                        cb_5.setVisibility(View.VISIBLE);
                        cb_5.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line24.setVisibility(View.VISIBLE);
                    } else if (a == 5) {
                        cb_6.setChecked(item.getItems().get(a).isCheck);

                        cb_6.setText(item.getItems().get(5).getDes());
                        cb_6.setTag(item.getItems().get(5).getId());
                        cb_6.setVisibility(View.VISIBLE);
                        cb_6.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line25.setVisibility(View.VISIBLE);
                    } else if (a == 6) {
                        cb_7.setChecked(item.getItems().get(a).isCheck);

                        cb_7.setText(item.getItems().get(6).getDes());
                        cb_7.setTag(item.getItems().get(6).getId());
                        cb_7.setVisibility(View.VISIBLE);
                        cb_7.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line26.setVisibility(View.VISIBLE);
                    } else if (a == 7) {
                        cb_8.setChecked(item.getItems().get(a).isCheck);


                        cb_8.setText(item.getItems().get(7).getDes());
                        cb_8.setTag(item.getItems().get(7).getId());
                        cb_8.setVisibility(View.VISIBLE);
                        cb_8.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line27.setVisibility(View.VISIBLE);
                    } else if (a == 8) {
                        cb_9.setChecked(item.getItems().get(a).isCheck);

                        cb_9.setText(item.getItems().get(8).getDes());
                        cb_9.setTag(item.getItems().get(8).getId());
                        cb_9.setVisibility(View.VISIBLE);
                        cb_9.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line28.setVisibility(View.VISIBLE);
                    } else if (a == 9) {
                        cb_10.setChecked(item.getItems().get(a).isCheck);


                        cb_10.setText(item.getItems().get(9).getDes());
                        cb_10.setTag(item.getItems().get(9).getId());
                        cb_10.setVisibility(View.VISIBLE);
                        cb_10.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line29.setVisibility(View.VISIBLE);
                    } else if (a == 10) {
                        cb_11.setChecked(item.getItems().get(a).isCheck);


                        cb_11.setText(item.getItems().get(10).getDes());
                        cb_11.setTag(item.getItems().get(10).getId());
                        cb_11.setVisibility(View.VISIBLE);
                        cb_11.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line30.setVisibility(View.VISIBLE);
                    } else if (a == 11) {
                        cb_12.setChecked(item.getItems().get(a).isCheck);

                        cb_12.setText(item.getItems().get(11).getDes());
                        cb_12.setTag(item.getItems().get(11).getId());
                        cb_12.setVisibility(View.VISIBLE);
                        cb_12.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line31.setVisibility(View.VISIBLE);
                    } else if (a == 12) {
                        cb_13.setChecked(item.getItems().get(a).isCheck);

                        cb_13.setText(item.getItems().get(12).getDes());
                        cb_13.setTag(item.getItems().get(12).getId());
                        cb_13.setVisibility(View.VISIBLE);
                        cb_13.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line32.setVisibility(View.VISIBLE);
                    } else if (a == 13) {
                        cb_14.setChecked(item.getItems().get(a).isCheck);

                        cb_14.setText(item.getItems().get(13).getDes());
                        cb_14.setTag(item.getItems().get(13).getId());
                        cb_14.setVisibility(View.VISIBLE);

                        cb_14.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line33.setVisibility(View.VISIBLE);
                    } else if (a == 14) {
                        cb_15.setChecked(item.getItems().get(a).isCheck);

                        cb_15.setText(item.getItems().get(14).getDes());
                        cb_15.setTag(item.getItems().get(14).getId());
                        cb_15.setVisibility(View.VISIBLE);
                        cb_15.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                boolean isChecked=!item.getItems().get(num).isCheck;
                                checkBoxL(item, position, num, isChecked);
                            }
                        });
                        line34.setVisibility(View.VISIBLE);
                    }
                }

            }


        } else if ("03".equals(code)) {
            holder.getView(R.id.mLdx).setVisibility(View.GONE);
            holder.getView(R.id.mLrx).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL3).setVisibility(View.GONE);
            EditText mEditText = (EditText) holder.getView(R.id.edXYDM);

            if (mEditText.getTag() != null && mEditText.getTag() instanceof TextWatcher) {
                mEditText.removeTextChangedListener((TextWatcher) mEditText.getTag());
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
                    getData().get(position).inputContent = s.toString();

                }
            };
            mEditText.addTextChangedListener(textWatcher);
            mEditText.setTag(textWatcher);
            mEditText.setText(getData().get(position).inputContent );
        } else if ("04".equals(code)) {
            holder.getView(R.id.mLdx).setVisibility(View.GONE);
            holder.getView(R.id.mLrx).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.mL3).setVisibility(View.VISIBLE);
            EditText mEditText = (EditText) holder.getView(R.id.clue_content);

            if (mEditText.getTag() != null && mEditText.getTag() instanceof TextWatcher) {
                mEditText.removeTextChangedListener((TextWatcher) mEditText.getTag());
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
                    getData().get(position).inputContent = s.toString();

                }
            };
            mEditText.addTextChangedListener(textWatcher);
            mEditText.setTag(textWatcher);
            mEditText.setText(getData().get(position).inputContent );
        }


    }


    public interface OnZxwjDetailClickListener {
        void onClick(String questionId, int position, int cj, boolean isShowNext, String type);

    }

    private OnZxwjDetailClickListener mClickListener;

    public void setmClickListener(OnZxwjDetailClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


    private void radioButtonL(final SurveyDetailBean.Question1Bean item, final int position, final int pos) {
        boolean isto = false;
        boolean isSel = false;
        for (int a = 0; a < item.getItems().size(); a++) {
            if (item.getItems().get(a).isCheck) {
                isSel = true;
            }
        }
        for (int a = 0; a < item.getItems().size(); a++) {
            item.getItems().get(a).isCheck = false;
            if (item.getItems().get(a).isIsShowNext()) {
                isto = true;
            }

        }
        if (item.getItems() != null && item.getItems().size() >= (pos + 1)) {
            Log.e("TAG", "-------radioButtonL---pos--" + pos);
            item.getItems().get(pos).isCheck = true;
             if(!isSel && !item.getItems().get(pos).isIsShowNext()){
                 Handler handler = new Handler();
                 final Runnable r = new Runnable() {
                     @Override
                     public void run() {
                         notifyDataSetChanged();
                     }
                 };
                 handler.post(r);


             }else{
                 if (isto) {
                     if (mClickListener != null) {
                         mClickListener.onClick(item.getId(), position, item.cj, item.getItems().get(pos).isIsShowNext(), item.getType());
                     }
                 } else {
                     Handler handler = new Handler();
                     final Runnable r = new Runnable() {
                         @Override
                         public void run() {
                             notifyDataSetChanged();
                         }
                     };
                     handler.post(r);
                 }

             }


        }

    }


    private void checkBoxL(final SurveyDetailBean.Question1Bean item, final int position, final int pos, final boolean isChecked) {

        if (item.getItems() != null && item.getItems().size() >= (pos + 1)) {
            Log.e("TAG", "-------checkBoxL---pos--" + pos);

            boolean isSel = false;
            boolean isto = false;
            for (int a = 0; a < item.getItems().size(); a++) {
                if (item.getItems().get(a).isCheck) {
                    isSel = true;
                }
                if (item.getItems().get(a).isIsShowNext()) {
                    isto = true;
                }
            }

            item.getItems().get(pos).isCheck = isChecked;
            //  notifyDataSetChanged();

            if(!isSel && !item.getItems().get(pos).isIsShowNext()){
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                };
                handler.post(r);
            }else{
                if (isto) {
                    if (mClickListener != null) {
                        mClickListener.onClick(item.getId(), position, item.cj, item.getItems().get(pos).isIsShowNext(), item.getType());
                    }
                }else{
                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    };
                    handler.post(r);
                }


            }



        }
    }
}