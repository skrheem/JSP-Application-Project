package model;

import java.util.Date;

public class Shain {
	
	private Integer shain_id;
	private String namae_kana;
	private String namae_eigo;
	private Date nyuusha_nengappi;
	private Date taisha_nengappi;
	private Integer busho_id;
	private Integer yakushoku_id;
	private String kokuseki;
	private String jumin_bangou;
	private String juusho;
	private String denwa_uchi;
	private String denwa_keitai;
	private String meeru;
	private String bikou;
	private String kubun;
	private Date tanjyoubi;
	private String jyoutai;
	private String SNS;
	private String kouza_bangou;
	public Shain(Integer shain_id, String namae_kana, String namae_eigo, Date nyuusha_nengappi, Date taisha_nengappi,
			Integer busho_id, Integer yakushoku_id, String kokuseki, String jumin_bangou, String juusho,
			String denwa_uchi, String denwa_keitai, String meeru, String bikou, String kubun, Date tanjyoubi,
			String jyoutai, String SNS) {
		super();
		this.shain_id = shain_id;
		this.namae_kana = namae_kana;
		this.namae_eigo = namae_eigo;
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.taisha_nengappi = taisha_nengappi;
		this.busho_id = busho_id;
		this.yakushoku_id = yakushoku_id;
		this.kokuseki = kokuseki;
		this.jumin_bangou = jumin_bangou;
		this.juusho = juusho;
		this.denwa_uchi = denwa_uchi;
		this.denwa_keitai = denwa_keitai;
		this.meeru = meeru;
		this.bikou = bikou;
		this.kubun = kubun;
		this.tanjyoubi = tanjyoubi;
		this.jyoutai = jyoutai;
		this.SNS = SNS;
	}

	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getNamae_kana() {
		return namae_kana;
	}
	public void setNamae_kana(String namae_kana) {
		this.namae_kana = namae_kana;
	}
	public String getNamae_eigo() {
		return namae_eigo;
	}
	public void setNamae_eigo(String namae_eigo) {
		this.namae_eigo = namae_eigo;
	}
	public Date getNyuusha_nengappi() {
		return nyuusha_nengappi;
	}
	public void setNyuusha_nengappi(Date nyuusha_nengappi) {
		this.nyuusha_nengappi = nyuusha_nengappi;
	}
	public Date getTaisha_nengappi() {
		return taisha_nengappi;
	}
	public void setTaisha_nengappi(Date taisha_nengappi) {
		this.taisha_nengappi = taisha_nengappi;
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
	public String getKokuseki() {
		return kokuseki;
	}
	public void setKokuseki(String kokuseki) {
		this.kokuseki = kokuseki;
	}
	public String getJumin_bangou() {
		return jumin_bangou;
	}
	public void setJumin_bangou(String jumin_bangou) {
		this.jumin_bangou = jumin_bangou;
	}
	public String getJuusho() {
		return juusho;
	}
	public void setJuusho(String juusho) {
		this.juusho = juusho;
	}
	public String getDenwa_uchi() {
		return denwa_uchi;
	}
	public void setDenwa_uchi(String denwa_uchi) {
		this.denwa_uchi = denwa_uchi;
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
	public String getBikou() {
		return bikou;
	}
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}
	public Date getTanjyoubi() {
		return tanjyoubi;
	}
	public void setTanjyoubi(Date tanjyoubi) {
		this.tanjyoubi = tanjyoubi;
	}
	public String getJyoutai() {
		return jyoutai;
	}
	public void setJyoutai(String jyoutai) {
		this.jyoutai = jyoutai;
	}
	public String getSNS() {
		return SNS;
	}
	public void setSNS(String SNS) {
		this.SNS = SNS;
	}
	
	
    public Shain(Integer shain_id, String namae_kana, String namae_eigo, Date nyuusha_nengappi, Date taisha_nengappi, 
            String busho_mei, String yakushoku_mei, String kokuseki, String jumin_bangou, String juusho, String denwa_uchi, 
            String denwa_keitai, String meeru, String SNS, String kubun, String jyoutai, String kouza_bangou) {
   this.shain_id = shain_id;
   this.namae_kana = namae_kana;
   this.namae_eigo = namae_eigo;
   this.nyuusha_nengappi = nyuusha_nengappi;
   this.taisha_nengappi = taisha_nengappi;
   this.busho_id = busho_id;
   this.yakushoku_id = yakushoku_id;
   this.kokuseki = kokuseki;
   this.jumin_bangou = jumin_bangou;
   this.juusho = juusho;
   this.denwa_uchi = denwa_uchi;
   this.denwa_keitai = denwa_keitai;
   this.meeru = meeru;
   this.SNS = SNS; 
   this.kubun = kubun;
   this.jyoutai = jyoutai;
   this.kouza_bangou = kouza_bangou;
}
	
	
}