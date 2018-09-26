package com.zl.spring.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//帮我们创建容器
import com.zl.spring.bean.User;
import com.zl.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪一个配置文件
@ContextConfiguration("classpath:com/zl/spring/aop/applicationContext.xml")
public class DemoTest {
	@Resource(name = "userService")
	private UserService userService;

	@Test
	public void userTest() {
		userService.save();
	}
}
