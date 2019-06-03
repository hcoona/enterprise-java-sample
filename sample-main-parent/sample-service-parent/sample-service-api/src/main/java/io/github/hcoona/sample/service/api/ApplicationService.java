package io.github.hcoona.sample.service.api;

import io.github.hcoona.sample.service.api.v1.Application;
import org.apiguardian.api.API;

import java.util.List;

@API(status = API.Status.EXPERIMENTAL)
public interface ApplicationService {
  @API(status = API.Status.EXPERIMENTAL)
  List<Application> list();

  @API(status = API.Status.EXPERIMENTAL)
  Application get(String name);
}
