package takjxh.android.com.taapp.view.mulitmenuselect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-06 15:30
 * @Description:
 **/
public class ChildrenUtil {

    public static List<Children> position;
    public static HashMap<String, ArrayList<Children>>  positions;

    public static  List<Children> itemsList;

    public static List<Children> getPositions(String flag, List<Children> treeItemBeanList) {
        if (position == null) {
            initPositions(treeItemBeanList);
        }
        if (flag.equals(ChildrenDataManager.START_ID)) {
            return position;
        } else {
            return positions.get(flag);
        }
    }

    private static void initPositions(List<Children> treeItemBeanList) {

        position = new ArrayList<Children>();
        positions = new HashMap<String, ArrayList<Children>>();
        for (int i = 0; i < treeItemBeanList.size(); i++) {
            Children item = treeItemBeanList.get(i);
            if (item.getParentId().equals(ChildrenDataManager.START_ID) ) {
                position.add(item);
            } else {
                if (positions.get(item.getParentId()) == null) {
                    ArrayList<Children> temp = new ArrayList<Children>();
                    temp.add(item);
                    positions.put(item.getParentId(), temp);
                } else {
                    positions.get(item.getParentId()).add(item);
                }
            }
            if(item.getChildren()!=null){
                getItem(item.getChildren());
            }
        }
    }

    private static void getItem(List<Children> data ){
        for (int i = 0; i < data.size(); i++) {
            Children item = data.get(i);
            if (positions.get(item.getParentId()) == null) {
                ArrayList<Children> temp = new ArrayList<Children>();
                temp.add(item);
                positions.put(item.getParentId(), temp);
            } else {
                positions.get(item.getParentId()).add(item);
            }
            if(item.getChildren()!=null){
                getItem(item.getChildren());
            }
        }
    }

    /**
     * 获取所有可以选择的子项
     */
    public static List<Children> getSelList(List<Children> treeItemBeanList) {
        if (itemsList == null) {
            initSelList(treeItemBeanList);
        }
        return itemsList;
    }

    private static void initSelList(List<Children> treeItemBeanList){

        itemsList = new ArrayList<Children>();
        getSelItems(treeItemBeanList);

    }


    private static void getSelItems(List<Children> data ){

        for(int i = 0; i < data.size(); i++){
            Children item = data.get(i);
            if(item.getChildren()!=null &&item.getChildren().size()>0){
                getSelItems(item.getChildren());
            }else{
                itemsList.add(item);
            }
        }
    }




}
