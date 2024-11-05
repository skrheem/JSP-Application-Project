package command;
//김찬호 金燦鎬
//휴가근태설정
//休暇勤怠設定
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import kihonkankyousettei.model.KintaiKoumoku;
import kihonkankyousettei.model.KyuukaKoumoku;
import mvc.command.CommandHandler;
import service.KyuukaKintaiKoumokuService;

/**
 * KyuukaKintaiKoumokuHandler는 휴가 및 근태 항목 관련 요청을 처리하는 핸들러 클래스입니다.
 * KyuukaKintaiKoumokuHandlerは休暇および勤怠項目関連リクエストを処理するハンドラークラスです。
 */
public class KyuukaKintaiKoumokuHandler implements CommandHandler {

	private KyuukaKintaiKoumokuService service = new KyuukaKintaiKoumokuService();

	private static final String ACTION_GET_ALL_KYUUKA = "getAllKyuukaKoumoku";
	private static final String ACTION_GET_ALL_KINTAI = "getAllKintaiKoumoku";
	private static final String ACTION_REGISTER_KYUUKA = "registerKyuukaKoumoku";
	private static final String ACTION_REGISTER_KINTAI = "registerKintaiKoumoku";
	private static final String ACTION_REGISTER_HYUGA = "registerHyuga"; // 사원별 휴가일수 저장을 위한 액션 / 社員別休暇日数保存用のアクション
	private static final String VIEW_PAGE = "/WEB-INF/view/KyuukaKintaiKoumoku.jsp"; // JSP 경로 설정 / JSPのパス設定


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		System.out.println("Received action: " + action); // Action 파라미터 로깅 / アクションパラメータのログ出力

