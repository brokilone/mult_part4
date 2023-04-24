package notification;


public class NotificationDemo {
  public static void main(String[] args) {
    NotificationSystem system = new NotificationSystem();

    NotificationListener userListener = new EmailNotificationListener("alice@gmail.com");
    system.registerListener(userListener);
    NotificationListener systemListener = new FileLogNotificationListener("log.txt");
    system.registerListener(systemListener);

    system.sendNotification(new Notification("Новое сообщение!", "Проверьте свой почтовый ящик."));
    system.sendNotification(new Notification("Новый заказ!", "Новый заказ был размещен на вашей странице."));

    system.unregisterListener(userListener);

    system.sendNotification(new Notification("Обновление приложения!", "У нас есть новое обновление для вас."));

    System.out.println("Список слушателей: " + system.getListeners());


  }
}
