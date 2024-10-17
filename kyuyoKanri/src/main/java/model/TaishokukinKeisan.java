package model;

import java.math.BigDecimal;
import java.util.Date;

public class TaishokukinKeisan {
	private Integer keisan_id;
	private Integer taishoku_id;
	private Integer shain_id;
	private BigDecimal kihon_taishokukin;
	private Integer kasan_id;
	private Integer gensan_id;
	private BigDecimal jissyu_taishokukin;
	private BigDecimal shotoku_zei;
	private BigDecimal chihou_zei;
	private Date keisan_nengappi;
	private String taishku_riyuu;
	public TaishokukinKeisan(Integer keisan_id, Integer taishoku_id, Integer shain_id, BigDecimal kihon_taishokukin,
			Integer kasan_id, Integer gensan_id, BigDecimal jissyu_taishokukin, BigDecimal shotoku_zei,
			BigDecimal chihou_zei, Date keisan_nengappi, String taishku_riyuu) {
		super();
		this.keisan_id = keisan_id;
		this.taishoku_id = taishoku_id;
		this.shain_id = shain_id;
		this.kihon_taishokukin = kihon_taishokukin;
		this.kasan_id = kasan_id;
		this.gensan_id = gensan_id;
		this.jissyu_taishokukin = jissyu_taishokukin;
		this.shotoku_zei = shotoku_zei;
		this.chihou_zei = chihou_zei;
		this.keisan_nengappi = keisan_nengappi;
		this.taishku_riyuu = taishku_riyuu;
	}
	public Integer getKeisan_id() {
		return keisan_id;
	}
	public void setKeisan_id(Integer keisan_id) {
		this.keisan_id = keisan_id;
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
	public BigDecimal getKihon_taishokukin() {
		return kihon_taishokukin;
	}
	public void setKihon_taishokukin(BigDecimal kihon_taishokukin) {
		this.kihon_taishokukin = kihon_taishokukin;
	}
	public Integer getKasan_id() {
		return kasan_id;
	}
	public void setKasan_id(Integer kasan_id) {
		this.kasan_id = kasan_id;
	}
	public Integer getGensan_id() {
		return gensan_id;
	}
	public void setGensan_id(Integer gensan_id) {
		this.gensan_id = gensan_id;
	}
	public BigDecimal getJissyu_taishokukin() {
		return jissyu_taishokukin;
	}
	public void setJissyu_taishokukin(BigDecimal jissyu_taishokukin) {
		this.jissyu_taishokukin = jissyu_taishokukin;
	}
	public BigDecimal getShotoku_zei() {
		return shotoku_zei;
	}
	public void setShotoku_zei(BigDecimal shotoku_zei) {
		this.shotoku_zei = shotoku_zei;
	}
	public BigDecimal getChihou_zei() {
		return chihou_zei;
	}
	public void setChihou_zei(BigDecimal chihou_zei) {
		this.chihou_zei = chihou_zei;
	}
	public Date getKeisan_nengappi() {
		return keisan_nengappi;
	}
	public void setKeisan_nengappi(Date keisan_nengappi) {
		this.keisan_nengappi = keisan_nengappi;
	}
	public String getTaishku_riyuu() {
		return taishku_riyuu;
	}
	public void setTaishku_riyuu(String taishku_riyuu) {
		this.taishku_riyuu = taishku_riyuu;
	}
	
}
