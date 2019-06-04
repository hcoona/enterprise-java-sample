package io.github.hcoona.sample.service.server.restful.controller;

import io.github.hcoona.sample.service.server.restful.service.HelloService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/hello")
@Produces({MediaType.APPLICATION_JSON})
public class HelloController {
  @Inject
  HelloService helloService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return helloService.getString();
  }
}
