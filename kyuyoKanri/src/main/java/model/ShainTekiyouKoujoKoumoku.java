package model;

import java.util.Date;

public class ShainTekiyouKoujoKoumoku {
	private Integer shain_id;
	private Integer koujoKoumoku_id;
	private Date koujoKoumoku_tekiyoubi;
	public ShainTekiyouKoujoKoumoku(Integer shain_id, Integer koujoKoumoku_id, Date koujoKoumoku_tekiyoubi) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
	}
	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}
	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}
	public void setKoujoKoumoku_id(Integer koujoKoumoku_id) {
		this.koujoKoumoku_id = koujoKoumoku_id;
	}
	public Date getKoujoKoumoku_tekiyoubi() {
		return koujoKoumoku_tekiyoubi;
	}
	public void setKoujoKoumoku_tekiyoubi(Date koujoKoumoku_tekiyoubi) {
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
	}
}
