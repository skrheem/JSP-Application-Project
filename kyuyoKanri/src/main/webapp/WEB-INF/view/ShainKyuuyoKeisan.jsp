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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ShainKyuuyoKeisan.css">
<style>
/* 기본 스타일 */
body {
	font-family: Arial, sans-serif;
}

/* 버튼 스타일 */
.toggle-buttons {
	display: flex;
	gap: 10px;
	margin-bottom: 10px;
}

.toggle-button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
}

/* 활성화된 버튼 */
.active {
	background-color: #008080;
	color: white;
}

/* 비활성화된 버튼 */
.inactive {
	background-color: #ccc;
	color: #333;
}

/* 계산 버튼 스타일 */
.buttons {
	display: flex;
	align-items: center;
	gap: 10px;
}

.button {
	display: inline-flex;
	align-items: center;
	padding: 5px 10px;
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	border-radius: 3px;
	cursor: pointer;
	font-size: 14px;
}

.button img {
	margin-right: 5px;
}

/* Tip 아이콘 스타일 */
.tip {
	color: #0066cc;
	font-size: 16px;
	cursor: pointer;
}
</style>
<script>
var selectedShainId = 0;
//계산방법 토글 상태 변경 시 호출될 함수
function toggleKeisanMethod() {
    var isChecked = document.getElementById('calc-method').checked;
    var keisanLabels = document.querySelectorAll('.keisan-label');
    keisanLabels.forEach(function(label) {
        label.style.display = isChecked ? 'block' : 'none';
    });
}

// 페이지 로드 시와 체크박스 상태 변경 시에 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', function() {
    var calcCheckbox = document.getElementById('calc-method');
    calcCheckbox.addEventListener('change', toggleKeisanMethod);
    toggleKeisanMethod();  // 초기 로드 시에도 상태 적용
});
// JavaScript function to update totals dynamically with comma formatting
function updateTotals() {
    let incomeTotal = 0;
    let deductionTotal = 0;

    // 지급항목의 총합 계산
    document.querySelectorAll('.income-section input').forEach(input => {
        const value = parseFloat(input.value.replace(/,/g, '')) || 0;
        incomeTotal += value;
    });

    // 공제항목의 총합 계산
    document.querySelectorAll('.deduction-section input').forEach(input => {
        const value = parseFloat(input.value.replace(/,/g, '')) || 0;
        deductionTotal += value;
    });

    // 지급 총액, 공제 총액, 실지급액을 UI에 업데이트
    const netPay = incomeTotal - deductionTotal;  // 실지급액 계산
    document.getElementById('incomeTotal').textContent = incomeTotal.toLocaleString() + '원';
    document.getElementById('deductionTotal').textContent = deductionTotal.toLocaleString() + '원';
    document.getElementById('netPay').textContent = netPay.toLocaleString() + '원';
}

// Function to format input values with commas
function formatInput(input) {
    console.log("Original value:", input.value); // 원래 값 로깅
    const value = parseFloat(input.value.replace(/,/g, '')) || 0;
    console.log("Parsed value:", value); // 파싱 후 값 로깅
    input.value = value.toLocaleString();
    console.log("Formatted value:", input.value); // 포맷팅 후 값 로깅
    updateTotals();
}

function printKoujoKiroku() {
    var koujoKirokuList = JSON.parse('${shainKoujoKirokuJson}'); // 서버에서 받은 JSON 문자열 사용

    koujoKirokuList.forEach(function(item) {
        var inputField = document.getElementById('koujo-' + item.koujoKoumoku_id);
        if (inputField) {
            // 값이 비어있는 경우 0으로 설정, 아니면 숫자 형식으로 포맷
            var formattedValue = item.gaku ? Number(item.gaku).toLocaleString('ko-KR') : '0';
            inputField.value = formattedValue;
        }
    });

    updateTotals(); // 계산된 총합을 업데이트
}

