package dev.sample.framework.rest.provider;

import dev.sample.framework.core.mapper.JsonObjectMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Jsonpプロバイダー.
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JsonpProvider extends JacksonJsonProvider {

  /**
   * デフォルトコンストラクタ.
   */
  public JsonpProvider() {
    setMapper(JsonObjectMapper.create());
  }

}
