package util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	    // 원시 타입 래퍼 클래스는 toString으로 값만 출력
	    if (obj instanceof Double || obj instanceof Integer || obj instanceof Float || 
	        obj instanceof Long || obj instanceof Short || obj instanceof Byte || 
	        obj instanceof Boolean || obj instanceof Character) {
	        return obj.toString();
	    }

	    Field[] fields = obj.getClass().getDeclaredFields();
	    sb.append(obj.getClass().getSimpleName()).append("{");

	    boolean firstProperty = true;

	    for (Field field : fields) {
	        field.setAccessible(true);
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
    // Map 처리
    public static String formatMap(Map<?, ?> map) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("Map{");

        boolean firstEntry = true;

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!firstEntry) {
                sb.append(", ");
            }

            sb.append(entry.getKey()).append("=");

            Object value = entry.getValue();
            if (value instanceof List<?>) {
                sb.append(formatList((List<?>) value));
            } else if (value instanceof Map<?, ?>) {
                sb.append(formatMap((Map<?, ?>) value));
            } else {
                sb.append(formatObject(value));
            }

            firstEntry = false;
        }

        sb.append("}");
        return sb.toString();
    }
}