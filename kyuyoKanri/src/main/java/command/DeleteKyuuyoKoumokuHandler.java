package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.DeleteKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지의 M 버튼(지급항목) "삭제" 버튼 기능
//給与入力・管理ページのMボタン(給与項目)機能
public class DeleteKyuuyoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		DeleteKoumokuJouhouService ds = new DeleteKoumokuJouhouService();

		Integer kyuuyokoumoku_id = Integer.parseInt(req.getParameter("kyuuyokoumoku_id"));
		String kyuuyokoumoku_mei = req.getParameter("kyuuyokoumoku_mei");

		// 基本給与項目の削除を防ぐ 기본급여항목은 삭제 불가
		if (kyuuyokoumoku_mei.equals("基本給") || kyuuyokoumoku_mei.equals("食費")) {
			res.getWriter().write("{\"status\":\"fail\", \"message\":\"削除失敗 \"}");
			return null;
		} 
		// 基本給与項目じゃないなら削除可能 기본급여항목이 아니라면 삭제 가능
		else {
			int rValue = ds.deleteKyuuyoKoumoku(kyuuyokoumoku_id);
			if (rValue > 0) {
	        	// 削除成功 삭제성공
	            res.getWriter().write("{\"status\":\"success\", \"message\":\"削除成功 \"}");
	        } else {
	        	// 削除失敗 삭제실패
	            res.getWriter().write("{\"status\":\"fail\", \"message\":\"削除失敗 \"}");
	        }
		}
		

		return null;
	}

}
