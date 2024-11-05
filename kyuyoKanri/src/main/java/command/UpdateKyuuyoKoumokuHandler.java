package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.UpdateKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 M버튼으로 급여항목을 수정하는 클래스
//給与入力・管理ページでMボタンを使用して給与項目を修正するクラス
public class UpdateKyuuyoKoumokuHandler implements CommandHandler {

	@Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UpdateKoumokuJouhouService us = new UpdateKoumokuJouhouService();

        Integer kyuuyokoumoku_id = Integer.parseInt(req.getParameter("kyuuyokoumoku_id"));
        String kyuuyokoumoku_mei = req.getParameter("kyuuyokoumoku_mei");
        String keisanhouhou = req.getParameter("keisanhouhou");
        String zenshadani = req.getParameter("zenshadani");
        
         int rValue = us.updateKyuuyoKoumoku(kyuuyokoumoku_id, kyuuyokoumoku_mei, keisanhouhou, zenshadani, kyuuyokoumoku_mei);
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
