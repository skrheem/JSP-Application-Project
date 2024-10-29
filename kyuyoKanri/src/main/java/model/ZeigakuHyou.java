package model;

public class ZeigakuHyou {
	private Integer zeigaku_id;
	private int ijyou;
	private int miman;
	private int FuyouKazokuSuu;
	private int shotoku_zei;
	public ZeigakuHyou(Integer zeigaku_id, int ijyou, int miman, int fuyouKazokuCount, int shotoku_zei) {
		super();
		this.zeigaku_id = zeigaku_id;
		this.ijyou = ijyou;
		this.miman = miman;
		FuyouKazokuSuu = fuyouKazokuCount;
		this.shotoku_zei = shotoku_zei;
	}
	
	public ZeigakuHyou(Integer zeigaku_id, int shotoku_zei) {
		super();
		this.zeigaku_id = zeigaku_id;
		this.shotoku_zei = shotoku_zei;
	}

	public Integer getZeigau_id() {
		return zeigaku_id;
	}
	public int getIjyou() {
		return ijyou;
	}
	public int getMiman() {
		return miman;
	}
	public int getFuyouKazokuSuu() {
		return FuyouKazokuSuu;
	}
	public int getShotoku_zei() {
		return shotoku_zei;
	}
	
}
