package dev.sample.framework.rest.provider.exceptionmapper;

import dev.sample.framework.core.exception.ApplicationException;
import dev.sample.framework.core.util.MessageUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * アプリケーション基底例外Mapper.
 */
@Provider
@Slf4j
public class ApplicationExceptionMapper extends ExceptionMapperBase<ApplicationExceptionMapper, ApplicationException>
    implements ExceptionMapper<ApplicationException> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Response toResponse(ApplicationException exception) {
    log.error(ExceptionUtils.getStackTrace(exception));
    ERROR_LOGGER.error(ExceptionUtils.getStackTrace(exception));

    return super.toResponse(exception);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<ApplicationExceptionMapper> getClassType() {
    return ApplicationExceptionMapper.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<String> getErrors(ApplicationException exception) {
    return exception.getErrorList().stream()
        .map(MessageUtils::getErrorMessage)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getStatusCode(ApplicationException exception) {
    return Status.INTERNAL_SERVER_ERROR.getStatusCode();
  }

}
