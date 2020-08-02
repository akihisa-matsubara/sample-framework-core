package dev.sample.framework.jsf.ui.upload;

/**
 * ファイルアップロード Callback.
 */
public interface FileUploadCallback {

  /**
   * ファイルアップロード後処理.
   *
   * @param parameterDto ファイルアップロードパラメーターDTO
   */
  void uploadedFile(FileUploadParameterDto parameterDto);

}