		if (action == null || action.isEmpty()) {
			return VIEW_PAGE; // action이 없는 경우 VIEW_PAGE로 이동 / actionがない場合はVIEW_PAGEに移動
		}

		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response, action);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response, action);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return VIEW_PAGE;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response, String action)
			throws IOException {
		response.setContentType("application/json; charset=UTF-8");

		try {
			switch (action) {
			case ACTION_GET_ALL_KYUUKA:
				return getAllKyuukaKoumoku(response);
			case ACTION_GET_ALL_KINTAI:
				return getAllKintaiKoumoku(response);
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
				return VIEW_PAGE; // 잘못된 action인 경우 VIEW_PAGE로 이동 / 不正なアクションの場合はVIEW_PAGEに移動
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			return VIEW_PAGE;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response, String action)
			throws IOException {
		response.setContentType("application/json; charset=UTF-8");

		try {
			switch (action) {
			case ACTION_REGISTER_KYUUKA:
				return registerKyuukaKoumoku(request, response);
			case ACTION_REGISTER_KINTAI:
				return registerKintaiKoumoku(request, response);
			case ACTION_REGISTER_HYUGA: // 사원별 휴가일수 저장 / 社員別休暇日数保存
				return registerHyuga(request, response);
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
				return VIEW_PAGE;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
			return VIEW_PAGE;
		}
	}

	// 사원별 휴가일수 저장 / 社員別休暇日数保存
	private String registerHyuga(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			String[] shainIds = request.getParameterValues("shainId"); // 선택된 사원 ID 배열 / 選択された社員ID配列
			if (shainIds != null && shainIds.length > 0) {
				int vacationDays = Integer.parseInt(request.getParameter("kyuuka_nissuu")); // 계산된 휴가일수 / 計算された休暇日数
				int kyuukaKoumokuId = Integer.parseInt(request.getParameter("kyuukaKoumokuId")); // 휴가 항목 ID 추가 / 休暇項目ID追加

				for (String shainId : shainIds) {
					service.insertKyuukaNissuuShain(shainId, kyuukaKoumokuId, vacationDays);
				}

				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("success", true);
				jsonResponse.put("message", "사원별 휴가일수가 성공적으로 저장되었습니다。/ 社員別休暇日数が正常に保存されました。");

				try (PrintWriter out = response.getWriter()) {
					out.print(jsonResponse.toString());
					out.flush();
				}
			} else {
				throw new IllegalArgumentException("선택된 사원이 없습니다。/ 選択された社員がいません。");
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		return null;
	}

	// 휴가 항목 전체 조회 / 休暇項目の全体照会
	private String getAllKyuukaKoumoku(HttpServletResponse response) throws SQLException, IOException {
	    ArrayList<KyuukaKoumoku> kyuukaList = service.getAllKyuukaKoumoku();
	    JSONArray jsonArray = new JSONArray();

	    for (KyuukaKoumoku kyuuka : kyuukaList) {
	        JSONObject kyuukaJson = new JSONObject();
	        kyuukaJson.put("id", kyuuka.getKyuukaKoumoku_id()); // ID 값 포함 / ID値を含む
	        kyuukaJson.put("kyuukaShurui", kyuuka.getKyuukaShurui());
	        kyuukaJson.put("tekiyouKaishi", kyuuka.getTekiyouKaishi());
	        kyuukaJson.put("tekiyouShuuryou", kyuuka.getTekiyouShuuryou());
	        kyuukaJson.put("shiyouUmu", kyuuka.getShiyouUmu());
	        jsonArray.put(kyuukaJson);
	    }

	    try (PrintWriter out = response.getWriter()) {
	        out.print(jsonArray.toString());
	        out.flush();
	    }
	    return null;
	}

	// 근태 항목 전체 조회 / 勤怠項目の全体照会
	private String getAllKintaiKoumoku(HttpServletResponse response) throws SQLException, IOException {
		ArrayList<KintaiKoumoku> kintaiList = service.getAllKintaiKoumoku();
		JSONArray jsonArray = new JSONArray(kintaiList);

		try (PrintWriter out = response.getWriter()) {
			out.print(jsonArray.toString());
			out.flush();
		}
		return null;
	}

	// 휴가 항목 등록 / 休暇項目の登録
	private String registerKyuukaKoumoku(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			KyuukaKoumoku kyuukaKoumoku = new KyuukaKoumoku();
			kyuukaKoumoku.setKyuukaShurui(request.getParameter("kyuukaShurui"));

			// 날짜 형식을 Date로 변환 / 日付形式をDateに変換
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				kyuukaKoumoku.setTekiyouKaishi(dateFormat.parse(request.getParameter("tekiyouKaishi")));
				kyuukaKoumoku.setTekiyouShuuryou(dateFormat.parse(request.getParameter("tekiyouShuuryou")));
			} catch (ParseException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
				return null;
			}

			kyuukaKoumoku.setShiyouUmu(request.getParameter("shiyouUmu").charAt(0));

			boolean success = service.registerKyuukaKoumoku(kyuukaKoumoku);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", success);
			jsonResponse.put("message", success ? "휴가 항목이 성공적으로 추가되었습니다。" : "휴가 항목 추가에 실패했습니다。");
			jsonResponse.put("message", success ? "休暇項目が正常に追加されました。" : "休暇項目の追加に失敗しました。");

			try (PrintWriter out = response.getWriter()) {
				out.print(jsonResponse.toString());
				out.flush();
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		}
		return null;
	}

	// 근태 항목 등록 / 勤怠項目の登録
	private String registerKintaiKoumoku(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			KintaiKoumoku kintaiKoumoku = new KintaiKoumoku();
			kintaiKoumoku.setKintai_mei(request.getParameter("kintai_mei"));
			kintaiKoumoku.setTani(request.getParameter("tani"));
			kintaiKoumoku.setKyuukaKoumoku_id(Integer.parseInt(request.getParameter("kyuukaKoumoku_id")));
			kintaiKoumoku.setGroup_id(Integer.parseInt(request.getParameter("group_id")));
			kintaiKoumoku.setRoudouJikanRenkei(request.getParameter("roudouJikanRenkei"));
			kintaiKoumoku.setShiyouUmu(request.getParameter("shiyouUmu").charAt(0));

			boolean success = service.registerKintaiKoumoku(kintaiKoumoku);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", success);
			jsonResponse.put("message", success ? "근태 항목이 성공적으로 추가되었습니다。" : "근태 항목 추가에 실패했습니다。");
			jsonResponse.put("message", success ? "勤怠項目が正常に追加されました。" : "勤怠項目の追加に失敗しました。");

			try (PrintWriter out = response.getWriter()) {
				out.print(jsonResponse.toString());
				out.flush();
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
		}
		return null;
	}
}
