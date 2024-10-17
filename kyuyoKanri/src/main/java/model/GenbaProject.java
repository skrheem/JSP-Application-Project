package model;

public class GenbaProject {
	private Integer genba_project_id;
	private String genba_project_mei;
	public GenbaProject(Integer genba_project_id, String genba_project_mei) {
		super();
		this.genba_project_id = genba_project_id;
		this.genba_project_mei = genba_project_mei;
	}
	public Integer getGenba_project_id() {
		return genba_project_id;
	}
	public void setGenba_project_id(Integer genba_project_id) {
		this.genba_project_id = genba_project_id;
	}
	public String getGenba_project_mei() {
		return genba_project_mei;
	}
	public void setGenba_project_mei(String genba_project_mei) {
		this.genba_project_mei = genba_project_mei;
	}
}
