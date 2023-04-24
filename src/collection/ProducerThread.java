package collection;

import java.util.List;

public class ProducerThread extends Thread {
  private final List<Integer> list;

  public ProducerThread(List<Integer> list) {
    this.list = list;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
        list.add(i);
    }
  }
}
