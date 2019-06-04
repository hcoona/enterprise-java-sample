package io.github.hcoona.sample.service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.hcoona.sample.service.model.Application;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.inject.Provider;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

@EnableWeld
class ModificationTest {

  @WeldSetup WeldInitiator weld = WeldInitiator.from(new Weld()).build();

  @Inject Provider<TransactionalWorkOfUnit> unitProvider;

  @Test
  void testSaveApplication() {
    Application application =
        new Application()
            .setApplicationId("application_1559116214688_0001")
            .setUser("hcoona")
            .setQueue("default")
            .setName("test_job_1_new")
            .setTrackingUrl("http://127.0.0.1:8060/cluster/app/application_1559116214688_0001")
            .setStartTime(LocalDateTime.parse("2019-05-29T16:16:34"))
            .setFinishTime(LocalDateTime.parse("2019-05-29T16:17:09"))
            .setApplicationType("DistributedShell");
    assertThat(application).matches(app -> app.getId() == 0);
    unitProvider.get().createApplication(application);
    assertThat(application).matches(app -> app.getId() != 0);
  }
}
