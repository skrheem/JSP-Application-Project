package model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainKyuuyoKiroku {
	private Integer shain_id;
	private Integer kyuuyoKoumoku_id;
	private BigDecimal kyuuyo_kingaku;
	private Date kyuuyoKoumoku_shikyuubi;
	private String kyuuyo_jisuu;
	public ShainKyuuyoKiroku(Integer shain_id, Integer kyuuyoKoumoku_id, BigDecimal kyuuyo_kingaku,
			Date kyuuyoKoumoku_shikyuubi, String kyuuyo_jisuu) {
		super();
		this.shain_id = shain_id;
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyo_kingaku = kyuuyo_kingaku;
		this.kyuuyoKoumoku_shikyuubi = kyuuyoKoumoku_shikyuubi;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getKyuuyoKoumoku_id() {
		return kyuuyoKoumoku_id;
	}
	public void setKyuuyoKoumoku_id(Integer kyuuyoKoumoku_id) {
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
	}
	public BigDecimal getKyuuyo_kingaku() {
		return kyuuyo_kingaku;
	}
	public void setKyuuyo_kingaku(BigDecimal kyuuyo_kingaku) {
		this.kyuuyo_kingaku = kyuuyo_kingaku;
	}
	public Date getKyuuyoKoumoku_shikyuubi() {
		return kyuuyoKoumoku_shikyuubi;
	}
	public void setKyuuyoKoumoku_shikyuubi(Date kyuuyoKoumoku_shikyuubi) {
		this.kyuuyoKoumoku_shikyuubi = kyuuyoKoumoku_shikyuubi;
	}
	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}
	public void setKyuuyo_jisuu(String kyuuyo_jisuu) {
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}
}
