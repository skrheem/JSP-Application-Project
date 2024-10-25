package model;
//崔東周 최동주
public final class Busho {

	private Integer busho_id;
	private String busho_mei;
	
	public Busho(Integer busho_id, String busho_mei) {
		super();
		this.busho_id = busho_id;
		this.busho_mei = busho_mei;
	}

	public Integer getBusho_id() {
		return busho_id;
	}

	public String getBusho_mei() {
		return busho_mei;
	}
	
}
