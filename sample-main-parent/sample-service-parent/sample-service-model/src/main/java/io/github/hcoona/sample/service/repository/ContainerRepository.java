package io.github.hcoona.sample.service.repository;

import io.github.hcoona.sample.service.SampleServiceDatabase;
import io.github.hcoona.sample.service.SampleServiceEntityManagerResolver;
import io.github.hcoona.sample.service.model.Container;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerConfig;

@Repository
@EntityManagerConfig(
    entityManagerResolver = SampleServiceEntityManagerResolver.class,
    qualifier = SampleServiceDatabase.class
)
public interface ContainerRepository extends EntityRepository<Container, Long> {

  Container findByContainerId(String containerId);
}
