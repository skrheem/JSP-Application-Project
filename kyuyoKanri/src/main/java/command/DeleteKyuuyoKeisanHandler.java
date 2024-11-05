package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKyuuyoKeisanService;

//임세규 林世圭
//급여입력/관리 페이지의 선택한 사원의 급여계산기록을 삭제
//給与入力・管理ページで選択した社員の給与計算を削除
public class DeleteKyuuyoKeisanHandler implements CommandHandler{
	
	private DeleteKyuuyoKeisanService ds = new DeleteKyuuyoKeisanService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
		int result = ds.deleteKyuuyoKeisanKiroku(shain_id, kyuuyoGatsu, kyuuyoJisuu);
        System.out.println("삭제된 레코드의 수 : " + result);
        
        // 削除機能の結果を転送 삭제 여부의 결과를 반환
        res.setContentType("application/json; charset=UTF-8");
        
        if (result > 0) {
        	// 削除成功 삭제성공
            res.getWriter().write("{\"status\":\"success\", \"message\":\"削除成功 \"}");
        } else {
        	// 削除失敗 삭제실패
            res.getWriter().write("{\"status\":\"fail\", \"message\":\"削除失敗 \"}");
        }
        
		return null;
	}

}
