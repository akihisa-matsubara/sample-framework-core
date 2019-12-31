package jp.co.sample.framework.jsf.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ExceptionHandlerFactoryImpl extends ExceptionHandlerFactory {

  private final ExceptionHandlerFactory factory;

  /**
   * コンストラクタ.
   *
   * @param factory factory
   */
  public ExceptionHandlerFactoryImpl(ExceptionHandlerFactory factory) {
    this.factory = factory;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {
    return new ExceptionHandlerWrpperImpl(factory.getExceptionHandler());
  }

}
