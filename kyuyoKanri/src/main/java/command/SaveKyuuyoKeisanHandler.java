package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.UpdateKyuuyoKeisanKirokuService;

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
        
        // 성공 여부에 따른 응답 전송
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
