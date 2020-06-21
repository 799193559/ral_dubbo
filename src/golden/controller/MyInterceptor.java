package golden.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import golden.model.User;
import golden.service.userservice;
import golden.controller.deToken_;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor{
  @Resource
  userservice userService;
  
  public void setUserService(userservice userService) {
    this.userService = userService;
  }
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception,TokenExpiredException {
    Cookie[] cookies = request.getCookies();
    String token = null;
    if (cookies != null) {
      byte b;
      int i;
      Cookie[] arrayOfCookie;
      for (i = (arrayOfCookie = cookies).length, b = 0; b < i; ) {
        Cookie cookie = arrayOfCookie[b];
        if (cookie.getName().equals("token"))
          token = cookie.getValue(); 
        b++;
      } 
    } 
    if (token != null) {
      deToken_ detoken = new deToken_();
      DecodedJWT jwt = null;
      jwt = detoken.deToken(token);
      if (jwt == null)
        return false; 
      jwt = JWT.decode(token);
      String account = jwt.getClaim("account").asString();
      User user_1 = new User();
      user_1.setAccount(account);
      User user_2 = this.userService.selectbyAccount(user_1);
      if (user_2 == null) {
        System.out.print("account don't exit");
        return false;
      } 
      String name_1 = user_2.getUsername();
      String name_2 = jwt.getClaim("username").asString();
      if (name_1.equals(name_2))
        return true; 
      System.out.println("username don't match");
      return false;
    } 
    String temp = request.getHeader("Authorization");
    if (temp != null) {
      String[] str = temp.split(" ");
      token = str[1];
      deToken_ detoken = new deToken_();
      DecodedJWT jwt = null;
      jwt = detoken.deToken(token);
      if (jwt == null)
        return false; 
      jwt = JWT.decode(token);
      String account = jwt.getClaim("account").asString();
      User user_1 = new User();
      user_1.setUsername(account);
      User user_2 = this.userService.selectbyAccount(user_1);
      if (user_2 == null) {
        System.out.print("account don't exit");
        return false;
      } 
      String name_1 = user_2.getUsername();
      String name_2 = jwt.getClaim("username").asString();
      if (name_1.equals(name_2))
        return true; 
      System.out.println("username don't match");
      return false;
    } 
    return false;
  }
  
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {}
  
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {}
}
