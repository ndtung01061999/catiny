package com.regitiny.catiny.tools.utils;

import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtils
{

  private static UserRepository userRepository = null;

  public static final User thisUser()
  {
    return SecurityUtils.getCurrentUserLogin().flatMap(userLogin -> userRepository.findOneByLogin(userLogin)).orElse(null);
  }

  public static final User getUserById(Long id)
  {
    return userRepository.findById(id).orElse(null);
  }

  @Autowired
  private void setUserRepository(UserRepository userRepository)
  {
    UserUtils.userRepository = userRepository;
  }
}
