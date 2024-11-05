package command;
//김찬호 金燦鎬
//급여항목 설정
//給与項目設定
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import kihonkankyousettei.model.KoujoKoumoku;
import kihonkankyousettei.model.KyuuyoKoumoku;
import mvc.command.CommandHandler;
import service.KyuuyoKoumokuSettingService;

/**
 * KyuuyoKoumokuSettingHandler는 급여 및 공제 항목 관련 요청을 처리하는 핸들러 클래스입니다.
 * KyuuyoKoumokuSettingHandlerは給与および控除項目関連リクエストを処理するハンドラークラスです。
 */
public class KyuuyoKoumokuSettingHandler implements CommandHandler {

	private KyuuyoKoumokuSettingService service = new KyuuyoKoumokuSettingService();
	private static final String ACTION_GET_ALL_KYUYO = "getAllKyuuyoKoumoku";
	private static final String ACTION_GET_ALL_KOUJO = "getAllKoujoKoumoku";
	private static final String ACTION_REGISTER_KYUYO = "registerKyuuyoKoumoku";
	private static final String ACTION_REGISTER_KOUJO = "registerKoujoKoumoku";
	private static final String VIEW_PAGE = "/WEB-INF/view/KyuuyoKoumokuSetting.jsp"; // JSP 경로 설정 / JSPパス設定

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String action = req.getParameter("action");
		System.out.println("Received action: " + action); // Action 파라미터 로깅 / アクションパラメータのログ出力

		if (action == null || action.isEmpty()) {
			return VIEW_PAGE; // action이 없는 경우 VIEW_PAGE로 이동 / actionがない場合はVIEW_PAGEに移動
		}

		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res, action);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res, action);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return VIEW_PAGE;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res, String action) throws IOException {
		res.setContentType("application/json; charset=UTF-8");

		try {
			switch (action) {
			case ACTION_GET_ALL_KYUYO:
				return getAllKyuuyoKoumoku(res);
			case ACTION_GET_ALL_KOUJO:
				return getAllKoujoKoumoku(res);
			default:
				res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res, String action) throws IOException {
		res.setContentType("application/json; charset=UTF-8");

		try {
			switch (action) {
			case ACTION_REGISTER_KYUYO:
				return registerKyuuyoKoumoku(req, res);
			case ACTION_REGISTER_KOUJO:
				return registerKoujoKoumoku(req, res);
			default:
				res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			return null;
		}
	}

	// 모든 급여 항목 조회 / すべての給与項目の照会
	private String getAllKyuuyoKoumoku(HttpServletResponse res) throws SQLException, IOException {
		List<KyuuyoKoumoku> kyuuyoList = service.getAllKyuuyoKoumoku();
		JSONArray jsonArray = new JSONArray(kyuuyoList);

		try (PrintWriter out = res.getWriter()) {
			out.print(jsonArray.toString());
			out.flush();
		}
		return null;
	}

	// 모든 공제 항목 조회 / すべての控除項目の照会
	private String getAllKoujoKoumoku(HttpServletResponse res) throws SQLException, IOException {
		List<KoujoKoumoku> koujoList = service.getAllKoujoKoumoku();
		JSONArray jsonArray = new JSONArray(koujoList);

		try (PrintWriter out = res.getWriter()) {
			out.print(jsonArray.toString());
			out.flush();
		}
		return null;
	}

	// 급여 항목 등록 / 給与項目の登録
	private String registerKyuuyoKoumoku(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException {
		try {
			KyuuyoKoumoku kyuuyoKoumoku = new KyuuyoKoumoku();
			kyuuyoKoumoku.setKyuuyoKoumoku_mei(req.getParameter("kyuuyoKoumoku_mei"));
			kyuuyoKoumoku.setKazeiKubun(req.getParameter("kazeiKubun"));
			kyuuyoKoumoku.setHikazeiGendogaku(new BigDecimal(req.getParameter("hikazeiGendogaku")));
			kyuuyoKoumoku.setBikou(req.getParameter("bikou"));
			kyuuyoKoumoku.setKeisanHouhou(req.getParameter("keisanHouhou"));
			kyuuyoKoumoku.setZenshaDani(req.getParameter("zenshaDani"));
			kyuuyoKoumoku.setKintaiRenkei(req.getParameter("kintaiRenkei"));
			kyuuyoKoumoku.setIkkatsuShiharai(req.getParameter("ikkatsuShiharai"));
			kyuuyoKoumoku.setIkkatsuShiharaiGaku(new BigDecimal(req.getParameter("ikkatsuShiharaiGaku")));
			kyuuyoKoumoku.setShiyouUmu(req.getParameter("shiyouUmu").charAt(0));

			boolean success = service.registerKyuuyoKoumoku(kyuuyoKoumoku);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", success);
			jsonResponse.put("message", success ? "급여 항목이 성공적으로 추가되었습니다。" : "급여 항목 추가에 실패했습니다。");
			jsonResponse.put("message", success ? "給与項目が正常に追加されました。" : "給与項目の追加に失敗しました。");

			res.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST); // 상태 코드 설정 / ステータスコードの設定
			try (PrintWriter out = res.getWriter()) {
				out.print(jsonResponse.toString());
				out.flush();
			}
		} catch (NumberFormatException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		}
		return null;
	}

	// 공제 항목 등록 / 控除項目の登録
	private String registerKoujoKoumoku(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException {
		try {
			KoujoKoumoku koujoKoumoku = new KoujoKoumoku();
			koujoKoumoku.setKoujoKoumoku_mei(req.getParameter("koujoKoumoku_mei"));
			koujoKoumoku.setKoujoRitsu(new BigDecimal(req.getParameter("koujoRitsu")));
			koujoKoumoku.setKeisanHouhou(req.getParameter("keisanHouhou"));
			koujoKoumoku.setShiyouUmu(req.getParameter("shiyouUmu").charAt(0));

			boolean success = service.registerKoujoKoumoku(koujoKoumoku);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", success);
			jsonResponse.put("message", success ? "공제 항목이 성공적으로 추가되었습니다。" : "공제 항목 추가에 실패했습니다。");
			jsonResponse.put("message", success ? "控除項目が正常に追加されました。" : "控除項目の追加に失敗しました。");

			res.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST); // 상태 코드 설정 / ステータスコードの設定
			try (PrintWriter out = res.getWriter()) {
				out.print(jsonResponse.toString());
				out.flush();
			}
		} catch (NumberFormatException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		}
		return null;
	}
}
