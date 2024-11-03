package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKyuuyoKeisanService;

public class DeleteKyuuyoKeisanHandler implements CommandHandler{
	
	private DeleteKyuuyoKeisanService ds = new DeleteKyuuyoKeisanService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//"deleteKeisanKiroku.do?shain_id=" + shain_id + "&kyuuyoGatsu=" + kyuuyoGatsu + "&kyuuyoJisuu=" + jisuu;
		
		int shain_id = Integer.parseInt(req.getParameter("shain_id"));
		String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
		String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
		
		int result = ds.deleteKyuuyoKeisanKiroku(shain_id, kyuuyoGatsu, kyuuyoJisuu);
        System.out.println("삭제된 레코드의 수 : " + result);
        
        // 성공 여부에 따른 응답 전송
        res.setContentType("application/json; charset=UTF-8");
        
        if (result > 0) {
            // 삭제 성공
            res.getWriter().write("{\"status\":\"success\", \"message\":\"삭제가 완료되었습니다.\"}");
        } else {
            // 삭제 실패
            res.getWriter().write("{\"status\":\"fail\", \"message\":\"삭제에 실패했습니다.\"}");
        }
        
		return null;
	}

}
