package com.zl.spring.jdbctemplate;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DemoTest {
	@Test
	public void jdbcTest() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql:///web17");
		dataSource.setUser("zhouliang");
		dataSource.setPassword("zhouliang");
		JdbcTemplate jTemplate = new JdbcTemplate();
		jTemplate.setDataSource(dataSource);
		String sql = "insert into user values('zhaosi','23321','123321')";
		jTemplate.update(sql);
	}
}
