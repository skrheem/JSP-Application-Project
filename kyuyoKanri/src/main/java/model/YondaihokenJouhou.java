package model;

import oracle.sql.DATE;

public class YondaihokenJouhou {

	private Integer shain_id;
	private String hokenKubun;
	private String kigouBangou;
	private DATE shutoku_bi;
	private DATE soshitsu_bi;
	public YondaihokenJouhou(Integer shain_id, String hokenKubun, String kigouBangou, DATE shutoku_bi, DATE soshitsu_bi) {
		super();
		this.shain_id = shain_id;
		this.hokenKubun = hokenKubun;
		this.kigouBangou = kigouBangou;
		this.shutoku_bi = shutoku_bi;
		this.soshitsu_bi = soshitsu_bi;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getHokenKubun() {
		return hokenKubun;
	}
	public void setHokenKubun(String hokenKubun) {
		this.hokenKubun = hokenKubun;
	}
	public String getKigouBangou() {
		return kigouBangou;
	}
	public void setKigouBangou(String kigouBangou) {
		this.kigouBangou = kigouBangou;
	}
	public DATE getShutoku_bi() {
		return shutoku_bi;
	}
	public void setShutoku_bi(DATE shutoku_bi) {
		this.shutoku_bi = shutoku_bi;
	}
	public DATE getSoshitsu_bi() {
		return soshitsu_bi;
	}
	public void setSoshitsu_bi(DATE soshitsu_bi) {
		this.soshitsu_bi = soshitsu_bi;
	}
	
	
}
