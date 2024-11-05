package command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import keisan.model.ShainKyuuyoKeisanKiroku;
import mvc.command.CommandHandler;
import service.PastKyuuyoKeisanKirokuService;

//임세규 林世圭
//급여입력/관리 페이지에서 지난급여 기록을 불러오는 클래스
//給与入力・管理ページで過去の給与記録を読み込むクラス
public class PastKyuuyoKeisanKirokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		PastKyuuyoKeisanKirokuService ps = new PastKyuuyoKeisanKirokuService();
		
		ArrayList<ShainKyuuyoKeisanKiroku> pList = ps.getPastKeisanKiroku();
			
		JSONArray jsonArray = new JSONArray();
		for (ShainKyuuyoKeisanKiroku p : pList) {
			JSONObject jsonObject = new JSONObject();
			
	        String year = p.getKyuuyo_gatsu().toString().substring(0, 4);
	        
	        String month = p.getKyuuyo_gatsu().toString().substring(5, 7);
	        
	        String kyuuyo_jisuu = p.getKyuuyo_jisuu().length() == 1 ? "0" + p.getKyuuyo_jisuu() : p.getKyuuyo_jisuu();
	          
	        String kyuuyo_gatsu = year + "年 " + month + "月 " + kyuuyo_jisuu + "回";
	        
			jsonObject.put("kyuuyo_gatsu", kyuuyo_gatsu);
			jsonObject.put("originalKyuuyo_gatsu", p.getKyuuyo_gatsu().toString());
			jsonObject.put("originalKyuuyo_jisuu", p.getKyuuyo_jisuu());
			
			
			jsonArray.put(jsonObject);
		}
		System.out.println(jsonArray);
		
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().write(jsonArray.toString());
		res.getWriter().flush();

		return null;
	}

}
