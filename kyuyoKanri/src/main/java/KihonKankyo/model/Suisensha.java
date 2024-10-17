package KihonKankyo.model;

import java.util.Date;

public class Suisensha {
	
	private Integer suisensha_id;
	private Integer shain_id;
	private String suisensha_mei;
	private String kankei;
	private String kaisha_mei;
	private String yakushoku_id;
	private String suisensha_denwa;
	public Suisensha(Integer suisensha_id, Integer shain_id, String suisensha_mei, String kankei, String kaisha_mei,
			String yakushoku_id, String suisensha_denwa) {
		super();
		this.suisensha_id = suisensha_id;
		this.shain_id = shain_id;
		this.suisensha_mei = suisensha_mei;
		this.kankei = kankei;
		this.kaisha_mei = kaisha_mei;
		this.yakushoku_id = yakushoku_id;
		this.suisensha_denwa = suisensha_denwa;
	}
	public Integer getSuisensha_id() {
		return suisensha_id;
	}
	public void setSuisensha_id(Integer suisensha_id) {
		this.suisensha_id = suisensha_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getSuisensha_mei() {
		return suisensha_mei;
	}
	public void setSuisensha_mei(String suisensha_mei) {
		this.suisensha_mei = suisensha_mei;
	}
	public String getKankei() {
		return kankei;
	}
	public void setKankei(String kankei) {
		this.kankei = kankei;
	}
	public String getKaisha_mei() {
		return kaisha_mei;
	}
	public void setKaisha_mei(String kaisha_mei) {
		this.kaisha_mei = kaisha_mei;
	}
	public String getYakushoku_id() {
		return yakushoku_id;
	}
	public void setYakushoku_id(String yakushoku_id) {
		this.yakushoku_id = yakushoku_id;
	}
	public String getSuisensha_denwa() {
		return suisensha_denwa;
	}
	public void setSuisensha_denwa(String suisensha_denwa) {
		this.suisensha_denwa = suisensha_denwa;
	}

}
