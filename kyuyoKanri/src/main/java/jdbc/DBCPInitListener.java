package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// web.xml에 정의해둔 파라미터 poolConfig 값을 가져옴
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		
		/* poolConfig 변수에 저장되는 값!??
		oracledriver=oracle.jdbc.driver.OracleDriver
  		Url=jdbc:oracle:thin:@localhost:1521/xe
  		User=system
    	Pass=1234
    	validationQuery=select 1
    	minIdle=3
    	maxTotal=30
    	poolName=board 
		 */
		
		// Properties를 생성
		Properties prop = new Properties();
		
		try {
			// poolConfig로부터 바이트 단위로 문자를 읽어 prop에 저장
			// Properties는 = 나 : 을 구분자로 삼아 키와 값을 나누어 저장한다.
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		// prop에 담긴 키가 oracledriver에 해당하는 값을 가져온다.
		// oracledriver=oracle.jdbc.driver.OracleDriver
		String driverClass = prop.getProperty("oracledriver");
		try {
			// OracleDriver 클래스의 이름을 통해 Class 객체를 생성
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}

	private void initConnectionPool(Properties prop) {
		try {
			// prop에 담긴 키가 Url, User, Pass인 값을 가져온다.
			// Url=jdbc:oracle:thin:@localhost:1521/xe
	  		// User=system
	  		// Pass=1234
			
			/*
    	minIdle=3
    	maxTotal=30
    	poolName=board */
			String Url = prop.getProperty("Url");
			String id = prop.getProperty("User");
			String pw = prop.getProperty("Pass");
			System.out.println(Url);
			System.out.println(id);
			System.out.println(pw);

			// 데이터베이스 연결을 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(Url, id, pw);

			// 재사용 가능한 커넥션을 생성
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			// prop에 담긴 키가 validationQuery인 값을 가져옴
			// validationQuery=select 1
			String validationQuery = prop.getProperty("validationQuery");
			// 커넥션이 유효한지 확인하는 간단한 쿼리
			if (validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			
			// 커넥션 풀의 설정을 정의
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// 일정시간 마다 비활성 연결을 제거하는 시간을 설정
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// 연결이 사용되지 않을 때도 유효성을 검사
			poolConfig.setTestWhileIdle(true);
			
			// prop에 키가 minIdle인 값을 가져옴, 없다면 5를 설정
			int minIdle = getIntProperty(prop, "minIdle", 5);
			// 최소 5개의 연결을 유지
			poolConfig.setMinIdle(minIdle);
			
			// prop에 키가 minIdle인 값을 가져옴, 없다면 50을 설정
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			// 최대 50개의 연결을 유지
			poolConfig.setMaxTotal(maxTotal);
			
			// 커넥션 풀을 생성
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory,
					poolConfig);
			poolableConnFactory.setPool(connectionPool);

			// 커넥션 풀을 JDBC 드라이버로 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

			String poolName = prop.getProperty("poolName");

			// 생성된 커넥션 풀의 이름을 설정
			driver.registerPool(poolName, connectionPool);
			System.out.println("Available pools: " + Arrays.toString(driver.getPoolNames()));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		// prop에 키가 propName 변수의 값과 같은 키의 값을 가져옴
		String value = prop.getProperty(propName);
		// 담긴 값이 없다면
		if (value == null)
			// 기본 값 설정
			return defaultValue;
		// 정수형으로 형변환 후 반환
		return Integer.parseInt(value);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
}
