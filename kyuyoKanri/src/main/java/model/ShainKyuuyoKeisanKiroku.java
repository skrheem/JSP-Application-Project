package model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainKyuuyoKeisanKiroku {
	private Integer shain_id;
	private Date kyuuyo_gatsu;
	private String kyuuyo_jisuu;
	private Date kyuuyo_shikyuubi;
	private BigDecimal shikyuuSougaku;
	private BigDecimal koujoSougaku;
	private BigDecimal jissai_kyuuyo;
	
	private String kubun;
	private String namae_kana;
	private Integer busho_id;

	public ShainKyuuyoKeisanKiroku(Integer shain_id, Date kyuuyo_gatsu, String kyuuyo_jisuu, Date kyuuyo_shikyuubi,
			BigDecimal shikyuuSougaku, BigDecimal koujoSougaku, BigDecimal jissai_kyuuyo) {
		super();
		this.shain_id = shain_id;
		this.kyuuyo_gatsu = kyuuyo_gatsu;
		this.kyuuyo_jisuu = kyuuyo_jisuu;
		this.kyuuyo_shikyuubi = kyuuyo_shikyuubi;
		this.shikyuuSougaku = shikyuuSougaku;
		this.koujoSougaku = koujoSougaku;
		this.jissai_kyuuyo = jissai_kyuuyo;
	}

	public Integer getShain_id() {
		return shain_id;
	}

	public void setShain_id(Integer shain_id) {
		this.shain_id = shain_id;
	}

	public Date getKyuuyo_gatsu() {
		return kyuuyo_gatsu;
	}

	public void setKyuuyo_gatsu(Date kyuuyo_gatsu) {
		this.kyuuyo_gatsu = kyuuyo_gatsu;
	}

	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}

	public void setKyuuyo_jisuu(String kyuuyo_jisuu) {
		this.kyuuyo_jisuu = kyuuyo_jisuu;
	}

	public Date getKyuuyo_shikyuubi() {
		return kyuuyo_shikyuubi;
	}

	public void setKyuuyo_shikyuubi(Date kyuuyo_shikyuubi) {
		this.kyuuyo_shikyuubi = kyuuyo_shikyuubi;
	}

	public BigDecimal getShikyuuSougaku() {
		return shikyuuSougaku;
	}

	public void setShikyuuSougaku(BigDecimal shikyuuSougaku) {
		this.shikyuuSougaku = shikyuuSougaku;
	}

	public BigDecimal getKoujoSougaku() {
		return koujoSougaku;
	}

	public void setKoujoSougaku(BigDecimal koujoSougaku) {
		this.koujoSougaku = koujoSougaku;
	}

	public BigDecimal getJissai_kyuuyo() {
		return jissai_kyuuyo;
	}

	public void setJissai_kyuuyo(BigDecimal jissai_kyuuyo) {
		this.jissai_kyuuyo = jissai_kyuuyo;
	}

	public ShainKyuuyoKeisanKiroku(Integer shain_id, String kubun, String namae_kana, String busho_mei,
			int shikyuuSougaku, int koujoSougaku, int jissai_kyuuyo) {
		super();
		this.shain_id = shain_id;
// 기존 필드에는 없던 필드들에 대한 초기화를 처리하거나 새로운 필드를 추가해야 합니다.
// 예시: 다른 정보 저장을 위한 임시 필드로 처리 가능
		this.kubun = kubun; // 추가된 필드 (String 타입으로 처리)
		this.namae_kana = namae_kana; // 추가된 필드 (String 타입으로 처리)
		this.busho_id = null; // busho_mei는 String이지만 busho_id로 변환이 필요할 수도 있음
		this.shikyuuSougaku = BigDecimal.valueOf(shikyuuSougaku); // int 값을 BigDecimal로 변환
		this.koujoSougaku = BigDecimal.valueOf(koujoSougaku); // int 값을 BigDecimal로 변환
		this.jissai_kyuuyo = BigDecimal.valueOf(jissai_kyuuyo); // int 값을 BigDecimal로 변환
	}
}
