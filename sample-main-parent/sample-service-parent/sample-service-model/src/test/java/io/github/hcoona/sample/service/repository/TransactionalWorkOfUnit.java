package io.github.hcoona.sample.service.repository;

import io.github.hcoona.sample.service.model.Application;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
class TransactionalWorkOfUnit {

  @Inject
  ApplicationRepository applicationRepository;

  void createApplication(Application application) {
    applicationRepository.save(application);
  }
}
