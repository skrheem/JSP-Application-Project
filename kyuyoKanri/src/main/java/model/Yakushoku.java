package model;

public class Yakushoku {
	
	private int yakushoku_id;
	private String yakushoku_mei;

	public Yakushoku(int yakushoku_id, String yakushoku_mei) {
		super();
		this.yakushoku_id = yakushoku_id;
		this.yakushoku_mei = yakushoku_mei;
	}

	public int getYakushoku_id() {
		return yakushoku_id;
	}
	public void setYakushoku_id(int yakushoku_id) {
		this.yakushoku_id = yakushoku_id;
	}

	public String getYakushoku_mei() {
		return yakushoku_mei;
	}
	public void setYakushoku_mei(String yakushoku_mei) {
		this.yakushoku_mei = yakushoku_mei;
	}
	
	
}
