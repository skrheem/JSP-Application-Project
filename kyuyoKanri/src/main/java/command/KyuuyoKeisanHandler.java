package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.KoujoKoumoku;
import model.KyuuyoKoumoku;
import model.ShainKyuuyoKeisanKiroku;
import mvc.command.CommandHandler;
import service.getKoumokuJouhouService;
import service.kyuuyoKanriShainListService;

public class KyuuyoKeisanHandler implements CommandHandler {
    private static final String FORM_VIEW = "/WEB-INF/view/ShainKyuuyoKeisan.jsp";
    
    private kyuuyoKanriShainListService ks = new kyuuyoKanriShainListService();
    private getKoumokuJouhouService gs = new getKoumokuJouhouService();
    
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET"))
            return processForm(req, res);
        else if (req.getMethod().equalsIgnoreCase("POST"))
            return processSubmit(req, res);
        else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    // 급여입력/관리 페이지에 GET 요청으로 접속 시 현재 시간을 기준으로 한 귀속연월로 설정하고 급여 차수는 1차로 설정한다.
    // 급여항목과 공제항목 값을 가져온다.
    private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	String koukinzei = req.getParameter("koukinzei");
    	ArrayList<ShainKyuuyoKeisanKiroku> skList = ks.getShainList(koukinzei);
    	ArrayList<KyuuyoKoumoku> kyuuyoList = gs.getKyuuyoKoumokuJouhou();
        ArrayList<KoujoKoumoku> kihonKoujoList = gs.getKihonKoujoKoumokuJouhou();
        ArrayList<KoujoKoumoku> koujoList = gs.getKoujoKoumokuJouhou();
        
        req.getSession().setAttribute("skList", skList);
        req.getSession().setAttribute("kyuuyoList", kyuuyoList);
        req.getSession().setAttribute("kihonKoujoList", kihonKoujoList);
        req.getSession().setAttribute("koujoList", koujoList);
        return FORM_VIEW;
    }
    
    private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
    	
    	String koukinzei = req.getParameter("koukinzei");
        String year = req.getParameter("kyuuyoNen");
        String month = req.getParameter("kyuuyoGatsu");
        String jisuu = req.getParameter("kyuuyoJisuu");
        
        ArrayList<ShainKyuuyoKeisanKiroku> skList = ks.getPostShainList(year, month, jisuu, koukinzei);

        req.getSession().setAttribute("skList", skList);
        req.getSession().setAttribute("selectedYear", year);
        req.getSession().setAttribute("selectedMonth", month);
        req.getSession().setAttribute("selectedJisuu", jisuu);

        // 필요 시 추가 속성 설정
        return FORM_VIEW;
    }
}