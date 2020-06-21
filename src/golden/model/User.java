package golden.model;

import java.io.Serializable;

public class User implements Serializable{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private Integer id;
  
  private String account;
  
  private String password;
  
  private String username;
  
  public Integer getId() {
    return this.id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getAccount() {
    return this.account;
  }
  
  public void setAccount(String account) {
    this.account = account;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String name) {
    this.username = name;
  }
}
