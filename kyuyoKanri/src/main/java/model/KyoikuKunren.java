package model;

import java.math.BigDecimal;
import java.util.Date;

public class KyoikuKunren {

	private Integer kunren_id;
	private Integer shain_id;
	private String kunren_shubetsu;
	private String kyouiku_mei;
	private Date kaishi_bi;
	private Date shuuryou_bi;
	private String kikan;
	private BigDecimal kyouiku_hiyou;
	private BigDecimal henkin_hiyou;

	public KyoikuKunren(Integer kunren_id, Integer shain_id, String kunren_shubetsu, String kyouiku_mei, Date kaishi_bi,
			Date shuuryou_bi, String kikan, BigDecimal kyouiku_hiyou, BigDecimal henkin_hiyou) {
		super();
		this.kunren_id = kunren_id;
		this.shain_id = shain_id;
		this.kunren_shubetsu = kunren_shubetsu;
		this.kyouiku_mei = kyouiku_mei;
		this.kaishi_bi = kaishi_bi;
		this.shuuryou_bi = shuuryou_bi;
		this.kikan = kikan;
		this.kyouiku_hiyou = kyouiku_hiyou;
		this.henkin_hiyou = henkin_hiyou;
	}

	public Integer getKunren_id() {
		return kunren_id;
	}

	public void setKunren_id(Integer kunren_id) {
		this.kunren_id = kunren_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public String getKunren_shubetsu() {
		return kunren_shubetsu;
	}

	public void setKunren_shubetsu(String kunren_shubetsu) {
		this.kunren_shubetsu = kunren_shubetsu;
	}

	public String getKyouiku_mei() {
		return kyouiku_mei;
	}

	public void setKyouiku_mei(String kyouiku_mei) {
		this.kyouiku_mei = kyouiku_mei;
	}

	public Date getKaishi_bi() {
		return kaishi_bi;
	}

	public void setKaishi_bi(Date kaishi_bi) {
		this.kaishi_bi = kaishi_bi;
	}

	public Date getShuuryou_bi() {
		return shuuryou_bi;
	}

	public void setShuuryou_bi(Date shuuryou_bi) {
		this.shuuryou_bi = shuuryou_bi;
	}

	public String getKikan() {
		return kikan;
	}

	public void setKikan(String kikan) {
		this.kikan = kikan;
	}

	public BigDecimal getKyouiku_hiyou() {
		return kyouiku_hiyou;
	}

	public void setKyouiku_hiyou(BigDecimal kyouiku_hiyou) {
		this.kyouiku_hiyou = kyouiku_hiyou;
	}

	public BigDecimal getHenkin_hiyou() {
		return henkin_hiyou;
	}

	public void setHenkin_hiyou(BigDecimal henkin_hiyou) {
		this.henkin_hiyou = henkin_hiyou;
	}

}
