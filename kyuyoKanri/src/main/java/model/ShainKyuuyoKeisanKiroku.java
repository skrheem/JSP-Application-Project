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
	private String busho_mei;
	
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

	public Date getKyuuyo_gatsu() {
		return kyuuyo_gatsu;
	}

	public String getKyuuyo_jisuu() {
		return kyuuyo_jisuu;
	}

	public Date getKyuuyo_shikyuubi() {
		return kyuuyo_shikyuubi;
	}

	public BigDecimal getShikyuuSougaku() {
		return shikyuuSougaku;
	}

	public BigDecimal getKoujoSougaku() {
		return koujoSougaku;
	}

	public BigDecimal getJissai_kyuuyo() {
		return jissai_kyuuyo;
	}
	
	public String getKubun() {
		return kubun;
	}

	public String getNamae_kana() {
		return namae_kana;
	}

	public Integer getBusho_id() {
		return busho_id;
	}

	public String getBusho_mei() {
		return busho_mei;
	}
	
	public ShainKyuuyoKeisanKiroku(Integer shain_id, String kubun, String namae_kana, String busho_mei,
			int shikyuuSougaku, int koujoSougaku, int jissai_kyuuyo) {
		super();
		this.shain_id = shain_id;
		this.kubun = kubun; // 추가된 필드 (String 타입으로 처리)
		this.namae_kana = namae_kana; // 추가된 필드 (String 타입으로 처리)
		this.busho_mei = busho_mei; // busho_mei는 String이지만 busho_id로 변환이 필요할 수도 있음
		this.shikyuuSougaku = BigDecimal.valueOf(shikyuuSougaku); // int 값을 BigDecimal로 변환
		this.koujoSougaku = BigDecimal.valueOf(koujoSougaku); // int 값을 BigDecimal로 변환
		this.jissai_kyuuyo = BigDecimal.valueOf(jissai_kyuuyo); // int 값을 BigDecimal로 변환
	}
}
