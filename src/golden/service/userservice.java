package golden.service;

import org.springframework.cache.annotation.Cacheable;

import golden.model.User;

public interface userservice {
  void insertuser(User paramUser);
  
  User selectuser(User paramUser);
  
  User selectbyAccount(User paramUser);
  

  User selectUserById(Integer paramInteger);
}
