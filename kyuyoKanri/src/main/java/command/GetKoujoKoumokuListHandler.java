package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.KoujoKoumoku;
import mvc.command.CommandHandler;
import service.GetKoumokuJouhouService;
import util.ObjectFormatter;

public class GetKoujoKoumokuListHandler implements CommandHandler {

	private GetKoumokuJouhouService koumokuService = new GetKoumokuJouhouService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArrayList<KoujoKoumoku> kList = koumokuService.getKoujoKoumokuJouhou();

		// JSON 배열 생성
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

        // JSON 응답 설정
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
