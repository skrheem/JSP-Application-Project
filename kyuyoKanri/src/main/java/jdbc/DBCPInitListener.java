package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnection;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolingDriver;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String poolConfig = sce.getServletContext().getInitParameter("poolConfig");

        Properties prop = new Properties();
        try {
            prop.load(new StringReader(poolConfig));
        } catch (IOException e) {
            throw new RuntimeException("config load fail", e);
        }

        loadJDBCDriver(prop);
        initConnectionPool(prop);
    }

    private void loadJDBCDriver(Properties prop) {
        String driverClass = prop.getProperty("oracledriver");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("fail to load JDBC Driver", e);
        }
    }

    private void initConnectionPool(Properties prop) {
        try {
            String url = prop.getProperty("Url");
            String id = prop.getProperty("User");
            String pw = prop.getProperty("Pass");

            ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, id, pw);
            PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);

            String validationQuery = prop.getProperty("validationQuery");
            if (validationQuery != null && !validationQuery.isEmpty()) {
                poolableConnFactory.setValidationQuery(validationQuery);
            }

            GenericObjectPoolConfig<PoolableConnection> poolConfig = new GenericObjectPoolConfig<>();
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
            poolConfig.setTestWhileIdle(true);

            int minIdle = getIntProperty(prop, "minIdle", 5);
            poolConfig.setMinIdle(minIdle);

            int maxTotal = getIntProperty(prop, "maxTotal", 50);
            poolConfig.setMaxTotal(maxTotal);

            GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
            poolableConnFactory.setPool(connectionPool);

            PoolingDriver driver = new PoolingDriver();
            String poolName = prop.getProperty("poolName");
            driver.registerPool(poolName, connectionPool);

            System.out.println("Available pools: " + Arrays.toString(driver.getPoolNames()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getIntProperty(Properties prop, String propName, int defaultValue) {
        String value = prop.getProperty(propName);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // contextDestroyed는 리소스 정리를 위해 필요에 따라 구현할 수 있습니다.
        // 여기에 데이터베이스 풀을 해제하는 등의 작업을 추가할 수 있습니다.
        
        // 예시: 등록된 풀 해제
        try {
            PoolingDriver driver = new PoolingDriver();
            String poolName = sce.getServletContext().getInitParameter("poolName");
            if (poolName != null) {
                driver.closePool(poolName);
                System.out.println("Connection pool [" + poolName + "] has been closed.");
            }
        } catch (Exception e) {
            System.err.println("Failed to close the connection pool: " + e.getMessage());
        }
    }
}
