package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import keisan.model.KoujoKoumoku;
import keisan.model.KyuuyoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 출력할 급여항목들의 정보를 가져오는 클래스
//給与入力・管理ページで出力する給与項目の情報を取得するクラス
public class GetKyuuyoKoumokuListHandler implements CommandHandler {

	private GetKoumokuJouhouService koumokuService = new GetKoumokuJouhouService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArrayList<KyuuyoKoumoku> kList = koumokuService.getKyuuyoKoumokuJouhou();

		JSONArray jsonArray = new JSONArray();
		for (KyuuyoKoumoku k : kList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("kyuuyokoumoku_id", k.getKyuuyoKoumoku_id());
			jsonObject.put("kyuuyokoumoku_mei", k.getKyuuyoKoumoku_mei());
			jsonObject.put("kazeikubun", k.getKazeiKubun());
			jsonObject.put("hikazeigendogaku", k.getHikazeiGendogaku());
			jsonObject.put("keisanhouhou", k.getKeisanHouhou());
			jsonObject.put("zenshadani", k.getZenshaDani());
			jsonObject.put("kintairenkei", k.getKintaiRenkei());
			jsonObject.put("ikkatsushiharai", k.getIkkatsuShiharai());
			jsonObject.put("ikkatsushiharaigaku", k.getIkkatsuShiharaiGaku());

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
