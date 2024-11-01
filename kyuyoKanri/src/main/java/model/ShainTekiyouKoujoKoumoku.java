package model;

import java.math.BigDecimal;
import java.util.Date;

public class ShainTekiyouKoujoKoumoku {
	private Integer shain_id;
	private Integer koujoKoumoku_id;
	private Date koujoKoumoku_tekiyoubi;

	private String koujoKoumoku_mei;
	private double koujoKoumoku_ritsu;
	private String keisanHouhou;

	private BigDecimal kihonKyuu;

	private double koujoGaku;
	
	private String kihonKoumoku;

	
	
	public ShainTekiyouKoujoKoumoku() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 임세규 林世圭 급여입력・관리 페이지 / 給与入力・管理ページ
	// 사원별로 적용되는 공제항목의 이름, 공제율, 계산방법을 담는다.
	// 社員ごとに適用される控除項目の名、控除率、計算方法を持つ。
	public ShainTekiyouKoujoKoumoku(String koujoKoumoku_mei, double koujoKoumoku_ritsu, String keisanHouhou) {
		super();
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.koujoKoumoku_ritsu = koujoKoumoku_ritsu;
		this.keisanHouhou = keisanHouhou;
	}

	public ShainTekiyouKoujoKoumoku(Integer shain_id, Integer koujoKoumoku_id, Date koujoKoumoku_tekiyoubi) {
		super();
		this.shain_id = shain_id;
		this.koujoKoumoku_id = koujoKoumoku_id;
		this.koujoKoumoku_tekiyoubi = koujoKoumoku_tekiyoubi;
	}

	// 급여입력관리에서 사원에게 적용되는 공제항목의 이름과 공제율, 기본급을 가져와서 값을 계산할거임
	public ShainTekiyouKoujoKoumoku(Integer koujokoumoku_id, double koujoRitsu, String koujoKoumoku_mei, BigDecimal kihonkyuu, String kihonKoumoku, String keisanHouhou) {
		this.koujoKoumoku_id = koujokoumoku_id;
		this.koujoKoumoku_ritsu = koujoRitsu;
		this.koujoKoumoku_mei = koujoKoumoku_mei;
		this.kihonKyuu = kihonkyuu;
		this.kihonKoumoku = kihonKoumoku;
		this.keisanHouhou = keisanHouhou;
	}
	public ShainTekiyouKoujoKoumoku(Integer koujokoumoku_id, double koujoGaku ) {
		this.koujoKoumoku_id = koujokoumoku_id;
		this.koujoGaku = koujoGaku;
	}
	public Integer getShain_id() {
		return shain_id;
	}

	public Integer getKoujoKoumoku_id() {
		return koujoKoumoku_id;
	}

	public Date getKoujoKoumoku_tekiyoubi() {
		return koujoKoumoku_tekiyoubi;
	}

	public String getKoujoKoumoku_mei() {
		return koujoKoumoku_mei;
	}

	public double getKoujoKoumoku_ritsu() {
		return koujoKoumoku_ritsu;
	}

	public String getKeisanHouhou() {
		return keisanHouhou;
	}

	public BigDecimal getKihonKyuu() {
		return kihonKyuu;
	}

	public double getKoujoGaku() {
		return koujoGaku;
	}
	
	public void setKoujoGaku(double koujoGaku) {
		this.koujoGaku = koujoGaku;
	}

	public void setKihonKoumoku(String kihonKoumoku) {
		this.kihonKoumoku = kihonKoumoku;
	}

	public double keisan() {
		return (BigDecimal.valueOf(this.koujoKoumoku_ritsu).multiply(this.kihonKyuu)).doubleValue();
	}
	
	public String getKihonKoumoku() {
		return this.kihonKoumoku;
	}
}
