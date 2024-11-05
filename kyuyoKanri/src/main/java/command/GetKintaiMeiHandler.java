package command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import keisan.model.KintaiKoumoku;
import mvc.command.CommandHandler;
import service.GetKintaiKoumokuMeiService;

//임세규 林世圭
//급여입력/관리 페이지에서 급여항목을 수정할 때 근태연계를 위해 근태항목 이름들을 출력
//給与入力・管理ページで給与項目を修正する際に勤怠連携のために勤怠項目の名前を出力
public class GetKintaiMeiHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		GetKintaiKoumokuMeiService ks = new GetKintaiKoumokuMeiService();
		ArrayList<KintaiKoumoku> kList = ks.getKintaiMeiList();
		
		JSONArray jsonArray = new JSONArray();
		for (KintaiKoumoku k : kList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("kintai_mei", k.getKintai_mei());

			jsonArray.put(jsonObject);
		}

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
