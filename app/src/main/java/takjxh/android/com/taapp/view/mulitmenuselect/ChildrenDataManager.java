package takjxh.android.com.taapp.view.mulitmenuselect;

import android.content.Context;

import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-06 15:54
 * @Description:
 **/
public class ChildrenDataManager {

    public static String START_ID="08b2cd9e614d11eab3ca00ff0bcb9432";

   /* public enum DictType {
        POSITION_FUNCTION("hy.txt");

        String fileName;
        private DictType(String fileName){
            this.fileName = fileName;
        }
        public String getFileName(){
            return fileName;
        }
    }*/

    private static ChildrenDataManager mInstance;

    private ChildrenDataManager() {

    }

    public static ChildrenDataManager getInstance() {
        if (mInstance == null) {
            mInstance = new ChildrenDataManager();
        }
        return mInstance;
    }

    public List<Children> getTripleColumnData(List<Children> treeItemBeanList, String flag) {
        return ChildrenUtil.getPositions( flag, treeItemBeanList);
    }


  /*  public List<Children> getTripleColumnData(Context mContext, String flag) {
        return ChildrenUtil.getPositions(mContext, flag, ChildrenDataManager.DictType.POSITION_FUNCTION.getFileName());
    }*/
}
