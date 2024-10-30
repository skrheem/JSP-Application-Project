package command;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import model.KyuuyoKoumoku;
import model.ShainKoujoKiroku;
import model.ShainKyuuyoKiroku;
import model.ShainTekiyouKoujoKoumoku;
import mvc.command.CommandHandler;
import service.getKoumokuJouhouService;
import service.getKyuuyoKeisanKirokuListService;
import util.ObjectFormatter;

public class getKyuuyoKirokuHandler implements CommandHandler {
	public static void main(String a[]) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			getKyuuyoKeisanKirokuListService gsk = new getKyuuyoKeisanKirokuListService();
			ArrayList<ShainKyuuyoKiroku> skkList = gsk.getShainKyuuyoKiroku(1);

			ArrayList<ShainKoujoKiroku> skjList = gsk.getShainKoujoKiroku(1);
			try {
				System.out.println(ObjectFormatter.formatList(skkList));
				System.out.println(ObjectFormatter.formatList(skjList));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String FORM_VIEW = "/WEB-INF/view/ShainKyuuyoKeisan.jsp";

	private getKoumokuJouhouService gs = new getKoumokuJouhouService();

	private getKyuuyoKeisanKirokuListService gsk = new getKyuuyoKeisanKirokuListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET"))
			return processForm(req, res);
		else if (req.getMethod().equalsIgnoreCase("POST"))
			return processSubmit(req, res);
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// 급여입력/관리 페이지에 GET 요청으로 접속 시 급여항목과 공제항목의 정보를 가져온다.
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 국민연금
		ShainTekiyouKoujoKoumoku kokumin = new ShainTekiyouKoujoKoumoku();
		// 건강보험
		ShainTekiyouKoujoKoumoku kenkou = new ShainTekiyouKoujoKoumoku();
		// 장기요양보험
		ShainTekiyouKoujoKoumoku chouki = new ShainTekiyouKoujoKoumoku();
		// 고용보험
		ShainTekiyouKoujoKoumoku koyou = new ShainTekiyouKoujoKoumoku();
		// 소득세
		ShainTekiyouKoujoKoumoku shotoku = new ShainTekiyouKoujoKoumoku();
		// 지방소득세
		ShainTekiyouKoujoKoumoku chihou = new ShainTekiyouKoujoKoumoku();
		// 계산된 소득세
		int shotokuZei = 0;
		// 계산된 지방소득세
		int chihouZei = 0;
		// 공제총액
		int koujoSougaku = 0;
		// 급여총액
		int kyuuyoSougaku = 0;
		// 실지급액
		int jissaiSougaku = 0;
		// 쿼리스트링으로 받는 사원id
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		// 사원에게 적용되는 공제항목들 리스트
		ArrayList<ShainTekiyouKoujoKoumoku> tekiyouList = gs.getShainTekiyouKoujoKoumokuList(shain_id);
		// 사원의 급여항목기록 리스트
		ArrayList<ShainKyuuyoKiroku> skkList = gsk.getShainKyuuyoKiroku(shain_id);
		// 사원의 공제항목기록 리스트
		ArrayList<ShainKoujoKiroku> skjList = gsk.getShainKoujoKiroku(shain_id);
		// 각 공제항목들을 계산한 값을 저장하는 리스트
		ArrayList<ShainTekiyouKoujoKoumoku> keisanList = new ArrayList<>();

		// 사원에게 적용되는 공제항목의 공제금액을 계산하여 리스트에 저장
		for (ShainTekiyouKoujoKoumoku s : tekiyouList) {
			// 국민연금 계산
			if ((s.getKoujoKoumoku_mei()).equals("国民年金")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
				kokumin = s;
			} 
			// 건강보험 계산
			else if ((s.getKoujoKoumoku_mei()).equals("健康保険")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
				kenkou = s;
			} 
			// 장기요양보험 계산(건강보험 * 0.1295)
			else if ((s.getKoujoKoumoku_mei()).equals("長期介護保険")) {
				s.setKoujoGaku((s.getKihonKyuu().multiply(new BigDecimal("0.03545"))).multiply(new BigDecimal("0.1295"))
						.intValue());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
				chouki = s;
			} 
			// 고용보험 계산
			else if ((s.getKoujoKoumoku_mei()).equals("雇用保険")) {
				s.setKoujoGaku(s.keisan());
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) s.getKoujoGaku()));
				koyou = s;
			} 
			// 소득세 계산(세액표 테이블에서 가져옴)
			else if ((s.getKoujoKoumoku_mei()).equals("所得税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), gs.keisanShotokuZei(shain_id)));
				shotoku = s;
				shotokuZei = gs.keisanShotokuZei(shain_id);
			}
			// 지방소득세 계산(소득세 * 0.1)
			else if ((s.getKoujoKoumoku_mei()).equals("地方所得税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), (int) (gs.keisanShotokuZei(shain_id) * 0.1)));
				chihou = s;
				chihouZei = (int) (gs.keisanShotokuZei(shain_id) * 0.1);
			}
		}

		
		
		for (ShainKoujoKiroku sj : skjList)
			koujoSougaku += sj.getKoujo_kingaku().intValue();
		
		koujoSougaku += (shotokuZei + chihouZei);
		
		for (ShainKyuuyoKiroku sk : skkList) 
			kyuuyoSougaku += sk.getKyuuyo_kingaku().intValue();
		
		jissaiSougaku = kyuuyoSougaku - koujoSougaku;
		
		req.setAttribute("shainKyuuyoKirokuList", skkList);
		req.setAttribute("kokumin", kokumin);
		req.setAttribute("kenkou", kenkou);
		req.setAttribute("chouki", chouki);
		req.setAttribute("koyou", koyou);
		req.setAttribute("shotoku", shotoku);
		req.setAttribute("shotokuZei", shotokuZei);
		req.setAttribute("chihou", chihou);
		req.setAttribute("chihouZei", chihouZei);
		req.setAttribute("shainKoujoKirokuList", skjList);
		req.setAttribute("koujoKingakuJson", buildJsonFromList(keisanList));
		req.setAttribute("kyuuyoSougaku", kyuuyoSougaku);
		req.setAttribute("koujoSougaku", koujoSougaku);
		req.setAttribute("jissaiSougaku", jissaiSougaku);
		return "kanri.do";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		return "";
	}
	
	public String buildJsonFromList(ArrayList<ShainTekiyouKoujoKoumoku> keisanList) {
	    StringBuilder jsonBuilder = new StringBuilder();
	    jsonBuilder.append("[");
	    
	    for (int i = 0; i < keisanList.size(); i++) {
	        ShainTekiyouKoujoKoumoku item = keisanList.get(i);
	        
	        if (i > 0) {
	            jsonBuilder.append(",");
	        }
	        
	        jsonBuilder.append("{");
	        jsonBuilder.append("\"koujoKoumoku_id\":\"").append(item.getKoujoKoumoku_id()).append("\",");
	        jsonBuilder.append("\"gaku\":\"").append((int) item.getKoujoGaku()).append("\"");
	        jsonBuilder.append("}");
	    }
	    
	    jsonBuilder.append("]");
	    return jsonBuilder.toString();
	}
}
