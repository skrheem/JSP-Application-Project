package command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.UpdateKoumokuJouhouService;

public class UpdateKyuuyoKoumokuHandler implements CommandHandler {

	@Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UpdateKoumokuJouhouService us = new UpdateKoumokuJouhouService();
        // 쿼리 스트링으로 전달된 파라미터를 변수에 저장
        Integer kyuuyokoumoku_id = Integer.parseInt(req.getParameter("kyuuyokoumoku_id"));
        String kyuuyokoumoku_mei = req.getParameter("kyuuyokoumoku_mei");
        String keisanhouhou = req.getParameter("keisanhouhou");
        String zenshadani = req.getParameter("zenshadani");
        
        int rValue = us.updateKyuuyoKoumoku(kyuuyokoumoku_id, kyuuyokoumoku_mei, keisanhouhou, zenshadani, kyuuyokoumoku_mei);
        return null;
    }

}
