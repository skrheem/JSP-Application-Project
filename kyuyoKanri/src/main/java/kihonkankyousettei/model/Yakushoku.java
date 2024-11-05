package kihonkankyousettei.model;

public class Yakushoku {

	private Integer yakushoku_id;
	private String yakushoku_mei;

	public Yakushoku(Integer yakushoku_id, String yakushoku_mei) {
		super();
		this.yakushoku_id = yakushoku_id;
		this.yakushoku_mei = yakushoku_mei;
	}

	public Yakushoku(String yakushoku_mei) {
		super();
		this.yakushoku_mei = yakushoku_mei;
	}

	public Yakushoku() {

	}

	public Integer getYakushoku_id() {
		return yakushoku_id;
	}

	public void setYakushoku_id(Integer yakushoku_id) {
		this.yakushoku_id = yakushoku_id;
	}

	public String getYakushoku_mei() {
		return yakushoku_mei;
	}

	public void setYakushoku_mei(String yakushoku_mei) {
		this.yakushoku_mei = yakushoku_mei;
	}

}
