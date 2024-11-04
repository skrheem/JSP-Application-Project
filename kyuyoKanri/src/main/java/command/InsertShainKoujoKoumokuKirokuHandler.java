package command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.InsertShainKoujoKirokuService;

public class InsertShainKoujoKoumokuKirokuHandler implements CommandHandler {
	
	InsertShainKoujoKirokuService is = new InsertShainKoujoKirokuService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoNengappi = req.getParameter("kyuuyoNengappi");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
		System.out.println(shain_id);
		System.out.println(kyuuyoNengappi);
		System.out.println(kyuuyoJisuu);
		
		int rValue = 0;
	    // 전송된 데이터를 Map에 저장
	    Map<String, String> koujoData = new HashMap<>();
	    
	    Enumeration<String> parameterNames = req.getParameterNames();
	    while (parameterNames.hasMoreElements()) {
	        String paramName = parameterNames.nextElement();
	        
	        if (!paramName.equals("kyuuyoNengappi") && !paramName.equals("shain_id") && !paramName.equals("kyuuyoJisuu")) {
	            String paramValue = req.getParameter(paramName);
	            koujoData.put(paramName, paramValue);
	        }
	    }
	    
	    for (Map.Entry<String, String> entry : koujoData.entrySet()) {
	        int koujokoumoku_id = Integer.parseInt(entry.getKey());
	        int koujokoumoku_kingaku = Integer.parseInt(entry.getValue());
	        
	        rValue += is.insertShainKoujoKiroku(shain_id, koujokoumoku_id, koujokoumoku_kingaku, kyuuyoNengappi, kyuuyoJisuu);
	    }
	    System.out.println(koujoData);
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
