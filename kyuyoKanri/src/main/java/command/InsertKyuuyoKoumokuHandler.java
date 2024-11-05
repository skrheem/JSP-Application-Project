package command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.InsertKoumokuJouhouService;

//임세규 林世圭
//급여입력/관리 페이지에서 M버튼으로 급여항목을 추가하는 클래스
//給与入力・管理ページでMボタンを使用して給与項目を追加するクラス
public class InsertKyuuyoKoumokuHandler implements CommandHandler{

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	InsertKoumokuJouhouService is = new InsertKoumokuJouhouService();
        String kyuuyokoumoku_mei = req.getParameter("kyuuyokoumoku_mei");
        String kazeikubun = req.getParameter("kazeikubun");
        String keisanhouhou = req.getParameter("keisanhouhou");
        String zenshadani = req.getParameter("zenshadani");
        String kintairenkei = req.getParameter("kintairenkei");
        BigDecimal hikazeigendogaku = null;
        BigDecimal ikkatsushiharaigaku = null;
        String bikou = " ";
        String shiyouUmu = "Y";
        String ikkatsushiharai = (kintairenkei.equals("一括支給額") ? "Y" : "N");
        try {
            hikazeigendogaku = new BigDecimal(req.getParameter("hikazeigendogaku"));
        } catch (NumberFormatException e) {
            System.out.println("hikazeigendogaku 変換エラー: " + e.getMessage());
        }
        try {
            ikkatsushiharaigaku = new BigDecimal(req.getParameter("ikkatsushiharaigaku"));
        } catch (NumberFormatException e) {
            System.out.println("ikkatsushiharaigaku 変換エラー: " + e.getMessage());
        }
        
       int rValue = is.insertKyuuyoKoumoku(kyuuyokoumoku_mei, kazeikubun, hikazeigendogaku, bikou, keisanhouhou, zenshadani, kintairenkei, ikkatsushiharai, ikkatsushiharaigaku, shiyouUmu);
       if (rValue > 0) {
           // 挿入成功 삽입 성공
           res.getWriter().write("{\"status\":\"success\", \"message\":\"挿入成功\"}");
       } else {
           // 挿入失敗 삽입 실패
           res.getWriter().write("{\"status\":\"fail\", \"message\":\"挿入失敗\"}");
       }
        return null;
    }
	

}
