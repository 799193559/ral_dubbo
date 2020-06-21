package golden.serviceimpl;

import golden.dao.userdao;
import golden.model.User;
import golden.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("userservice")
public class userserviceimpl implements userservice {
  @Autowired
  userdao userdao;
  
  public void insertuser(User nuser) {
    this.userdao.insertuser(nuser);
  }
  
  public User selectuser(User nuser) {
    return this.userdao.selectuser(nuser);
  }
  
  @Cacheable(value="aboutUser", key="'user'+#id")
  @Override
  public User selectbyAccount(User nuser) {
    return this.userdao.selectbyAccount(nuser);
  }
  
  @Cacheable(value="aboutUser", key="'user'+#id")
  @Override
  public User selectUserById(Integer id) {
    return this.userdao.selectbyid(id);
  }
}
