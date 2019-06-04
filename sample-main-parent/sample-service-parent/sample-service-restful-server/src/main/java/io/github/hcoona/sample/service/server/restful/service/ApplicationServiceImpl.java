package io.github.hcoona.sample.service.server.restful.service;

import io.github.hcoona.sample.service.api.ApplicationService;
import io.github.hcoona.sample.service.api.v1.Application;
import io.github.hcoona.sample.service.repository.ApplicationRepository;
import io.github.hcoona.sample.service.server.restful.util.ProtoV1Utils;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
  @Inject ApplicationRepository applicationRepository;

  @Override
  public List<Application> list() {
    return applicationRepository.findAll().stream()
        .map(ProtoV1Utils::convertFromJpaModel)
        .collect(Collectors.toList());
  }

  @Override
  public Application get(String name) {
    if (name.startsWith("applications/")) {
      String applicationId = name.substring("applications/".length());
      return applicationRepository.findOptionalByApplicationId(applicationId)
          .map(ProtoV1Utils::convertFromJpaModel)
          .orElse(null);
    } else {
      throw new IllegalArgumentException("name must starts with 'applications/'");
    }
  }

  @Override
  public Application create(Application application) {
    io.github.hcoona.sample.service.model.Application jpaApplication =
        applicationRepository
            .findOptionalByApplicationId(application.getApplicationId())
            .orElseGet(
                () -> {
                  io.github.hcoona.sample.service.model.Application app =
                      ProtoV1Utils.convertToJpaModel(application);
                  applicationRepository.save(app);
                  return app;
                });
    return ProtoV1Utils.convertFromJpaModel(jpaApplication);
  }
}
