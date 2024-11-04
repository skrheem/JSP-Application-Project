package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.DeleteKoumokuJouhouService;

public class DeleteKyuuyoKoumokuHandler implements CommandHandler {

	@Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		DeleteKoumokuJouhouService ds = new DeleteKoumokuJouhouService();
		

        Integer kyuuyokoumoku_id = Integer.parseInt(req.getParameter("kyuuyokoumoku_id"));
        String kyuuyokoumoku_mei = req.getParameter("kyuuyokoumoku_mei");

     
        if(kyuuyokoumoku_mei.equals("基本給") || kyuuyokoumoku_mei.equals("食費")) {
        	return null;
        } else {
            int rValue = ds.deleteKyuuyoKoumoku(kyuuyokoumoku_id);
        }
        
        return null;
    }

}
