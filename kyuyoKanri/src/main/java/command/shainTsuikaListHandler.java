package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Shain;
import mvc.command.CommandHandler;
import service.getShainTsuikaListService;

public class shainTsuikaListHandler implements CommandHandler{
	
	private getShainTsuikaListService ts = new getShainTsuikaListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("요청 확인");
		ArrayList<Shain> shainList = ts.tsuikaShainList();
		
		JSONArray jsonArray = new JSONArray();
	    for (Shain s : shainList) {
	        JSONObject jsonObject = new JSONObject();
	        
	        jsonObject.put("kubun", s.getKubun());
	        jsonObject.put("shain_id", s.getShain_id());
	        jsonObject.put("name", s.getNamae_kana());
	        jsonObject.put("busho_mei", s.getBusho_mei());
	        jsonObject.put("yakushoku_mei", s.getYakushoku_mei());
	        jsonObject.put("jyoutai", s.getJyoutai());
	        
	        
	        jsonArray.put(jsonObject);
	    }
	    System.out.println(jsonArray);
	 // 응답 헤더 설정 및 JSON 응답 출력
	    res.setContentType("application/json; charset=UTF-8");
	    res.getWriter().write(jsonArray.toString());
	    res.getWriter().flush();
		return null;
	}
	
}
