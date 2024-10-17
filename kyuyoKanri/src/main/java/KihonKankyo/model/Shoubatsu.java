package KihonKankyo.model;

import java.util.Date;

public class Shoubatsu {

	private Integer shouBatsu_id;
	private Integer shain_id;
	private String shouBatsuKubun;
	private String shouBatsuMei;
	private Date shouBatsu_bi;
	private String shouBatsuSha;
	private String shouBatsuNaiyou;
	private String bikou;

	public Shoubatsu(Integer shouBatsu_id, Integer shain_id, String shouBatsuKubun, String shouBatsuMei,
			Date shouBatsu_bi, String shouBatsuSha, String shouBatsuNaiyou, String bikou) {
		super();
		this.shouBatsu_id = shouBatsu_id;
		this.shain_id = shain_id;
		this.shouBatsuKubun = shouBatsuKubun;
		this.shouBatsuMei = shouBatsuMei;
		this.shouBatsu_bi = shouBatsu_bi;
		this.shouBatsuSha = shouBatsuSha;
		this.shouBatsuNaiyou = shouBatsuNaiyou;
		this.bikou = bikou;
	}

	public Integer getShouBatsu_id() {
		return shouBatsu_id;
	}

	public void setShouBatsu_id(Integer shouBatsu_id) {
		this.shouBatsu_id = shouBatsu_id;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public String getShouBatsuKubun() {
		return shouBatsuKubun;
	}

	public void setShouBatsuKubun(String shouBatsuKubun) {
		this.shouBatsuKubun = shouBatsuKubun;
	}

	public String getShouBatsuMei() {
		return shouBatsuMei;
	}

	public void setShouBatsuMei(String shouBatsuMei) {
		this.shouBatsuMei = shouBatsuMei;
	}

	public Date getShouBatsu_bi() {
		return shouBatsu_bi;
	}

	public void setShouBatsu_bi(Date shouBatsu_bi) {
		this.shouBatsu_bi = shouBatsu_bi;
	}

	public String getShouBatsuSha() {
		return shouBatsuSha;
	}

	public void setShouBatsuSha(String shouBatsuSha) {
		this.shouBatsuSha = shouBatsuSha;
	}

	public String getShouBatsuNaiyou() {
		return shouBatsuNaiyou;
	}

	public void setShouBatsuNaiyou(String shouBatsuNaiyou) {
		this.shouBatsuNaiyou = shouBatsuNaiyou;
	}

	public String getBikou() {
		return bikou;
	}

	public void setBikou(String bikou) {
		this.bikou = bikou;
	}

}
