package pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {


  public static void main(String[] args) throws Exception {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    FutureTask<String> futureTask = new FutureTask<>(new ExampleTask());

    executor.execute(futureTask);

    System.out.println("Main thread continues working...");


    String result = futureTask.get();
    System.out.println("Result of ExampleTask: " + result);

    executor.shutdown();
  }
}


class ExampleTask implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(5000);
    return "Hello, world!";
  }
}