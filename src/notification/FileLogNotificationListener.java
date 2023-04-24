package notification;

public class FileLogNotificationListener implements NotificationListener {
  private final String logFile;

  public FileLogNotificationListener(String logFile) {
    this.logFile = logFile;
  }

  @Override
  public void onNotificationReceived(Notification notification) {

    System.out.println("Запись в файл " + logFile + " выполнена: " + notification);
  }
}
