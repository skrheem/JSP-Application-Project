package model;

import java.util.Date;

public class KyuuyoShikyuuJouhou {
	
	private Integer kaisha_id;
	private Date kyuuyoSanteiKaishi;
	private Date kyuuyoSanteiShuuryou;
	private Date kyuuyoShikyuu_bi;
	private String kinyuuKikan;
	private String kouza_bangou;
	private String yokinShaMeigi;
	
	public KyuuyoShikyuuJouhou(Integer kaisha_id, Date kyuuyoSanteiKaishi, Date kyuuyoSanteiShuuryou,
			Date kyuuyoShikyuu_bi, String kinyuuKikan, String kouza_bangou, String yokinShaMeigi) {
		super();
		this.kaisha_id = kaisha_id;
		this.kyuuyoSanteiKaishi = kyuuyoSanteiKaishi;
		this.kyuuyoSanteiShuuryou = kyuuyoSanteiShuuryou;
		this.kyuuyoShikyuu_bi = kyuuyoShikyuu_bi;
		this.kinyuuKikan = kinyuuKikan;
		this.kouza_bangou = kouza_bangou;
		this.yokinShaMeigi = yokinShaMeigi;
	}

	public Integer getKaisha_id() {
		return kaisha_id;
	}

	public void setKaisha_id(Integer kaisha_id) {
		this.kaisha_id = kaisha_id;
	}

	public Date getkyuuyoSanteiKaishi() {
		return kyuuyoSanteiKaishi;
	}

	public void setkyuuyoSanteiKaishi(Date kyuuyoSanteiKaishi) {
		this.kyuuyoSanteiKaishi = kyuuyoSanteiKaishi;
	}

	public Date getKyuuyoSanteiShuuryou() {
		return kyuuyoSanteiShuuryou;
	}

	public void setKyuuyoSanteiShuuryou(Date kyuuyoSanteiShuuryou) {
		this.kyuuyoSanteiShuuryou = kyuuyoSanteiShuuryou;
	}

	public Date getKyuuyoShikyuu_bi() {
		return kyuuyoShikyuu_bi;
	}

	public void setKyuuyoShikyuu_bi(Date kyuuyoShikyuu_bi) {
		this.kyuuyoShikyuu_bi = kyuuyoShikyuu_bi;
	}

	public String getKinyuuKikan() {
		return kinyuuKikan;
	}

	public void setKinyuuKikan(String kinyuuKikan) {
		this.kinyuuKikan = kinyuuKikan;
	}

	public String getKouza_bangou() {
		return kouza_bangou;
	}

	public void setKouza_bangou(String kouza_bangou) {
		this.kouza_bangou = kouza_bangou;
	}

	public String getYokinShaMeigi() {
		return yokinShaMeigi;
	}

	public void setYokinShaMeigi(String yokinShaMeigi) {
		this.yokinShaMeigi = yokinShaMeigi;
	}
	
}
