package collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionsDemo {

  public static void main(String[] args) throws InterruptedException {
    List<Integer> list = new ArrayList<>();
    ProducerThread[] producers = new ProducerThread[20];

    for (int i = 0; i < 20; i++) {
      producers[i] = new ProducerThread(list);
    }

    for (ProducerThread producer : producers) {
      producer.start();
    }

    for (ProducerThread producer : producers) {
      producer.join();
    }

    System.out.println(list);
  }
}
