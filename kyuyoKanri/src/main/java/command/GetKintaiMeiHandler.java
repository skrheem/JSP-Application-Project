package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import model.KintaiKoumoku;
import mvc.command.CommandHandler;
import service.getKintaiKoumokuMeiService;

public class GetKintaiMeiHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		getKintaiKoumokuMeiService ks = new getKintaiKoumokuMeiService();
		ArrayList<KintaiKoumoku> kList = ks.getKintaiMeiList();
		
		JSONArray jsonArray = new JSONArray();
		for (KintaiKoumoku k : kList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("kintai_mei", k.getKintai_mei());

			jsonArray.put(jsonObject);
		}

		// JSON 응답 설정
		res.setContentType("application/json; charset=UTF-8");
		try {
			res.getWriter().write(jsonArray.toString());
			res.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