function autoCalculate() {
    // 서버에서 받은 JSON 문자열을 파싱 시도
    var koujoList = null;
    try {
        koujoList = JSON.parse('${koujoKingakuJson}');
    } catch (e) {
        console.error('Parsing error:', e);
        alert('사원을 선택해주세요.');
        return; // 함수 실행 중지
    }

    // 파싱 결과가 null이거나 빈 배열인 경우 경고 후 함수 중지
    if (!koujoList || koujoList.length === 0) {
        alert('공제 항목 데이터가 존재하지 않습니다.');
        return; // 함수 실행 중지
    }

    // 공제 항목 데이터가 존재하는 경우, 각 항목에 대한 처리 수행
    koujoList.forEach(function(item) {
        var inputField = document.getElementById('koujo-' + item.koujoKoumoku_id);
        if (inputField) {
            // 숫자를 한국어 통화 형식으로 포맷 (예: '1,000')
            inputField.value = Number(item.gaku).toLocaleString('ko-KR');
        } else {
            console.error('Element not found: koujo-' + item.koujoKoumoku_id);
        }
    });

    // 총합을 업데이트하는 함수 호출
    updateTotals();
}

// Placeholder function for M button
function handleMButtonClick() {
    alert('M 버튼 기능이 실행되었습니다.');
}
function submitForm() {
    document.getElementById('autoSubmitForm').submit();
}
</script>
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
				<form action="kanri.do" method="post" id="autoSubmitForm">
					<label for="year">귀속연월</label> <select name="kyuuyoNen"
						id="kyuuyoNen" onchange="submitForm()">
						<script>
        for (let year = 2005; year <= 2025; year++) {
            document.write('<option value="' + year + '">' + year + '년</option>');
        }
        </script>
					</select> <select name="kyuuyoGatsu" id="kyuuyoGatsu"
						onchange="submitForm()">
						<script>
            for (let month = 1; month <= 12; month++) {
                const monthStr = month.toString().padStart(2, '0');
                document.write('<option value="' + monthStr + '">' + monthStr + '월</option>');
            }
        </script>
					</select> <label for="kyuuyoJisuu">급여차수</label> <select name="kyuuyoJisuu"
						id="kyuuyoJisuu" onchange="submitForm()">
						<script>
        for (let cycle = 1; cycle <= 10; cycle++) {
            const cycleStr = cycle.toString().padStart(2, '0');
            document.write('<option value="' + cycleStr + '">급여-' + cycleStr + '차</option>');
        }
        </script>
					</select>
				</form>

				<label for="kyuuyoJisuu">급여차수</label> <select id="kyuuyoJisuu">
					<script>
					for (let cycle = 1; cycle <= 10; cycle++) {
					    const cycleStr = cycle.toString().padStart(2, '0');
					    document.write('<option>급여-' + cycleStr + '차</option>');
					}
					</script>
				</select> <label for="standard-period">정산기간</label> <input type="text"
					id="standard-period-start" placeholder="2024-09-01"> <span>~</span>
				<input type="text" id="standard-period-end" placeholder="2024-09-30">
				<label for="pay-date">급여지급일</label> <input type="text" id="pay-date"
					placeholder="2024-10-05">
				<button class="btn edit">수정</button>
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
						onclick="toggleButton('generalIncome')">일반소득</button>
					<button id="businessIncome" class="toggle-button inactive"
						onclick="toggleButton('businessIncome')">사업소득/기타소득</button>
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
			</div>


		</section>

		<!-- 실지급액 outside the 항목 상자 -->
		<c:set var="netPayment" value="${kyuuyoSougaku - koujoSougaku}" />
		<div class="net-pay-summary">
			실지급액:
			<fmt:formatNumber value="${netPayment}" type="number" pattern="#,##0" />
		</div>

		<button class="btn save">저장</button>
		<button class="btn clear">내용 지우기</button>
	</div>

</body>
</html>