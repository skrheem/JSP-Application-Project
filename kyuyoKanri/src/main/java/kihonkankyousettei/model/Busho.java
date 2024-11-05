package kihonkankyousettei.model;

public class Busho {

	private Integer busho_id;
	private String busho_mei;

	public Busho(Integer busho_id, String busho_mei) {
		super();
		this.busho_id = busho_id;
		this.busho_mei = busho_mei;
	}

	public Busho(String busho_mei) {
		super();
		this.busho_mei = busho_mei;
	}

	public Busho() {
		
	}

	public Integer getBusho_id() {
		return busho_id;
	}

	public void setBusho_id(Integer busho_id) {
		this.busho_id = busho_id;
	}

	public String getBusho_mei() {
		return busho_mei;
	}

	public void setBusho_mei(String busho_mei) {
		this.busho_mei = busho_mei;
	}

}
