package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.UpdateKoumokuJouhouService;

public class UpdateKoujoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UpdateKoumokuJouhouService us = new UpdateKoumokuJouhouService();
		
		// 각 파라미터를 req.getParameter로 받아오기
        Integer koujokoumoku_id = Integer.parseInt(req.getParameter("koujokoumoku_id"));
        String koujokoumoku_mei = req.getParameter("koujokoumoku_mei");
        String keisanHouhou = req.getParameter("keisanHouhou");
        String zenshadani = req.getParameter("zenshadani");
        String bikou = req.getParameter("bikou");

        int rValue = us.updateKoujoKoumoku(koujokoumoku_id, koujokoumoku_mei, keisanHouhou, zenshadani, bikou);
        
		return null;
	}

}
