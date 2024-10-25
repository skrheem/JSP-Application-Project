package model;

import java.math.BigDecimal;

public class TaishokukinKasan {
	private Integer kasan_id;
	private Integer shain_id;
	private BigDecimal kasan_kingaku;
	private String kasan_riyuu;
	public TaishokukinKasan(Integer kasan_id, Integer shain_id, BigDecimal kasan_kingaku, String kasan_riyuu) {
		super();
		this.kasan_id = kasan_id;
		this.shain_id = shain_id;
		this.kasan_kingaku = kasan_kingaku;
		this.kasan_riyuu = kasan_riyuu;
	}
	public Integer getkasan_id() {
		return kasan_id;
	}
	public void setkasan_id(Integer kasan_id) {
		this.kasan_id = kasan_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public BigDecimal getkasan_kingaku() {
		return kasan_kingaku;
	}
	public void setkasan_kingaku(BigDecimal kasan_kingaku) {
		this.kasan_kingaku = kasan_kingaku;
	}
	public String getkasan_riyuu() {
		return kasan_riyuu;
	}
	public void setkasan_riyuu(String kasan_riyuu) {
		this.kasan_riyuu = kasan_riyuu;
	}
	
}
