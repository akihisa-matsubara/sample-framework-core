package jp.co.sample.framework.jsf.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class LoggingPhaseListener implements PhaseListener {

  private static final long serialVersionUID = 1L;

  @Override
  public void beforePhase(PhaseEvent event) {
    log.debug("<-------- " + StringUtils.rightPad(event.getPhaseId().toString(), 25)  + " - Before Phase -------->");
  }

  @Override
  public void afterPhase(PhaseEvent event) {
    log.debug("<-------- " + StringUtils.rightPad(event.getPhaseId().toString(), 25)  + " - After Phase  -------->");
  }

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.ANY_PHASE;
  }

}
