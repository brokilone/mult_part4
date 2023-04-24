package latch;

import java.util.concurrent.CountDownLatch;

public class Developer implements Runnable{

    private final CountDownLatch countDownLatch;

    public Developer(CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      System.out.println("Готовлюсь к интервью...");
      try {
        countDownLatch.await();
        System.out.println("Готов решать любые задачи!");
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        e.printStackTrace();
      }
    }

}
