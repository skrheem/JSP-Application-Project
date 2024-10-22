package model;

import java.util.Date;

public class KintaiKiroku {
	
	private Integer KintaiKiroku_kiroku_id;
	private Integer shain_id;
	private Integer KintaiKiroku_id;
	private Date KintaiKiroku_kaishi;
	private Date KintaiKiroku_shuuryou;
	private Integer KintaiKiroku_nissuu;
	private Integer teate;
	private String tekiyou;
	private Date touroku_nengappi;
	private Date nyuuryoku_bi;

	public KintaiKiroku(Integer KintaiKiroku_kiroku_id, Integer shain_id, Integer KintaiKiroku_id, Date KintaiKiroku_kaishi, Date KintaiKiroku_shuuryou,
			Integer KintaiKiroku_nissuu, Integer teate, String tekiyou, Date touroku_nengappi, Date nyuuryoku_bi) {
		super();
		this.KintaiKiroku_kiroku_id = KintaiKiroku_kiroku_id;
		this.shain_id = shain_id;
		this.KintaiKiroku_id = KintaiKiroku_id;
		this.KintaiKiroku_kaishi = KintaiKiroku_kaishi;
		this.KintaiKiroku_shuuryou = KintaiKiroku_shuuryou;
		this.KintaiKiroku_nissuu = KintaiKiroku_nissuu;
		this.teate = teate;
		this.tekiyou = tekiyou;
		this.touroku_nengappi = touroku_nengappi;
		this.nyuuryoku_bi = nyuuryoku_bi;
	}

	public Integer getKintaiKiroku_kiroku_id() {
		return KintaiKiroku_kiroku_id;
	}
	public void setKintaiKiroku_kiroku_id(Integer KintaiKiroku_kiroku_id) {
		this.KintaiKiroku_kiroku_id = KintaiKiroku_kiroku_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public Integer getKintaiKiroku_id() {
		return KintaiKiroku_id;
	}
	public void setKintaiKiroku_id(Integer KintaiKiroku_id) {
		this.KintaiKiroku_id = KintaiKiroku_id;
	}

	public Date getKintaiKiroku_kaishi() {
		return KintaiKiroku_kaishi;
	}
	public void setKintaiKiroku_kaishi(Date KintaiKiroku_kaishi) {
		this.KintaiKiroku_kaishi = KintaiKiroku_kaishi;
	}

	public Date getKintaiKiroku_shuuryou() {
		return KintaiKiroku_shuuryou;
	}
	public void setKintaiKiroku_shuuryou(Date KintaiKiroku_shuuryou) {
		this.KintaiKiroku_shuuryou = KintaiKiroku_shuuryou;
	}

	public Integer getKintaiKiroku_nissuu() {
		return KintaiKiroku_nissuu;
	}
	public void setKintaiKiroku_nissuu(Integer KintaiKiroku_nissuu) {
		this.KintaiKiroku_nissuu = KintaiKiroku_nissuu;
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
		
}
