package dev.sample.framework.jsf.ui.upload;

import dev.sample.framework.core.config.ConfigUtils;
import dev.sample.framework.core.exception.dto.ErrorMessage;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.MessageUtils;
import dev.sample.framework.jsf.exception.UiComponentException;
import dev.sample.framework.jsf.ui.util.FileUploadUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * ファイル アップロード BackingBean.
 */
@ViewScoped
@Named
@Getter
@Slf4j
public class FileUploadBean implements Serializable {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** キー情報：許容ファイルサイズ. */
  private static final String KEY_LIMIT_SIZE = "framework.file.upload.limitSize";

  /** upload file map (key:clientId, value:upload file). */
  private Map<String, Part> uploadFileMap = new ConcurrentHashMap<>();

  /** upload file dto map (key:clientId, value:error message). */
  private Map<String, String> errMsgMap = new ConcurrentHashMap<>();

  /**
   * ファイル アップロード時のAjaxリスナー.
   *
   * @param callback ファイルアップロード Callback
   * @param key キー情報(clientId)
   */
  public void pushUploadFile(FileUploadCallback callback, String key) {
    errMsgMap.remove(key);
    Part uploadFile = uploadFileMap.remove(key);
    if (uploadFile == null) {
      throw new UiComponentException(new ErrorMessage(CoreMessageId.F1005E, key));
    }
    int limitSize = ConfigUtils.getAsInt(KEY_LIMIT_SIZE);
    if (uploadFile.getSize() > limitSize) {
      // エラーメッセージダイアログ用に設定して終了
      errMsgMap.put(key, MessageUtils.getMessage(CoreMessageId.F1006E, String.valueOf(limitSize), String.valueOf(uploadFile.getSize())));
      return;
    }
    if (uploadFile.getSize() == 0) {
      // キャンセル
      callback.cancelUploadFile(key);
      return;
    }

    FileUploadParameterDto dto = new FileUploadParameterDto();
    dto.setId(key);
    dto.setContentType(uploadFile.getContentType());
    dto.setFileName(uploadFile.getSubmittedFileName());
    dto.setFileContents(FileUploadUtils.toString(uploadFile));
    dto.setFileSize(uploadFile.getSize());

    log.debug("File uploaded successfully. {}", dto);

    // 主画面Callback呼び出し
    callback.uploadedFile(dto);
  }

}
