package latch;

public enum InterviewPart {
  DATA_STRUCTURES("Структуры данных"),
  RECURSION("Рекурсия"),
  TREE_TRAVERSAL("Обход дерева"),
  BREADTH_FIRST_SEARCH("Поиск в ширину"),
  DEPTH_FIRST_SEARCH("Поиск в глубину");

  private final String name;

  InterviewPart(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
