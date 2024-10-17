package model;

import java.math.BigDecimal;
import java.util.Date;

public class HiyatoiKinmuKiroku {
	private Integer kinmuKiroku_id;
	private Integer shain_id;
	private Integer genba_project_id;
	private Date kinmu_nengappi;
	private BigDecimal shiharai_ritsu;
	private BigDecimal sougaku;
	private BigDecimal shotokuzei;
	private BigDecimal chihozei;
	private BigDecimal jissai_shikyuu;
	private Date touroku_nengappi;
	public HiyatoiKinmuKiroku(Integer kinmuKiroku_id, Integer shain_id, Integer genba_project_id, Date kinmu_nengappi,
			BigDecimal shiharai_ritsu, BigDecimal sougaku, BigDecimal shotokuzei, BigDecimal chihozei,
			BigDecimal jissai_shikyuu, Date touroku_nengappi) {
		super();
		this.kinmuKiroku_id = kinmuKiroku_id;
		this.shain_id = shain_id;
		this.genba_project_id = genba_project_id;
		this.kinmu_nengappi = kinmu_nengappi;
		this.shiharai_ritsu = shiharai_ritsu;
		this.sougaku = sougaku;
		this.shotokuzei = shotokuzei;
		this.chihozei = chihozei;
		this.jissai_shikyuu = jissai_shikyuu;
		this.touroku_nengappi = touroku_nengappi;
	}
	public Integer getKinmuKiroku_id() {
		return kinmuKiroku_id;
	}
	public void setKinmuKiroku_id(Integer kinmuKiroku_id) {
		this.kinmuKiroku_id = kinmuKiroku_id;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getGenba_project_id() {
		return genba_project_id;
	}
	public void setGenba_project_id(Integer genba_project_id) {
		this.genba_project_id = genba_project_id;
	}
	public Date getKinmu_nengappi() {
		return kinmu_nengappi;
	}
	public void setKinmu_nengappi(Date kinmu_nengappi) {
		this.kinmu_nengappi = kinmu_nengappi;
	}
	public BigDecimal getShiharai_ritsu() {
		return shiharai_ritsu;
	}
	public void setShiharai_ritsu(BigDecimal shiharai_ritsu) {
		this.shiharai_ritsu = shiharai_ritsu;
	}
	public BigDecimal getSougaku() {
		return sougaku;
	}
	public void setSougaku(BigDecimal sougaku) {
		this.sougaku = sougaku;
	}
	public BigDecimal getShotokuzei() {
		return shotokuzei;
	}
	public void setShotokuzei(BigDecimal shotokuzei) {
		this.shotokuzei = shotokuzei;
	}
	public BigDecimal getChihozei() {
		return chihozei;
	}
	public void setChihozei(BigDecimal chihozei) {
		this.chihozei = chihozei;
	}
	public BigDecimal getJissai_shikyuu() {
		return jissai_shikyuu;
	}
	public void setJissai_shikyuu(BigDecimal jissai_shikyuu) {
		this.jissai_shikyuu = jissai_shikyuu;
	}
	public Date getTouroku_nengappi() {
		return touroku_nengappi;
	}
	public void setTouroku_nengappi(Date touroku_nengappi) {
		this.touroku_nengappi = touroku_nengappi;
	}
}
