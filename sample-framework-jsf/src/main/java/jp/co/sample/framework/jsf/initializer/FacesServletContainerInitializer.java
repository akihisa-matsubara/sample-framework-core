package jp.co.sample.framework.jsf.initializer;

import jp.co.sample.framework.core.config.ActiveProfile;
import jp.co.sample.framework.jsf.constant.FacesConstant;
import java.util.Set;
import javax.faces.application.ProjectStage;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * FacesServletコンテナのイニシャライザー.
 */
public class FacesServletContainerInitializer implements ServletContainerInitializer {

  /**
   * <PRE>
   * 起動処理.
   * 以下の環境固有のContextパラメータを設定
   * ・javax.faces.PROJECT_STAGE
   * ・javax.faces.FACELETS_REFRESH_PERIOD
   * </PRE>
   *
   * @param c HandlesTypesアノテーションで指定されたクラスタイプで拡張、実装、またはアノテーションされたクラスのセット
   * @param ctx ServletContext
   */
  @Override
  public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
    // Contextパラメータを設定
    ProjectStage projectStage = ActiveProfile.toProjectStage();
    ctx.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, projectStage.toString());

    // 開発用設定
    if (ProjectStage.Development == projectStage) {
      ctx.setInitParameter(FacesConstant.FACELETS_REFRESH_PERIOD, "1");
    }
  }
}
