package util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
//import model.Shain;

public class ObjectFormatter {
    // 사용자 정의 클래스의 프로퍼티 값을 출력하는 클래스
	// 단일 객체나 객체를 담고있는 ArrayList를 넘겨주면 그 객체의 프로퍼티 값을 출력한다.
	/*
	public static void main(String[] args) {
	    try {
	        Connection conn = ConnectionProvider.getConnection();
	        
	        **ArrayList<Shain> ShainList = getInstance().getShainSentakuList(conn);
	        
	        try {
	        
	            System.out.println(ObjectFormatter.formatList(**ShainList));
	            
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	 */
	// 객체를 처리
    public static String formatObject(Object obj) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();

        sb.append(obj.getClass().getSimpleName()).append("{");

        boolean firstProperty = true;

        for (Field field : fields) {
            field.setAccessible(true);  // private 필드도 접근할 수 있도록 설정
            Object value = field.get(obj);

            if (value != null) {
                if (!firstProperty) {
                    sb.append(", ");
                }
                sb.append(field.getName()).append("=").append(value.toString());
                firstProperty = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }
    // 객체를 담은 리스트를 처리
    public static String formatList(List<?> objects) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < objects.size(); i++) {
            sb.append(formatObject(objects.get(i)));

            // 마지막 객체가 아니면 쉼표와 줄바꿈 추가
            if (i < objects.size() - 1) {
                sb.append(",\n");
            }
        }
    
        return sb.toString();
    }
}