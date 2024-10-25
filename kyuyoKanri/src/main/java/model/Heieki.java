package model;

import oracle.sql.DATE;

public class Heieki {
	
	private Integer heieki_id;
	private Integer shain_id;
	private String heiekiKubun;
	private String gunBetsu;
	private DATE fukumukiKaishi;
	private DATE fukumukiShuuryou;
	private String Kaikyuu;
	private String heika;
	private String mirikouRiyuuCode;
	public Heieki(Integer heieki_id, Integer shain_id, String heiekiKubun, String gunBetsu, DATE fukumukiKaishi,
			DATE fukumukiShuuryou, String kaikyuu, String heika, String mirikouRiyuuCode) {
		super();
		this.heieki_id = heieki_id;
		this.shain_id = shain_id;
		this.heiekiKubun = heiekiKubun;
		this.gunBetsu = gunBetsu;
		this.fukumukiKaishi = fukumukiKaishi;
		this.fukumukiShuuryou = fukumukiShuuryou;
		Kaikyuu = kaikyuu;
		this.heika = heika;
		this.mirikouRiyuuCode = mirikouRiyuuCode;
	}
	public Integer getHeieki_id() {
		return heieki_id;
	}
	public void setHeieki_id(Integer heieki_id) {
		this.heieki_id = heieki_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getHeiekiKubun() {
		return heiekiKubun;
	}
	public void setHeiekiKubun(String heiekiKubun) {
		this.heiekiKubun = heiekiKubun;
	}
	public String getGunBetsu() {
		return gunBetsu;
	}
	public void setGunBetsu(String gunBetsu) {
		this.gunBetsu = gunBetsu;
	}
	public DATE getFukumukiKaishi() {
		return fukumukiKaishi;
	}
	public void setFukumukiKaishi(DATE fukumukiKaishi) {
		this.fukumukiKaishi = fukumukiKaishi;
	}
	public DATE getFukumukiShuuryou() {
		return fukumukiShuuryou;
	}
	public void setFukumukiShuuryou(DATE fukumukiShuuryou) {
		this.fukumukiShuuryou = fukumukiShuuryou;
	}
	public String getKaikyuu() {
		return Kaikyuu;
	}
	public void setKaikyuu(String kaikyuu) {
		Kaikyuu = kaikyuu;
	}
	public String getHeika() {
		return heika;
	}
	public void setHeika(String heika) {
		this.heika = heika;
	}
	public String getMirikouRiyuuCode() {
		return mirikouRiyuuCode;
	}
	public void setMirikouRiyuuCode(String mirikouRiyuuCode) {
		this.mirikouRiyuuCode = mirikouRiyuuCode;
	}
	
	

}
