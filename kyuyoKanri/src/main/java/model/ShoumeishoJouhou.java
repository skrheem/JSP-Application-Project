package model;

import oracle.sql.DATE;

public class ShoumeishoJouhou {
	
	private int hakkouBangou;
	private int shain_id;
	private String kubun;
	private DATE hakkou_bi;
	private String hakkouYouto;
	public ShoumeishoJouhou(int hakkouBangou, int shain_id, String kubun, DATE hakkou_bi, String hakkouYouto) {
		super();
		this.hakkouBangou = hakkouBangou;
		this.shain_id = shain_id;
		this.kubun = kubun;
		this.hakkou_bi = hakkou_bi;
		this.hakkouYouto = hakkouYouto;
	}
	public int getHakkouBangou() {
		return hakkouBangou;
	}
	public void setHakkouBangou(int hakkouBangou) {
		this.hakkouBangou = hakkouBangou;
	}
	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}
	public DATE getHakkou_bi() {
		return hakkou_bi;
	}
	public void setHakkou_bi(DATE hakkou_bi) {
		this.hakkou_bi = hakkou_bi;
	}
	public String getHakkouYouto() {
		return hakkouYouto;
	}
	public void setHakkouYouto(String hakkouYouto) {
		this.hakkouYouto = hakkouYouto;
	}
	
	

}
