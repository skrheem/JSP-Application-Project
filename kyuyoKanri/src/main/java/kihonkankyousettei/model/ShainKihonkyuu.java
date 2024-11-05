package kihonkankyousettei.model;

public class ShainKihonkyuu {

    private Integer kihonKyuu_id;
    private Integer shain_id;
    private Integer kihonKyuu;
    private Integer kokuminNenkinTekiyouGaku;
    private Integer kenkouHokenTekiyouGaku;
    private Integer koyouHokenTekiyouGaku;
    private String kouza_bangou;
    private String yokinshaMeigi;
    private String kinyuuKikan;

    public ShainKihonkyuu(Integer kihonKyuu_id, Integer shain_id, Integer kihonKyuu,
                          Integer kokuminNenkinTekiyouGaku, Integer kenkouHokenTekiyouGaku, 
                          Integer koyouHokenTekiyouGaku, String kouza_bangou, 
                          String yokinshaMeigi, String kinyuuKikan) {
        this.kihonKyuu_id = kihonKyuu_id;
        this.shain_id = shain_id;
        this.kihonKyuu = kihonKyuu;
        this.kokuminNenkinTekiyouGaku = kokuminNenkinTekiyouGaku;
        this.kenkouHokenTekiyouGaku = kenkouHokenTekiyouGaku;
        this.koyouHokenTekiyouGaku = koyouHokenTekiyouGaku;
        this.kouza_bangou = kouza_bangou;
        this.yokinshaMeigi = yokinshaMeigi;
        this.kinyuuKikan = kinyuuKikan;
    }

    // 기본 생성자
    public ShainKihonkyuu() {}

    // Getter 및 Setter 메서드들
    public Integer getKihonKyuu_id() {
        return kihonKyuu_id;
    }

    public void setKihonKyuu_id(Integer kihonKyuu_id) {
        this.kihonKyuu_id = kihonKyuu_id;
    }

    public Integer getShain_id() {
        return shain_id;
    }

    public void setShain_id(Integer shain_id) {
        this.shain_id = shain_id;
    }

    public Integer getKihonKyuu() {
        return kihonKyuu;
    }

    public void setKihonKyuu(Integer kihonKyuu) {
        this.kihonKyuu = kihonKyuu;
    }

    public Integer getKokuminNenkinTekiyouGaku() {
        return kokuminNenkinTekiyouGaku;
    }

    public void setKokuminNenkinTekiyouGaku(Integer kokuminNenkinTekiyouGaku) {
        this.kokuminNenkinTekiyouGaku = kokuminNenkinTekiyouGaku;
    }

    public Integer getKenkouHokenTekiyouGaku() {
        return kenkouHokenTekiyouGaku;
    }

    public void setKenkouHokenTekiyouGaku(Integer kenkouHokenTekiyouGaku) {
        this.kenkouHokenTekiyouGaku = kenkouHokenTekiyouGaku;
    }

    public Integer getKoyouHokenTekiyouGaku() {
        return koyouHokenTekiyouGaku;
    }

    public void setKoyouHokenTekiyouGaku(Integer koyouHokenTekiyouGaku) {
        this.koyouHokenTekiyouGaku = koyouHokenTekiyouGaku;
    }

    public String getKouza_bangou() {
        return kouza_bangou;
    }

    public void setKouza_bangou(String kouza_bangou) {
        this.kouza_bangou = kouza_bangou;
    }

    public String getYokinshaMeigi() {
        return yokinshaMeigi;
    }

    public void setYokinshaMeigi(String yokinshaMeigi) {
        this.yokinshaMeigi = yokinshaMeigi;
    }

    public String getKinyuuKikan() {
        return kinyuuKikan;
    }

    public void setKinyuuKikan(String kinyuuKikan) {
        this.kinyuuKikan = kinyuuKikan;
    }
}
