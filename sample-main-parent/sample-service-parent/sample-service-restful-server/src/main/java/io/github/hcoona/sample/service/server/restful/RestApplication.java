package io.github.hcoona.sample.service.server.restful;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
  private static final Set<Class<?>> resourceClasses;

  static {
    try {
      resourceClasses =
          Stream.concat(
                  Stream.of(OpenApiResource.class, AcceptHeaderOpenApiResource.class),
                  ClassPath.from(Thread.currentThread().getContextClassLoader())
                      .getTopLevelClassesRecursive(RestApplication.class.getPackage().getName())
                      .stream()
                      .map(ClassInfo::load)
                      .filter(c -> c.isAnnotationPresent(Path.class)))
              .collect(Collectors.toSet());
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  @Override
  public Set<Class<?>> getClasses() {
    return resourceClasses;
  }
}
