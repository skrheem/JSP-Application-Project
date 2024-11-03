package command;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import model.KyuuyoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

public class GetKyuuyoKoumokuKirokuHandler implements CommandHandler{
	
	private GetKoumokuJouhouService gs = new GetKoumokuJouhouService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String kyuuyoNengappi = req.getParameter("kyuuyoNengappi");
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		
		// 급여항목의 정보를 저장하는 리스트
		ArrayList<KyuuyoKoumoku> kyuuyoList = gs.getKyuuyokoumokuKingakuList(shain_id, kyuuyoNengappi);
		
		JSONArray kyuuyoArray = new JSONArray();
		for (KyuuyoKoumoku k : kyuuyoList) {
			JSONObject kyuuyoObject = new JSONObject();
			
			kyuuyoObject.put("kyuuyoKoumoku_id", k.getKyuuyoKoumoku_id());
			kyuuyoObject.put("hikazeiGendogaku", k.getHikazeiGendogaku());
			
			kyuuyoArray.put(kyuuyoObject);
		}
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(kyuuyoArray.toString());
		out.flush();


		return null;
	}
	
}
