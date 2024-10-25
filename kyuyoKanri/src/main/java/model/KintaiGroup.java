package model;
//崔東周 최동주
public class KintaiGroup {

	private Integer group_id;
	private String group_name;
	
	public KintaiGroup(Integer group_id, String group_name) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
	}

	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}
