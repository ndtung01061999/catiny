package com.regitiny.catiny.quick;

import com.regitiny.catiny.domain.Permission;


public class PermissionQuick
{
  private final Permission permission = new Permission();

  public PermissionBuilder quickBuild()
  {
    return new PermissionBuilder();
  }

  public Permission allTrue()
  {
    return permission.read(false)
      .write(true)
      .share(true)
      .add(true)
      .delete(true)
      .level(0);
  }

  public Permission allFalse()
  {
    return permission.read(false)
      .write(false)
      .share(false)
      .add(false)
      .delete(false)
      .level(Integer.MAX_VALUE);
  }

  public class PermissionBuilder
  {
    public PermissionBuilder read()
    {
      permission.read(true);
      return this;
    }

    public PermissionBuilder write()
    {
      permission.write(true);
      return this;
    }

    public PermissionBuilder share()
    {
      permission.share(true);
      return this;
    }

    public PermissionBuilder delete()
    {
      permission.delete(true);
      return this;
    }

    public PermissionBuilder add()
    {
      permission.add(true);
      return this;
    }

    public PermissionBuilder level()
    {
      permission.level(Integer.MAX_VALUE);
      return this;
    }

    public Permission build()
    {
      return permission;
    }
  }
}
