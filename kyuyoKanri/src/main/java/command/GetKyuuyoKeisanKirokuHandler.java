package command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import keisan.model.ShainKyuuyoKeisanKiroku;
import mvc.command.CommandHandler;
import service.KyuuyoKanriShainListService;

//임세규 林世圭
//급여입력/관리 페이지에서 출력할 사원별급여계산기록을 가져오는 클래스
//給与入力・管理ページで出力する社員別給与計算記録を取得するクラス
public class GetKyuuyoKeisanKirokuHandler implements CommandHandler {
	
	private KyuuyoKanriShainListService ks = new KyuuyoKanriShainListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("사원들의 계산기록 가져오기 핸들러");
	    String kyuuyoNendo = req.getParameter("kyuuyoNendo");
	    String kyuuyoGatsu = req.getParameter("kyuuyoGatsu");
	    String kyuuyoJisuu = req.getParameter("kyuuyoJisuu");
	    int koukinzeiType = Integer.parseInt(req.getParameter("koukinzei"));

	    ArrayList<ShainKyuuyoKeisanKiroku> skList = ks.getShainList(kyuuyoNendo, kyuuyoGatsu, kyuuyoJisuu, koukinzeiType);
	    System.out.println(skList.isEmpty());
	    
	    res.setContentType("application/json; charset=UTF-8");
	    PrintWriter out = res.getWriter();
	    
	    // 리스트가 비어 있을 때
	    if (skList.isEmpty()) {
	        JSONObject jsonResponse = new JSONObject();
	        jsonResponse.put("status", "empty");
	        jsonResponse.put("message", "검색 결과가 없습니다.");
	        out.print(jsonResponse.toString());
	    } else {
	        // 리스트가 비어 있지 않을 때, 데이터를 JSON 배열로 변환
	        JSONArray jsonArray = new JSONArray();
	        for (ShainKyuuyoKeisanKiroku s : skList) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("shain_id", s.getShain_id());
	            jsonObject.put("kubun", s.getKubun());
	            jsonObject.put("name", s.getNamae_kana());
	            jsonObject.put("busho_mei", s.getBusho_mei());
	            jsonObject.put("shikyuuSougaku", s.getShikyuuSougaku());
	            jsonObject.put("koujoSougaku", s.getKoujoSougaku());
	            jsonObject.put("jissai_kyuuyo", s.getJissai_kyuuyo());
	            jsonObject.put("kyuuyoSanteiKaishi", s.getKYUUYOSANTEIKAISHI());
	            jsonObject.put("kyuuyoSanteiShuuryou", s.getKYUUYOSANTEISHUURYOU());
	            jsonObject.put("kyuuyoShikyuuBi", s.getKYUUYO_SHIKYUUBI());
	            jsonObject.put("koukinzei", s.getKoukinzei_kubun());
	            jsonObject.put("kyuuyoGatsu", s.getKyuuyo_gatsu());
	            jsonArray.put(jsonObject);
	        }
	        out.print(jsonArray.toString());
	    }
	    out.flush();
	    return null;
	}
}
