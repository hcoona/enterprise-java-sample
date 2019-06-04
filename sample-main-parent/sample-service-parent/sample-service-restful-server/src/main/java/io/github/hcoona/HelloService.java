package io.github.hcoona;

import javax.enterprise.context.Dependent;

@Dependent
public class HelloService {
  public String getString() {
    return "Hello World!";
  }
}
