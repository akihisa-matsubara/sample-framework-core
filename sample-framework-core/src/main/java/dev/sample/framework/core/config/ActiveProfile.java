package dev.sample.framework.core.config;

import javax.faces.application.ProjectStage;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import dev.sample.common.constant.Profile;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.MessageUtils;

/**
 * 有効なプロファイル.
 */
@UtilityClass
@Slf4j
public class ActiveProfile {

  /** 有効なプロファイル.  */
  public static final String PROFILE;

  static {
    PROFILE = ConfigUtils.getActiveProfile();
    log.info(MessageUtils.getMessage(CoreMessageId.F0004I, PROFILE));
  }

  /**
   * Project Stageを取得します.
   *
   * @return Project Stage
   */
  public static ProjectStage toProjectStage() {
    switch (PROFILE) {
      case Profile.DEV:
      case Profile.UT:
      case Profile.UIT:
      case Profile.CI:
      case Profile.IT:
        return ProjectStage.Development;

      default:
        return ProjectStage.Production;

    }
  }

}
