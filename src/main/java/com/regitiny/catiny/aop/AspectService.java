package com.regitiny.catiny.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Aspect for logging execution of service and repository Spring components.
 * <p>
 * By default, it only runs with the "dev" profile.
 */
@Aspect
public class AspectService
{
  private final Environment env;
  private final ApplicationContext applicationContext;

  public AspectService(Environment env, ApplicationContext applicationContext)
  {
    this.env = env;
    this.applicationContext = applicationContext;
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(
    " execution(* com.regitiny.catiny.repository.search.*.save(..))))"
  )
  public void crudRepositorySavePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * sau khi save thì dữ liệu hibernate lồng nhau bởi các relationship
   * với elasticsearch thì dữ liệu sẽ trở thành các jsonObject lồng nhau vô tận
   * nên tạm thời sử dụng method này dùng MapStruct để : Entity -> EntityDTO -> Entity
   *
   * @param joinPoint join point for advice.
   * @return result.
   * @throws Throwable throws {@link IllegalArgumentException}.
   */
  @Around("crudRepositorySavePointcut() ")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable
  {
    Logger log = logger(joinPoint);

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    try
    {
      var args = joinPoint.getArgs();
      if (args.length != 1)
        return joinPoint.proceed();
      var entity = args[0];
      var entityName = entity.getClass().getSimpleName();
      var mapper = applicationContext.getBean(StringUtils.uncapitalize(entityName) + "MapperImpl");// entityNameMapperImpl
      var entityDTO = mapper.getClass().getMethod("toDto", Class.forName("com.regitiny.catiny.domain." + entityName)).invoke(mapper, entity);
      var entityResult = mapper.getClass().getMethod("toEntity", Class.forName("com.regitiny.catiny.service.dto." + entityName + "DTO")).invoke(mapper, entityDTO);

      log.debug("original data : {} = ", entity);
      log.debug("processed data : {} = ", entityResult);
      Object result = joinPoint.proceed(new Object[]{entityResult});
      log.debug("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
      return result;
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }

  /**
   * Retrieves the {@link Logger} associated to the given {@link JoinPoint}.
   *
   * @param joinPoint join point we want the logger for.
   * @return {@link Logger} associated to the given {@link JoinPoint}.
   */
  private Logger logger(JoinPoint joinPoint)
  {
    return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
  }


}
