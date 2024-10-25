package model;

import java.util.Date;
// 최동주 崔東周
public class KintaiKiroku {
	
	private Integer Kintai_kiroku_id;
	private Integer shain_id;
	private Integer Kintai_id;
	private String Kintai_mei;
	private Date Kintai_kaishi;
	private Date Kintai_shuuryou;
	private Integer Kintai_nissuu;
	private Integer teate;
	private String tekiyou;
	private Date touroku_nengappi;
	private Date nyuuryoku_bi;
	
	public KintaiKiroku(Integer kintai_kiroku_id, Integer shain_id, Integer kintai_id, String kintai_mei, Date kintai_kaishi,
			Date kintai_shuuryou, Integer kintai_nissuu, Integer teate, String tekiyou, Date touroku_nengappi,
			Date nyuuryoku_bi) {
		super();
		this.Kintai_kiroku_id = kintai_kiroku_id;
		this.shain_id = shain_id;
		this.Kintai_id = kintai_id;
		this.Kintai_mei = kintai_mei;
		this.Kintai_kaishi = kintai_kaishi;
		this.Kintai_shuuryou = kintai_shuuryou;
		this.Kintai_nissuu = kintai_nissuu;
		this.teate = teate;
		this.tekiyou = tekiyou;
		this.touroku_nengappi = touroku_nengappi;
		this.nyuuryoku_bi = nyuuryoku_bi;
	}
	

	public Integer getKintai_kiroku_id() {
		return Kintai_kiroku_id;
	}
	public void setKintai_kiroku_id(Integer kintai_kiroku_id) {
		Kintai_kiroku_id = kintai_kiroku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getKintai_id() {
		return Kintai_id;
	}
	public void setKintai_id(Integer kintai_id) {
		Kintai_id = kintai_id;
	}
	//KintaiDao.java에서 추가
	public String getKintai_mei() {
		// TODO Auto-generated method stub
		return Kintai_mei;
	}
	public void setKintai_mei(String kintai_mei) {
		Kintai_mei = kintai_mei;
	}
	public Date getKintai_kaishi() {
		return Kintai_kaishi;
	}
	public void setKintai_kaishi(Date kintai_kaishi) {
		Kintai_kaishi = kintai_kaishi;
	}
	public Date getKintai_shuuryou() {
		return Kintai_shuuryou;
	}
	public void setKintai_shuuryou(Date kintai_shuuryou) {
		Kintai_shuuryou = kintai_shuuryou;
	}
	public Integer getKintai_nissuu() {
		return Kintai_nissuu;
	}
	public void setKintai_nissuu(Integer kintai_nissuu) {
		Kintai_nissuu = kintai_nissuu;
	}
	public Integer getTeate() {
		return teate;
	}
	public void setTeate(Integer teate) {
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
	
	//KintaiKirokuDao.java에서 추가
	public KintaiKiroku(Integer kintai_kiroku_id2, Date nyuuryoku_bi2, String kintai_mei2, Date kintai_kaishi2,
			Date kintai_shuuryou2, Integer kintai_nissuu2, Integer teate2, String tekiyou2) {
		// TODO Auto-generated constructor stub
		this.Kintai_kiroku_id = kintai_kiroku_id2;
		this.nyuuryoku_bi = nyuuryoku_bi2;
		this.Kintai_mei = kintai_mei2;
		this.Kintai_kaishi = kintai_kaishi2;
		this.Kintai_shuuryou = kintai_shuuryou2;
		this.Kintai_nissuu = kintai_nissuu2;
		this.teate = teate2;
		this.tekiyou = tekiyou2;
	}

	
}








