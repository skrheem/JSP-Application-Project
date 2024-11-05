package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import service.UpdateKyuuyoKeisanService;

//임세규 林世圭
//급여입력/관리 페이지에서 산정기간과 급여지급일을 수정하는 클래스
//給与入力・管理ページで算定期間と給与支給日を修正するクラス
public class ModifySanteiShikyuuBiHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/ShainKyuuyoKeisan.jsp";

	private UpdateKyuuyoKeisanService us = new UpdateKyuuyoKeisanService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("GET 요청");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String santei_kaishi = req.getParameter("santei_kaishi");
		String santei_shuuryou = req.getParameter("santei_shuuryou");
		String shikyuu_bi = req.getParameter("shikyuu_bi");
		String exSantei_kaishi = req.getParameter("EXsantei_kaishi");
		String exSantei_shuuryou = req.getParameter("EXsantei_shuuryou");
		String exShikyuu_bi = req.getParameter("EXshikyuu_bi");
		String koukinzei_kubun = req.getParameter("koukinzei");

		int rValue = us.updateSanteiShikyuuBi(santei_kaishi, santei_shuuryou, shikyuu_bi, exSantei_kaishi,
				exSantei_shuuryou, exShikyuu_bi, koukinzei_kubun);

		if (rValue > 0) {
			res.setContentType("text/plain");
			res.getWriter().write("success");
		} else {
			res.setContentType("text/plain");
			res.getWriter().write("fail");
		}
		return null;
	}
}
