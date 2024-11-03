package command;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.KyuuyoKoumoku;
import model.ShainTekiyouKoujoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

public class GetKoujoKoumokuKirokuHandler implements CommandHandler {
	
	private GetKoumokuJouhouService gs = new GetKoumokuJouhouService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 뷰로부터 파라미터 값 획득
		String jisuu = req.getParameter("kyuuyoJisuu");
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		// 사원에게 적용되는 "기본"공제항목들 리스트
		ArrayList<ShainTekiyouKoujoKoumoku> tekiyouList = gs.getShainTekiyouKoujoKoumokuList(shain_id);
		// 각 공제항목들을 계산한 값을 저장하는 리스트
		ArrayList<ShainTekiyouKoujoKoumoku> keisanList = new ArrayList<>();
		
		// 사원에게 적용되는 공제항목의 공제금액을 계산하여 리스트에 저장
		for (ShainTekiyouKoujoKoumoku s : tekiyouList) {
			// 국민연금 계산
			if ((s.getKoujoKoumoku_mei()).equals("国民年金")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
			}
			// 건강보험 계산
			else if ((s.getKoujoKoumoku_mei()).equals("健康保険")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
			}
			// 장기요양보험 계산(건강보험 * 0.1295)
			else if ((s.getKoujoKoumoku_mei()).equals("長期介護保険")) {
				s.setKoujoGaku((s.getKihonKyuu().multiply(new BigDecimal("0.03545"))).multiply(new BigDecimal("0.1295"))
						.intValue());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
			}
			// 고용보험 계산
			else if ((s.getKoujoKoumoku_mei()).equals("雇用保険")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));

			}
			// 소득세 계산(세액표 테이블에서 가져옴)
			else if ((s.getKoujoKoumoku_mei()).equals("所得税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), gs.keisanShotokuZei(shain_id)));
			}
			// 지방소득세 계산(소득세 * 0.1)
			else if ((s.getKoujoKoumoku_mei()).equals("地方所得税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(),
						(int) (gs.keisanShotokuZei(shain_id) * 0.1)));
			}
		}

		JSONArray jsonArray = new JSONArray();
		for (ShainTekiyouKoujoKoumoku s : keisanList) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("koujoKoumoku_id", s.getKoujoKoumoku_id());
			jsonObject.put("koujoGaku", s.getKoujoGaku());

			jsonArray.put(jsonObject);
		}
		
		res.setContentType("application/json; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.print(jsonArray.toString());
		out.flush();


		return null;
	}
}
