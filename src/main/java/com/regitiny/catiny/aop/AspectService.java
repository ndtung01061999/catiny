package com.regitiny.catiny.aop;

import com.regitiny.catiny.domain.MasterUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.UUID;

/**
 * Aspect for logging execution of service and repository Spring components.
 * <p>
 * By default, it only runs with the "dev" profile.
 */
@Aspect
public class AspectService
{

  private final Environment env;

  public AspectService(Environment env)
  {
    this.env = env;
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(
    " execution(* org.springframework.data.repository.CrudRepository.save(..))))"
  )
  public void crudRepositorySavePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Advice that logs when a method is entered and exited.
   *
   * @param joinPoint join point for advice.
   * @return result.
   * @throws Throwable throws {@link IllegalArgumentException}.
   */
  @Around("crudRepositorySavePointcut() && repositorySearchPackagePointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable
  {
    Logger log = logger(joinPoint);

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    try
    {
      var x = (MasterUser) joinPoint.getArgs()[0];
      x.uuid(UUID.randomUUID());
      Object result = joinPoint.proceed(new Object[]{x});

      log.debug("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
      if (result.getClass().getName().equals(MasterUser.class))
      {
        log.debug("hihi");
        var r = (MasterUser) result;
        log.debug(r.toString());
      }
      return result;
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }

  /**
   * Pointcut that matches all repository search packages.
   */
  @Pointcut(
    "within(com.regitiny.catiny.repository.search..*)"
  )
  public void repositorySearchPackagePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
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
