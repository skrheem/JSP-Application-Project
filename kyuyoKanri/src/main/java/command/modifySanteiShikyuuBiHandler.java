package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import service.updateKyuuyoKeisanService;

public class modifySanteiShikyuuBiHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/ShainKyuuyoKeisan.jsp";
	
	private updateKyuuyoKeisanService us = new updateKyuuyoKeisanService();
	
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
	    String exSantei_kaishi = req.getParameter("standard-period-start");
	    String exSantei_shuuryou = req.getParameter("standard-period-end");
	    String exShikyuu_bi = req.getParameter("pay-date");
	    String koukinzei_kubun = req.getParameter("koukinzei");

	    System.out.println("santei_kaishi: " + santei_kaishi);
	    System.out.println("santei_shuuryou: " + santei_shuuryou);
	    System.out.println("shikyuu_bi: " + shikyuu_bi);
	    System.out.println("EXsantei_kaishi: " + exSantei_kaishi);
	    System.out.println("EXsantei_shuuryou: " + exSantei_shuuryou);
	    System.out.println("EXshikyuu_bi: " + exShikyuu_bi);
	    System.out.println("koukinzei_kubun: " + koukinzei_kubun);
//		String santei_kaishi = req.getParameter("santei_kaishi");
//		String santei_shuuryou = req.getParameter("santei_shuuryou");
//		String shikyuu_bi = req.getParameter("shikyuu_bi");
//		String exSantei_kaishi = splitDate(req.getParameter("EXsantei_kaishi"));
//		String exSantei_shuuryou = splitDate(req.getParameter("EXsantei_shuuryou"));
//		String exShikyuu_bi = splitDate(req.getParameter("EXshikyuu_bi"));
//		String koukinzei_kubun = req.getParameter("koukinzei");


		
		System.out.println("업데이트 핸들러 접속");
		System.out.println("업데이트 할 값 : ");
		System.out.println("산정개시일 : " + santei_kaishi);
		System.out.println("산정종료일 : " + santei_shuuryou);
		System.out.println("급여지급일 : " + shikyuu_bi);
		System.out.println("업데이트 대상 : ");
		System.out.println("산정개시일 : " + exSantei_kaishi);
		System.out.println("산정종료일 : " + exSantei_shuuryou);
		System.out.println("급여지급일 : " + exShikyuu_bi);
		System.out.println("갑근세구분 : " + koukinzei_kubun);
		int rValue = us.updateSanteiShikyuuBi(santei_kaishi, santei_shuuryou, shikyuu_bi, exSantei_kaishi, exSantei_shuuryou, exShikyuu_bi, koukinzei_kubun);
		
		//System.out.println("쿼리로 영향 받은 레코드 수 : " + rValue);
		
		return "";
	}
	
	public String splitDate(String dateTime) {
        int spaceIndex = dateTime.indexOf(" ");
        String datePart = dateTime.substring(0, spaceIndex);
        
        return datePart;
	}
}
