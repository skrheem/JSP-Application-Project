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

		ShainTekiyouKoujoKoumoku kokumin = new ShainTekiyouKoujoKoumoku();
		ShainTekiyouKoujoKoumoku kenkou = new ShainTekiyouKoujoKoumoku();
		ShainTekiyouKoujoKoumoku chouki = new ShainTekiyouKoujoKoumoku();
		ShainTekiyouKoujoKoumoku koyou = new ShainTekiyouKoujoKoumoku();
		ShainTekiyouKoujoKoumoku shotoku = new ShainTekiyouKoujoKoumoku();
		ShainTekiyouKoujoKoumoku chihou = new ShainTekiyouKoujoKoumoku();
		int shotokuZei = 0;
		int chihouZei = 0;
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		System.out.println(shain_id);
		int kihonkyuu = gs.getShainKihonkyuu(shain_id);

		ArrayList<ShainTekiyouKoujoKoumoku> sktList = gs.getShainTekiyouKoujoKoumokuList(shain_id);

		ArrayList<ShainKyuuyoKiroku> skkList = gsk.getShainKyuuyoKiroku(shain_id);

		ArrayList<ShainKoujoKiroku> skjList = gsk.getShainKoujoKiroku(shain_id);

		ArrayList<ShainTekiyouKoujoKoumoku> keisanList = new ArrayList<>();

		for (ShainTekiyouKoujoKoumoku s : sktList) {
			if ((s.getKoujoKoumoku_mei()).equals("国民年金")) {
				s.setKoujoGaku(s.keisan());
				System.out.println(ObjectFormatter.formatObject(s));
				kokumin = s;
			} else if ((s.getKoujoKoumoku_mei()).equals("健康保険")) {
				s.setKoujoGaku(s.keisan());
				System.out.println(ObjectFormatter.formatObject(s));
				kenkou = s;
			} else if ((s.getKoujoKoumoku_mei()).equals("長期介護保険")) {
				s.setKoujoGaku((s.getKihonKyuu().multiply(new BigDecimal("0.03545"))).multiply(new BigDecimal("0.1295"))
						.intValue());
				System.out.println(ObjectFormatter.formatObject(s));
				chouki = s;
			} else if ((s.getKoujoKoumoku_mei()).equals("雇用保険")) {
				s.setKoujoGaku(s.keisan());
				System.out.println(ObjectFormatter.formatObject(s));
				koyou = s;
			} else if ((s.getKoujoKoumoku_mei()).equals("所得税")) {
				shotokuZei = gs.keisanShotokuZei(shain_id);
				System.out.println(shotokuZei);
				shotoku = s;
			} else if ((s.getKoujoKoumoku_mei()).equals("地方税")) {
				chihouZei = (int) (gs.keisanShotokuZei(shain_id) * 0.1);
				System.out.println(chihouZei);
				chihou = s;
			}

			if (s.getKoujoKoumoku_mei().equals("長期介護保険")) {
				BigDecimal returnValue = (s.getKihonKyuu().multiply(new BigDecimal("0.03545")))
						.multiply(new BigDecimal("0.1295"));
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), returnValue.doubleValue()));
			} else if(s.getKoujoKoumoku_mei().equals("所得税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), shotokuZei));
			} else if(s.getKoujoKoumoku_mei().equals("地方税")) {
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), chihouZei));
			} else
				keisanList.add(new ShainTekiyouKoujoKoumoku(s.getKoujoKoumoku_id(), s.keisan()));
		}

		int koujoSougaku = 0;
		int kyuuyoSougaku = 0;
		
		for (ShainKoujoKiroku sj : skjList)
			koujoSougaku += sj.getKoujo_kingaku().intValue();
		
		koujoSougaku += (shotokuZei + chihouZei);
		
		for (ShainKyuuyoKiroku sk : skkList) 
			kyuuyoSougaku += sk.getKyuuyo_kingaku().intValue();
		
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
		req.setAttribute("kihonkyuu", kihonkyuu);
		req.setAttribute("kyuuyoSougaku", kyuuyoSougaku);
		req.setAttribute("koujoSougaku", koujoSougaku);
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
