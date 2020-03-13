package takjxh.android.com.commlibrary.net;

import takjxh.android.com.commlibrary.net.response.ErrorResponse;


public class ApiException extends RuntimeException {

  public int code;
  public ErrorResponse errorResponse;
  public String error;
  public ApiException(int code, ErrorResponse errorResponse) {
    super(errorResponse.msg);
    this.error = errorResponse.msg;
    this.code = code;
    this.errorResponse = errorResponse;
  }

  public ApiException(int code, String error) {
    super(error);
    this.code = code;
    this.error = error;
  }
}