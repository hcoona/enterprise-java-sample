package io.github.hcoona.sample.service.server.restful;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
class LoggerProducer {
  @Produces
  Logger produce(InjectionPoint injectionPoint) {
    return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
  }
}
