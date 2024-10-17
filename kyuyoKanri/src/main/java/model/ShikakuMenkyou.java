package model;

import oracle.sql.DATE;

public class ShikakuMenkyou {

	private int shikaku_id;
	private int shain_id;
	private String shikaku_mei;
	private DATE shutoku_bi;
	private String hakkou_kikan;
	private String shoumei_bangou;
	private String bikou;
	public int getShikaku_id() {
		return shikaku_id;
	}
	
	public ShikakuMenkyou(int shikaku_id, int shain_id, String shikaku_mei, DATE shutoku_bi, String hakkou_kikan,
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

	public void setShikaku_id(int shikaku_id) {
		this.shikaku_id = shikaku_id;
	}
	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}
	public String getShikaku_mei() {
		return shikaku_mei;
	}
	public void setShikaku_mei(String shikaku_mei) {
		this.shikaku_mei = shikaku_mei;
	}
	public DATE getShutoku_bi() {
		return shutoku_bi;
	}
	public void setShutoku_bi(DATE shutoku_bi) {
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
