package model;

import java.math.BigDecimal;

public class KyuuyoKoumoku {
	private Integer kyuuyoKoumoku_id;
	private String kyuuyoKoumoku_mei;
	private String kazeiKubun;
	private BigDecimal hikazeiGendogaku;
	private String bikou;
	private String keisanHouhou;
	private String zenshaDani;
	private String kintaiRenkei;
	private String ikkatsuShiharai;
	private BigDecimal ikkatsuShiharaiGaku;
	private String shiyouUmu;
	public KyuuyoKoumoku(Integer kyuuyoKoumoku_id, String kyuuyoKoumoku_mei, String kazeiKubun,
			BigDecimal hikazeiGendogaku, String bikou, String keisanHouhou, String zenshaDani, String kintaiRenkei,
			String ikkatsuShiharai, BigDecimal ikkatsuShiharaiGaku, String shiyouUmu) {
		super();
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyoKoumoku_mei = kyuuyoKoumoku_mei;
		this.kazeiKubun = kazeiKubun;
		this.hikazeiGendogaku = hikazeiGendogaku;
		this.bikou = bikou;
		this.keisanHouhou = keisanHouhou;
		this.zenshaDani = zenshaDani;
		this.kintaiRenkei = kintaiRenkei;
		this.ikkatsuShiharai = ikkatsuShiharai;
		this.ikkatsuShiharaiGaku = ikkatsuShiharaiGaku;
		this.shiyouUmu = shiyouUmu;
	}
	public KyuuyoKoumoku(Integer kyuuyoKoumoku_id, String kyuuyoKoumoku_mei, String keisanHouhou) {
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyoKoumoku_mei = kyuuyoKoumoku_mei;
		this.keisanHouhou = keisanHouhou;
	}
	public KyuuyoKoumoku(Integer kyuuyoKoumoku_id, BigDecimal hikazeiGendogaku) {
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.hikazeiGendogaku = hikazeiGendogaku;
	}
	public Integer getKyuuyoKoumoku_id() {
		return kyuuyoKoumoku_id;
	}
	public String getKyuuyoKoumoku_mei() {
		return kyuuyoKoumoku_mei;
	}
	public String getKazeiKubun() {
		return kazeiKubun;
	}
	public BigDecimal getHikazeiGendogaku() {
		return hikazeiGendogaku;
	}
	public String getBikou() {
		return bikou;
	}
	public String getKeisanHouhou() {
		return keisanHouhou;
	}
	public String getZenshaDani() {
		return zenshaDani;
	}
	public String getKintaiRenkei() {
		return kintaiRenkei;
	}
	public String getIkkatsuShiharai() {
		return ikkatsuShiharai;
	}
	public BigDecimal getIkkatsuShiharaiGaku() {
		return ikkatsuShiharaiGaku;
	}
	public String getShiyouUmu() {
		return shiyouUmu;
	}
	@Override
	public String toString() {
		return "KyuuyoKoumoku [kyuuyoKoumoku_id=" + kyuuyoKoumoku_id + ", kyuuyoKoumoku_mei=" + kyuuyoKoumoku_mei
				+ ", kazeiKubun=" + kazeiKubun + ", hikazeiGendogaku=" + hikazeiGendogaku + ", bikou=" + bikou
				+ ", keisanHouhou=" + keisanHouhou + ", zenshaDani=" + zenshaDani + ", kintaiRenkei=" + kintaiRenkei
				+ ", ikkatsuShiharai=" + ikkatsuShiharai + ", ikkatsuShiharaiGaku=" + ikkatsuShiharaiGaku
				+ ", shiyouUmu=" + shiyouUmu + "]";
	}
	
}
