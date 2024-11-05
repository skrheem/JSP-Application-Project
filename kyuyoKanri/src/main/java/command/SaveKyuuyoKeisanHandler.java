package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.UpdateKyuuyoKeisanKirokuService;

//임세규 林世圭
//급여입력/관리 페이지에서 사원의 급여계산기록을 저장하는 클래스
//給与入力・管理ページで社員の給与計算記録を保存するクラス
public class SaveKyuuyoKeisanHandler implements CommandHandler{

	private UpdateKyuuyoKeisanKirokuService is = new UpdateKyuuyoKeisanKirokuService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		int kyuuyoSougaku = Integer.parseInt(req.getParameter("kyuuyoSougaku"));
		int koujoSougaku = Integer.parseInt(req.getParameter("koujoSougaku"));
		int jissaiKyuuyo = Integer.parseInt(req.getParameter("jissaiKyuuyo"));
		
		int result = is.updateKyuuyoKeisan(shain_id, kyuuyoGatsu, kyuuyoJisuu, kyuuyoSougaku, koujoSougaku, jissaiKyuuyo);
        
        res.setContentType("application/json; charset=UTF-8");
        
        if (result > 0) {
            res.getWriter().write("{\"status\":\"success\", \"message\":\"저장되었습니다.\"}");
        } else {
            // 삭제 실패
            res.getWriter().write("{\"status\":\"fail\", \"message\":\"저장에 실패했습니다.\"}");
        }
		
		return null;
	}

}
