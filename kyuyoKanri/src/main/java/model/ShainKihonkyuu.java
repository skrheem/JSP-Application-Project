package model;

import java.math.BigDecimal;

public class ShainKihonkyuu {

	private int kihonKyuu_id;
	private int shain_id;
	private BigDecimal kihonKyuu;
	private int kokuminNenkinTekiyouGaku;
	private int koyouHokenTekiyouGaku;
	private String kouza_bangou;
	private String yokinshaMeigi;
	private String kinyuuKikan;
	public ShainKihonkyuu(int kihonKyuu_id, int shain_id, BigDecimal kihonKyuu, int kokuminNenkinTekiyouGaku,
			int koyouHokenTekiyouGaku, String kouza_bangou, String yokinshaMeigi, String kinyuuKikan) {
		super();
		this.kihonKyuu_id = kihonKyuu_id;
		this.shain_id = shain_id;
		this.kihonKyuu = kihonKyuu;
		this.kokuminNenkinTekiyouGaku = kokuminNenkinTekiyouGaku;
		this.koyouHokenTekiyouGaku = koyouHokenTekiyouGaku;
		this.kouza_bangou = kouza_bangou;
		this.yokinshaMeigi = yokinshaMeigi;
		this.kinyuuKikan = kinyuuKikan;
	}
	public int getKihonKyuu_id() {
		return kihonKyuu_id;
	}
	public void setKihonKyuu_id(int kihonKyuu_id) {
		this.kihonKyuu_id = kihonKyuu_id;
	}
	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}
	public BigDecimal getKihonKyuu() {
		return kihonKyuu;
	}
	public void setKihonKyuu(BigDecimal kihonKyuu) {
		this.kihonKyuu = kihonKyuu;
	}
	public int getKokuminNenkinTekiyouGaku() {
		return kokuminNenkinTekiyouGaku;
	}
	public void setKokuminNenkinTekiyouGaku(int kokuminNenkinTekiyouGaku) {
		this.kokuminNenkinTekiyouGaku = kokuminNenkinTekiyouGaku;
	}
	public int getKoyouHokenTekiyouGaku() {
		return koyouHokenTekiyouGaku;
	}
	public void setKoyouHokenTekiyouGaku(int koyouHokenTekiyouGaku) {
		this.koyouHokenTekiyouGaku = koyouHokenTekiyouGaku;
	}
	public String getKouza_bangou() {
		return kouza_bangou;
	}
	public void setKouza_bangou(String kouza_bangou) {
		this.kouza_bangou = kouza_bangou;
	}
	public String getYokinshaMeigi() {
		return yokinshaMeigi;
	}
	public void setYokinshaMeigi(String yokinshaMeigi) {
		this.yokinshaMeigi = yokinshaMeigi;
	}
	public String getKinyuuKikan() {
		return kinyuuKikan;
	}
	public void setKinyuuKikan(String kinyuuKikan) {
		this.kinyuuKikan = kinyuuKikan;
	}
	
	
}
