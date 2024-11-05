package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import keisan.model.KoujoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;
import util.ObjectFormatter;

//임세규 林世圭
//급여입력/관리 페이지에서 출력할 공제항목들의 정보를 가져오는 클래스
//給与入力・管理ページで出力する控除項目の情報を取得するクラス
public class GetKoujoKoumokuListHandler implements CommandHandler {

	private GetKoumokuJouhouService koumokuService = new GetKoumokuJouhouService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArrayList<KoujoKoumoku> kList = koumokuService.getKoujoKoumokuJouhou();

        JSONArray jsonArray = new JSONArray();
        for (KoujoKoumoku k : kList) {
            JSONObject jsonObject = new JSONObject();
            
            jsonObject.put("koujokoumoku_id", k.getKoujoKoumoku_id());
            jsonObject.put("koujokoumoku_mei", k.getKoujoKoumoku_mei());
            jsonObject.put("koujoritsu", k.getKoujoRitsu());
            jsonObject.put("keisanhouhou", k.getKeisanHouhou());
            jsonObject.put("shiyouumu", String.valueOf(k.getShiyouUmu()));
            jsonObject.put("zenshadani", k.getZenshaDani());
            jsonObject.put("koujokoumokukubun", k.getKoujoKoumokuKubun());
            jsonObject.put("bikou", k.getBikou());
            
            jsonArray.put(jsonObject);
        }

        res.setContentType("application/json; charset=UTF-8");
        try {
            res.getWriter().write(jsonArray.toString());
            res.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}

}
