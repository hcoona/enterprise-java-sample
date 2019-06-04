package io.github.hcoona.sample.service.server.restful.controller;

import io.github.hcoona.sample.service.api.ApplicationService;
import io.github.hcoona.sample.service.server.restful.model.Application;
import io.github.hcoona.sample.service.util.PresentationModelUtils;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Transactional
@Path("v1/applications")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ApplicationController {
  @Inject Provider<ApplicationService> applicationServiceProvider;

  /**
   * List all Applications.
   *
   * @return The Applications
   */
  @GET
  public List<Application> list() {
    ApplicationService applicationService = applicationServiceProvider.get();
    return applicationService.list().stream()
        .map(PresentationModelUtils::convertFromProto)
        .collect(Collectors.toList());
  }

  /**
   * Get the Application by its Id.
   *
   * @param applicationId The Application Id
   * @return The Application
   */
  @GET
  @Path("{applicationId}")
  public Application get(@PathParam("applicationId") String applicationId) {
    ApplicationService applicationService = applicationServiceProvider.get();
    return Optional
        .ofNullable(applicationService.get("applications/" + applicationId))
        .map(PresentationModelUtils::convertFromProto)
        .orElseThrow(NotFoundException::new);
  }

  /**
   * Create an Application.
   *
   * @param application The Application
   * @param uriInfo The request URI information
   * @return JAX-RS Response
   */
  @PUT
  public Response put(Application application, @Context UriInfo uriInfo) {
    ApplicationService applicationService = applicationServiceProvider.get();
    io.github.hcoona.sample.service.api.v1.Application createdApplication =
        applicationService.create(PresentationModelUtils.convertToProto(application));
    URI location =
        uriInfo.getAbsolutePathBuilder().path(createdApplication.getApplicationId()).build();
    return Response.created(location)
        .entity(PresentationModelUtils.convertFromProto(createdApplication))
        .build();
  }
}
