package golden.controller;

import com.alibaba.fastjson.JSONObject;

import com.auth0.jwt.exceptions.TokenExpiredException;

import golden.model.User;
import golden.service.userservice;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import golden.redis.*;

@Controller
@RequestMapping({"/user"})
public class TUserController {
  @Autowired
  userservice nuserservice;
  
  @Resource
  private RedisTemplate<String, byte[]> redisTemplate;

  
  @RequestMapping({"/login"})
  @ResponseBody
  public JSONObject login(@RequestBody String data, String token, HttpServletRequest head, HttpServletResponse response) throws NoSuchAlgorithmException {
    String message;
    int code;
    String account = null, password = null;
    JSONObject result = new JSONObject();
    User nuser = new User();
    JSONObject json1 = JSONObject.parseObject(data);
    account = (String)json1.get("account");
    password = (String)json1.get("password");
    if (account == null) {
      message = "account loss";
      code = -2;
      result.put("code", Integer.valueOf(code));
      result.put("message", message);
      return result;
    } 
    
    if (password == null) {
    	message = "password loss";
        code = -3;
        result.put("code", Integer.valueOf(code));
        result.put("message", message);
        return result;
      } 
      
    nuser.setAccount(account);
    if (password != "null") {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte[] b = md.digest();
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < b.length; offset++) {
        int i = b[offset];
        if (i < 0)
          i += 256; 
        if (i < 16)
          buf.append("0"); 
        buf.append(Integer.toHexString(i));
      } 
      nuser.setPassword(buf.toString());
    } 
    User user_1 = this.nuserservice.selectuser(nuser);
    if (this.nuserservice.selectuser(nuser) != null) {
      message = "success";
      code = 1;
      token_1 token_2 = new token_1();
      token = token_2.getToken(true, user_1.getAccount(), user_1.getUsername());
      response.addCookie(new Cookie("token", token));
      result.put("account", nuser.getAccount());
      result.put("message", message);
      result.put("code", Integer.valueOf(code));
      result.put("token", token);
    } else if (this.nuserservice.selectbyAccount(nuser) == null) {
      message = "account doesn't exit";
      code = -1;
    } else {
      message = "password error";
      code = 0;
    } 
    result.put("message", message);
    result.put("code", Integer.valueOf(code));
    return result;
  }
  
  @RequestMapping({"/register"})
  @ResponseBody
  public JSONObject toregister(@RequestBody String data) throws NoSuchAlgorithmException {
    JSONObject result = new JSONObject();
    String account = null, password = null, username = null;
    User nuser = new User();
    JSONObject json1 = JSONObject.parseObject(data);
    account = (String)json1.get("account");
    password = (String)json1.get("password");
    username = (String)json1.get("username");
    if (account == null ) {
      String str = "account loss";
      int i = -2;
      result.put("code", Integer.valueOf(i));
      result.put("message", str);
      return result;
    } 
    
    if (password == null) {
    	String str = "password loss";
        int i = -3;
        result.put("code", Integer.valueOf(i));
        result.put("message", str);
        return result;
      } 
    
    
    if (username == null) {
    	String str = "username loss";
        int i = -4;
        result.put("code", Integer.valueOf(i));
        result.put("message", str);
        return result;
      } 
    
    nuser.setAccount(account);
    nuser.setPassword(password);
    nuser.setUsername(username);
    
    if (this.nuserservice.selectbyAccount(nuser) != null) {
      String str = "account exits";
      int i = 0;
      result.put("message", str);
      result.put("code", Integer.valueOf(i));
      return result;
    } 
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(nuser.getPassword().getBytes());
    byte[] b = md.digest();
    StringBuffer buf = new StringBuffer("");
    for (int offset = 0; offset < b.length; offset++) {
      int i = b[offset];
      if (i < 0)
        i += 256; 
      if (i < 16)
        buf.append("0"); 
      buf.append(Integer.toHexString(i));
    } 
    nuser.setPassword(buf.toString());
    this.nuserservice.insertuser(nuser);
    if (this.nuserservice.selectuser(nuser) != null) {
      String str = "success";
      int i = 1;
      result.put("message", str);
      result.put("code", Integer.valueOf(i));
      return result;
    } 
    String message = "failure";
    int code = -1;
    result.put("message", message);
    result.put("code", Integer.valueOf(code));
    return result;
  }
  
  @RequestMapping({"/multilanguage"})
  @ResponseBody
  public JSONObject tomultilanguage(@RequestBody String json1) throws IOException {
    	JSONObject result = new JSONObject();
    	JSONObject temp = JSONObject.parseObject(json1);
    	String language = (String)temp.get("language");
        String text = (String)temp.get("text");
        if (language == null ) {
            String str = "language loss";
            int i = -1;
            result.put("code", Integer.valueOf(i));
            result.put("message", str);
            return result;
          } 
        
        if (text == null ) {
            String str = "text loss";
            int i = -2;
            result.put("code", Integer.valueOf(i));
            result.put("message", str);
            return result;
          }
        
        okhttp Okhttp = new okhttp();
        String response = Okhttp.postRequest("http://122.51.39.164:3000/api/multilanguage", json1.toString());
        result = JSONObject.parseObject(response);
        return result;      
  }
  
  @RequestMapping({"/redistest"})
  @ResponseBody
  public JSONObject redistest(Integer id) throws IOException {
    	JSONObject result = new JSONObject();
    	User suser=new User();
		suser=this.nuserservice.selectUserById(id);
        result.put("user", suser);
        return result; 
//        String account;
//        JSONObject json_1 = JSONObject.parseObject(json);//以字符串的形式解析json
//        account = (String)json_1.get("account");
//        Integer id = (Integer)json_1.get("id");
//        User suser_=new User();
//        suser_.setAccount(account);
//        User suser_1 = new User();
//        suser_1=null;
//        try{
//        	suser_1=(User)SerializeUtil.unserialize(redisTemplate.opsForValue().get(account));
//            System.out.print(suser_1);    
//        }
//        catch (IllegalArgumentException e){
//        	System.out.println("null");
//        }         
//            if(suser_1==null) {
//    			suser_.setId(suser.getId());
//    			suser_.setUsername(suser.getUsername());
//    			suser_.setPassword(suser.getPassword());
//    			System.out.print(suser_);
//    			byte[] info=SerializeUtil.serialize(suser);
//    			System.out.print(info);
//        		redisTemplate.opsForValue().set(account, info, 1, TimeUnit.HOURS); 
//            }
//            else {
//            	result.put("user_", suser_1);
//             	return result;
//            }
//}
}
}
