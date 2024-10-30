package model;

import java.math.BigDecimal;

// 갑근세 데이터를 담는 클래스 
public class Koukinzei {
	private Integer shain_id;
	private String koukinzei_kubun;
	private BigDecimal shotokuzei_kouzoritsu;
	private BigDecimal chuushouKigyou;
	
	public Koukinzei() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Koukinzei(Integer shain_id, String koukinzei_kubun, BigDecimal shotokuzei_kouzoritsu,
			BigDecimal chuushouKigyou) {
		super();
		this.shain_id = shain_id;
		this.koukinzei_kubun = koukinzei_kubun;
		this.shotokuzei_kouzoritsu = shotokuzei_kouzoritsu;
		this.chuushouKigyou = chuushouKigyou;
	}
	
	public Integer getShain_id() {
		return shain_id;
	}
	
	public String getKoukinzei_kubun() {
		return koukinzei_kubun;
	}
	
	public BigDecimal getShotokuzei_kouzoritsu() {
		return shotokuzei_kouzoritsu;
	}
	
	public BigDecimal getChuushouKigyou() {
		return chuushouKigyou;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public void setKoukinzei_kubun(String koukinzei_kubun) {
		this.koukinzei_kubun = koukinzei_kubun;
	}

	public void setShotokuzei_kouzoritsu(BigDecimal shotokuzei_kouzoritsu) {
		this.shotokuzei_kouzoritsu = shotokuzei_kouzoritsu;
	}

	public void setChuushouKigyou(BigDecimal chuushouKigyou) {
		this.chuushouKigyou = chuushouKigyou;
	}
	
}
