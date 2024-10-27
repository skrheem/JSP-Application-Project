package model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainKoujoKiroku {
	private Integer shain_id;
	private Integer koujoKoumoku_id;
	private BigDecimal koujo_kingaku;
	private Date koujo_nengappi;
	private String kyuuyo_jisuu;
	private String koujoKoumoku_mei;
	private String keisanHouhou;
	
	public ShainKoujoKiroku(Integer shain_id, Integer koujoKoumoku_id, BigDecimal koujo_kingaku, Date koujo_nengappi,
			String kyuuyo_jisuu) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujo_kingaku = koujo_kingaku;
		this.koujo_nengappi = koujo_nengappi;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}

	public ShainKoujoKiroku(Integer shain_id, Integer koujoKoumoku_id, BigDecimal koujo_kingaku, Date koujo_nengappi,
			String kyuuyo_jisuu, String koujoKoumoku_mei, String keisanHouhou) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujo_kingaku = koujo_kingaku;
		this.koujo_nengappi = koujo_nengappi;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.keisanHouhou = keisanHouhou;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}

	public BigDecimal getKoujo_kingaku() {
		return koujo_kingaku;
	}

	public Date getKoujo_nengappi() {
		return koujo_nengappi;
	}

	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}

	public String getKoujoKoumoku_mei() {
		return koujoKoumoku_mei;
	}

	public String getKeisanHouhou() {
		return keisanHouhou;
	}
	
}
