package model;

public class FurikomiYoushiki {
	private Integer youshiki_id;
	private String youshiki_mei;
	public FurikomiYoushiki(Integer youshiki_id, String youshiki_mei) {
		super();
		this.youshiki_id = youshiki_id;
		this.youshiki_mei = youshiki_mei;
	}
	public Integer getYoushiki_id() {
		return youshiki_id;
	}
	public void setYoushiki_id(Integer youshiki_id) {
		this.youshiki_id = youshiki_id;
	}
	public String getYoushiki_mei() {
		return youshiki_mei;
	}
	public void setYoushiki_mei(String youshiki_mei) {
		this.youshiki_mei = youshiki_mei;
	}
}
