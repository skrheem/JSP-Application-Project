<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>급여입력/관리</title>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui/1.12.1/i18n/datepicker-ko.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ShainKyuuyoKeisan.css">
<script src="${pageContext.request.contextPath}/js/ShainKyuuyoKeisan.js"></script>
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
				<form action="kanri.do?koukinzei=${skList[0].getKoukinzei_kubun()}" method="post" id="autoSubmitForm">
					<label for="year">귀속연월</label> <select name="kyuuyoNen"
						id="kyuuyoNen" onchange="submitForm()">
						<option value="" disabled selected>연도 선택</option>
						<c:forEach var="year" begin="2005" end="2025">
							<option value="${year}"
								<c:if test="${year == param.kyuuyoNen}">selected</c:if>>${year}년</option>
						</c:forEach>
					</select> <select name="kyuuyoGatsu" id="kyuuyoGatsu"
						onchange="submitForm()">
						<option value="" disabled selected>월 선택</option>
						<c:forEach var="month" begin="1" end="12">
							<c:set var="monthStr"
								value="${month lt 10 ? '0' + month : month}" />
							<option value="${monthStr}"
								<c:if test="${monthStr == param.kyuuyoGatsu}">selected</c:if>>${monthStr}월</option>
						</c:forEach>
					</select> <label for="kyuuyoJisuu">급여차수</label> <select name="kyuuyoJisuu"
						id="kyuuyoJisuu" onchange="submitForm()">
						<option value="" disabled selected>차수 선택</option>
						<c:forEach var="cycle" begin="1" end="10">
							<c:set var="cycleStr"
								value="${cycle lt 10 ? '0' + cycle : cycle}" />
							<option value="${cycleStr}"
								<c:if test="${cycleStr == param.kyuuyoJisuu}">selected</c:if>>급여-${cycleStr}차</option>
						</c:forEach>
					</select>
				</form>

				<form id="modifyForm" action="modifySanteiShikyuuBi.do"
					method="post">
					<label for="standard-period">정산기간</label> <input type="text"
						id="standard-period-start" name="santei_kaishi"
						placeholder="2024-09-01" autocomplete="off"> <span>~</span>
					<input type="text" id="standard-period-end" name="santei_shuuryou"
						placeholder="2024-09-30" autocomplete="off"> <label
						for="pay-date">급여지급일</label> <input type="text" id="pay-date"
						name="shikyuu_bi" placeholder="2024-10-05" autocomplete="off">

					<c:forEach var="shain" items="${skList}">
						<input type="hidden" name="EXsantei_kaishi"
							value="${shain.getKYUUYOSANTEIKAISHI()}">
						<input type="hidden" name="EXsantei_shuuryou"
							value="${shain.getKYUUYOSANTEISHUURYOU()}">
						<input type="hidden" name="EXshikyuu_bi"
							value="${shain.getKYUUYO_SHIKYUUBI()}">
						<input type="hidden" name="koukinzei"
							value="${shain.getKoukinzei_kubun()}">
					</c:forEach>

					<button type="submit" class="btn edit">수정</button>
				</form>


				<label for="calc-method">계산방법</label>
				<div class="calc-toggle">
					<span>off</span> <input type="checkbox" id="calc-method">
				</div>
			</div>
		</section>

		<section class="main-horizontal-content">
			<div class="table-section employee-list"
				style="display: flex; flex-direction: column;">
				<div style="padding-bottom: 10px">
					<button onclick="handleMButtonClick()">지난급여 불러오기</button>
					<button onclick="handleMButtonClick()">신규추가</button>
				</div>
				<table class="payroll-table">
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
						<c:forEach var="shain" items="${skList}">
							<tr
								onclick="location.href='getKyuuyoInfo.do?shain_id=${shain.getShain_id()}';updateTotals()"
								style="cursor: pointer;" class="hideable">

								<td>${shain.getKubun()}</td>
								<td>${shain.getNamae_kana()}</td>
								<td>${shain.getBusho_mei()}</td>
								<td><fmt:formatNumber value="${shain.getShikyuuSougaku()}"
										pattern="#,##0" /></td>
								<td><fmt:formatNumber value="${shain.getKoujoSougaku()}"
										pattern="#,##0" /></td>
								<td><fmt:formatNumber value="${shain.getJissai_kyuuyo()}"
										pattern="#,##0" /></td>

							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<div class="calcBox">
				<div class="toggle-buttons">
					<button id="generalIncome" class="toggle-button active"
						onclick="location.href='kanri.do?koukinzei=勤労所得者'">일반소득</button>
					<button id="businessIncome" class="toggle-button inactive"
						onclick="location.href='kanri.do?koukinzei=不勤労所得者'">사업소득/기타소득</button>
				</div>
				<div class="income-deduction-content">
					<div class="income-section">
						<div class="section-header">
							<h3>지급항목</h3>
							<button onclick="handleMButtonClick()" class="m-btn">M</button>
						</div>
						<c:choose>
							<c:when test="${not empty shainKyuuyoKirokuList}">
								<!-- shainKoujoKirokuList가 존재하고 비어있지 않은 경우 -->
								<c:forEach var="kyuuyoKoumoku" items="${shainKyuuyoKirokuList}">
									<label>${kyuuyoKoumoku.getKyuuyoKoumoku_mei()}</label>
									<input type="text" oninput="formatInput(this)"
										value="<fmt:formatNumber value='${kyuuyoKoumoku.getKyuuyo_kingaku()}' type='number' pattern='#,##0'/>"
										id="kyuuyo-${kyuuyoKoumoku.getKyuuyoKoumoku_id()}">
									<div class="keisan-label">${kyuuyoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<!-- shainKoujoKirokuList가 존재하지 않거나 비어 있는 경우 -->
								<c:forEach var="kyuuyoKoumoku" items="${kyuuyoList}">
									<label>${kyuuyoKoumoku.getKyuuyoKoumoku_mei() }</label>
									<input type="text" oninput="formatInput(this)" value="0"
										id="kyuuyo-${kyuuyoKoumoku.getKyuuyoKoumoku_id()}">
									<div class="keisan-label">
										${kyuuyoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="total">
							지급총액:
							<fmt:formatNumber
								value="${empty kyuuyoSougaku ? 0 : kyuuyoSougaku}" type="number"
								pattern="#,##0" />
						</div>
					</div>
					<div class="deduction-section">
						<div class="section-header">
							<h3>공제항목</h3>
							<button onclick="handleMButtonClick()" class="m-btn">M</button>
							<button onclick="autoCalculate()" class="auto-calc-btn">자동계산</button>
						</div>

						<c:choose>
							<c:when
								test="${not empty kokumin and not empty kenkou and not empty chouki and not empty koyou}">
								<!-- 기본항목인 공제항목 들이 존재하고 비어있지 않은 경우 -->
								<label>${kokumin.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${kokumin.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="koujo-${kokumin.getKoujoKoumoku_id()}">
								<div class="keisan-label">${kokumin.getKeisanHouhou()}</div>

								<label>${kenkou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${kenkou.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="koujo-${kenkou.getKoujoKoumoku_id()}">
								<div class="keisan-label">${kokumin.getKeisanHouhou()}</div>

								<label>${chouki.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${chouki.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="koujo-${chouki.getKoujoKoumoku_id()}">
								<div class="keisan-label">${chouki.getKeisanHouhou()}</div>

								<label>${koyou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${koyou.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="koujo-${koyou.getKoujoKoumoku_id()}">
								<div class="keisan-label">${koyou.getKeisanHouhou()}</div>

								<label>${shotoku.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${shotokuZei}' type='number' pattern='#,##0'/>"
									id="koujo-${shotoku.getKoujoKoumoku_id()}">
								<div class="keisan-label">${shotoku.getKeisanHouhou()}</div>

								<label>${chihou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${chihouZei}' type='number' pattern='#,##0'/>"
									id="koujo-${chihou.getKoujoKoumoku_id()}">
								<div class="keisan-label">${chihou.getKeisanHouhou()}</div>

							</c:when>
							<c:otherwise>
								<!-- shainKoujoKirokuList가 존재하지 않거나 비어 있는 경우 -->
								<c:forEach var="koujoKoumoku" items="${kihonKoujoList}">
									<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
									<input type="text" oninput="formatInput(this)" value="0"
										id="kihonkoujo-${koujoKoumoku.getKoujoKoumoku_id()}">
									<div class="keisan-label">
										${koujoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
								<c:forEach var="koujoKoumoku" items="${koujoList}">
									<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
									<input type="text" oninput="formatInput(this)" value="0"
										id="koujo-${koujoKoumoku.getKoujoKoumoku_id()}">
									<div class="keisan-label">
										${koujoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="total">
							공제총액:
							<fmt:formatNumber
								value="${empty koujoSougaku ? 0 : koujoSougaku}" type="number"
								pattern="#,##0" />
						</div>
					</div>
				</div>
				<div class="net-pay-summary">
					<span class="net-pay-font">실지급액:</span> <span class="net-pay-value">
						<fmt:formatNumber
							value="${empty jissaiSougaku ? 0 : jissaiSougaku}" type="number"
							pattern="#,##0원" />
					</span>
				</div>
				<div class="calc-button-box">
					<button class="btn save">
						<span>저장</span>
					</button>
					<button class="btn clear">
						<span>내용 지우기</span>
					</button>
				</div>

			</div>
		</section>




	</div>

</body>
</html>