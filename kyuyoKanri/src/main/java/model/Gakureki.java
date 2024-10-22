package model;

import oracle.sql.DATE;

public class Gakureki {

	private Integer gakureki_id;
	private Integer shain_id;
	private String gakurekiKubun;
	private DATE nyugakuNengatsu;
	private DATE sotsugyoNengatsu;
	private String gakkoName;
	private String senmon;
	private String shuuryouJyoutai;
	public Gakureki(Integer gakureki_id, Integer shain_id, String gakurekiKubun, DATE nyugakuNengatsu, DATE sotsugyoNengatsu,
			String gakkoName, String senmon, String shuuryouJyoutai) {
		super();
		this.gakureki_id = gakureki_id;
		this.shain_id = shain_id;
		this.gakurekiKubun = gakurekiKubun;
		this.nyugakuNengatsu = nyugakuNengatsu;
		this.sotsugyoNengatsu = sotsugyoNengatsu;
		this.gakkoName = gakkoName;
		this.senmon = senmon;
		this.shuuryouJyoutai = shuuryouJyoutai;
	}
	public Integer getGakureki_id() {
		return gakureki_id;
	}
	public void setGakureki_id(Integer gakureki_id) {
		this.gakureki_id = gakureki_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getGakurekiKubun() {
		return gakurekiKubun;
	}
	public void setGakurekiKubun(String gakurekiKubun) {
		this.gakurekiKubun = gakurekiKubun;
	}
	public DATE getNyugakuNengatsu() {
		return nyugakuNengatsu;
	}
	public void setNyugakuNengatsu(DATE nyugakuNengatsu) {
		this.nyugakuNengatsu = nyugakuNengatsu;
	}
	public DATE getSotsugyoNengatsu() {
		return sotsugyoNengatsu;
	}
	public void setSotsugyoNengatsu(DATE sotsugyoNengatsu) {
		this.sotsugyoNengatsu = sotsugyoNengatsu;
	}
	public String getGakkoName() {
		return gakkoName;
	}
	public void setGakkoName(String gakkoName) {
		this.gakkoName = gakkoName;
	}
	public String getSenmon() {
		return senmon;
	}
	public void setSenmon(String senmon) {
		this.senmon = senmon;
	}
	public String getShuuryouJyoutai() {
		return shuuryouJyoutai;
	}
	public void setShuuryouJyoutai(String shuuryouJyoutai) {
		this.shuuryouJyoutai = shuuryouJyoutai;
	}
	
	
}
