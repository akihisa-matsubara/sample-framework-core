package jp.co.sample.framework.core.config;

import jp.co.sample.common.constant.Profile;
import jp.co.sample.framework.core.message.CoreMessageId;
import jp.co.sample.framework.core.util.MessageUtils;
import javax.faces.application.ProjectStage;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 有効なプロファイル.
 */
@UtilityClass
@Slf4j
public class ActiveProfile {

  /** Key値:active.profile. */
  private static final String KEY_ACTIVE_PROFILE = "active.profile";

  /** active profile (初期値はut).  */
  public static final String PROFILE;

  static {
    String activeProfile = System.getProperty(KEY_ACTIVE_PROFILE);
    PROFILE = StringUtils.isEmpty(activeProfile) ? Profile.UT : activeProfile;
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
      case Profile.IT:
        return ProjectStage.Development;

      default:
        return ProjectStage.Production;

    }
  }

}
