package command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keisan.model.KoujoKoumoku;
import keisan.model.KyuuyoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지의 초기상태를 구성하는 클래스
//給与入力・管理ページの初期状態を構成するクラス
public class KyuuyoKeisanHandler implements CommandHandler {
    private static final String FORM_VIEW = "/WEB-INF/view/ShainKyuuyoKeisan.jsp";
   
    private GetKoumokuJouhouService gs = new GetKoumokuJouhouService();
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	ArrayList<KyuuyoKoumoku> kyuuyoList = gs.getKyuuyoKoumokuJouhou();
        ArrayList<KoujoKoumoku> kihonKoujoList = gs.getKihonKoujoKoumokuJouhou();
        ArrayList<KoujoKoumoku> koujoList = gs.getKoujoKoumokuJouhou();

        req.getSession().setAttribute("kyuuyoList", kyuuyoList);
        req.getSession().setAttribute("kihonKoujoList", kihonKoujoList);
        req.getSession().setAttribute("koujoList", koujoList);
        return FORM_VIEW;
    }
}