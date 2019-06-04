package io.github.hcoona;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {
  @GET
  public List<Student> get() {
    return Arrays.asList(get(1), get(2));
  }

  @GET
  @Path("/{id}")
  public Student get(@PathParam("id") long id) {
    return new Student().setId(id).setName("Shuai");
  }
}
