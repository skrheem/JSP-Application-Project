package command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.InsertShainKyuuyoKirokuService;
import util.ObjectFormatter;

public class InsertShainKyuuyoKoumokuKirokuHandler implements CommandHandler {
	
	InsertShainKyuuyoKirokuService is = new InsertShainKyuuyoKirokuService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("핸들러");
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoNengappi = req.getParameter("kyuuyoNengappi");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		int rValue = 0;
	    // 전송된 데이터를 Map에 저장
	    Map<String, String> kyuuyoData = new HashMap<>();
	    
	    Enumeration<String> parameterNames = req.getParameterNames();
	    while (parameterNames.hasMoreElements()) {
	        String paramName = parameterNames.nextElement();
	        
	        if (!paramName.equals("kyuuyoNengappi") && !paramName.equals("shain_id") && !paramName.equals("kyuuyoJisuu")) {
	            String paramValue = req.getParameter(paramName);
	            kyuuyoData.put(paramName, paramValue);
	        }
	    }
	    
	    for (Map.Entry<String, String> entry : kyuuyoData.entrySet()) {
	        int kyuuyokoumoku_id = Integer.parseInt(entry.getKey());
	        int kyuuyokoumoku_kingaku = Integer.parseInt(entry.getValue());
	        
	        rValue += is.insertShainKyuuyoKiroku(shain_id, kyuuyokoumoku_id, kyuuyokoumoku_kingaku, kyuuyoNengappi, kyuuyoJisuu);
	    }
	    if (rValue > 0) {
			res.setContentType("text/plain");
			res.getWriter().write("success");
		} else {
			res.setContentType("text/plain");
			res.getWriter().write("fail");
		}
	    
	    return null;
	}
	

}
