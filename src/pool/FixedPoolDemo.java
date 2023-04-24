package pool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedPoolDemo {
  public static void main(String[] args) throws MalformedURLException, InterruptedException {

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    List<URL> imageUrls = Arrays.asList(
        new URL("https://example.com/image1.jpg"),
        new URL("https://example.com/image2.jpg"),
        new URL("https://example.com/image3.jpg"),
        new URL("https://example.com/image4.jpg")
    );
    for (URL url : imageUrls) {
      executorService.execute(() -> {
        // Загрузка и сохранение изображения
        saveImageFromUrl(url);
      });
    }
    executorService.shutdown();
    executorService.awaitTermination(1L, TimeUnit.MINUTES);
  }

  private static void saveImageFromUrl(URL url) {
    System.out.println("Сохраняю из " + url.toString() + ", поток " +
        Thread.currentThread().getName());
  }
}
