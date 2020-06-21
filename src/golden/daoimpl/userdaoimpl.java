package golden.daoimpl;

import golden.dao.userdao;
import golden.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class userdaoimpl extends SqlSessionDaoSupport implements userdao {
  @Autowired
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    super.setSqlSessionFactory(sqlSessionFactory);
  }
  
  String ns = "golden.mapper.UserMapper.";
  
  public void insertuser(User nuser) {
    getSqlSession().insert(String.valueOf(this.ns) + "insert", nuser);
  }
  
  public User selectuser(User nuser) {
    return (User)getSqlSession().selectOne(String.valueOf(this.ns) + "selectByaccountAndpassword", nuser);
  }
  
  public User selectbyAccount(User nuser) {
    return (User)getSqlSession().selectOne(String.valueOf(this.ns) + "selectbyaccount", nuser);
  }
  
  

  public User selectbyid(Integer valueOf) {
    return (User)getSqlSession().selectOne(String.valueOf(this.ns) + "selectByPrimaryKey", valueOf);
  }
}
