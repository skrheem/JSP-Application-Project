package model;

import java.math.BigDecimal;

public class KoujoKoumoku {
	private Integer koujoKoumoku_id;
	private String koujoKoumoku_mei;
	private BigDecimal koujoRitsu;
	private String keisanHouhou;
	private char shiyouUmu;
	private String zenshaDani;
	private String koujoKoumokuKubun;

	public KoujoKoumoku(Integer koujoKoumoku_id, String koujoKoumoku_mei, BigDecimal koujoRitsu, String keisanHouhou,
			char shiyouUmu, String zenshaDani, String koujoKoumokuKubun) {
		super();
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.koujoRitsu = koujoRitsu;
		this.keisanHouhou = keisanHouhou;
		this.shiyouUmu = shiyouUmu;
		this.zenshaDani = zenshaDani;
		this.koujoKoumokuKubun = koujoKoumokuKubun;
	}
	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}
	public void setKoujoKoumoku_id(Integer koujoKoumoku_id) {
		this.koujoKoumoku_id = koujoKoumoku_id;
	}
	public String getKoujoKoumoku_mei() {
		return koujoKoumoku_mei;
	}
	public void setKoujoKoumoku_mei(String koujoKoumoku_mei) {
		this.koujoKoumoku_mei = koujoKoumoku_mei;
	}
	public BigDecimal getKoujoRitsu() {
		return koujoRitsu;
	}
	public void setKoujoRitsu(BigDecimal koujoRitsu) {
		this.koujoRitsu = koujoRitsu;
	}
	public String getKeisanHouhou() {
		return keisanHouhou;
	}
	public void setKeisanHouhou(String keisanHouhou) {
		this.keisanHouhou = keisanHouhou;
	}
	public char getShiyouUmu() {
		return shiyouUmu;
	}
	public void setShiyouUmu(char shiyouUmu) {
		this.shiyouUmu = shiyouUmu;
	}
	public String getZenshaDani() {
		return zenshaDani;
	}
	public void setZenshaDani(String zenshaDani) {
		this.zenshaDani = zenshaDani;
	}
	public String getKoujoKoumokuKubun() {
		return koujoKoumokuKubun;
	}
	public void setKoujoKoumokuKubun(String koujoKoumokuKubun) {
		this.koujoKoumokuKubun = koujoKoumokuKubun;
	}
}
