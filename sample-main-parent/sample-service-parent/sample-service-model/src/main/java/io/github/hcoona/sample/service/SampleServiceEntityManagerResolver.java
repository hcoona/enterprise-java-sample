package io.github.hcoona.sample.service;

import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerResolver;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

@Dependent
public class SampleServiceEntityManagerResolver implements EntityManagerResolver {
  @Inject
  @SampleServiceDatabase
  Provider<EntityManager> entityManagerProvider;

  @Override
  public EntityManager resolveEntityManager() {
    return entityManagerProvider.get();
  }
}
