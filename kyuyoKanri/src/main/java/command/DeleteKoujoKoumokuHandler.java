package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지의 M 버튼(공제항목) "삭제" 버튼 기능
//給与入力・管理ページのMボタン(控除項目)機能
public class DeleteKoujoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		DeleteKoumokuJouhouService ds = new DeleteKoumokuJouhouService();

		Integer koujokoumoku_id = Integer.parseInt(req.getParameter("koujokoumoku_id"));
		String koujokoumoku_mei = req.getParameter("koujokoumoku_mei");

		// 基本控除項目の削除を防ぐ 기본공제항목은 삭제 불가
		if (koujokoumoku_mei.equals("国民年金") || koujokoumoku_mei.equals("健康保険") || koujokoumoku_mei.equals("長期介護保険")
				|| koujokoumoku_mei.equals("雇用保険") || koujokoumoku_mei.equals("所得税")
				|| koujokoumoku_mei.equals("地方所得税")) {
			return null;
		}
		// 基本控除項目じゃないなら削除可能 기본공제항목이 아니라면 삭제 가능
		else {
			int rValue = ds.deleteKoujoKoumoku(koujokoumoku_id);
			if (rValue > 0) {
	            // 削除成功 삭제 성공
	            res.getWriter().write("{\"status\":\"success\", \"message\":\"削除成功\"}");
	        } else {
	            // 削除失敗 삭제 실패
	            res.getWriter().write("{\"status\":\"fail\", \"message\":\"削除失敗\"}");
	        }
		}

		return null;
	}

}
