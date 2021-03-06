package com.ai.web.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * C3P0工具类获取DataSource和Connection对象
 */
public class C3P0Utils {
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
