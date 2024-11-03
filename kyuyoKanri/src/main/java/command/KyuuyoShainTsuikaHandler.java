package command;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import mvc.command.CommandHandler;
import service.KyuuyoShainTsuikaService;

public class KyuuyoShainTsuikaHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	KyuuyoShainTsuikaService ks = new KyuuyoShainTsuikaService();
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
        String santeiKaishi = data.getString("santeiKaishi");
        String santeiShuuryou = data.getString("santeiShuuryou");
        String shikyuu_bi = data.getString("shikyuu_bi");

        String kyuuyoBi = kyuuyoNendo + "-" + kyuuyoGatsu  + "-" + "01";
        System.out.println(kyuuyoBi);
        
        JSONArray selectedIds = data.getJSONArray("shainIds");
        for (int i = 0; i < selectedIds.length(); i++) {
        	ks.shainKeisanKirokuTsuika(kyuuyoBi, kyuuyoJisuu, shikyuu_bi, Integer.parseInt(selectedIds.getString(i)), santeiKaishi, santeiShuuryou);
        }
        
        // AJAX 요청에 대한 응답 설정
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write("{\"status\":\"success\"}");
        
        return null;
    }
}