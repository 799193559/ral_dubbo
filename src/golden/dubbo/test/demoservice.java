package golden.dubbo.test;

import com.alibaba.dubbo.config.annotation.Service;

@Service(interfaceName="golden.dubbo.test.demoservice")
public interface demoservice {
	public String sayHello(String name); 
}
