package model;

import java.math.BigDecimal;
import java.util.Date;

public class Taishoku {
	private Integer taishoku_id;
	private Integer shain_id;
	private String taishokuKubun;
	private Date taishoku_bi;
	private String riyuu;
	private BigDecimal taishoku_kin;
	private String taishokugoRenrakusaki;
	private String taishokuKinMeisaisho;

	public Taishoku(Integer taishoku_id, Integer shain_id, String taishokuKubun, Date taishoku_bi, String riyuu,
			BigDecimal taishoku_kin, String taishokugoRenrakusaki, String taishokuKinMeisaisho) {
		super();
		this.taishoku_id = taishoku_id;
		this.shain_id = shain_id;
		this.taishokuKubun = taishokuKubun;
		this.taishoku_bi = taishoku_bi;
		this.riyuu = riyuu;
		this.taishoku_kin = taishoku_kin;
		this.taishokugoRenrakusaki = taishokugoRenrakusaki;
		this.taishokuKinMeisaisho = taishokuKinMeisaisho;
	}

	public Taishoku(Integer shain_id, String taishokuKubun, Date taishoku_bi, String riyuu, BigDecimal taishoku_kin,
			String taishokugoRenrakusaki, String taishokuKinMeisaisho) {
		super();
		this.shain_id = shain_id;
		this.taishokuKubun = taishokuKubun;
		this.taishoku_bi = taishoku_bi;
		this.riyuu = riyuu;
		this.taishoku_kin = taishoku_kin;
		this.taishokugoRenrakusaki = taishokugoRenrakusaki;
		this.taishokuKinMeisaisho = taishokuKinMeisaisho;
	}

	public Taishoku() {
		
	}

	public Integer getTaishoku_id() {
		return taishoku_id;
	}

	public void setTaishoku_id(Integer taishoku_id) {
		this.taishoku_id = taishoku_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public String getTaishokuKubun() {
		return taishokuKubun;
	}

	public void setTaishokuKubun(String taishokuKubun) {
		this.taishokuKubun = taishokuKubun;
	}

	public Date getTaishoku_bi() {
		return taishoku_bi;
	}

	public void setTaishoku_bi(Date taishoku_bi) {
		this.taishoku_bi = taishoku_bi;
	}

	public String getRiyuu() {
		return riyuu;
	}

	public void setRiyuu(String riyuu) {
		this.riyuu = riyuu;
	}

	public BigDecimal getTaishoku_kin() {
		return taishoku_kin;
	}

	public void setTaishoku_kin(BigDecimal taishoku_kin) {
		this.taishoku_kin = taishoku_kin;
	}

	public String getTaishokugoRenrakusaki() {
		return taishokugoRenrakusaki;
	}

	public void setTaishokugoRenrakusaki(String taishokugoRenrakusaki) {
		this.taishokugoRenrakusaki = taishokugoRenrakusaki;
	}

	public String getTaishokuKinMeisaisho() {
		return taishokuKinMeisaisho;
	}

	public void setTaishokuKinMeisaisho(String taishokuKinMeisaisho) {
		this.taishokuKinMeisaisho = taishokuKinMeisaisho;
	}

}
