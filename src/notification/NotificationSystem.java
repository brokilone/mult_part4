package notification;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationSystem {
  private final List<NotificationListener> listeners = new CopyOnWriteArrayList<>();

  public void registerListener(NotificationListener listener) {
    listeners.add(listener);
  }

  public void unregisterListener(NotificationListener listener) {
    listeners.remove(listener);
  }

  public void sendNotification(Notification notification) {
    for (NotificationListener listener : listeners) {
      listener.onNotificationReceived(notification);
    }
  }

  public List<NotificationListener> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

}
