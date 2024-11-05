package command;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import mvc.command.CommandHandler;
import service.KyuuyoShainTsuikaService;

//임세규 林世圭
//급여입력/관리 페이지에서 사원의 새로운 급여계산기록을 추가하는 클래스
//給与入力・管理ページで社員の新しい給与計算記録を追加するクラス
public class KyuuyoShainTsuikaHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	KyuuyoShainTsuikaService ks = new KyuuyoShainTsuikaService();

        StringBuilder jsonData = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }
        
        JSONObject data = new JSONObject(jsonData.toString());

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
        
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write("{\"status\":\"success\"}");
        
        return null;
    }
}