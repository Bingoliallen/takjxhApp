package takjxh.android.com.taapp.activityui.bean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 21:32
 * @Description:
 **/
public class ContributeDetial {


    /**
     * id : 369fd2bef64c11e9954d6caf29bd3cf5
     * title : 人民日报钟声：中美良性互动多多益善
     * cover : /word/media/image1.png
     * video : null
     * type : 0104
     * content : <p style="">教育督导新系统测试地址</p>
     * createUser : 张三
     * time : 2019-10-25 16:17:39
     */

    private String id;
    private String title;
    private String cover;
    private Object video;
    private String type;
    private String content;
    private String createUser;
    private String time;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
