package model;

import java.math.BigDecimal;
import java.util.Date;

public final class FurikomiShinseiKiroku {
	
		private Integer shinsei_id;
		private Integer shukkin_id;
		private Integer nyuukin_id;
		private Integer yokinsha_id;
		private Date furikomi_bi;
		private String shukkinKinyuuKikan;
		private String shukkinKouza;
		private String nyuukinKinyuuKikan;
		private String nyuukinKouza;
		private String yokinshaMeigi;
		private BigDecimal furikomi_kingaku;
		private String bikou;
		private String shoriKekka;
		private Date kyuuyo_gatsu;
		private String kyuuyo_jisuu;
		public FurikomiShinseiKiroku(Integer shinsei_id, Integer shukkin_id, Integer nyuukin_id, Integer yokinsha_id,
				Date furikomi_bi, String shukkinKinyuuKikan, String shukkinKouza, String nyuukinKinyuuKikan,
				String nyuukinKouza, String yokinshaMeigi, BigDecimal furikomi_kingaku, String bikou, String shoriKekka,
				Date kyuuyo_gatsu, String kyuuyo_jisuu) {
			super();
			this.shinsei_id = shinsei_id;
			this.shukkin_id = shukkin_id;
			this.nyuukin_id = nyuukin_id;
			this.yokinsha_id = yokinsha_id;
			this.furikomi_bi = furikomi_bi;
			this.shukkinKinyuuKikan = shukkinKinyuuKikan;
			this.shukkinKouza = shukkinKouza;
			this.nyuukinKinyuuKikan = nyuukinKinyuuKikan;
			this.nyuukinKouza = nyuukinKouza;
			this.yokinshaMeigi = yokinshaMeigi;
			this.furikomi_kingaku = furikomi_kingaku;
			this.bikou = bikou;
			this.shoriKekka = shoriKekka;
			this.kyuuyo_gatsu = kyuuyo_gatsu;
			this.kyuuyo_jisuu = kyuuyo_jisuu;
		}
		public Integer getShinsei_id() {
			return shinsei_id;
		}
		public Integer getShukkin_id() {
			return shukkin_id;
		}
		public Integer getNyuukin_id() {
			return nyuukin_id;
		}
		public Integer getYokinsha_id() {
			return yokinsha_id;
		}
		public Date getFurikomi_bi() {
			return furikomi_bi;
		}
		public String getShukkinKinyuuKikan() {
			return shukkinKinyuuKikan;
		}
		public String getShukkinKouza() {
			return shukkinKouza;
		}
		public String getNyuukinKinyuuKikan() {
			return nyuukinKinyuuKikan;
		}
		public String getNyuukinKouza() {
			return nyuukinKouza;
		}
		public String getYokinshaMeigi() {
			return yokinshaMeigi;
		}
		public BigDecimal getFurikomi_kingaku() {
			return furikomi_kingaku;
		}
		public String getBikou() {
			return bikou;
		}
		public String getShoriKekka() {
			return shoriKekka;
		}
		public Date getKyuuyo_gatsu() {
			return kyuuyo_gatsu;
		}
		public String getKyuuyo_jisuu() {
			return kyuuyo_jisuu;
		}
}
