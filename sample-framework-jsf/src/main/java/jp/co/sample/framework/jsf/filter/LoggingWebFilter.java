package jp.co.sample.framework.jsf.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * ログ出力フィルター.
 */
@WebFilter(filterName = "loggingFilter", urlPatterns = "/*")
@Slf4j
public class LoggingWebFilter implements Filter {

  /**
   * 初期処理.
   *
   * @param filterConfig {@code FilterConfig}
   * @throws ServletException {@code ServletException}
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.trace("web filter init.");
  }

  /**
   * フィルタ処理.
   *
   * @param request {@code ServletRequest}
   * @param response {@code ServletResponse}
   * @param chain {@code FilterChain}
   * @throws IOException {@code IOException}
   * @throws ServletException {@code ServletException}
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.trace("doFilter start.");
    try {
      chain.doFilter(request, response);
    } finally {
      log.trace("doFilter end.");
    }
  }

  /**
   * 終了処理.
   */
  @Override
  public void destroy() {
    log.trace("web filter destroy.");
  }

}
