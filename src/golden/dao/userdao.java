package golden.dao;

import org.springframework.cache.annotation.Cacheable;

import golden.model.User;

public interface userdao {
  void insertuser(User paramUser);
  
  User selectuser(User paramUser);
  
  User selectbyAccount(User paramUser);
  
  
  User selectbyid(Integer paramInteger);
}
