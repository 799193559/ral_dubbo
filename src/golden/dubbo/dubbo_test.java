
package golden.dubbo;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

import golden.dubbo.test.demoservice;
  

  
  @Controller
  @RequestMapping({"/user"}) 
  public class dubbo_test {
	
	  @Autowired
	  private demoservice demoservice_1;
//	  = (demoservice) context.getBean("demoService");

  
  @RequestMapping({"/testdubbo"}) 
  @ResponseBody
  public  String testdubbo() { 
	  
//   ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"ApplicationContext-2.xml"});	  
   String str =demoservice_1.sayHello("consumer 1...");
   System.out.println(str); 
//   context.close();
   return str;
  }
  }
 