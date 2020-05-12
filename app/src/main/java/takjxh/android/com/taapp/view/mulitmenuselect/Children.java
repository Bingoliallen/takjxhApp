package takjxh.android.com.taapp.view.mulitmenuselect;

import java.util.List;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-06 15:24
 * @Description:
 **/
public class Children {


    /**
     * children : []
     * id : 148a49bc7d2511ea9aec00163e123f46
     * name : 外贸业
     * open : false
     * parentId : 08b2cd9e614d11eab3ca00ff0bcb9432
     */

    private String id;
    private String name;
    private boolean open;
    private String parentId;
    private List<Children> children;

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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
}
