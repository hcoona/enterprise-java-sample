package io.github.hcoona.sample.service.api;

import io.github.hcoona.sample.service.api.v1.Container;
import java.util.List;
import org.apiguardian.api.API;

@API(status = API.Status.EXPERIMENTAL)
public interface ContainerService {

  @API(status = API.Status.EXPERIMENTAL)
  List<Container> list(String parent);

  @API(status = API.Status.EXPERIMENTAL)
  Container get(String name);
}
