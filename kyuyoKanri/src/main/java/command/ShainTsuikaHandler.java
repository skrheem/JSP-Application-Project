package command;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.ShainKyuuyoKeisanKirokuDao;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class ShainTsuikaHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	ShainKyuuyoKeisanKirokuDao skDao = ShainKyuuyoKeisanKirokuDao.getInstance();
        // 요청에서 JSON 데이터를 읽음
        StringBuilder jsonData = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }
        
        // JSON 데이터를 JSONObject로 변환
        JSONObject data = new JSONObject(jsonData.toString());
        
        // kyuuyoNendo와 kyuuyoGatsu 값을 추출하여 출력
        String kyuuyoNendo = data.getString("kyuuyoNendo");
        String kyuuyoGatsu = data.getString("kyuuyoGatsu");
        String kyuuyoJisuu = data.getString("kyuuyoJisuu");
        System.out.println("Kyuuyo Nendo: " + kyuuyoNendo);
        System.out.println("Kyuuyo Gatsu: " + kyuuyoGatsu);
        System.out.println("kyuuyoJisuu: " + kyuuyoJisuu);

        String kyuuyoBi = kyuuyoNendo + "-" + kyuuyoGatsu  + "-" + "01";
        System.out.println(kyuuyoBi);
        
        try {
        	Connection conn = ConnectionProvider.getConnection();
        	JSONArray selectedIds = data.getJSONArray("shainIds");
            for (int i = 0; i < selectedIds.length(); i++) {
                skDao.insertShainKeisanKiroku(conn, kyuuyoBi, kyuuyoJisuu, Integer.parseInt(selectedIds.getString(i)));
            }
        }catch (SQLException e) {
			e.printStackTrace();
		}
        // shainIds 배열을 추출하여 각 사원 ID를 출력
        
        
        // AJAX 요청에 대한 응답 설정
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write("{\"status\":\"success\"}");
        
        return null;
    }
}