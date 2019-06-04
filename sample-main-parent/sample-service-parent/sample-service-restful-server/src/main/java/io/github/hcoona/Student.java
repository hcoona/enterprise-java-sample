package io.github.hcoona;

public class Student {
  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public Student setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Student setName(String name) {
    this.name = name;
    return this;
  }
}
