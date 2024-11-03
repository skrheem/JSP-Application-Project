<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>급여입력/관리</title>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui/1.12.1/i18n/datepicker-ko.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ShainKyuuyoKeisan.css">
<script src="${pageContext.request.contextPath}/js/ShainKyuuyoKeisan.js"></script>
<style>
</style>
</head>
<body>
	<div class="container">
		<header class="header">
			<h1>급여입력/관리</h1>
			<p>월별, 사원별 급여 및 상여금 정보를 입력, 저장, 관리하는 메뉴입니다.</p>
		</header>
		<!-- Adding filter section below the description -->
		<section class="filter-section">
			<div class="filters">
				<label for="year">귀속연월</label> <select name="kyuuyoNendo" id="kyuuyoNendo"
					onchange="getShainList()">
					<option value="" disabled>연도 선택</option>
					<c:forEach var="year" begin="2005" end="2025">
						<option value="${year}"
							<c:if test="${year == param.kyuuyoNendo || (empty param.kyuuyoNendo && year == 2024)}">selected</c:if>>
							${year}년</option>
					</c:forEach>
				</select> <select name="kyuuyoGatsu" id="kyuuyoGatsu" onchange="getShainList()">
					<option value="" disabled>월 선택</option>
					<c:forEach var="month" begin="1" end="12">
						<c:set var="monthStr" value="${month lt 10 ? '0' + month : month}" />
						<option value="${monthStr}"
							<c:if test="${monthStr == param.kyuuyoGatsu || (empty param.kyuuyoGatsu && monthStr == '09')}">selected</c:if>>
							${monthStr}월</option>
					</c:forEach>
				</select> <label for="kyuuyoJisuu">급여차수</label> <select name="kyuuyoJisuu" id="kyuuyoJisuu"
					onchange="getShainList()">
					<option value="" disabled>차수 선택</option>
					<c:forEach var="cycle" begin="1" end="10">
						<c:set var="cycleStr" value="${cycle lt 10 ? '0' + cycle : cycle}" />
						<option value="${cycleStr}"
							<c:if test="${cycleStr == param.kyuuyoJisuu || (empty param.kyuuyoJisuu && cycleStr == '01')}">selected</c:if>>
							급여-${cycleStr}차</option>
					</c:forEach>
				</select>
				<div id="modifyFormContainer">
					<label for="standard-period">정산기간</label> <input type="text" id="standard-period-start"
						name="santei_kaishi" placeholder="2024-11-01" autocomplete="off"> <span>~</span> <input
						type="text" id="standard-period-end" name="santei_shuuryou" placeholder="2024-11-30"
						autocomplete="off"> <label for="pay-date">급여지급일</label> <input type="text"
						id="pay-date" name="shikyuu_bi" placeholder="2024-12-05" autocomplete="off"> <input
						type="text" id="EXstandard-period-start" name="standard-period-start" autocomplete="off"
						style="display: none"> <input type="text" id="EXstandard-period-end"
						name="standard-period-end" autocomplete="off" style="display: none"> <input
						type="text" id="EXpay-date" name="pay-date" autocomplete="off" style="display: none">
					<button type="button" id="submitButton" class="btn edit" onclick="modify()">수정</button>
				</div>

				<label for="calc-method">계산방법</label>
				<div class="calc-toggle">
					<span>off</span> <input type="checkbox" id="calc-method">
				</div>
			</div>
		</section>

		<section class="main-horizontal-content">
			<div class="table-section employee-list" style="display: flex; flex-direction: column;">
				<div style="padding-bottom: 10px">
					<button onclick="handleMButtonClick()">지난급여 불러오기</button>
					<button onclick="openPopup();getShainTsuikaList()">신규추가</button>
					<button id="oneDelete" onclick="deleteOne()" data-value="0">선택삭제</button>
					<button onclick="deleteAll()">전체삭제</button>
				</div>

				<table id="keisanKirokuTable" class="payroll-table">
					<thead>
						<tr>
							<th>구분</th>
							<th>성명</th>
							<th>부서</th>
							<th>지급총액</th>
							<th>공제총액</th>
							<th>실지급액</th>
						</tr>
					</thead>
					<tbody>
						<!-- AJAX로 받은 데이터가 여기에 동적으로 추가됩니다 -->
					</tbody>
				</table>
				<div id="totalSummary">
					<p>
						급여 <strong>종합정보</strong>
					</p>
					<p>
						월 합계: <span id="totalRowCount">0</span> 건
					</p>
					<p>
						급여 총액 합계: <span id="totalAllShikyuuSougaku">0</span> 원
					</p>
					<p>
						공제 총액 합계: <span id="totalAllKoujoSougaku">0</span> 원
					</p>
					<p>
						실지급액 합계: <span id="totalAllJissaiKyuuyo">0</span> 원
					</p>
				</div>
			</div>
			<div class="calcBox">
				<div class="toggle-buttons" id="incomeDiv" data-value="0">
					<button id="generalIncome" class="toggle-button active" onclick="kinroList()">일반소득</button>
					<button id="businessIncome" class="toggle-button inactive" onclick="businessList()">사업소득/기타소득</button>
				</div>
				<div class="income-deduction-content">
					<div class="income-section">
						<div class="section-header">
							<h3>지급항목</h3>
							<button onclick="openKyuuyoPopup()" class="m-btn">M</button>
						</div>
						<c:choose>
							<c:when test="${not empty shainKyuuyoKirokuList}">
								<!-- shainKoujoKirokuList가 존재하고 비어있지 않은 경우 -->
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
							지급총액: <span id="totalKyuuyoSougaku">0</span> 원
						</div>
					</div>
					<div class="deduction-section">
						<div class="section-header">
							<h3>공제항목</h3>
							<button onclick="openKoujoPopup()" class="m-btn">M</button>
							<button onclick="calc(this)" class="auto-calc-btn" id="calcButton" data-value="0">자동계산</button>
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
							공제총액: <span id="totalKoujoSougaku">0</span> 원
						</div>
					</div>
				</div>
				<div class="net-pay-summary">
					<span class="net-pay-font">실지급액:</span> 
					<span class="net-pay-value" id="totalJissaiKyuuyo">0</span>
				</div>
				<div class="calc-button-box">
					<button class="btn save" id="saveButton" onclick="saveKyuuyoKiroku()" data-value="0">
						<span>저장</span>
					</button>
					<button class="btn clear" id="clearAllButton" onclick="reset()">
						<span>내용 지우기</span>
					</button>
				</div>
			</div>
		</section>
	</div>

	<div id="overlay"></div>

	<div id="popup">
		<h2>급여지급 사원선택</h2>
		<input type="text" id="searchInput" placeholder="사원검색">
		<button onclick="toggleSelectAll()">전체선택</button>
		<table id="shainTable">
			<thead>
				<tr>
					<th>선택</th>
					<!-- 체크박스 열 -->
					<th>구분</th>
					<th>사원번호</th>
					<th>성명</th>
					<th>부서</th>
					<th>직위</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<!-- 사원 목록이 여기에 표시됩니다 -->
			</tbody>
		</table>
		<button onclick="selectShain()">사원선택</button>
		<button onclick="closePopup()">선택취소</button>
	</div>

	<!-- 지급항목 팝업 -->
	<div class="popup kyuuyoPopup" id="popupKyuuyo">
		<h2>지급항목 변경</h2>
		<label for="category">지급항목 선택</label> <select id="category">
			<option>선택하세요</option>
		</select> <label for="item">지급항목</label> <input type="text" id="item" placeholder="지급 항목을 입력해주세요.">

		<label>과세여부</label>
		<div class="radio-group">
			<input type="radio" id="taxable" name="tax" value="전체과세"> <label for="taxable">전체과세</label>
			<input type="radio" id="non-taxable" name="tax" value="비과세"> <label for="non-taxable">비과세</label>
		</div>

		<label for="non-tax-name">비과세명</label> <input type="text" id="non-tax-name" placeholder="비과세 항목"
			disabled> <label for="limit-amount">비과세 한도액</label> <input type="text" id="limit-amount"
			placeholder="0 원" disabled> <label for="calc-method">계산방법</label> <input type="text"
			id="calc-method" placeholder="계산방법을 입력해주세요."> <label for="unit">결산단위</label> <select
			id="unit">
			<option>선택하세요</option>
		</select> <label for="bulk-pay">근태연계/일괄지급</label> <select id="bulk-pay">
			<option>선택하세요</option>
		</select> <label for="bulk-amount">일괄지급액</label> <input type="text" id="bulk-amount" placeholder="0 원"
			disabled>

		<div class="buttons">
			<button class="add-btn">추가</button>
			<button class="edit-btn">수정</button>
			<button class="delete-btn">삭제</button>
			<button onclick="closeKPopup()">닫기</button>
		</div>
	</div>

	<!-- 공제항목 팝업 -->
	<div class="popup koujoPopup" id="popupKoujo">
		<h2>공제항목 변경</h2>
		<div class="notice">
			* 공제항목별로 수정하실 수 있습니다. (이번달만 적용)<br> * <strong>계속 수정을 원하시면 급여항목설정에서 수정해주세요.</strong>
		</div>

		<label for="category">공제항목 선택</label> <select id="category">
			<option>선택하세요</option>
		</select> <label for="item">공제항목</label> <input type="text" id="item" placeholder="공제항목을 입력해주세요.">

		<label for="calc-method">계산방법</label> <input type="text" id="calc-method"
			placeholder="계산방법을 입력해주세요."> <label for="unit">결산단위</label> <select id="unit">
			<option>선택하세요</option>
		</select> <label for="note">비고</label> <input type="text" id="note" placeholder="비고를 입력해주세요.">

		<div class="buttons">
			<button class="add-btn">추가</button>
			<button class="edit-btn">수정</button>
			<button class="delete-btn">삭제</button>
			<button onclick="closeKPopup()">닫기</button>
		</div>
	</div>
</body>
</html>