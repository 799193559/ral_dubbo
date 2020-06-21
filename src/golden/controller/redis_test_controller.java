package golden.controller;


import javax.annotation.Resource;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import golden.model.User;
import golden.service.*;
 
import redis.clients.jedis.Jedis;
 
@Controller
@RequestMapping("/user")
public class redis_test_controller {
    private static Logger log=LoggerFactory.getLogger(redis_test_controller.class);
    
   
    //����
    @Resource
    private RedisTemplate redisTemplate;
    
	@RequestMapping("/testIndex")
	@ResponseBody
	public void testIndex() {
		 System.out.println("��");
         //��redsi�����ֵ
		 redisTemplate.opsForValue().set("f", "����������redis��");
		 //��ӡȡֵ
		 System.err.println("redis���"+redisTemplate.opsForValue().get("f"));
	}
}
