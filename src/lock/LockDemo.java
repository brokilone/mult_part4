package lock;

public class LockDemo {
  public static void main(String[] args) {
    Resource resource = new Resource();
    new Thread(resource::read).start();
    new Thread(resource::read).start();
    new Thread(resource::read).start();
    new Thread(() -> resource.write("data2")).start();
    new Thread(() -> resource.write("data3")).start();

  }
}
