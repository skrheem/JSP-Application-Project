package model;

public class Tantousha {
	
	private Integer tantousha_id;
	private String namae_kana;
	private String denwa_tantousha;
	private String denwa_keitai;
	private String meeru;
	private Integer kaisha_id;
	private Integer busho_id;
	private Integer yakushoku_id;
	public Tantousha(Integer tantousha_id, String namae_kana, String denwa_tantousha, String denwa_keitai, String meeru,
			Integer kaisha_id, Integer busho_id, Integer yakushoku_id) {
		super();
		this.tantousha_id = tantousha_id;
		this.namae_kana = namae_kana;
		this.denwa_tantousha = denwa_tantousha;
		this.denwa_keitai = denwa_keitai;
		this.meeru = meeru;
		this.kaisha_id = kaisha_id;
		this.busho_id = busho_id;
		this.yakushoku_id = yakushoku_id;
	}
	public Tantousha() {

	}
	
	public Tantousha(String namae_kana, String denwa_tantousha, String denwa_keitai, String meeru, Integer kaisha_id, String busho, String yakushoku) {
		super();
		this.namae_kana = namae_kana;
        this.denwa_tantousha = denwa_tantousha;
        this.denwa_keitai = denwa_keitai;
        this.meeru = meeru;
        this.kaisha_id = kaisha_id;
        this.busho_id = busho_id;
        this.yakushoku_id = yakushoku_id;
    }
	public Integer getTantousha_id() {
		return tantousha_id;
	}
	public void setTantousha_id(Integer tantousha_id) {
		this.tantousha_id = tantousha_id;
	}
	public String getNamae_kana() {
		return namae_kana;
	}
	public void setNamae_kana(String namae_kana) {
		this.namae_kana = namae_kana;
	}
	public String getDenwa_tantousha() {
		return denwa_tantousha;
	}
	public void setDenwa_tantousha(String denwa_tantousha) {
		this.denwa_tantousha = denwa_tantousha;
	}
	public String getDenwa_keitai() {
		return denwa_keitai;
	}
	public void setDenwa_keitai(String denwa_keitai) {
		this.denwa_keitai = denwa_keitai;
	}
	public String getMeeru() {
		return meeru;
	}
	public void setMeeru(String meeru) {
		this.meeru = meeru;
	}
	public Integer getKaisha_id() {
		return kaisha_id;
	}
	public void setKaisha_id(Integer kaisha_id) {
		this.kaisha_id = kaisha_id;
	}
	public Integer getBusho_id() {
		return busho_id;
	}
	public void setBusho_id(Integer busho_id) {
		this.busho_id = busho_id;
	}
	public Integer getYakushoku_id() {
		return yakushoku_id;
	}
	public void setYakushoku_id(Integer yakushoku_id) {
		this.yakushoku_id = yakushoku_id;
	}
	
}
