package takjxh.android.com.taapp.view.mulitmenuselect;

import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 8:42
 * @Description:
 **/
public class ContributeDataManager {

    public static final String START_ID="0000";

    private static ContributeDataManager mInstance;

    private ContributeDataManager() {

    }

    public static ContributeDataManager getInstance() {
        if (mInstance == null) {
            mInstance = new ContributeDataManager();
        }
        return mInstance;
    }

    public List<Contribute> getTripleColumnData(List<Contribute> treeItemBeanList, String flag) {
        return ContributeUtil.getPositions(flag, treeItemBeanList);
    }
}
