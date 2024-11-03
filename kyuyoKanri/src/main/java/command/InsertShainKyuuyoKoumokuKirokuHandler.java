package command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.InsertShainKyuuyoKirokuService;

public class InsertShainKyuuyoKoumokuKirokuHandler implements CommandHandler {
	
	InsertShainKyuuyoKirokuService is = new InsertShainKyuuyoKirokuService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoNengappi = req.getParameter("kyuuyoNengappi");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
	    // 전송된 데이터를 Map에 저장
	    Map<String, String> kyuuyoData = new HashMap<>();
	    
	    for (Map.Entry<String, String> entry : kyuuyoData.entrySet()) {
	        int kyuuyokoumoku_id = Integer.parseInt(entry.getKey());
	        int kyuuyokoumoku_kingaku = Integer.parseInt(entry.getValue());
	        
	        is.insertShainKyuuyoKiroku(shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyoNengappi, kyuuyoJisuu);
	    }
	    
	    
	    // 처리 후 반환할 뷰 이름 또는 null 반환
	    return null;
	}
	

}
