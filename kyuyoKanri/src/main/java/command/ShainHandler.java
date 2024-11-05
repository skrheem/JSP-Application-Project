package command;

//김찬호 金燦鎬
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kihonkankyousettei.model.Shain;
import mvc.command.CommandHandler;
import service.ShainService;

/**
 * ShainHandler는 사원 관련 요청을 처리하는 핸들러 클래스입니다.
 * ShainHandlerは社員関連リクエストを処理するハンドラークラスです。
 */
public class ShainHandler extends HttpServlet implements CommandHandler {
	private static final long serialVersionUID = 1L;
	private ShainService shainService = new ShainService();

	/**
	 * GET 요청을 처리하는 메서드입니다. GETリクエストを処理するメソッドです。
	 *
	 * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
	 * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
	 * @throws ServletException 서블릿 처리 중 발생할 수 있는 예외 / サーブレット処理中に発生する可能性のある例外
	 * @throws IOException      입출력 처리 중 발생할 수 있는 예외 / 入出力処理中に発生する可能性のある例外
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String command = request.getParameter("command");
			String action = request.getParameter("action");

			// command와 action이 null이 아닌지 확인
			if ("ShainHandler".equals(command) && "getAllShain".equals(action)) {
				getAllShain(response); // getAllShain 호출
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid command or action parameter");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
		} finally {
			response.getWriter().flush();
		}
	}

	/**
	 * action에 따른 처리를 수행하는 메서드입니다. actionに応じた処理を実行するメソッドです。
	 *
	 * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
	 * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
	 * @param action   실행할 작업 / 実行するアクション
	 * @throws IOException 입출력 오류 발생 시 / 入出力エラー発生時
	 */
	private void processForm(HttpServletRequest request, HttpServletResponse response, String action)
			throws IOException {
		response.setContentType("application/json; charset=UTF-8");

		try {
			switch (action) {
			case "getAllShain":
				getAllShain(response); // 모든 사원 가져오기 / すべての社員の取得
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
		}
	}

	/**
	 * 모든 사원 ID 목록을 JSON 형식으로 반환하는 메서드입니다. すべての社員IDリストをJSON形式で返すメソッドです。
	 *
	 * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
	 * @throws SQLException 데이터베이스 처리 중 오류 발생 시 / データベース処理中にエラーが発生した場合
	 * @throws IOException  응답 작성 중 오류 발생 시 / 応答作成中にエラーが発生した場合
	 */
	private void getAllShain(HttpServletResponse response) throws SQLException, IOException {
		ArrayList<Shain> shainList;
		try {
			shainList = shainService.getShainList(); // 사원 목록 가져오기 / 社員リストの取得
			System.out.println("Shain List: " + shainList); // 데이터가 제대로 반환되는지 확인 / データが正しく返されるか確認
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"사원 목록을 가져오는 중 오류가 발생했습니다: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "社員リストの取得中にエラーが発生しました: " + e.getMessage());
			return;
		}

		if (shainList == null || shainList.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT, "사원 데이터가 없습니다。/ 社員データがありません。");
			return;
		}

		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("[");

		// JSON 수동 생성 (ID만 포함) / JSONを手動で作成（IDのみを含む）
		for (int i = 0; i < shainList.size(); i++) {
			Shain shain = shainList.get(i);
			jsonBuilder.append("{\"shain_id\":").append(shain.getShain_id()).append("}");
			if (i < shainList.size() - 1) {
				jsonBuilder.append(","); // 마지막 요소가 아닐 경우 쉼표 추가 / 最後の要素でない場合はカンマを追加
			}
		}
		jsonBuilder.append("]");

		response.getWriter().write(jsonBuilder.toString()); // JSON 응답 / JSON応答
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null; // 이 부분은 필요에 따라 구현 / この部分は必要に応じて実装
	}
}
