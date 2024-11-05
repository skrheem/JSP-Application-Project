package keisan.model;

import java.math.BigDecimal;

public class KoujoKoumoku {
	private Integer koujoKoumoku_id;
	private String koujoKoumoku_mei;
	private BigDecimal koujoRitsu;
	private String keisanHouhou;
	private char shiyouUmu;
	private String zenshaDani;
	private String koujoKoumokuKubun;
	private String kihonKoumoku;
	private String bikou;
	public KoujoKoumoku(Integer koujoKoumoku_id, String koujoKoumoku_mei, BigDecimal koujoRitsu, String keisanHouhou,
			char shiyouUmu, String zenshaDani, String koujoKoumokuKubun, String kihonKoumoku, String bikou) {
		super();
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.koujoRitsu = koujoRitsu;
		this.keisanHouhou = keisanHouhou;
		this.shiyouUmu = shiyouUmu;
		this.zenshaDani = zenshaDani;
		this.koujoKoumokuKubun = koujoKoumokuKubun;
		this.kihonKoumoku = kihonKoumoku;
		this.bikou = bikou;
	}
	public KoujoKoumoku(Integer koujoKoumoku_id, String koujoKoumoku_mei, String keisanHouhou) {
		super();
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.keisanHouhou = keisanHouhou;
	}
	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}
	public String getKoujoKoumoku_mei() {
		return koujoKoumoku_mei;
	}
	public BigDecimal getKoujoRitsu() {
		return koujoRitsu;
	}
	public String getKeisanHouhou() {
		return keisanHouhou;
	}
	public char getShiyouUmu() {
		return shiyouUmu;
	}
	public String getZenshaDani() {
		return zenshaDani;
	}
	public String getKoujoKoumokuKubun() {
		return koujoKoumokuKubun;
	}
	public String getBikou() {
		return bikou;
	}
	public String getKihonKoumoku() {
		return kihonKoumoku;
	}
	
}
