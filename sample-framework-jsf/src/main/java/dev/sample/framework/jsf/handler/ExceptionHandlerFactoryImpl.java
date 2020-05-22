package dev.sample.framework.jsf.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * ExceptionHandlerFactory実装.
 */
public class ExceptionHandlerFactoryImpl extends ExceptionHandlerFactory {

  /** ExceptionHandlerFactory. */
  private final ExceptionHandlerFactory factory;

  /**
   * コンストラクタ.
   *
   * @param factory factory
   */
  public ExceptionHandlerFactoryImpl(ExceptionHandlerFactory factory) {
    this.factory = factory;
  }

  /**
   * ExceptionHandlerを取得します.
   *
   * @return {@link ExceptionHandler}
   */
  @Override
  public ExceptionHandler getExceptionHandler() {
    return new ExceptionHandlerWrpperImpl(factory.getExceptionHandler());
  }

}
