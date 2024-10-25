package model;

import java.util.Date;

public class Shain {
	
	private Integer shain_id;
	private String namae_kana;
	private String namae_eigo;
	private Date nyuusha_nengappi;
	private Date taisha_nengappi;
	private String busho_mei;
	private String yakushoku_mei;
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
	public Shain(String kubun, Integer shain_id, String namae_kana, String busho_mei, String yakushoku_mei, String jyoutai) {
		super();
		this.shain_id = shain_id;
		this.namae_kana = namae_kana;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
		this.kubun = kubun;
		this.jyoutai = jyoutai;
	}
	public Shain(Integer shain_id, String namae_kana, String namae_eigo, Date nyuusha_nengappi, Date taisha_nengappi,
			String busho_mei, String yakushoku_mei, String kokuseki, String jumin_bangou, String juusho,
			String denwa_uchi, String denwa_keitai, String meeru, String bikou, String kubun, Date tanjyoubi,
			String jyoutai, String SNS) {
		super();
		this.shain_id = shain_id;
		this.namae_kana = namae_kana;
		this.namae_eigo = namae_eigo;
		this.nyuusha_nengappi = nyuusha_nengappi;
		this.taisha_nengappi = taisha_nengappi;
		this.busho_mei = busho_mei;
		this.yakushoku_mei = yakushoku_mei;
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
	public String getBusho_mei() {
		return busho_mei;
	}
	public void setBusho_mei(String busho_mei) {
		this.busho_mei = busho_mei;
	}
	public String getYakushoku_id() {
		return yakushoku_mei;
	}
	public void setYakushoku_id(String yakushoku_mei) {
		this.yakushoku_mei = yakushoku_mei;
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
}
