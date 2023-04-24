package notification;

public class EmailNotificationListener implements NotificationListener {
  private String email;

  public EmailNotificationListener(String email) {
    this.email = email;
  }

  @Override
  public void onNotificationReceived(Notification notification) {
    System.out.println("Email на адрес " + email + " отправлен: " + notification);
  }
}
