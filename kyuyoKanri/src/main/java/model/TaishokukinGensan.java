package model;

import java.math.BigDecimal;

public class TaishokukinGensan {
	private Integer gensan_id;
	private Integer shain_id;
	private BigDecimal gensan_kingaku;
	private String gensan_riyuu;
	public TaishokukinGensan(Integer gensan_id, Integer shain_id, BigDecimal gensan_kingaku, String gensan_riyuu) {
		super();
		this.gensan_id = gensan_id;
		this.shain_id = shain_id;
		this.gensan_kingaku = gensan_kingaku;
		this.gensan_riyuu = gensan_riyuu;
	}
	public Integer getGensan_id() {
		return gensan_id;
	}
	public void setGensan_id(Integer gensan_id) {
		this.gensan_id = gensan_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public BigDecimal getGensan_kingaku() {
		return gensan_kingaku;
	}
	public void setGensan_kingaku(BigDecimal gensan_kingaku) {
		this.gensan_kingaku = gensan_kingaku;
	}
	public String getGensan_riyuu() {
		return gensan_riyuu;
	}
	public void setGensan_riyuu(String gensan_riyuu) {
		this.gensan_riyuu = gensan_riyuu;
	}
	
}
