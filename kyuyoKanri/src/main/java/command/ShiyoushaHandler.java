package command;
//김찬호 金燦鎬
//기본환경설정 사용자정보
//基本環境設定 使用者情報
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import jdbc.connection.ConnectionProvider;
import kihonkankyousettei.dao.BushoDao;
import kihonkankyousettei.model.Busho;
import kihonkankyousettei.model.KaishaJouhou;
import kihonkankyousettei.model.KyuuyoShikyuuJouhou;
import kihonkankyousettei.model.Tantousha;
import mvc.command.CommandHandler;
import service.ShiyoushaJouhouService;

/**
 * ShiyoushaHandler는 회사 정보, 담당자 정보, 급여 지급 정보 등을 처리하는 핸들러 클래스입니다.
 * ShiyoushaHandlerは会社情報、担当者情報、給与支給情報などを処理するハンドラークラスです。
 */
public class ShiyoushaHandler implements CommandHandler {
	private ShiyoushaJouhouService shiyoushaService = new ShiyoushaJouhouService();

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

	/**
	 * GET 요청을 처리하는 메서드로, action에 따라 회사를 조회합니다.
	 * GETリクエストを処理するメソッドで、actionに応じて会社情報を照会します。
	 */
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			switch (action != null ? action : "") {
			case "getKaisha":
				return getKaishaById(request, response);
			case "getTantousha":
				return getTantoushaById(request, response);
			case "getKyuuyoShikyuuJouhou":
				return getKyuuyoShikyuuJouhou(request, response);
			default:
				return "/WEB-INF/view/ShiyoushaJouhou.jsp";
			}
		} catch (Exception e) {
			throw new RuntimeException("처리 중 오류 발생: " + e.getMessage(), e);
		}
	}

	/**
	 * POST 요청을 처리하는 메서드로, action에 따라 데이터를 삽입하거나 업데이트합니다.
	 * POSTリクエストを処理するメソッドで、actionに応じてデータを挿入または更新します。
	 */
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");

		try {
			switch (action != null ? action : "") {
			case "insertKaisha":
				return insertKaisha(request, response);
			case "updateKaisha":
				return updateKaisha(request, response);
			case "insertTantousha":
				return insertTantousha(request, response);
			case "updateTantousha":
				return updateTantousha(request, response);
			case "insertKyuuyoShikyuuJouhou":
				return insertKyuuyoShikyuuJouhou(request, response);
			case "updateKyuuyoShikyuuJouhou":
				return updateKyuuyoShikyuuJouhou(request, response);
			default:
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			PrintWriter out = response.getWriter();
			out.print("{\"success\": false, \"message\": \"처리 중 오류 발생: " + e.getMessage() + "\"}");
			out.flush();
			return null;
		}
	}

	// 회사 정보 삽입 / 会社情報の挿入
	private String insertKaisha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		KaishaJouhou kaisha = new KaishaJouhou();
		kaisha.setKaisha_mei(request.getParameter("companyName"));
		kaisha.setDaihyousha_mei(request.getParameter("ceo"));
		kaisha.setJigyousha_touroku_bango(request.getParameter("businessNumber"));
		kaisha.setHoujin_touroku_bango(request.getParameter("registrationNumber"));
		kaisha.setSetsuribi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("establishmentDate")));
		kaisha.setHomepage(request.getParameter("homepage"));
		kaisha.setYuubin_bango(request.getParameter("zipcode"));
		kaisha.setJigyousho_jyuusho(request.getParameter("address"));
		kaisha.setDenwa_kaisha(request.getParameter("phone"));
		kaisha.setFakkusu_bango(request.getParameter("fax"));
		kaisha.setGyoutai(request.getParameter("industry"));
		kaisha.setShumoku(request.getParameter("category"));

		shiyoushaService.insertKaisha(kaisha);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"회사 정보가 저장되었습니다。/ 会社情報が保存されました。\"}");
		out.flush();
		return null;
	}

	// 회사 정보 업데이트 / 会社情報の更新
	private String updateKaisha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int kaishaId = Integer.parseInt(request.getParameter("kaishaId"));
		KaishaJouhou kaisha = new KaishaJouhou();
		kaisha.setKaisha_mei(request.getParameter("companyName"));
		kaisha.setDaihyousha_mei(request.getParameter("ceo"));
		kaisha.setJigyousha_touroku_bango(request.getParameter("businessNumber"));
		kaisha.setHoujin_touroku_bango(request.getParameter("registrationNumber"));
		kaisha.setSetsuribi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("establishmentDate")));
		kaisha.setHomepage(request.getParameter("homepage"));
		kaisha.setYuubin_bango(request.getParameter("zipcode"));
		kaisha.setJigyousho_jyuusho(request.getParameter("address"));
		kaisha.setDenwa_kaisha(request.getParameter("phone"));
		kaisha.setFakkusu_bango(request.getParameter("fax"));
		kaisha.setGyoutai(request.getParameter("industry"));
		kaisha.setShumoku(request.getParameter("category"));

		shiyoushaService.updateKaisha(kaisha, kaishaId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"회사 정보가 수정되었습니다。/ 会社情報が更新されました。\"}");
		out.flush();
		return null;
	}

	// 담당자 정보 삽입 / 担当者情報の挿入
	private String insertTantousha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Tantousha tantousha = new Tantousha();
		tantousha.setNamae_kana(request.getParameter("namae_kana"));
		tantousha.setDenwa_tantousha(request.getParameter("denwa_tantousha"));
		tantousha.setDenwa_keitai(request.getParameter("denwa_keitai"));
		tantousha.setMeeru(request.getParameter("meeru"));

		shiyoushaService.insertTantousha(tantousha);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"담당자 정보가 저장되었습니다。/ 担当者情報が保存されました。\"}");
		out.flush();
		return null;
	}

	// 담당자 정보 업데이트 / 担当者情報の更新
	private String updateTantousha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int tantoushaId = Integer.parseInt(request.getParameter("tantoushaId"));
		Tantousha tantousha = new Tantousha();
		tantousha.setNamae_kana(request.getParameter("namae_kana"));
		tantousha.setDenwa_tantousha(request.getParameter("denwa_tantousha"));
		tantousha.setDenwa_keitai(request.getParameter("denwa_keitai"));
		tantousha.setMeeru(request.getParameter("meeru"));

		shiyoushaService.updateTantousha(tantousha, tantoushaId);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"담당자 정보가 수정되었습니다。/ 担当者情報が更新されました。\"}");
		out.flush();
		return null;
	}

	// 모든 부서 목록 조회 / すべての部署リストの照会
	public List<Busho> getBushoList() throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return BushoDao.selectAllBusho(conn);
		}
	}

	// 급여 지급 정보 삽입 / 給与支給情報の挿入
	private String insertKyuuyoShikyuuJouhou(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KyuuyoShikyuuJouhou kyuuyoShikyuuJouhou = new KyuuyoShikyuuJouhou();
		kyuuyoShikyuuJouhou
				.setKyuuyoSanteiKaishi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
		kyuuyoShikyuuJouhou
				.setKyuuyoSanteiShuuryou(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
		kyuuyoShikyuuJouhou
				.setKyuuyoShikyuu_bi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("payDate")));
		kyuuyoShikyuuJouhou.setKinyuuKikan(request.getParameter("bank"));
		kyuuyoShikyuuJouhou.setKouza_bangou(request.getParameter("accountNumber"));
		kyuuyoShikyuuJouhou.setYokinShaMeigi(request.getParameter("accountHolder"));

		shiyoushaService.insertShikyuuJouhou(kyuuyoShikyuuJouhou);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"급여 지급 정보가 저장되었습니다。/ 給与支給情報が保存されました。\"}");
		out.flush();
		return null;
	}

	// 급여 지급 정보 업데이트 / 給与支給情報の更新
	private String updateKyuuyoShikyuuJouhou(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KyuuyoShikyuuJouhou kyuuyoShikyuuJouhou = new KyuuyoShikyuuJouhou();
		kyuuyoShikyuuJouhou
				.setKyuuyoSanteiKaishi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
		kyuuyoShikyuuJouhou
				.setKyuuyoSanteiShuuryou(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
		kyuuyoShikyuuJouhou
				.setKyuuyoShikyuu_bi(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("payDate")));
		kyuuyoShikyuuJouhou.setKinyuuKikan(request.getParameter("bank"));
		kyuuyoShikyuuJouhou.setKouza_bangou(request.getParameter("accountNumber"));
		kyuuyoShikyuuJouhou.setYokinShaMeigi(request.getParameter("accountHolder"));

		shiyoushaService.updateShikyuuJouhou(kyuuyoShikyuuJouhou);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"success\": true, \"message\": \"급여 지급 정보가 수정되었습니다。/ 給与支給情報が更新されました。\"}");
		out.flush();
		return null;
	}

	// 회사 정보 조회 / 会社情報の照会
	private String getKaishaById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int kaishaId = Integer.parseInt(request.getParameter("kaishaId"));
		KaishaJouhou kaisha = shiyoushaService.getKaishaById(kaishaId);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("companyName", kaisha.getKaisha_mei());
		jsonObject.put("ceo", kaisha.getDaihyousha_mei());
		jsonObject.put("businessNumber", kaisha.getJigyousha_touroku_bango());
		jsonObject.put("registrationNumber", kaisha.getHoujin_touroku_bango());
		jsonObject.put("establishmentDate", kaisha.getSetsuribi());
		jsonObject.put("homepage", kaisha.getHomepage());
		jsonObject.put("zipcode", kaisha.getYuubin_bango());
		jsonObject.put("address", kaisha.getJigyousho_jyuusho());
		jsonObject.put("phone", kaisha.getDenwa_kaisha());
		jsonObject.put("fax", kaisha.getFakkusu_bango());
		jsonObject.put("industry", kaisha.getGyoutai());
		jsonObject.put("category", kaisha.getShumoku());

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		return null;
	}

	// 담당자 정보 조회 / 担当者情報の照会
	private String getTantoushaById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int tantoushaId = Integer.parseInt(request.getParameter("tantoushaId"));
		Tantousha tantousha = shiyoushaService.getTantoushaById(tantoushaId);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("namae_kana", tantousha.getNamae_kana());
		jsonObject.put("denwa_tantousha", tantousha.getDenwa_tantousha());
		jsonObject.put("denwa_keitai", tantousha.getDenwa_keitai());
		jsonObject.put("meeru", tantousha.getMeeru());

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		return null;
	}

	// 급여 지급 정보 조회 / 給与支給情報の照会
	private String getKyuuyoShikyuuJouhou(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int kaishaId = Integer.parseInt(request.getParameter("kaishaId"));
		KyuuyoShikyuuJouhou kyuuyoShikyuuJouhou = shiyoushaService.getShikyuuJouhouByKaishaId(kaishaId);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("startDate", kyuuyoShikyuuJouhou.getKyuuyoSanteiKaishi());
		jsonObject.put("endDate", kyuuyoShikyuuJouhou.getKyuuyoSanteiShuuryou());
		jsonObject.put("payDate", kyuuyoShikyuuJouhou.getKyuuyoShikyuu_bi());
		jsonObject.put("bank", kyuuyoShikyuuJouhou.getKinyuuKikan());
		jsonObject.put("accountNumber", kyuuyoShikyuuJouhou.getKouza_bangou());
		jsonObject.put("accountHolder", kyuuyoShikyuuJouhou.getYokinShaMeigi());

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toString());
		out.flush();
		return null;
	}
}
