package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Resource {
  private Object data = "data";
  private final ReadWriteLock lock = new ReentrantReadWriteLock();

  public Object read() {
    lock.readLock().lock();
    try {
      System.out.println("Reading data... Thread: " + Thread.currentThread().getName());
      Thread.sleep(3000L);
      System.out.println("Reading is finished. Thread: " + Thread.currentThread().getName());
      return data;
    } catch (InterruptedException e) {
      e.printStackTrace();
      return null;
    } finally {
      lock.readLock().unlock();
    }
  }

  public void write(Object newData){
    System.out.println("Try to write... Thread: " + Thread.currentThread().getName());
    lock.writeLock().lock();
    try {
      System.out.println("I am in writing section! "+ Thread.currentThread().getName());
      Thread.sleep(3000L);
      data = newData;
      System.out.println("Writing is finished. Thread: " + Thread.currentThread().getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.writeLock().unlock();
    }
  }
}
