package takjxh.android.com.commlibrary.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class TimerTask<T extends TimerTask.CallBack> {

  //private Timer mTimer;
  private ScheduledExecutorService scheduledExecutorService = null;

  private MyHandler mHandler;
  private java.util.TimerTask mTask;
  private boolean mIsRunning = false;

  /**
   * 开启定时
   */
  public void startTimerTask(T reference, long delay, TimeUnit period) {
    if (mHandler == null) {
      mHandler = new MyHandler(reference);
    }
    /*  if (mTimer == null) {
      mTimer = new Timer();
      mTask = new java.util.TimerTask() {
        @Override
        public void run() {
          if (mHandler != null) {
            mHandler.sendMessage(Message.obtain());
          }
        }
      };
      mIsRunning = true;
      mTimer.schedule(mTask, delay, period);
    }*/
    if(scheduledExecutorService ==null){

      mTask = new java.util.TimerTask() {
        @Override
        public void run() {
          if (mHandler != null) {
            mHandler.sendMessage(Message.obtain());
          }
        }
      };
      mIsRunning = true;

      getScheduledExecutorService().schedule(mTask, delay, period);
    }

  }

  /**
   * 取消定时
   */
  public void stopTimerTask() {
   /* if (mTimer != null) {
      mTimer.cancel();
      mTask.cancel();
      mTimer.purge();
      mIsRunning = false;
      mTask = null;
      mTimer = null;
    }*/
    if (scheduledExecutorService != null) {

      mTask.cancel();
      mIsRunning = false;
      mTask = null;
      shutDownNowScheduledExecutor();
    }

  }

  public boolean isRunning() {
    return mIsRunning;
  }

  public interface CallBack {

    void call();
  }


  private static final int SCHE_THREAD_SIZE = 5;

  public ScheduledExecutorService getScheduledExecutorService() {

    if (!isScheduledServiceEnable()) {
      scheduledExecutorService = new ScheduledThreadPoolExecutor(SCHE_THREAD_SIZE);
    }

    return scheduledExecutorService;
  }

  public void shutDownScheduledExecutor() {
    if (!isScheduledServiceEnable()) {
      //     先前提交的任务将会被工作线程执行，新的线程将会被拒绝。这个方法
      //     不会等待提交的任务执行完，我们可以用awaitTermination来等待任务执行完。
      getScheduledExecutorService().shutdown();
    }
  }

  public void shutDownNowScheduledExecutor() {
    if (!isScheduledServiceEnable()) {
      getScheduledExecutorService().shutdownNow();
    }
  }

  private boolean isScheduledServiceEnable() {
    return !(scheduledExecutorService == null
            || scheduledExecutorService.isShutdown()
            || scheduledExecutorService.isTerminated());
  }




  private static class MyHandler<T extends CallBack> extends Handler {

    private WeakReference<T> reference;

    public MyHandler(T t) {
      reference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
      if (reference.get() != null) {
        reference.get().call();
      }
    }

  }
}
