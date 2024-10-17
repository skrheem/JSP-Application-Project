package model;

public class ShainKyuukaNissuu {
	
	private int shain_id;
	private int kyuukaKoumoku_id;
	private int kyuukaNissuu;

	public ShainKyuukaNissuu(int shain_id, int kyuukaKoumoku_id, int kyuukaNissuu) {
		super();
		this.shain_id = shain_id;
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.kyuukaNissuu = kyuukaNissuu;
	}

	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}

	public int getKyuukaKoumoku_id() {
		return kyuukaKoumoku_id;
	}
	public void setKyuukaKoumoku_id(int kyuukaKoumoku_id) {
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
	}

	public int getKyuukaNissuu() {
		return kyuukaNissuu;
	}
	public void setKyuukaNissuu(int kyuukaNissuu) {
		this.kyuukaNissuu = kyuukaNissuu;
	}
	
}
