package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.service.LocalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @param <S> EntityService
 * @param <Q> EntityQueryService
 */
@Log4j2
@Component
public class LocalServiceImpl<S, Q> implements LocalService<S, Q>
{
  protected S localService;
  protected Q localQueryService;
  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public S localService()
  {
    if (Objects.isNull(localService))
      setup();
    else
      log.warn("LocalService already exist");
    if (Objects.isNull(localQueryService))
      log.warn("LocalService really doesn't exist");
    return localService;
  }

  @Override
  public Q localQueryService()
  {
    if (Objects.isNull(localQueryService))
      setup();
    else
      log.warn("LocalQueryService already exist");
    if (Objects.isNull(localQueryService))
      log.warn("LocalQueryService really doesn't exist");
    return localQueryService;
  }

  public void setup()
  {
    var thisName = getClass().getSimpleName();
    var matcher = Pattern.compile("^[A-Z]+").matcher(thisName);
    matcher.find();
    var firstUpper = matcher.group(0);
    thisName = thisName.replaceFirst("^[A-Z]+", firstUpper.toLowerCase());

    if (Objects.isNull(localService))
    {
      var serviceName = thisName.replace("AdvanceServiceImpl", "QueryService");
      var bean = applicationContext.getBean(serviceName);
      localQueryService = (Q) bean;
    }
    else
    {
      log.debug("not found LocalQueryService");
    }

    if (Objects.isNull(localService))
    {
      var serviceName = thisName.replace("AdvanceServiceImpl", "ServiceImpl");
      var bean = applicationContext.getBean(serviceName);
      localService = (S) bean;
    }
    else
    {
      log.debug("not found LocalService");
    }
  }
}
