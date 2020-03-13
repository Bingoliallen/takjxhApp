package takjxh.android.com.commlibrary.net;



public class ResponseCode {

 public static final int RESPONSE_NULL = 0;
  public static final int SUCCESS = 1;                  //成功
/*  public static final int LOGIC_ERROR = 500;              //业务逻辑错误*/
  public static final int AUTH_ERROR =-1;               //未登录或者认证失败，返回登录页面

  private ResponseCode() {
    /* cannot be instantiated */
    throw new UnsupportedOperationException("cannot be instantiated");
  }
}
