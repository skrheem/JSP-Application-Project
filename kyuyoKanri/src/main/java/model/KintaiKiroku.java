package model;

import java.util.Date;

public class KintaiKiroku {
	
	private int kintai_kiroku_id;
	private int shain_id;
	private int kintai_id;
	private Date kintai_kaishi;
	private Date kintai_shuuryou;
	private int kintai_nissuu;
	private int teate;
	private String tekiyou;
	private Date touroku_nengappi;
	private Date nyuuryoku_bi;

	public KintaiKiroku(int kintai_kiroku_id, int shain_id, int kintai_id, Date kintai_kaishi, Date kintai_shuuryou,
			int kintai_nissuu, int teate, String tekiyou, Date touroku_nengappi, Date nyuuryoku_bi) {
		super();
		this.kintai_kiroku_id = kintai_kiroku_id;
		this.shain_id = shain_id;
		this.kintai_id = kintai_id;
		this.kintai_kaishi = kintai_kaishi;
		this.kintai_shuuryou = kintai_shuuryou;
		this.kintai_nissuu = kintai_nissuu;
		this.teate = teate;
		this.tekiyou = tekiyou;
		this.touroku_nengappi = touroku_nengappi;
		this.nyuuryoku_bi = nyuuryoku_bi;
	}

	public int getKintai_kiroku_id() {
		return kintai_kiroku_id;
	}
	public void setKintai_kiroku_id(int kintai_kiroku_id) {
		this.kintai_kiroku_id = kintai_kiroku_id;
	}

	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}

	public int getKintai_id() {
		return kintai_id;
	}
	public void setKintai_id(int kintai_id) {
		this.kintai_id = kintai_id;
	}

	public Date getKintai_kaishi() {
		return kintai_kaishi;
	}
	public void setKintai_kaishi(Date kintai_kaishi) {
		this.kintai_kaishi = kintai_kaishi;
	}

	public Date getKintai_shuuryou() {
		return kintai_shuuryou;
	}
	public void setKintai_shuuryou(Date kintai_shuuryou) {
		this.kintai_shuuryou = kintai_shuuryou;
	}

	public int getKintai_nissuu() {
		return kintai_nissuu;
	}
	public void setKintai_nissuu(int kintai_nissuu) {
		this.kintai_nissuu = kintai_nissuu;
	}

	public int getTeate() {
		return teate;
	}
	public void setTeate(int teate) {
		this.teate = teate;
	}

	public String getTekiyou() {
		return tekiyou;
	}
	public void setTekiyou(String tekiyou) {
		this.tekiyou = tekiyou;
	}

	public Date getTouroku_nengappi() {
		return touroku_nengappi;
	}
	public void setTouroku_nengappi(Date touroku_nengappi) {
		this.touroku_nengappi = touroku_nengappi;
	}

	public Date getNyuuryoku_bi() {
		return nyuuryoku_bi;
	}
	public void setNyuuryoku_bi(Date nyuuryoku_bi) {
		this.nyuuryoku_bi = nyuuryoku_bi;
	}
		
}
