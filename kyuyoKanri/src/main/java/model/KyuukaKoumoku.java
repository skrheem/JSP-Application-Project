package model;

import java.util.Date;

public class KyuukaKoumoku {
	
	private Integer kyuukaKoumoku_id;
	private String kyuukaShurui;
	private Date tekiyouKaishi;
	private Date tekiyouShuuryou;
	private char shiyouUmu;
	
	public KyuukaKoumoku(Integer kyuukaKoumoku_id, String kyuukaShurui, Date tekiyouKaishi, Date tekiyouShuuryou,
			char shiyouUmu) {
		super();
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.kyuukaShurui = kyuukaShurui;
		this.tekiyouKaishi = tekiyouKaishi;
		this.tekiyouShuuryou = tekiyouShuuryou;
		this.shiyouUmu = shiyouUmu;
	}

	public Integer getKyuukaKoumoku_id() {
		return kyuukaKoumoku_id;
	}
	public void setKyuukaKoumoku_id(Integer kyuukaKoumoku_id) {
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
	}

	public String getKyuukaShurui() {
		return kyuukaShurui;
	}
	public void setKyuukaShurui(String kyuukaShurui) {
		this.kyuukaShurui = kyuukaShurui;
	}

	public Date getTekiyouKaishi() {
		return tekiyouKaishi;
	}
	public void setTekiyouKaishi(Date tekiyouKaishi) {
		this.tekiyouKaishi = tekiyouKaishi;
	}

	public Date getTekiyouShuuryou() {
		return tekiyouShuuryou;
	}
	public void setTekiyouShuuryou(Date tekiyouShuuryou) {
		this.tekiyouShuuryou = tekiyouShuuryou;
	}

	public char getShiyouUmu() {
		return shiyouUmu;
	}
	public void setShiyouUmu(char shiyouUmu) {
		this.shiyouUmu = shiyouUmu;
	}

	
}
