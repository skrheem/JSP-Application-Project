package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKyuuyoKeisanService;

//임세규 林世圭
//급여입력/관리 페이지의 M 버튼(지급항목) "삭제" 버튼 기능
//給与入力・管理ページのMボタン(給与項目)機能
public class DeleteKyuuyoKeisanHandler implements CommandHandler{
	
	private DeleteKyuuyoKeisanService ds = new DeleteKyuuyoKeisanService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
		int result = ds.deleteKyuuyoKeisanKiroku(shain_id, kyuuyoGatsu, kyuuyoJisuu);
        System.out.println("삭제된 레코드의 수 : " + result);
        
        // 削除機能の結果を転送
        // 삭제 여부의 결과를 반환
        res.setContentType("application/json; charset=UTF-8");
        
        if (result > 0) {
        	// 削除成功
        	// 삭제성공
            res.getWriter().write("{\"status\":\"success\", \"message\":\"삭제가 완료되었습니다.\"}");
        } else {
        	// 削除失敗
        	// 삭제실패
            res.getWriter().write("{\"status\":\"fail\", \"message\":\"삭제에 실패했습니다.\"}");
        }
        
		return null;
	}

}
