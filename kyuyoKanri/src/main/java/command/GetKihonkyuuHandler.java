package command;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 선택한 사원의 기본급을 출력
//給与入力・管理ページで選択した社員の基本給を出力
public class GetKihonkyuuHandler implements CommandHandler {

	private GetKoumokuJouhouService gs = new GetKoumokuJouhouService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		
		int kihonkyuu = gs.getShainKihonkyuu(shain_id);

	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("kihonkyuu", kihonkyuu);

	    res.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = res.getWriter();
	    out.print(jsonResponse.toString());
	    out.flush();
		
		return null;
	}

}
