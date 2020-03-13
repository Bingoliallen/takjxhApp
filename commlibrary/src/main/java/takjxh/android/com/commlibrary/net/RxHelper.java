package takjxh.android.com.commlibrary.net;

import android.util.Log;

import com.google.gson.Gson;

import takjxh.android.com.commlibrary.net.response.CommonResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class RxHelper {

  private RxHelper() {
  }

  /**
   * rxJava 线程设置
   */
  public static <T> Observable.Transformer<T, T> io_main() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * 将java对象转换成json字符串
   *
   * @param bean
   * @return
   */

  public static void beanToJson(Object bean) {
    try {
      Gson gson = new Gson();
      String jsonStr = gson.toJson(bean);
   //   System.out.println(jsonStr);
      Log.e("LBB","-------------返回结果："+jsonStr) ;
    }catch (Exception e){
      e.printStackTrace();
      Log.e("LBB","-------------返回结果解析异常："+e.getMessage()) ;
    }

  }




  /**
   * 返回元素
   */
  public static class Response2DataFunc<T> implements Func1<CommonResponse<T>, T> {

    @Override
    public T call(CommonResponse<T> response) {
      if(response!=null){
        beanToJson(response);
      }
      if (response == null) {
        throw new ApiException(ResponseCode.RESPONSE_NULL, "response is null");
      } else if (ResponseCode.SUCCESS == response.resCode) {
        return response.data;
      } else {
        throw new ApiException(response.resCode, response.resDes);
      }
    }
  }

  /**
   * 返回元素
   */
/*  public static class Response2DataFuncRESTful<T> implements Func1<Response<T>, T> {

    @Override
    public T call(Response<T> response) {
      if (0 == response.code()) {
        return response.body();
      } else {
        Converter<ResponseBody, ErrorResponse> errorConverter = BaseAppProfile.app_client.retrofit
            .responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        ErrorResponse error = null;
        try {
          error = errorConverter.convert(response.errorBody());
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (error == null) {
          throw new ApiException(response.code(),
              new ErrorResponse(ResponseCode.RESPONSE_NULL, "error response is null"));
        } else {
          throw new ApiException(response.code(), error);
        }
      }
    }
  }*/
}

