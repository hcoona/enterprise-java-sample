package io.github.hcoona.sample.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerResolver;

@Dependent
public class SampleServiceEntityManagerResolver implements EntityManagerResolver {

  @Inject @SampleServiceDatabase Provider<EntityManager> entityManagerProvider;

  @Override
  public EntityManager resolveEntityManager() {
    return entityManagerProvider.get();
  }
}
