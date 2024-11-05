package kihonkankyousettei.model;

import java.math.BigDecimal;

public class KyuuyoKoumoku {
	private Integer kyuuyoKoumoku_id;
	private String kyuuyoKoumoku_mei;
	private String kazeiKubun;
	private String bikou;
	private BigDecimal hikazeiGendogaku;
	private String keisanHouhou;
	private String zenshaDani;
	private String kintaiRenkei;
	private String ikkatsuShiharai;
	private BigDecimal ikkatsuShiharaiGaku;
	private char shiyouUmu;

	public KyuuyoKoumoku(Integer kyuuyoKoumoku_id, String kyuuyoKoumoku_mei, String kazeiKubun, String bikou,
			BigDecimal hikazeiGendogaku, String keisanHouhou, String zenshaDani, String kintaiRenkei,
			String ikkatsuShiharai, BigDecimal ikkatsuShiharaiGaku, char shiyouUmu) {
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyoKoumoku_mei = kyuuyoKoumoku_mei;
		this.kazeiKubun = kazeiKubun;
		this.bikou = bikou;
		this.hikazeiGendogaku = hikazeiGendogaku != null ? hikazeiGendogaku : BigDecimal.ZERO; // null 체크 후 기본값 설정
		this.keisanHouhou = keisanHouhou;
		this.zenshaDani = zenshaDani;
		this.kintaiRenkei = kintaiRenkei;
		this.ikkatsuShiharai = ikkatsuShiharai;
		this.ikkatsuShiharaiGaku = ikkatsuShiharaiGaku != null ? ikkatsuShiharaiGaku : BigDecimal.ZERO; // null 체크
		this.shiyouUmu = shiyouUmu;
	}

	public KyuuyoKoumoku(String kyuuyoKoumoku_mei, String kazeiKubun, String bikou, BigDecimal hikazeiGendogaku,
			String keisanHouhou, String zenshaDani, String kintaiRenkei, String ikkatsuShiharai,
			BigDecimal ikkatsuShiharaiGaku, char shiyouUmu) {

		this.kyuuyoKoumoku_mei = kyuuyoKoumoku_mei;
		this.kazeiKubun = kazeiKubun;
		this.bikou = bikou;
		this.hikazeiGendogaku = hikazeiGendogaku != null ? hikazeiGendogaku : BigDecimal.ZERO; // null 체크 후 기본값 설정
		this.keisanHouhou = keisanHouhou;
		this.zenshaDani = zenshaDani;
		this.kintaiRenkei = kintaiRenkei;
		this.ikkatsuShiharai = ikkatsuShiharai;
		this.ikkatsuShiharaiGaku = ikkatsuShiharaiGaku != null ? ikkatsuShiharaiGaku : BigDecimal.ZERO; // null 체크
		this.shiyouUmu = shiyouUmu;
	}

	public KyuuyoKoumoku() {

	}

	public Integer getKyuuyoKoumoku_id() {
		return kyuuyoKoumoku_id;
	}

	public void setKyuuyoKoumoku_id(Integer kyuuyoKoumoku_id) {
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
	}

	public String getKyuuyoKoumoku_mei() {
		return kyuuyoKoumoku_mei;
	}

	public void setKyuuyoKoumoku_mei(String kyuuyoKoumoku_mei) {
		this.kyuuyoKoumoku_mei = kyuuyoKoumoku_mei;
	}

	public String getKazeiKubun() {
		return kazeiKubun;
	}

	public void setKazeiKubun(String kazeiKubun) {
		this.kazeiKubun = kazeiKubun;
	}

	public BigDecimal getHikazeiGendogaku() {
		return hikazeiGendogaku;
	}

	public void setHikazeiGendogaku(BigDecimal hikazeiGendogaku) {
		this.hikazeiGendogaku = hikazeiGendogaku;
	}

	public String getBikou() {
		return bikou;
	}

	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

	public String getKeisanHouhou() {
		return keisanHouhou;
	}

	public void setKeisanHouhou(String keisanHouhou) {
		this.keisanHouhou = keisanHouhou;
	}

	public String getZenshaDani() {
		return zenshaDani;
	}

	public void setZenshaDani(String zenshaDani) {
		this.zenshaDani = zenshaDani;
	}

	public String getKintaiRenkei() {
		return kintaiRenkei;
	}

	public void setKintaiRenkei(String kintaiRenkei) {
		this.kintaiRenkei = kintaiRenkei;
	}

	public String getIkkatsuShiharai() {
		return ikkatsuShiharai;
	}

	public void setIkkatsuShiharai(String ikkatsuShiharai) {
		this.ikkatsuShiharai = ikkatsuShiharai;
	}

	public BigDecimal getIkkatsuShiharaiGaku() {
		return ikkatsuShiharaiGaku;
	}

	public void setIkkatsuShiharaiGaku(BigDecimal ikkatsuShiharaiGaku) {
		this.ikkatsuShiharaiGaku = ikkatsuShiharaiGaku;
	}

	public char getShiyouUmu() {
		return shiyouUmu;
	}

	public void setShiyouUmu(char shiyouUmu) {
		this.shiyouUmu = shiyouUmu;
	}
}
