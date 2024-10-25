package model;

public class KintaiKoumoku {

	private Integer kintai_id;
	private String kintai_mei;
	private String tani;
	private Integer kyuukaKoumoku_id;
	private Integer group_id;
	private String roudouJikanRenkei;
	private char shiyouUmu;
	
	public KintaiKoumoku(Integer kintai_id, String kintai_mei, String tani, Integer kyuukaKoumoku_id, Integer group_id,
			String roudouJikanRenkei, char shiyouUmu) {
		super();
		this.kintai_id = kintai_id;
		this.kintai_mei = kintai_mei;
		this.tani = tani;
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.group_id = group_id;
		this.roudouJikanRenkei = roudouJikanRenkei;
		this.shiyouUmu = shiyouUmu;
	}

	public KintaiKoumoku() {
		
	}

	public Integer getKintai_id() {
		return kintai_id;
	}
	public void setKintai_id(Integer kintai_id) {
		this.kintai_id = kintai_id;
	}

	public String getKintai_mei() {
		return kintai_mei;
	}
	public void setKintai_mei(String kintai_mei) {
		this.kintai_mei = kintai_mei;
	}

	public String getTani() {
		return tani;
	}
	public void setTani(String tani) {
		this.tani = tani;
	}

	public Integer getKyuukaKoumoku_id() {
		return kyuukaKoumoku_id;
	}
	public void setKyuukaKoumoku_id(Integer kyuukaKoumoku_id) {
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
	}

	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getRoudouJikanRenkei() {
		return roudouJikanRenkei;
	}
	public void setRoudouJikanRenkei(String roudouJikanRenkei) {
		this.roudouJikanRenkei = roudouJikanRenkei;
	}

	public char getShiyouUmu() {
		return shiyouUmu;
	}
	public void setShiyouUmu(char shiyouUmu) {
		this.shiyouUmu = shiyouUmu;
	}
	
}
