package com.zl.spring.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//帮我们创建容器
import com.zl.spring.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪一个配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class DemoTest {
	@Resource(name = "user")
	private User user;

	@Test
	public void userTest() {
		System.out.println(user);
	}
}
