package model;

import java.util.Date;

public class ShikakuMenkyou {

	private Integer shikaku_id;
	private Integer shain_id;
	private String shikaku_mei;
	private Date shutoku_bi;
	private String hakkou_kikan;
	private String shoumei_bangou;
	private String bikou;
	public Integer getShikaku_id() {
		return shikaku_id;
	}
	
	public ShikakuMenkyou(Integer shikaku_id, Integer shain_id, String shikaku_mei, Date shutoku_bi, String hakkou_kikan,
			String shoumei_bangou, String bikou) {
		super();
		this.shikaku_id = shikaku_id;
		this.shain_id = shain_id;
		this.shikaku_mei = shikaku_mei;
		this.shutoku_bi = shutoku_bi;
		this.hakkou_kikan = hakkou_kikan;
		this.shoumei_bangou = shoumei_bangou;
		this.bikou = bikou;
	}

	public void setShikaku_id(Integer shikaku_id) {
		this.shikaku_id = shikaku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getShikaku_mei() {
		return shikaku_mei;
	}
	public void setShikaku_mei(String shikaku_mei) {
		this.shikaku_mei = shikaku_mei;
	}
	public Date getShutoku_bi() {
		return shutoku_bi;
	}
	public void setShutoku_bi(Date shutoku_bi) {
		this.shutoku_bi = shutoku_bi;
	}
	public String getHakkou_kikan() {
		return hakkou_kikan;
	}
	public void setHakkou_kikan(String hakkou_kikan) {
		this.hakkou_kikan = hakkou_kikan;
	}
	public String getShoumei_bangou() {
		return shoumei_bangou;
	}
	public void setShoumei_bangou(String shoumei_bangou) {
		this.shoumei_bangou = shoumei_bangou;
	}
	public String getBikou() {
		return bikou;
	}
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}
	
}
