package model;
//崔東周 최동주
public class ShainKyuukaNissuu {
	
	private Integer shain_id;
	private Integer kyuukaKoumoku_id;
	private Integer kyuukaNissuu;

	public ShainKyuukaNissuu(Integer shain_id, Integer kyuukaKoumoku_id, Integer kyuukaNissuu) {
		super();
		this.shain_id = shain_id;
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.kyuukaNissuu = kyuukaNissuu;
	}

	public Integer getShain_id() {
		return shain_id;
	}
	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public Integer getKyuukaKoumoku_id() {
		return kyuukaKoumoku_id;
	}
	public void setKyuukaKoumoku_id(Integer kyuukaKoumoku_id) {
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
	}

	public Integer getKyuukaNissuu() {
		return kyuukaNissuu;
	}
	public void setKyuukaNissuu(Integer kyuukaNissuu) {
		this.kyuukaNissuu = kyuukaNissuu;
	}
	
}
