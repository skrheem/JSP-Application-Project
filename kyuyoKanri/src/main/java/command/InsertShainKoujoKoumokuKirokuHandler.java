package command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.InsertShainKoujoKirokuService;

//임세규 林世圭
//급여입력/관리 페이지에서 사원의 공제항목기록을 추가하는 클래스
//給与入力・管理ページで社員の控除項目記録を追加するクラス
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
