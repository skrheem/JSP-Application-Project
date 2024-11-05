package kihonkankyousettei.model;

import java.math.BigDecimal;

public class FurikomiYoushikiKoumoku {
	private Integer youshiki_id;
	private String koumoku_mei;
	private Integer disp_junban;
	private String disp_namae;
	public FurikomiYoushikiKoumoku(Integer youshiki_id, String koumoku_mei, Integer disp_junban, String disp_namae) {
		super();
		this.youshiki_id = youshiki_id;
		this.koumoku_mei = koumoku_mei;
		this.disp_junban = disp_junban;
		this.disp_namae = disp_namae;
	}
	public Integer getYoushiki_id() {
		return youshiki_id;
	}
	public void setYoushiki_id(Integer youshiki_id) {
		this.youshiki_id = youshiki_id;
	}
	public String getKoumoku_mei() {
		return koumoku_mei;
	}
	public void setKoumoku_mei(String koumoku_mei) {
		this.koumoku_mei = koumoku_mei;
	}
	public Integer getDisp_junban() {
		return disp_junban;
	}
	public void setDisp_junban(Integer disp_junban) {
		this.disp_junban = disp_junban;
	}
	public String getDisp_namae() {
		return disp_namae;
	}
	public void setDisp_namae(String disp_namae) {
		this.disp_namae = disp_namae;
	}
}
