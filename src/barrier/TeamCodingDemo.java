package barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TeamCodingDemo {

  private static final int TEAM_SIZE = 4;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(TEAM_SIZE);
    CyclicBarrier barrier = new CyclicBarrier(TEAM_SIZE, new TeamLeader());

    for (int i = 0; i < TEAM_SIZE; i++) {
      executor.submit(new TeamMember(barrier));
    }

    executor.shutdown();
  }
}

class TeamMember implements Runnable {
  private final CyclicBarrier barrier;

  public TeamMember(CyclicBarrier barrier) {
    this.barrier = barrier;
  }

  public void run() {
    try {
      System.out.println(Thread.currentThread().getName() + " начал выполнение задачи");
      Thread.sleep((long) (Math.random() * 3000));
      System.out.println(Thread.currentThread().getName() + " завершил выполнение задачи и ожидает других членов команды");
      barrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}

class TeamLeader implements Runnable {
  public void run() {
    System.out.println("Все члены команды закончили задачи, теперь начинаем обработку результатов");
  }
}
