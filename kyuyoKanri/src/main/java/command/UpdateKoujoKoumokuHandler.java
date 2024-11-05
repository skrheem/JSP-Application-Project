package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.UpdateKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 M버튼으로 공제항목을 수정하는 클래스
//給与入力・管理ページでMボタンを使用して控除項目を修正するクラス
public class UpdateKoujoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UpdateKoumokuJouhouService us = new UpdateKoumokuJouhouService();

		Integer koujokoumoku_id = Integer.parseInt(req.getParameter("koujokoumoku_id"));
		String koujokoumoku_mei = req.getParameter("koujokoumoku_mei");
		String keisanHouhou = req.getParameter("keisanHouhou");
		String zenshadani = req.getParameter("zenshadani");
		String bikou = req.getParameter("bikou");

		int rValue = us.updateKoujoKoumoku(koujokoumoku_id, koujokoumoku_mei, keisanHouhou, zenshadani, bikou);
		if (rValue > 0) {
			// 修正成功 수정 성공
			res.getWriter().write("{\"status\":\"success\", \"message\":\"修正成功\"}");
		} else {
			// 修正失敗 수정 실패
			res.getWriter().write("{\"status\":\"fail\", \"message\":\"修正失敗\"}");
		}
		return null;
	}

}
