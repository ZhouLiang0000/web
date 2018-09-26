package com.zl.spring.annotationaop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zl.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪一个配置文件
@ContextConfiguration("classpath:com/zl/spring/annotationaop/applicationContext.xml")
public class DemoTest {
	@Resource(name = "userService")
	private UserService userService;

	@Test
	public void userTest() {
		userService.delete();
	}
}
