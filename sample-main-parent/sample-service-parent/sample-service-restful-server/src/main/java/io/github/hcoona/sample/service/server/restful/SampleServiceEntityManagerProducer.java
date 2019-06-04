package io.github.hcoona.sample.service.server.restful;

import io.github.hcoona.sample.service.SampleServiceDatabase;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

@ApplicationScoped
public class SampleServiceEntityManagerProducer {

  private static final String PERSISTENCE_UNIT_NAME = "io.github.hcoona.sample.service";

  @Produces
  @SampleServiceDatabase
  @ApplicationScoped
  EntityManagerFactory produceEntityManagerFactory(BeanManager beanManager) {
    Map<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.bean.manager", beanManager);
    return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
  }

  void disposeEntityManagerFactory(
      @Disposes @SampleServiceDatabase EntityManagerFactory entityManagerFactory) {
    if (entityManagerFactory.isOpen()) {
      entityManagerFactory.close();
    }
  }

  @Produces
  @SampleServiceDatabase
  @TransactionScoped
  EntityManager produceEntityManager(
      @SampleServiceDatabase EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }

  void disposeEntityManager(@Disposes @SampleServiceDatabase EntityManager entityManager) {
    if (entityManager.isOpen()) {
      entityManager.close();
    }
  }

  @Produces
  @Default
  EntityManager produceTransactionalEntityManager(
      @SampleServiceDatabase EntityManager entityManager) {
    return entityManager;
  }
}
