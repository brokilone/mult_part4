package semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
  public static void main(String[] args) {
    Semaphore bookSemaphore = new Semaphore(3);

    OfficeWorker worker1 = new OfficeWorker(bookSemaphore);
    OfficeWorker worker2 = new OfficeWorker(bookSemaphore);
    OfficeWorker worker3 = new OfficeWorker(bookSemaphore);
    OfficeWorker worker4 = new OfficeWorker(bookSemaphore);

    Thread thread1 = new Thread(worker1, "Анна Петрова");
    Thread thread2 = new Thread(worker2, "Василий Васильков");
    Thread thread3 = new Thread(worker3, "Алексей Иванов");
    Thread thread4 = new Thread(worker4, "Петр Афанасьев");

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
  }
}

class OfficeWorker implements Runnable {
  private final Semaphore bookSemaphore;

  public OfficeWorker(Semaphore bookSemaphore) {
    this.bookSemaphore = bookSemaphore;
  }

  public void run() {
    try {
      bookSemaphore.acquire();
      System.out.println("Сотрудник " + Thread.currentThread().getName() + " берет книгу");
      Thread.sleep(2000);
      System.out.println("Сотрудник " + Thread.currentThread().getName() + " возвращает книгу");
      bookSemaphore.release();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
