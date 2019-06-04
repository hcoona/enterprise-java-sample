package io.github.hcoona.sample.service.repository;

import io.github.hcoona.sample.service.SampleServiceDatabase;
import io.github.hcoona.sample.service.SampleServiceEntityManagerResolver;
import io.github.hcoona.sample.service.model.Application;
import java.util.Optional;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerConfig;

@Repository
@EntityManagerConfig(
    entityManagerResolver = SampleServiceEntityManagerResolver.class,
    qualifier = SampleServiceDatabase.class)
public interface ApplicationRepository extends EntityRepository<Application, Long> {

  Application findByApplicationId(String applicationId);

  Optional<Application> findOptionalByApplicationId(String applicationId);
}
