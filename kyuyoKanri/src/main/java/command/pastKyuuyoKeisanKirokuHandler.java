package command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import model.ShainKyuuyoKeisanKiroku;
import mvc.command.CommandHandler;
import service.pastKyuuyoKeisanKirokuService;

public class pastKyuuyoKeisanKirokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		pastKyuuyoKeisanKirokuService ps = new pastKyuuyoKeisanKirokuService();
		
		ArrayList<ShainKyuuyoKeisanKiroku> pList = ps.getPastKeisanKiroku();
			
		JSONArray jsonArray = new JSONArray();
		for (ShainKyuuyoKeisanKiroku p : pList) {
			JSONObject jsonObject = new JSONObject();
			
	        String year = p.getKyuuyo_gatsu().toString().substring(0, 4);
	        
	        String month = p.getKyuuyo_gatsu().toString().substring(5, 7);
	        
	        String kyuuyo_jisuu = p.getKyuuyo_jisuu().length() == 1 ? "0" + p.getKyuuyo_jisuu() : p.getKyuuyo_jisuu();
	          
	        String kyuuyo_gatsu = year + "년 " + month + "월 " + kyuuyo_jisuu + "차";
	        
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
