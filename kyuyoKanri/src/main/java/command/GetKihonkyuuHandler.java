package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

public class GetKihonkyuuHandler implements CommandHandler {

	private GetKoumokuJouhouService gs = new GetKoumokuJouhouService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		
		int kihonkyuu = gs.getShainKihonkyuu(shain_id);

		// JSON 객체 생성
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("kihonkyuu", kihonkyuu);

	    // JSON 응답 설정 및 출력
	    res.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = res.getWriter();
	    out.print(jsonResponse.toString());
	    out.flush();
		
		return null;
	}

}
