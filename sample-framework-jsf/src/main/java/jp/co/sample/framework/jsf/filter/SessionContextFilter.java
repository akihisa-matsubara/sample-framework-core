package jp.co.sample.framework.jsf.filter;

import jp.co.sample.framework.core.config.ConfigUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

/**
 * セッション・コンテキストフィルター.
 */
@WebFilter(filterName = "sessionContextFilter", urlPatterns = "/*")
public class SessionContextFilter implements Filter {

  /** キー情報：セッションタイムアウトページ. */
  private static final String KEY_SESSION_TIMEOUT_PAGE = "sessionTimeoutPage";

  /**
   * 初期処理.
   *
   * @param filterConfig {@code FilterConfig}
   * @throws ServletException {@code ServletException}
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // nothing
  }

  /**
   * フィルタ処理.
   * リクエストを検証し、NGである場合はセッションタイムアウトページへ遷移させます.
   *
   * @param request {@code ServletRequest}
   * @param response {@code ServletResponse}
   * @param chain {@code FilterChain}
   * @throws IOException {@code IOException}
   * @throws ServletException {@code ServletException}
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    if (!isValid(httpRequest)) {
      // セッションタイムアウトページへリダイレクト
      ((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + ConfigUtils.getAsString(KEY_SESSION_TIMEOUT_PAGE));

    } else {
      chain.doFilter(request, response);

    }
  }

  /**
   * リクエストの検証.
   *
   * @param request {@code HttpServletRequest}
   * @return 有効なリクエストである場合はtrue
   */
  private boolean isValid(HttpServletRequest request) {
    // Getリクエスト または リクエスト内のセッションIDがまだ有効ならOK
    return isGetMethod(request) || request.isRequestedSessionIdValid();
  }

  /**
   * Getメソッドか判定します.
   *
   * @param request {@code HttpServletRequest}
   * @return Getメソッドである場合はtrue
   */
  private boolean isGetMethod(HttpServletRequest request) {
    return HttpMethod.GET.equals(request.getMethod());
  }

  /**
   * 終了処理.
   */
  @Override
  public void destroy() {
    // nothing
  }

}
