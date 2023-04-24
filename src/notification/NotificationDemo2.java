package notification;


import java.nio.charset.StandardCharsets;
import java.util.Random;

public class NotificationDemo2 {
  public static void main(String[] args) throws InterruptedException {
    NotificationSystem system = new NotificationSystem();


    String logFile = "log.txt";
    NotificationListener systemListener = new FileLogNotificationListener(logFile);
    system.registerListener(systemListener);
    System.out.println("Зарегистрирован слушатель " + logFile);

    Thread listenerRegistrar = new Thread(new ListenerRegistrar(system));
    Thread notificationGenerator = new Thread(new NotificationGenerator(system));

    listenerRegistrar.start();
    notificationGenerator.start();

    Thread.sleep(6000);

    listenerRegistrar.interrupt();

    system.unregisterListener(systemListener);
    System.out.println("Удален слушатель слушатель " + logFile);
    Thread.sleep(200);
    notificationGenerator.interrupt();

    System.out.println("Список слушателей: " + system.getListeners());


  }
}

class ListenerRegistrar implements Runnable {
  private final NotificationSystem system;
  private final Random random = new Random();

  public ListenerRegistrar(NotificationSystem system) {
    this.system = system;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      String email = random.nextInt(1000) + "@gmail.com";
      system.registerListener(new EmailNotificationListener(email));
      System.out.println("_______________________________");
      System.out.println("Зарегистрирован слушатель " + email);
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}


class NotificationGenerator implements Runnable {
  private final NotificationSystem system;
  private final Random random = new Random();

  public NotificationGenerator(NotificationSystem system) {
    this.system = system;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      byte[] array = new byte[7];
      random.nextBytes(array);
      Notification message = new Notification("New message", new String(array, StandardCharsets.UTF_8));
      system.sendNotification(message);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}