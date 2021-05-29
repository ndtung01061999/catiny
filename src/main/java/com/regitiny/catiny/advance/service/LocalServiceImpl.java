package com.regitiny.catiny.advance.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Log4j2
@Component
public class LocalServiceImpl<S, QS> implements LocalService<S, QS>
{
  @Autowired
  protected ApplicationContext applicationContext;

  protected S localService;
  protected QS localQueryService;

  @Override
  public S LocalService()
  {
    var thisName = getClass().getSimpleName();
    if (Objects.isNull(localService))
    {
      var serviceName = thisName.replace("AdvanceServiceImpl", "ServiceImpl");
      var matcher =Pattern.compile("^[A-Z]+").matcher(serviceName);
      matcher.find();
      var firstUpper =  matcher.group(0);
      serviceName=serviceName.replaceFirst("^[A-Z]+",firstUpper.toLowerCase() );
      var bean = applicationContext.getBean(serviceName);
      localService = (S) bean;
    }
    else
    {
      log.debug("not found LocalService");
    }
    return localService;
  }

  @Override
  public QS LocalQueryService()
  {
    var thisName = getClass().getSimpleName();
    if (Objects.isNull(localService))
    {
      var serviceName = thisName.replace("AdvanceServiceImpl", "QueryService");
      var matcher =Pattern.compile("^[A-Z]+").matcher(serviceName);
      matcher.find();
      var firstUpper =  matcher.group(0);
      serviceName=serviceName.replaceFirst("^[A-Z]+",firstUpper.toLowerCase() );
      var bean = applicationContext.getBean(serviceName);
      localQueryService = (QS) bean;
    }
    else
    {
      log.debug("not found LocalService");
    }
    return localQueryService;
  }
}
