package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.KoujoKoumoku;
import model.KyuuyoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

public class GetKyuuyoKoumokuListHandler implements CommandHandler {

	private GetKoumokuJouhouService koumokuService = new GetKoumokuJouhouService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArrayList<KyuuyoKoumoku> kList = koumokuService.getKyuuyoKoumokuJouhou();
		// JSON 배열 생성
		JSONArray jsonArray = new JSONArray();
		for (KyuuyoKoumoku k : kList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("kyuuyokoumoku_id", k.getKyuuyoKoumoku_id());
			jsonObject.put("kyuuyokoumoku_mei", k.getKyuuyoKoumoku_mei());
			jsonObject.put("kazeikubun", k.getKazeiKubun());
			jsonObject.put("hikazeigendogaku", k.getHikazeiGendogaku());
			//jsonObject.put("bikou", k.getBikou());
			jsonObject.put("keisanhouhou", k.getKeisanHouhou());
			jsonObject.put("zenshadani", k.getZenshaDani());
			jsonObject.put("kintairenkei", k.getKintaiRenkei());
			jsonObject.put("ikkatsushiharai", k.getIkkatsuShiharai());
			jsonObject.put("ikkatsushiharaigaku", k.getIkkatsuShiharaiGaku());
			//jsonObject.put("shiyouumu", k.getShiyouUmu());

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
