package pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executor = Executors.newCachedThreadPool();

    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("поток " + Thread.currentThread().getName() + " выполняет запрос к ресурсу 1");
      return "response from api 1";
    }, executor);

    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
      System.out.println("поток " + Thread.currentThread().getName() + " выполняет запрос к ресурсу 2");
      return "response from api 2";
    }, executor);

    CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
      System.out.println("поток " + Thread.currentThread().getName() + " высчитывает конечный результат");
      return result1 + " and " + result2;
    });

    System.out.println(combinedFuture.get());

    executor.shutdown();
  }
}

