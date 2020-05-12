package takjxh.android.com.taapp.activityui.bean;

import java.util.List;
import java.util.Map;

import takjxh.android.com.taapp.activityui.base.BaseComResponse;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-07 14:35
 * @Description:
 **/
public class PolicyapplyaddList<T> extends BaseComResponse {


    /**
     * typeId : 4da0df026bff11ea9aec00163e123f46
     * sysTradeName : 电力供应
     * userTradeId : 04e990a66cd111ea968b00ff0bcb9432
     */

   /* "": "2019厦门市民营小微企业扶持",
            "tradeId": "9d7effa46bfb11ea9aec00163e123f46",
            "": "文物及非物质文化遗产保护",
            "": "37e06c698f5e11ea9653e86a6477f339",*/




    public String typeId;
  /*  public String typeName;
    public String des;
   */

    public String sysTradeName;
    public String tradeId;
    public String applyId;
    public String userTradeId;

    public Map<String, List<PolicyapplyaddBean>> itemMap;


}
