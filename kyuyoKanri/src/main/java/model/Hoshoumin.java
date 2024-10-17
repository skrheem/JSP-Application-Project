package model;

import java.math.BigDecimal;
import java.util.Date;

public class Hoshoumin {

	private Integer hoshounin_id;
	private Integer shain_id;
	private BigDecimal hoshou_kingaku;
	private Date kaishi_nengappi;
	private Date shuuryou_nengappi;
	private String hoshounin_mei;
	private String kankei;
	private String hoshounin_jumin_bangou;
	private String hoshounin_denwa;

	public Hoshoumin(Integer hoshounin_id, Integer shain_id, BigDecimal hoshou_kingaku, Date kaishi_nengappi,
			Date shuuryou_nengappi, String hoshounin_mei, String kankei, String hoshounin_jumin_bangou,
			String hoshounin_denwa) {
		super();
		this.hoshounin_id = hoshounin_id;
		this.shain_id = shain_id;
		this.hoshou_kingaku = hoshou_kingaku;
		this.kaishi_nengappi = kaishi_nengappi;
		this.shuuryou_nengappi = shuuryou_nengappi;
		this.hoshounin_mei = hoshounin_mei;
		this.kankei = kankei;
		this.hoshounin_jumin_bangou = hoshounin_jumin_bangou;
		this.hoshounin_denwa = hoshounin_denwa;
	}

	public Integer getHoshounin_id() {
		return hoshounin_id;
	}

	public void setHoshounin_id(Integer hoshounin_id) {
		this.hoshounin_id = hoshounin_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public BigDecimal getHoshou_kingaku() {
		return hoshou_kingaku;
	}

	public void setHoshou_kingaku(BigDecimal hoshou_kingaku) {
		this.hoshou_kingaku = hoshou_kingaku;
	}

	public Date getKaishi_nengappi() {
		return kaishi_nengappi;
	}

	public void setKaishi_nengappi(Date kaishi_nengappi) {
		this.kaishi_nengappi = kaishi_nengappi;
	}

	public Date getShuuryou_nengappi() {
		return shuuryou_nengappi;
	}

	public void setShuuryou_nengappi(Date shuuryou_nengappi) {
		this.shuuryou_nengappi = shuuryou_nengappi;
	}

	public String getHoshounin_mei() {
		return hoshounin_mei;
	}

	public void setHoshounin_mei(String hoshounin_mei) {
		this.hoshounin_mei = hoshounin_mei;
	}

	public String getKankei() {
		return kankei;
	}

	public void setKankei(String kankei) {
		this.kankei = kankei;
	}

	public String getHoshounin_jumin_bangou() {
		return hoshounin_jumin_bangou;
	}

	public void setHoshounin_jumin_bangou(String hoshounin_jumin_bangou) {
		this.hoshounin_jumin_bangou = hoshounin_jumin_bangou;
	}

	public String getHoshounin_denwa() {
		return hoshounin_denwa;
	}

	public void setHoshounin_denwa(String hoshounin_denwa) {
		this.hoshounin_denwa = hoshounin_denwa;
	}

}
