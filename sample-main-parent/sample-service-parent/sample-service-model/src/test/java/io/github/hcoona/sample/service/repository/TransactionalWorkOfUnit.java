package io.github.hcoona.sample.service.repository;

import io.github.hcoona.sample.service.model.Application;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Transactional
class TransactionalWorkOfUnit {
  @Inject
  ApplicationRepository applicationRepository;

  void CreateApplication(Application application) {
    applicationRepository.save(application);
  }
}
