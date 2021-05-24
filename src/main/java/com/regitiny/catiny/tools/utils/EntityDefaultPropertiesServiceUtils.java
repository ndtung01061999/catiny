package com.regitiny.catiny.tools.utils;

import com.regitiny.catiny.security.AuthoritiesConstants;
import com.regitiny.catiny.security.SecurityUtils;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;

/**
 * author : @yuvytung
 * this class use to set basic properties before create or update entity
 * use for Entity , EntityDTO
 * example: uuid , role , createdDate , modifiedDate , createdBy , modifiedBy
 */
@Log4j2
public class EntityDefaultPropertiesServiceUtils {

  private EntityDefaultPropertiesServiceUtils() {
    throw new IllegalStateException("EntityDefaultPropertiesServiceUtils class");
  }

  public static Object setPropertiesBeforeCreate(Object object) {
    log.debug("class name of Object = {}", object.getClass().getName());
    try {
      Instant now = Instant.now();
      String thisUser = SecurityUtils.getCurrentUserLogin().orElse(null);

      object.getClass().getDeclaredMethod("setRole", String.class).invoke(object, AuthoritiesConstants.MANAGEMENT);
      object.getClass().getDeclaredMethod("setUuid", UUID.class).invoke(object, UUID.randomUUID());
      object.getClass().getDeclaredMethod("setCreatedDate", now.getClass()).invoke(object, now);
      object.getClass().getDeclaredMethod("setModifiedDate", now.getClass()).invoke(object, now);
      if (Objects.nonNull(thisUser)) {
        object.getClass().getDeclaredMethod("setCreatedBy", thisUser.getClass()).invoke(object, thisUser);
        object.getClass().getDeclaredMethod("setModifiedBy", thisUser.getClass()).invoke(object, thisUser);
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return object;
  }

  public static Object setPropertiesBeforeUpdate(Object object) {
    log.debug("class name of Object = {}", object.getClass().getName());
    try {
      Instant now = Instant.now();
      String thisUser = SecurityUtils.getCurrentUserLogin().orElse(null);

      object.getClass().getDeclaredMethod("setModifiedDate", now.getClass()).invoke(object, now);
      if (Objects.nonNull(thisUser)) object.getClass().getDeclaredMethod("setModifiedBy", thisUser.getClass()).invoke(object, thisUser);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return object;
  }

  public static Object setPropertiesBeforeSave(Object object) {
    try {
      Long id = (Long) object.getClass().getDeclaredMethod("getId").invoke(object);
      log.debug("id = {}", id);
      if (id == null) return setPropertiesBeforeCreate(object); else return setPropertiesBeforeUpdate(object);
    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      log.warn(e);
      return object;
    }
  }
}
