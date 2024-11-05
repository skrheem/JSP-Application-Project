package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.InsertKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 M버튼으로 공제항목을 추가하는 클래스
//給与入力・管理ページでMボタンを使用して控除項目を追加するクラス
public class InsertKoujoKoumokuHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		InsertKoumokuJouhouService is = new InsertKoumokuJouhouService();
        String koujokoumoku_mei = req.getParameter("koujokoumoku_mei");
        String keisanHouhou = req.getParameter("keisanHouhou");
        String zenshadani = req.getParameter("zenshadani");
        String bikou = req.getParameter("bikou");
        double koujoRitsu = 0;
        
        int rValue = is.insertKoujoKoumoku(koujokoumoku_mei, koujoRitsu, keisanHouhou, zenshadani, 'Y', null, null, bikou);
		return null;
	}

}
