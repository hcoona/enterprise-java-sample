package io.github.hcoona.sample.service.server.restful.filter;

import static io.github.hcoona.sample.service.server.restful.filter.Constants.REQUEST_ID;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebFilter(filterName = "ResponseIdFilter", urlPatterns = "/*")
public class ResponseIdFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Do nothing.
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    if (servletRequest instanceof HttpServletRequest
        && servletResponse instanceof HttpServletResponse) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      HttpServletResponseWrapper httpServletResponse =
          new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
      httpServletResponse.addHeader(REQUEST_ID, httpServletRequest.getHeader(REQUEST_ID));
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {
    // Do nothing.
  }
}
