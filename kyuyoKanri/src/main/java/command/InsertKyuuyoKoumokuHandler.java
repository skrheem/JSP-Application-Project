package command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.InsertKoumokuJouhouService;

public class InsertKyuuyoKoumokuHandler implements CommandHandler{

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	InsertKoumokuJouhouService is = new InsertKoumokuJouhouService();
        Integer kyuuyokoumoku_id = Integer.parseInt(req.getParameter("kyuuyokoumoku_id"));
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
            System.out.println("hikazeigendogaku 변환 오류: " + e.getMessage());
        }
        try {
            ikkatsushiharaigaku = new BigDecimal(req.getParameter("ikkatsushiharaigaku"));
        } catch (NumberFormatException e) {
            System.out.println("ikkatsushiharaigaku 변환 오류: " + e.getMessage());
        }
       int rValue = is.insertKyuuyoKoumoku(kyuuyokoumoku_mei, kazeikubun, hikazeigendogaku, bikou, keisanhouhou, zenshadani, kintairenkei, ikkatsushiharai, ikkatsushiharaigaku, shiyouUmu);
        
        return null;
    }
	

}
