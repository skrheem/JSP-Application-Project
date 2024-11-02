package command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.ShainKyuuyoKeisanKiroku;
import mvc.command.CommandHandler;
import service.kyuuyoKanriShainListService;

public class getKyuuyoKeisanKirokuHandler implements CommandHandler {
	
	private kyuuyoKanriShainListService ks = new kyuuyoKanriShainListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String kyuuyoNendo = req.getParameter("kyuuyoNendo");
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		int koukinzeiType = Integer.parseInt(req.getParameter("koukinzei"));

		ArrayList<ShainKyuuyoKeisanKiroku> skList = ks.getShainList(kyuuyoNendo, kyuuyoGatsu, kyuuyoJisuu,
				koukinzeiType);

		JSONArray jsonArray = new JSONArray();
		for (ShainKyuuyoKeisanKiroku s : skList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("shain_id", s.getShain_id());
			jsonObject.put("kubun", s.getKubun());
			jsonObject.put("name", s.getNamae_kana());
			jsonObject.put("busho_mei", s.getBusho_mei());
			jsonObject.put("shikyuuSougaku", s.getShikyuuSougaku());
			jsonObject.put("koujoSougaku", s.getKoujoSougaku());
			jsonObject.put("jissai_kyuuyo", s.getJissai_kyuuyo());
			jsonObject.put("kyuuyoSanteiKaishi", s.getKYUUYOSANTEIKAISHI());
			jsonObject.put("kyuuyoSanteiShuuryou", s.getKYUUYOSANTEISHUURYOU());
			jsonObject.put("kyuuyoShikyuuBi", s.getKYUUYO_SHIKYUUBI());
			jsonObject.put("koukinzei", s.getKoukinzei_kubun());

			jsonArray.put(jsonObject);
		}
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jsonArray.toString());
		out.flush();

		return null;
	}
}
