package model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainKyuuyoKeisanKiroku {
	private Integer shain_id;
	private Date kyuuyo_gatsu;
	private String kyuuyo_jisuu;
	private Date kyuuyo_shikyuubi;
	private Integer shikyuuSougaku;
	private Integer koujoSougaku;
	private Integer jissai_kyuuyo;
	
	private String kubun;
	private String namae_kana;
	private String busho_mei;
	
	
	
	public ShainKyuuyoKeisanKiroku(Integer shain_id, String kubun, String namae_kana, String busho_mei,
			Integer shikyuuSougaku, Integer koujoSougaku, Integer jissai_kyuuyo) {
		super();
		this.shain_id = shain_id;
		this.kubun = kubun;
		this.namae_kana = namae_kana;
		this.busho_mei = busho_mei;
		this.shikyuuSougaku = shikyuuSougaku;
		this.koujoSougaku = koujoSougaku;
		this.jissai_kyuuyo = jissai_kyuuyo;
	}
	
	public ShainKyuuyoKeisanKiroku(Integer shain_id, Date kyuuyo_gatsu, String kyuuyo_jisuu, Date kyuuyo_shikyuubi,
			Integer shikyuuSougaku, Integer koujoSougaku, Integer jissai_kyuuyo) {
		super();
		this.shain_id = shain_id;
		this.kyuuyo_gatsu = kyuuyo_gatsu;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
		this.kyuuyo_shikyuubi = kyuuyo_shikyuubi;
		this.shikyuuSougaku = shikyuuSougaku;
		this.koujoSougaku = koujoSougaku;
		this.jissai_kyuuyo = jissai_kyuuyo;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Date getKyuuyo_gatsu() {
		return kyuuyo_gatsu;
	}
	public void setKyuuyo_gatsu(Date kyuuyo_gatsu) {
		this.kyuuyo_gatsu = kyuuyo_gatsu;
	}
	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}
	public void setKyuuyo_jisuu(String kyuuyo_jisuu) {
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}
	public Date getKyuuyo_shikyuubi() {
		return kyuuyo_shikyuubi;
	}
	public void setKyuuyo_shikyuubi(Date kyuuyo_shikyuubi) {
		this.kyuuyo_shikyuubi = kyuuyo_shikyuubi;
	}
	public Integer getShikyuuSougaku() {
		return shikyuuSougaku;
	}
	public void setShikyuuSougaku(Integer shikyuuSougaku) {
		this.shikyuuSougaku = shikyuuSougaku;
	}
	public Integer getKoujoSougaku() {
		return koujoSougaku;
	}
	public void setKoujoSougaku(Integer koujoSougaku) {
		this.koujoSougaku = koujoSougaku;
	}
	public Integer getJissai_kyuuyo() {
		return jissai_kyuuyo;
	}
	public void setJissai_kyuuyo(Integer jissai_kyuuyo) {
		this.jissai_kyuuyo = jissai_kyuuyo;
	}
}
