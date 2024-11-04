package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKoumokuJouhouService;

public class DeleteKoujoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		DeleteKoumokuJouhouService ds = new DeleteKoumokuJouhouService();
		
		// 각 파라미터를 req.getParameter로 받아오기
        Integer koujokoumoku_id = Integer.parseInt(req.getParameter("koujokoumoku_id"));
        String koujokoumoku_mei = req.getParameter("koujokoumoku_mei");
        
        if(koujokoumoku_mei.equals("国民年金") || koujokoumoku_mei.equals("健康保険") || koujokoumoku_mei.equals("長期介護保険") || koujokoumoku_mei.equals("雇用保険") || koujokoumoku_mei.equals("所得税") || koujokoumoku_mei.equals("地方所得税")) {
        	return null;
        } else {
        	// 항목명을 통해 기본 항목이라면 삭제 불가 처리
            int rValue = ds.deleteKoujoKoumoku(koujokoumoku_id);
        }
        	
		return null;
	}

}
