package golden.test_redis;


import javax.annotation.Resource;
 
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import golden.model.User;
/**
 * 测试类
 * @author 史**
 *
 * 2019年1月30日
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})  
public class TestMyBatis {
        //日志 如果没有的可以不加
	    private static Logger logger = Logger.getLogger(TestMyBatis.class);   
 
	    @Resource
	    private RedisTemplate redisTemplate;
	    @Test  
	    public void test1() {  
	        //先给redis里面赋值
	        redisTemplate.opsForValue().set("chen", "测试redis");
            //在日志里打印 从reids里取出的数据
	        logger.info("value："+redisTemplate.opsForValue().get("chen"));
	       //在控制台打印从reids里取出的数据
	        System.out.println("\n完毕\n"+redisTemplate.opsForValue().get("chen"));
	    }
}