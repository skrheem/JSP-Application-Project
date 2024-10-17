package model;

public class FuyouKazoku {

	private int kazoku_id;
	private int shain_id;
	private String kankei;
	private String namae;
	private String kubun;
	private String jumin_bangou;
	private char shougaiUmu;
	private char injinKoseiUmu;
	private char kenkouHokenUmu;
	private char doukyoUmu;
	private char gakkunZeiUmu;
	private char hatachiUmu;
	public FuyouKazoku(int kazoku_id, int shain_id, String kankei, String namae, String kubun, String jumin_bangou,
			char shougaiUmu, char injinKoseiUmu, char kenkouHokenUmu, char doukyoUmu, char gakkunZeiUmu,
			char hatachiUmu) {
		super();
		this.kazoku_id = kazoku_id;
		this.shain_id = shain_id;
		this.kankei = kankei;
		this.namae = namae;
		this.kubun = kubun;
		this.jumin_bangou = jumin_bangou;
		this.shougaiUmu = shougaiUmu;
		this.injinKoseiUmu = injinKoseiUmu;
		this.kenkouHokenUmu = kenkouHokenUmu;
		this.doukyoUmu = doukyoUmu;
		this.gakkunZeiUmu = gakkunZeiUmu;
		this.hatachiUmu = hatachiUmu;
	}
	public int getKazoku_id() {
		return kazoku_id;
	}
	public void setKazoku_id(int kazoku_id) {
		this.kazoku_id = kazoku_id;
	}
	public int getShain_id() {
		return shain_id;
	}
	public void setShain_id(int shain_id) {
		this.shain_id = shain_id;
	}
	public String getKankei() {
		return kankei;
	}
	public void setKankei(String kankei) {
		this.kankei = kankei;
	}
	public String getNamae() {
		return namae;
	}
	public void setNamae(String namae) {
		this.namae = namae;
	}
	public String getKubun() {
		return kubun;
	}
	public void setKubun(String kubun) {
		this.kubun = kubun;
	}
	public String getJumin_bangou() {
		return jumin_bangou;
	}
	public void setJumin_bangou(String jumin_bangou) {
		this.jumin_bangou = jumin_bangou;
	}
	public char getShougaiUmu() {
		return shougaiUmu;
	}
	public void setShougaiUmu(char shougaiUmu) {
		this.shougaiUmu = shougaiUmu;
	}
	public char getInjinKoseiUmu() {
		return injinKoseiUmu;
	}
	public void setInjinKoseiUmu(char injinKoseiUmu) {
		this.injinKoseiUmu = injinKoseiUmu;
	}
	public char getKenkouHokenUmu() {
		return kenkouHokenUmu;
	}
	public void setKenkouHokenUmu(char kenkouHokenUmu) {
		this.kenkouHokenUmu = kenkouHokenUmu;
	}
	public char getDoukyoUmu() {
		return doukyoUmu;
	}
	public void setDoukyoUmu(char doukyoUmu) {
		this.doukyoUmu = doukyoUmu;
	}
	public char getGakkunZeiUmu() {
		return gakkunZeiUmu;
	}
	public void setGakkunZeiUmu(char gakkunZeiUmu) {
		this.gakkunZeiUmu = gakkunZeiUmu;
	}
	public char getHatachiUmu() {
		return hatachiUmu;
	}
	public void setHatachiUmu(char hatachiUmu) {
		this.hatachiUmu = hatachiUmu;
	}
	
	
}
