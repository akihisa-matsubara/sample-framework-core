package dev.sample.framework.rest.pres.resource;

import dev.sample.framework.core.code.ResultVo;
import dev.sample.framework.rest.pres.dto.ResponseBaseDto;

/**
 * <PRE>
 * リソース基底インターフェース.
 * APIのURLベースパスやAPI内で指定する書式フォーマットについては、
 * {@link SampleApplication}参照してください.
 * </PRE>
 */
public interface ResourceBase {

  /**
   * ResponseDtoを作成します.
   *
   * @return ResponseDto
   */
  @SuppressWarnings("rawtypes")
  public static ResponseBaseDto createResponse() {
    return createResponse(null);
  }

  /**
   * ResponseDtoを作成します.
   *
   * @param <T> response
   * @param response response
   * @return ResponseDto
   */
  @SuppressWarnings("rawtypes")
  public static <T> ResponseBaseDto createResponse(T response) {
    return ResponseBaseDto.<T>builder()
        .result(ResultVo.SUCCESS.getDecode())
        .response(response)
        .build();
  }

}
