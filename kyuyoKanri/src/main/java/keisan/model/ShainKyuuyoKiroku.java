package keisan.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainKyuuyoKiroku {
	private Integer shain_id;
	private Integer kyuuyoKoumoku_id;
	private BigDecimal kyuuyo_kingaku;
	private Date kyuuyoKoumoku_shikyuubi;
	private String kyuuyo_jisuu;
	private String kyuuyoKoumoku_mei;
	private String keisanHouhou;
	
	
	public ShainKyuuyoKiroku(Integer shain_id, Integer kyuuyoKoumoku_id, BigDecimal kyuuyo_kingaku,
			Date kyuuyoKoumoku_shikyuubi, String kyuuyo_jisuu) {
		super();
		this.shain_id = shain_id;
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyo_kingaku = kyuuyo_kingaku;
		this.kyuuyoKoumoku_shikyuubi = kyuuyoKoumoku_shikyuubi;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}
	
	public ShainKyuuyoKiroku(Integer shain_id, Integer kyuuyoKoumoku_id, BigDecimal kyuuyo_kingaku,
			Date kyuuyoKoumoku_shikyuubi, String kyuuyo_jisuu, String koujoKoumoku_mei, String keisanHouhou) {
		super();
		this.shain_id = shain_id;
		this.kyuuyoKoumoku_id = kyuuyoKoumoku_id;
		this.kyuuyo_kingaku = kyuuyo_kingaku;
		this.kyuuyoKoumoku_shikyuubi = kyuuyoKoumoku_shikyuubi;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
		this.kyuuyoKoumoku_mei = koujoKoumoku_mei;
		this.keisanHouhou = keisanHouhou;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public Integer getKyuuyoKoumoku_id() {
		return kyuuyoKoumoku_id;
	}

	public BigDecimal getKyuuyo_kingaku() {
		return kyuuyo_kingaku;
	}

	public Date getKyuuyoKoumoku_shikyuubi() {
		return kyuuyoKoumoku_shikyuubi;
	}

	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}

	public String getKyuuyoKoumoku_mei() {
		return kyuuyoKoumoku_mei;
	}

	public String getKeisanHouhou() {
		return keisanHouhou;
	}
	
	
}
