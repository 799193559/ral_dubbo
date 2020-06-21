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
 * ������
 * @author ʷ**
 *
 * 2019��1��30��
 */

@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})  
public class TestMyBatis {
        //��־ ���û�еĿ��Բ���
	    private static Logger logger = Logger.getLogger(TestMyBatis.class);   
 
	    @Resource
	    private RedisTemplate redisTemplate;
	    @Test  
	    public void test1() {  
	        //�ȸ�redis���渳ֵ
	        redisTemplate.opsForValue().set("chen", "����redis");
            //����־���ӡ ��reids��ȡ��������
	        logger.info("value��"+redisTemplate.opsForValue().get("chen"));
	       //�ڿ���̨��ӡ��reids��ȡ��������
	        System.out.println("\n���\n"+redisTemplate.opsForValue().get("chen"));
	    }
}