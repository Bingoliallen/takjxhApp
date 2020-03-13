package takjxh.android.com.commlibrary.net.response;


public class ErrorResponse {

  /**
   * err_code   错误码
   * err_msg    错误原因
   */
  public int code;
  public String msg;


  public ErrorResponse(int err_code, String err_msg) {
    this.code = err_code;
    this.msg = err_msg;
  }
}
