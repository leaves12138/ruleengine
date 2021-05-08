package io.inceptor.drl.util;


import java.util.concurrent.*;

public class SecondTimeWheel {
  static int started = 0;
  volatile static long currentMillisecond = 0L;
  static ScheduledExecutorService schedulor = Executors.newScheduledThreadPool(1);
  static Future future = null;

  synchronized
  public static void start() {
    started += 1;
    if (started == 1){
      currentMillisecond = System.currentTimeMillis();
      SecondTimeWheelTask.task = schedulor.scheduleAtFixedRate(SecondTimeWheelTask.instance, 0, 1, TimeUnit.SECONDS);
    }
  }

  synchronized
  public static void stop() {
    started -= 1;
    if (started == 0 && SecondTimeWheelTask.task != null) {
      SecondTimeWheelTask.task.cancel(true);
    }
    if (started < 0)
      started = 0;
  }

  public static long getMilliTime() {
    if (started <= 0) {
      throw new RuntimeException("time wheel has not started");
    }
    return currentMillisecond;
  }

  public static class SecondTimeWheelTask implements Runnable{
    public static SecondTimeWheelTask instance = new SecondTimeWheelTask();
    public static ScheduledFuture task = null;
    public void run() {
      SecondTimeWheel.currentMillisecond = System.currentTimeMillis();
    }
  }
}

