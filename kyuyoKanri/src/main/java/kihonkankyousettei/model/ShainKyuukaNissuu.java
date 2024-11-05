package kihonkankyousettei.model;

public class ShainKyuukaNissuu {

	private Integer shain_id;
	private Integer kyuukaKoumoku_id;
	private Integer kyuukaNissuu;

	// 모든 필드를 포함하는 생성자
	public ShainKyuukaNissuu(Integer shain_id, Integer kyuukaKoumoku_id, Integer kyuukaNissuu) {
		this.shain_id = shain_id;
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.kyuukaNissuu = kyuukaNissuu;
	}

	// kyuukaKoumoku_id와 kyuukaNissuu만 포함하는 생성자
	public ShainKyuukaNissuu(Integer kyuukaKoumoku_id, Integer kyuukaNissuu) {
		this.kyuukaKoumoku_id = kyuukaKoumoku_id;
		this.kyuukaNissuu = kyuukaNissuu;
	}

	// 기본 생성자
	public ShainKyuukaNissuu() {}

	// Getter와 Setter 메서드
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
