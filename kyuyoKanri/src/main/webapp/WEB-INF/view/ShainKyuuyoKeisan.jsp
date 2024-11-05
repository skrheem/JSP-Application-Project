<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>給与入力/管理</title>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui/1.12.1/i18n/datepicker-ko.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ShainKyuuyoKeisan.css">
<script src="${pageContext.request.contextPath}/js/ShainKyuuyoKeisan.js"></script>
<style>
</style>
</head>
<body>
	<div class="container">
		<header class="header">
			<h1>給与入力/管理</h1>
			<p>月別、社員別の給与およびボーナス情報を入力、保存、管理するメニューです。</p>
		</header>
		<section class="filter-section">
			<div class="filters">
				<label for="year">帰属年月</label> <select name="kyuuyoNendo"
					id="kyuuyoNendo" onchange="getShainList()">
					<option value="" disabled>年を選択</option>
					<c:forEach var="year" begin="2005" end="2025">
						<option value="${year}"
							<c:if test="${year == param.kyuuyoNendo || (empty param.kyuuyoNendo && year == 2024)}">selected</c:if>>
							${year}年</option>
					</c:forEach>
				</select> <select name="kyuuyoGatsu" id="kyuuyoGatsu"
					onchange="getShainList()">
					<option value="" disabled>月を選択</option>
					<c:forEach var="month" begin="1" end="12">
						<c:set var="monthStr" value="${month lt 10 ? '0' + month : month}" />
						<option value="${monthStr}"
							<c:if test="${monthStr == param.kyuuyoGatsu || (empty param.kyuuyoGatsu && monthStr == '09')}">selected</c:if>>
							${monthStr}月</option>
					</c:forEach>
				</select> <label for="kyuuyoJisuu">給与回数</label> <select name="kyuuyoJisuu"
					id="kyuuyoJisuu" onchange="getShainList()">
					<option value="" disabled>回数を選択</option>
					<c:forEach var="cycle" begin="1" end="10">
						<c:set var="cycleStr" value="${cycle lt 10 ? '0' + cycle : cycle}" />
						<option value="${cycleStr}"
							<c:if test="${cycleStr == param.kyuuyoJisuu || (empty param.kyuuyoJisuu && cycleStr == '01')}">selected</c:if>>
							給与-${cycleStr}回</option>
					</c:forEach>
				</select>
				<div id="modifyFormContainer">
					<label for="standard-period">精算期間</label> <input type="text"
						id="standard-period-start" name="santei_kaishi"
						placeholder="2024-11-01" autocomplete="off"> <span>~</span>
					<input type="text" id="standard-period-end" name="santei_shuuryou"
						placeholder="2024-11-30" autocomplete="off"> <label
						for="pay-date">給与支給日</label> <input type="text" id="pay-date"
						name="shikyuu_bi" placeholder="2024-12-05" autocomplete="off">
					<input type="text" id="EXstandard-period-start"
						name="standard-period-start" autocomplete="off"
						style="display: none"> <input type="text"
						id="EXstandard-period-end" name="standard-period-end"
						autocomplete="off" style="display: none"> <input
						type="text" id="EXpay-date" name="pay-date" autocomplete="off"
						style="display: none">
					<button type="button" id="submitButton" class="btn edit"
						onclick="modify()">修正</button>
				</div>

				<label for="calc-method">計算方法</label>
				<div class="calc-toggle">
					<span>off</span> <input type="checkbox" id="calc-method">
				</div>
			</div>
		</section>

		<section class="main-horizontal-content">
			<div class="table-section employee-list"
				style="display: flex; flex-direction: column;">
				<div style="padding-bottom: 10px">
					<button onclick="openPastPopup();pastKyuuyoKeisan()">過去給与を読み込む</button>
					<button onclick="openPopup();getShainTsuikaList()">新規追加</button>
					<button id="oneDelete" onclick="deleteOne()" data-value="0">選択削除</button>
					<button onclick="deleteAll()">全削除</button>
				</div>

				<table id="keisanKirokuTable" class="payroll-table">
					<thead>
						<tr>
							<th>区分</th>
							<th>氏名</th>
							<th>部署</th>
							<th>支給総額</th>
							<th>控除総額</th>
							<th>実支給額</th>
						</tr>
					</thead>
					<tbody>
						<!-- AJAX로 받은 데이터가 여기에 동적으로 추가됩니다 -->
					</tbody>
				</table>
				<div id="totalSummary">
					<p>
						給与 <strong>総合情報</strong>
					</p>
					<p>
						月合計: <span id="totalRowCount">0</span> 件
					</p>
					<p>
						給与総額合計: <span id="totalAllShikyuuSougaku">0</span> 円
					</p>
					<p>
						控除総額合計: <span id="totalAllKoujoSougaku">0</span> 円
					</p>
					<p>
						実支給額合計: <span id="totalAllJissaiKyuuyo">0</span> 円
					</p>
				</div>
			</div>
			<div class="calcBox">
				<div class="toggle-buttons" id="incomeDiv" data-value="0">
					<button id="generalIncome" class="toggle-button active"
						onclick="kinroList()">一般所得</button>
					<button id="businessIncome" class="toggle-button inactive"
						onclick="businessList()">事業所得/雑所得</button>
				</div>
				<div class="income-deduction-content">
					<div class="income-section">
						<div class="section-header">
							<h3>支給項目</h3>
							<button onclick="openKyuuyoPopup()" class="m-btn">M</button>
						</div>
						<c:choose>
							<c:when test="${not empty shainKyuuyoKirokuList}">
								<!-- shainKoujoKirokuListが存在し、空でない場合 -->
								<c:forEach var="kyuuyoKoumoku" items="${shainKyuuyoKirokuList}">
									<label>${kyuuyoKoumoku.getKyuuyoKoumoku_mei()}</label>
									<input type="text" oninput="formatWithCommas(this)" value="0"
										id="kyuuyo-${kyuuyoKoumoku.getKyuuyoKoumoku_id()}">
									<div class="keisan-label">${kyuuyoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<!-- shainKoujoKirokuList가 존재하지 않거나 비어 있는 경우 -->
								<c:forEach var="kyuuyoKoumoku" items="${kyuuyoList}">
									<label>${kyuuyoKoumoku.getKyuuyoKoumoku_mei() }</label>
									<input type="text" oninput="formatWithCommas(this)" value="0"
										id="kyuuyo-${kyuuyoKoumoku.getKyuuyoKoumoku_id()}">
									<div class="keisan-label">${kyuuyoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="total">
							支給総額: <span id="totalKyuuyoSougaku">0</span> 円
						</div>
					</div>
					<div class="deduction-section">
						<div class="section-header">
							<h3>控除項目</h3>
							<button onclick="openKoujoPopup()" class="m-btn">M</button>
							<button onclick="calc(this)" class="auto-calc-btn"
								id="calcButton" data-value="0">自動計算</button>
						</div>

						<c:forEach var="koujoKoumoku" items="${kihonKoujoList}">
							<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
							<input type="text" oninput="formatWithCommas(this)" value="0"
								id="kihonkoujo-${koujoKoumoku.getKoujoKoumoku_id()}">
							<div class="keisan-label">${koujoKoumoku.getKeisanHouhou()}</div>
							<br>
						</c:forEach>
						<c:forEach var="koujoKoumoku" items="${koujoList}">
							<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
							<input type="text" oninput="formatWithCommas(this)" value="0"
								id="koujo-${koujoKoumoku.getKoujoKoumoku_id()}">
							<div class="keisan-label">${koujoKoumoku.getKeisanHouhou()}</div>
							<br>
						</c:forEach>
						<div class="total">
							控除総額: <span id="totalKoujoSougaku">0</span> 円
						</div>
					</div>
				</div>
				<div class="net-pay-summary">
					<span class="net-pay-font">実支給額:</span> <span class="net-pay-value"
						id="totalJissaiKyuuyo">0</span>
				</div>
				<div class="calc-button-box">
					<button class="btn save" id="saveButton"
						onclick="saveInfo();saveKyuuyoKiroku();saveKoujoKiroku()"
						data-value="0">
						<span>保存</span>
					</button>
					<button class="btn clear" id="clearAllButton" onclick="reset()">
						<span>内容をクリア</span>
					</button>
				</div>
			</div>
		</section>
	</div>

	<div id="overlay" onclick="closeAllPopup(event)"></div>

	<div id="popup">
		<h2>給与支給社員選択</h2>
		<input type="text" id="searchInput" placeholder="社員検索">
		<button onclick="toggleSelectAll()">全選択</button>
		<table id="shainTable">
			<thead>
				<tr>
					<th>選択</th>
					<th>区分</th>
					<th>社員番号</th>
					<th>氏名</th>
					<th>部署</th>
					<th>職位</th>
					<th>状態</th>
				</tr>
			</thead>
			<tbody>
				<!-- 社員リストがここに表示されます -->
			</tbody>
		</table>
		<button onclick="selectShain()">社員選択</button>
		<button onclick="closePopup()">選択キャンセル</button>
	</div>

	<!-- 支給項目ポップアップ -->
	<div class="popup kyuuyoPopup" id="popupKyuuyo">
		<h2>支給項目変更</h2>
		<label for="category">支給項目選択</label> 
		<select id="kyuuyoCategory">
			<option>選択してください。</option>
		</select> 
		<label for="item">支給項目</label> 
		<input type="text" id="kyuuyo-mei" placeholder="支給項目を入力してください。"> 
		<label>課税有無</label>
		<div class="radio-group">
			<input type="radio" id="taxable" name="tax" value="全課税"> 
			<label for="taxable">全体課税</label> 
			<input type="radio" id="non-taxable" name="tax" value="非課税"> 
			<label for="non-taxable">非課税</label>
		</div>
		<label for="non-tax-name">非課税名</label> 
		<input type="text" id="non-tax-name" placeholder="非課税項目" > 
		<label for="limit-amount">非課税限度額</label> 
		<input type="text" id="limit-amount" placeholder="0 円" > 
		<label for="calc-method">計算方法</label> 
		<input type="text" id="kyuuyo-calc-method" placeholder="計算方法を入力してください。"> 
		<label for="unit">単位</label> 
		<select id="kyuuyo-unit">
			<option>選択してください。</option>
			<option>単位なし</option>
			<option value="1">1円</option>
			<option value="10">10円</option>
			<option value="100">100円</option>
		</select> 
		<label for="bulk-pay">勤怠連携/一括支給</label> 
		<select id="bulk-pay">
			<option>選択してください。</option>
			<option>なし</option>
			<option>一括支払い</option>
		</select> 
		<label for="bulk-amount">一括支給額</label> 
		<input type="text" id="bulk-amount" placeholder="0" >

		<div class="buttons">
			<button class="add-btn" onclick="addKyuuyo()">追加</button>
			<button class="edit-btn" onclick="editKyuuyo()">修正</button>
			<button class="delete-btn" onclick="deleteKyuuyo()">削除</button>
		</div>
	</div>

	<!-- 控除項目ポップアップ -->
	<div class="popup koujoPopup" id="popupKoujo">
		<h2>控除項目変更</h2>
		<label for="category">控除項目選択</label> 
		<select id="koujoCategory">
			<option>選択してください。</option>
		</select>
		<label for="item">控除項目</label> 
		<input type="text" id="koujo-mei" placeholder="控除項目を入力してください。"> 
			<label for="calc-method">計算方法</label>
		<input type="text" id="koujo-calc-method" placeholder="計算方法を入力してください。">
		<label for="unit">単位</label> 
		<select id="koujo-unit">
			<option>選択してください。</option>
			<option>単位なし</option>
			<option value="1">1円</option>
			<option value="10">10円</option>
			<option value="100">100円</option>
		</select> <label for="note">備考</label> 
		<input type="text" id="koujo-note" placeholder="備考を入力してください。">
		<input type="text" id="koujo-id" style="display: none">
		<div class="buttons">
			<button class="add-btn" onclick="addKoujo()">追加</button>
			<button class="edit-btn" onclick="editKoujo()">修正</button>
			<button class="delete-btn" onclick="deleteKoujo()">削除</button>
		</div>
	</div>

	<div id="popupPast">
		<h2>給与年月選択</h2>
		<select id="yearMonthSelectPast">
			<option id=>帰属年月回数選択</option>
		</select>
		<button onclick="loadPastSalaryInfo()">給与情報読み込み</button>
	</div>
</body>
</html>
