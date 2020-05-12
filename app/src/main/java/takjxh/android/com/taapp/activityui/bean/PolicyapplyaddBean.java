package takjxh.android.com.taapp.activityui.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-07 14:30
 * @Description:
 **/
public class PolicyapplyaddBean  implements MultiItemEntity {


    /**
     * id : b1cb25626c0111ea9aec00163e123f46
     * name : 贷款一：贷款合同签订时间
     * title : 贷款一基本信息
     * type : 06
     * ruleReqest : true
     * ruleText : null
     * ruleNumb : null
     * selectVal :
     * value :
     * fileName : null
     * syscode :
     * mark : 必须于2020.01.01~2020.5.31
     * template : null
     * minNum : null
     * maxNum : null
     * startTime : 1577808000000
     * endTime : 1590854400000
     */

    private String id;
    private String name;
    private String title;
    private String type;
    private boolean ruleReqest;
    private Object ruleText;
    private Object ruleNumb;
    private String selectVal;
    private String value;
    private Object fileName;
    private String syscode;
    private String mark;
    private Object template;
    private Object minNum;
    private Object maxNum;
    private Object startTime;
    private Object endTime;

   /* private String putKey="";

    public String getPutKey() {
        if(TextUtils.isEmpty()){

        }
        return putKey;
    }

    public void setPutKey(String putKey) {
        this.putKey = putKey;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRuleReqest() {
        return ruleReqest;
    }

    public void setRuleReqest(boolean ruleReqest) {
        this.ruleReqest = ruleReqest;
    }

    public Object getRuleText() {
        return ruleText;
    }

    public void setRuleText(Object ruleText) {
        this.ruleText = ruleText;
    }

    public Object getRuleNumb() {
        return ruleNumb;
    }

    public void setRuleNumb(Object ruleNumb) {
        this.ruleNumb = ruleNumb;
    }

    public String getSelectVal() {
        return selectVal;
    }

    public void setSelectVal(String selectVal) {
        this.selectVal = selectVal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Object getTemplate() {
        return template;
    }

    public void setTemplate(Object template) {
        this.template = template;
    }

    public Object getMinNum() {
        return minNum;
    }

    public void setMinNum(Object minNum) {
        this.minNum = minNum;
    }

    public Object getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Object maxNum) {
        this.maxNum = maxNum;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @Override
    public int getItemType() {
        if("01".equals(type)){
            return 1;
        }else if("02".equals(type)){
            return 2;
        }else if("03".equals(type)){
            return 3;
        }else if("04".equals(type)){
            return 4;
        }else if("05".equals(type)){
            return 5;
        }else if("06".equals(type)){
            return 6;
        }else if("07".equals(type)){
            return 7;
        }
        return 1;
    }
}
