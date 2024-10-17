package model;

import java.util.Date;

public class ShoumeiShoHakkou {

	private int shoumeisho_id;
	private int shain_id;
	private String hakkou_type;
	private String file_keiro;
	private Date hakkou_bi;
	
	public ShoumeiShoHakkou(int shoumeisho_id, int shain_id, String hakkou_type, String file_keiro, Date hakkou_bi) {
		super();
		this.shoumeisho_id = shoumeisho_id;
		this.shain_id = shain_id;
		this.hakkou_type = hakkou_type;
		this.file_keiro = file_keiro;
		this.hakkou_bi = hakkou_bi;
	}
	
	
	public int getShoumeisho_id() {
		return shoumeisho_id;
	}
	public void setShoumeisho_id(int shoumeisho_id) {
		this.shoumeisho_id = shoumeisho_id;
	}
	
	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}
	
	public String getHakkou_type() {
		return hakkou_type;
	}
	public void setHakkou_type(String hakkou_type) {
		this.hakkou_type = hakkou_type;
	}
	
	public String getFile_keiro() {
		return file_keiro;
	}
	public void setFile_keiro(String file_keiro) {
		this.file_keiro = file_keiro;
	}
	
	public Date getHakkou_bi() {
		return hakkou_bi;
	}
	public void setHakkou_bi(Date hakkou_bi) {
		this.hakkou_bi = hakkou_bi;
	}
	
	
}