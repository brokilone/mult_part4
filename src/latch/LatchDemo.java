package latch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LatchDemo {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(InterviewPart.values().length);
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    executorService.submit(new Developer(countDownLatch));

    Arrays.stream(InterviewPart.values())
        .map(part -> new PreparationRunnable(part, countDownLatch))
        .forEach(executorService::submit);

    executorService.shutdown();
    executorService.awaitTermination(1L, TimeUnit.MINUTES);
  }
}

