package latch;

import java.util.concurrent.CountDownLatch;

public class PreparationRunnable implements Runnable {

    private final InterviewPart interviewPart;
    private final CountDownLatch countDownLatch;

    public PreparationRunnable(InterviewPart interviewPart, CountDownLatch countDownLatch) {
      this.interviewPart = interviewPart;
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      System.out.println("Изучаем  тему '" + interviewPart.getName() + "'");
      try {
        Thread.sleep((long) (Math.random() * 3000));
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        e.printStackTrace();
      }
      System.out.println(interviewPart.getName() + " - изучено");
      countDownLatch.countDown();
    }

}
