<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="kihonkankyousettei.model.KaishaJouhou,kihonkankyousettei.model.Tantousha,kihonkankyousettei.model.KyuuyoShikyuuJouhou"%>

<!DOCTYPE html>
<!-- 김찬호 金燦鎬 -->
<!-- 기본환경설정 사용자정보 -->
<!-- 基本環境設定 使用者情報 -->
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>使用者情報</title>

<!-- CSS ファイル追加 -->
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/ShiyoushaJouhou.css">

<!-- jQuery 追加 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
    src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- JavaScript ファイル追加 -->
<script type="text/javascript"
    src="${pageContext.request.contextPath}/js/ShiyoushaJouhou.js"></script>

</head>

<body>
    <div class="container">
        <h1>使用者情報</h1>
        <p style="text-align: center; color: #888">* は必須入力事項です。必須入力事項未入力の場合はページ利用ができません。</p>

        <!-- 会社情報と担当者情報を並べて配置 -->
        <div class="row">
            <div class="form-section">
                <h2 class="section-title">会社情報</h2>
                <table>
                    <tr>
                        <td>商号 *</td>
                        <td><input type="text" name="kaisha_mei"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getKaisha_mei()
                                : "" %>"></td>
                        <td>代表者 *</td>
                        <td><input type="text" name="daihyousha_mei"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getDaihyousha_mei()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>事業者番号 *</td>
                        <td><input type="text" name="jigyousha_touroku_bango"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getJigyousha_touroku_bango()
                                : "" %>"></td>
                        <td>法人登録番号</td>
                        <td><input type="text" name="houjin_touroku_bango"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getHoujin_touroku_bango()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>設立日</td>
                        <td><input type="date" name="setsuribi"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getSetsuribi()
                                : "" %>"></td>
                        <td>ホームページ</td>
                        <td><input type="text" name="homepage"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getHomepage()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>郵便番号</td>
                        <td><input type="text" name="yuubin_bango"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getYuubin_bango()
                                : "" %>">
                            <button type="button" onclick="openPostcodePopup()">郵便番号検索</button></td>
                        <td>住所</td>
                        <td><input type="text" name="jigyousho_jyuusho"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getJigyousho_jyuusho()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>電話番号</td>
                        <td><input type="text" name="denwa_kaisha"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getDenwa_kaisha()
                                : "" %>"></td>
                        <td>ファックス番号</td>
                        <td><input type="text" name="fakkusu_bango"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getFakkusu_bango()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>業種</td>
                        <td><input type="text" name="gyoutai"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getGyoutai()
                                : "" %>"></td>
                        <td>種類</td>
                        <td><input type="text" name="shumoku"
                            value="<%= ((KaishaJouhou) request.getAttribute("kaisha")) != null
                                ? ((KaishaJouhou) request.getAttribute("kaisha")).getShumoku()
                                : "" %>"></td>
                    </tr>
                </table>
            </div>

            <div class="form-section">
                <h2 class="section-title">担当者情報</h2>
                <table>
                    <tr>
                        <td>氏名 *</td>
                        <td><input type="text" name="namae_kana"
                            value="<%= ((Tantousha) request.getAttribute("tantousha")) != null
                                ? ((Tantousha) request.getAttribute("tantousha")).getNamae_kana()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>部署</td>
                        <td><select name="busho">
                                <option value="企画チーム">企画チーム</option>
                                <option value="営業チーム">営業チーム</option>
                                <option value="開発チーム">開発チーム</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>職位</td>
                        <td><select name="yakushoku">
                                <option value="社員">社員</option>
                                <option value="代理">代理</option>
                                <option value="課長">課長</option>
                                <option value="チーム長">チーム長</option>
                                <option value="部長">部長</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>電話番号</td>
                        <td><input type="text" name="denwa_tantousha"
                            value="<%= ((Tantousha) request.getAttribute("tantousha")) != null
                                ? ((Tantousha) request.getAttribute("tantousha")).getDenwa_tantousha()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>携帯番号</td>
                        <td><input type="text" name="denwa_keitai"
                            value="<%= ((Tantousha) request.getAttribute("tantousha")) != null
                                ? ((Tantousha) request.getAttribute("tantousha")).getDenwa_keitai()
                                : "" %>"></td>
                    </tr>
                    <tr>
                        <td>メール</td>
                        <td><input type="text" name="meeru"
                            value="<%= ((Tantousha) request.getAttribute("tantousha")) != null
                                ? ((Tantousha) request.getAttribute("tantousha")).getMeeru()
                                : "" %>"></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 給与支給情報 -->
        <div class="form-section">
            <h2 class="section-title">給与支給情報</h2>
            <table>
                <tr>
                    <td>給与算定開始日</td>
                    <td><input type="date" name="kyuuyoSanteiKaishi"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getKyuuyoSanteiKaishi()
                                : "" %>"></td>
                    <td>給与算定終了日</td>
                    <td><input type="date" name="kyuuyoSanteiShuuryou"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getKyuuyoSanteiShuuryou()
                                : "" %>"></td>
                </tr>
                <tr>
                    <td>給与支給日</td>
                    <td><input type="text" name="kyuuyoShikyuu_bi"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getKyuuyoShikyuu_bi()
                                : "" %>"></td>
                    <td>金融機関</td>
                    <td><input type="text" name="kinyuuKikan"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getKinyuuKikan()
                                : "" %>"></td>
                </tr>
                <tr>
                    <td>口座番号</td>
                    <td><input type="text" name="kouza_bangou"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getKouza_bangou()
                                : "" %>"></td>
                    <td>預金者名義</td>
                    <td><input type="text" name="yokinShaMeigi"
                            value="<%= ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")) != null
                                ? ((KyuuyoShikyuuJouhou) request.getAttribute("kyuuyoJouhou")).getYokinShaMeigi()
                                : "" %>"></td>
                </tr>
            </table>
        </div>

        <!-- 会社ロゴおよび印鑑 -->
        <section>
            <h2 class="section-title">会社ロゴおよび印鑑</h2>
            <table style="width: 100%;">
                <tr>
                    <td style="text-align: center;">
                        <label for="companyLogo">会社ロゴ</label><br>
                        <input type="file" id="companyLogo" name="companyLogo"><br>
                        <button type="button" class="btn btn-add">登録</button>
                        <button type="button" class="btn btn-delete">削除</button>
                        <button type="button" class="btn btn-clear">修正依頼</button>
                    </td>
                    <td style="text-align: center;">
                        <label for="companySeal">会社印鑑</label><br>
                        <input type="file" id="companySeal" name="companySeal"><br>
                        <button type="button" class="btn btn-add">登録</button>
                        <button type="button" class="btn btn-delete">削除</button>
                        <button type="button" class="btn btn-clear">修正依頼</button>
                    </td>
                </tr>
            </table>
        </section>

        <!-- 保存およびキャンセルボタン -->
        <div class="button-group">
            <button type="button">保存</button>
            <button type="button" class="cancel">キャンセル</button>
        </div>
    </div>
</body>
</html>
