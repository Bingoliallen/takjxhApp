package takjxh.android.com.taapp.view.mulitmenuselect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 8:42
 * @Description:
 **/
public class ContributeUtil {

    public static List<Contribute> position;
    public static HashMap<String, ArrayList<Contribute>> positions;

    public static  List<Contribute> itemsList;

    public static List<Contribute> getPositions(String flag, List<Contribute> treeItemBeanList) {
        if (position == null) {
            initPositions(treeItemBeanList);
        }
        if (flag.equals(ContributeDataManager.START_ID)) {
            return position;
        } else {
            return positions.get(flag);
        }
    }

    private static void initPositions(List<Contribute> treeItemBeanList) {
        position = new ArrayList<Contribute>();
        positions = new HashMap<String, ArrayList<Contribute>>();
        for (int i = 0; i < treeItemBeanList.size(); i++) {
            Contribute item = treeItemBeanList.get(i);
            if (item.getParentId().equals(ContributeDataManager.START_ID) ) {
                position.add(item);
            } else {
                if (positions.get(item.getParentId()) == null) {
                    ArrayList<Contribute> temp = new ArrayList<Contribute>();
                    temp.add(item);
                    positions.put(item.getParentId(), temp);
                } else {
                    positions.get(item.getParentId()).add(item);
                }
            }
            if(item.getSecondList()!=null){
                getItem(item.getSecondList());
            }
        }
    }

    private static void getItem(List<Contribute> data ){
        for (int i = 0; i < data.size(); i++) {
            Contribute item = data.get(i);
            if (positions.get(item.getParentId()) == null) {
                ArrayList<Contribute> temp = new ArrayList<Contribute>();
                temp.add(item);
                positions.put(item.getParentId(), temp);
            } else {
                positions.get(item.getParentId()).add(item);
            }
            if(item.getSecondList()!=null){
                getItem(item.getSecondList());
            }
        }
    }

    /**
     * 获取所有可以选择的子项
     */
    public static List<Contribute> getSelList(List<Contribute> treeItemBeanList) {
        if (itemsList == null) {
            initSelList(treeItemBeanList);
        }
        return itemsList;
    }

    private static void initSelList(List<Contribute> treeItemBeanList){

        itemsList = new ArrayList<Contribute>();
        getSelItems(treeItemBeanList);

    }


    private static void getSelItems(List<Contribute> data ){

        for(int i = 0; i < data.size(); i++){
            Contribute item = data.get(i);
            if(item.getSecondList()!=null &&item.getSecondList().size()>0){
                getSelItems(item.getSecondList());
            }else{
                itemsList.add(item);
            }
        }
    }


}
