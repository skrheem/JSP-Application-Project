package command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import keisan.model.Shain;
import mvc.command.CommandHandler;
import service.GetShainTsuikaListService;

//임세규 林世圭
//급여입력/관리 페이지에서 신규추가 버튼을 눌렀을 때 출력할 사원 리스트를 가져오는 클래스
//給与入力・管理ページで新規追加ボタンを押した時に出力する社員リストを取得するクラス
public class KyuuyoShainTsuikaListHandler implements CommandHandler {
	
	private GetShainTsuikaListService ts = new GetShainTsuikaListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String koukinzei = req.getParameter("koukinzei");
		
		ArrayList<Shain> shainList = ts.tsuikaShainList(koukinzei);
		
		JSONArray jsonArray = new JSONArray();
	    for (Shain s : shainList) {
	        JSONObject jsonObject = new JSONObject();
	        
	        jsonObject.put("shain_id", s.getShain_id());
	        jsonObject.put("kubun", s.getKubun());
	        jsonObject.put("name", s.getNamae_kana());
	        jsonObject.put("busho_mei", s.getBusho_mei());
	        jsonObject.put("yakushoku_mei", s.getYakushoku_mei());
	        jsonObject.put("jyoutai", s.getJyoutai());
	        jsonObject.put("kihonkyuu", s.getKihonkyuu());
	        jsonObject.put("koukinzei_kubun", s.getKoukinzei());
	        
	        jsonArray.put(jsonObject);
	    }
	    System.out.println(jsonArray);

	    res.setContentType("application/json; charset=UTF-8");
	    res.getWriter().write(jsonArray.toString());
	    res.getWriter().flush();
		return null;
	}
	
}
