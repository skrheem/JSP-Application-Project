package kihonkankyousettei.model;

import java.math.BigDecimal;
import java.util.Date;

public class HoshouhoHoken {
	
	private Integer HoshouhoHoken_id;
	private Integer shain_id;
	private String kanyuuKikan;
	private String hoshouBangou;
	private BigDecimal hoshouKingaku;
	private Date kanyuu_bi;
	private Date yuukouKikan;
	private String bikou;
	public HoshouhoHoken(Integer HoshouhoHoken_id, Integer shain_id, String kanyuuKikan, String hoshouBangou,
			BigDecimal hoshouKingaku, Date kanyuu_bi, Date yuukouKikan, String bikou) {
		super();
		this.HoshouhoHoken_id = HoshouhoHoken_id;
		this.shain_id = shain_id;
		this.kanyuuKikan = kanyuuKikan;
		this.hoshouBangou = hoshouBangou;
		this.hoshouKingaku = hoshouKingaku;
		this.kanyuu_bi = kanyuu_bi;
		this.yuukouKikan = yuukouKikan;
		this.bikou = bikou;
	}
	public Integer getHoshouhoHoken_id() {
		return HoshouhoHoken_id;
	}
	public void setHoshouhoHoken_id(Integer HoshouhoHoken_id) {
		this.HoshouhoHoken_id = HoshouhoHoken_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public String getKanyuuKikan() {
		return kanyuuKikan;
	}
	public void setKanyuuKikan(String kanyuuKikan) {
		this.kanyuuKikan = kanyuuKikan;
	}
	public String getHoshouBangou() {
		return hoshouBangou;
	}
	public void setHoshouBangou(String hoshouBangou) {
		this.hoshouBangou = hoshouBangou;
	}
	public BigDecimal getHoshouKingaku() {
		return hoshouKingaku;
	}
	public void setHoshouKingaku(BigDecimal hoshouKingaku) {
		this.hoshouKingaku = hoshouKingaku;
	}
	public Date getKanyuu_bi() {
		return kanyuu_bi;
	}
	public void setKanyuu_bi(Date kanyuu_bi) {
		this.kanyuu_bi = kanyuu_bi;
	}
	public Date getYuukouKikan() {
		return yuukouKikan;
	}
	public void setYuukouKikan(Date yuukouKikan) {
		this.yuukouKikan = yuukouKikan;
	}
	public String getBikou() {
		return bikou;
	}
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

}
