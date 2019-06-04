package io.github.hcoona.sample.service.service;

import javax.enterprise.context.Dependent;

@Dependent
public class HelloService {
  public String getString() {
    return "Hello World!";
  }
}
