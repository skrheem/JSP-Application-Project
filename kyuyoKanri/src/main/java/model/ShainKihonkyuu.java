package model;

import java.math.BigDecimal;

public class ShainKihonkyuu {

	private Integer kihonKyuu_id;
	private Integer shain_id;
	private BigDecimal kihonKyuu;
	private Integer kokuminNenkinTekiyouGaku;
	private Integer koyouHokenTekiyouGaku;
	private String kouza_bangou;
	private String yokinshaMeigi;
	private String kinyuuKikan;
	public ShainKihonkyuu(Integer kihonKyuu_id, Integer shain_id, BigDecimal kihonKyuu, Integer kokuminNenkinTekiyouGaku,
			Integer koyouHokenTekiyouGaku, String kouza_bangou, String yokinshaMeigi, String kinyuuKikan) {
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
	public Integer getKihonKyuu_id() {
		return kihonKyuu_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public BigDecimal getKihonKyuu() {
		return kihonKyuu;
	}
	public Integer getKokuminNenkIntegerekiyouGaku() {
		return kokuminNenkinTekiyouGaku;
	}
	public Integer getKoyouHokenTekiyouGaku() {
		return koyouHokenTekiyouGaku;
	}
	public String getKouza_bangou() {
		return kouza_bangou;
	}
	public String getYokinshaMeigi() {
		return yokinshaMeigi;
	}
	public String getKinyuuKikan() {
		return kinyuuKikan;
	}
}
