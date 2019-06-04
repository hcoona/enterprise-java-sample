package io.github.hcoona.sample.service.api;

import io.github.hcoona.sample.service.api.v1.Application;
import java.util.List;
import org.apiguardian.api.API;

@API(status = API.Status.EXPERIMENTAL)
public interface ApplicationService {

  @API(status = API.Status.EXPERIMENTAL)
  List<Application> list();

  @API(status = API.Status.EXPERIMENTAL)
  Application get(String name);

  @API(status = API.Status.EXPERIMENTAL)
  Application create(Application application);
}
