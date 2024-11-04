package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKyuuyoKeisanService;

// 임세규 林世圭
// 급여입력/관리 페이지의 "전체삭제" 버튼 기능
// 給与入力・管理ページの”全体削除“ボタン機能
public class DeleteAllKyuuyoKeisanHandler implements CommandHandler{
	
	private DeleteKyuuyoKeisanService ds = new DeleteKyuuyoKeisanService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
		int result = ds.deleteAllKyuuyoKeisanKiroku(kyuuyoGatsu, kyuuyoJisuu);
        
        // 削除機能の結果を転送
        res.setContentType("application/json; charset=UTF-8");
        
        if (result > 0) {
            // 削除成功
            res.getWriter().write("{\"status\":\"success\", \"message\":\"削除成功\"}");
        } else {
            // 削除失敗
            res.getWriter().write("{\"status\":\"fail\", \"message\":\"削除失敗\"}");
        }
		
		return null;
	}

}
