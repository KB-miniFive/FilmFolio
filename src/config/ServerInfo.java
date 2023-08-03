package config;
/*
 * 오라클 서버 정보를 메타데이터화 시킨 인터페이스
 * 인터페이스의 구성요소인 상수값을 활용해서 서버 정보를 등록
 * ~~Test1에서 코드에 들어있는 서버정보를 인터페이스의 상수값으로 모듈화**
 */
public interface ServerInfo {
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	public static final String USER = "mykb";
	public static final String PASSWORD = "1234";
	

}
