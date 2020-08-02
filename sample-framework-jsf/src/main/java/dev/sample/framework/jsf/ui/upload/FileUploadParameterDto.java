package dev.sample.framework.jsf.ui.upload;

import java.io.Serializable;
import lombok.Data;

/*
 * ファイルアップロードパラメーターDTO（外部公開DTO）.
 */
@Data
public class FileUploadParameterDto implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** id (clientId). */
  private String id;

  /** content type. */
  private String contentType;

  /** file name. */
  private String fileName;

  /** file contents. */
  private String fileContents;

  /** file size. */
  private long fileSize;

}
