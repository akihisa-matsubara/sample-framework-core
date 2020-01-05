package jp.co.sample.framework.jsf.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * ログ出力PhaseListener.
 */
@Slf4j
public class LoggingPhaseListener implements PhaseListener {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Phase前処理.
   *
   * @param event {@link PhaseEvent}
   */
  @Override
  public void beforePhase(PhaseEvent event) {
    log.debug("<-------- " + StringUtils.rightPad(event.getPhaseId().toString(), 25)  + " - Before Phase -------->");
  }

  /**
   * Phase後処理.
   *
   * @param event {@link PhaseEvent}
   */
  @Override
  public void afterPhase(PhaseEvent event) {
    log.debug("<-------- " + StringUtils.rightPad(event.getPhaseId().toString(), 25)  + " - After Phase  -------->");
  }

  /**
   * PhaseIdを取得します.
   *
   * @return PhaseId
   */
  @Override
  public PhaseId getPhaseId() {
    return PhaseId.ANY_PHASE;
  }

}
