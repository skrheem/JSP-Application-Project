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
/* 팝업 뒷배경 스타일 */
#overlay {
	display: none; /* 초기에는 숨김 */
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 검은색 반투명 */
	z-index: 999; /* 팝업보다 낮은 z-index */
}

/* 팝업 창 스타일 */
#popup {
	display: none; /* 초기에는 숨김 */
	position: fixed;
	width: 690px;
	height: 650px;
	background-color: white;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%); /* 가운데 정렬 */
	z-index: 1000; /* overlay보다 높은 z-index */
	padding: 20px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
}
</style>
</head>
<script>


function submitForm() {
    const year = document.getElementById("kyuuyoNendo").value;
    const month = document.getElementById("kyuuyoGatsu").value;
    const jisuu = document.getElementById("kyuuyoJisuu").value;
    const koukinzei2 = document.getElementById("incomeDiv").getAttribute("data-value");
    //alert(koukinzei2);
   /*
    const koukinzei = "${skList[0].getKoukinzei_kubun()}" === "勤労所得者" ? 0 
            : "${skList[0].getKoukinzei_kubun()}" === "日雇い" ? 2 
            : 1;*/
    // 쿼리 스트링 구성 후 페이지 이동
    const queryString = "getKyuuyoInfo.do?kyuuyoNendo=" + year + "&kyuuyoGatsu=" + month + "&kyuuyoJisuu=" + jisuu + "&koukinzei=" + koukinzei2;
    window.location.href = queryString;
}
function getShainList() {
    const year = document.getElementById("kyuuyoNendo").value;
    const month = document.getElementById("kyuuyoGatsu").value;
    const jisuu = document.getElementById("kyuuyoJisuu").value;
    const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");

    const url = "getKyuuyoInfo.do?kyuuyoNendo=" + year + "&kyuuyoGatsu=" + month + "&kyuuyoJisuu=" + jisuu + "&koukinzei=" + koukinzei;

    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            const tbody = document.getElementById("keisanKirokuTable").getElementsByTagName("tbody")[0];
            tbody.innerHTML = "";

            response.forEach(shain => {
                const row = document.createElement("tr");

                // 테이블에 표시할 데이터 설정
                ["kubun", "name", "busho_mei", "shikyuuSougaku", "koujoSougaku", "jissai_kyuuyo"].forEach(key => {
                    const cell = document.createElement("td");
                    cell.textContent = shain[key];
                    row.appendChild(cell);
                });

                tbody.appendChild(row);
            });

            // 첫 번째 항목을 폼에 자동으로 추가
            if (response.length > 0) {
                const firstShain = response[0];
                document.getElementById("standard-period-start").value = firstShain["kyuuyoSanteiKaishi"];
                document.getElementById("standard-period-end").value = firstShain["kyuuyoSanteiShuuryou"];
                document.getElementById("pay-date").value = firstShain["kyuuyoShikyuuBi"];

                // shain_id 값을 숨김 필드로 폼에 추가
                let shainIdInput = document.getElementById("shain_id");
                if (!shainIdInput) {
                    shainIdInput = document.createElement("input");
                    shainIdInput.type = "hidden";
                    shainIdInput.id = "shain_id";
                    shainIdInput.name = "shain_id";
                    document.getElementById("modifyForm").appendChild(shainIdInput);
                }
                shainIdInput.value = firstShain["shain_id"];
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 실패:", status, error);
            alert("서버와의 통신에 문제가 발생했습니다.");
        }
    });
}

function kinroList() {
	// div의 값을 1로 변경
    document.getElementById('incomeDiv').setAttribute('data-value', '0');
    // 버튼의 상태 변경
    document.getElementById('generalIncome').classList.add('active');
    document.getElementById('generalIncome').classList.remove('inactive');
    document.getElementById('businessIncome').classList.add('inactive');
    document.getElementById('businessIncome').classList.remove('active');
	
	const year = document.getElementById("kyuuyoNendo").value;
    const month = document.getElementById("kyuuyoGatsu").value;
    const jisuu = document.getElementById("kyuuyoJisuu").value;
    const koukinzei = 1;
    
    getShainList();
}

function businessList() {
	// div의 값을 1로 변경
    document.getElementById('incomeDiv').setAttribute('data-value', '1');
    // 버튼의 상태 변경
    document.getElementById('businessIncome').classList.add('active');
    document.getElementById('businessIncome').classList.remove('inactive');
    document.getElementById('generalIncome').classList.add('inactive');
    document.getElementById('generalIncome').classList.remove('active');
	
	const year = document.getElementById("kyuuyoNendo").value;
    const month = document.getElementById("kyuuyoGatsu").value;
    const jisuu = document.getElementById("kyuuyoJisuu").value;
    const koukinzei = 1;
    
    getShainList();
}
function calc(button) {
    const dataValue = button.getAttribute("data-value");
    console.log(dataValue);
    getKoumokuInfo(dataValue);
}

/*document.getElementById('submitButton').addEventListener('click', function() {
	const data = {
		santei_kaishi: document.getElementById('standard-period-start').value,
		santei_shuuryou: document.getElementById('standard-period-end').value,
		shikyuu_bi: document.getElementById('pay-date').value,
		'standard-period-start': document.getElementById('EXstandard-period-start').value,
		'standard-period-end': document.getElementById('EXstandard-period-end').value,
		'pay-date': document.getElementById('EXpay-date').value
	};

	fetch('modifySanteiShikyuuBi.do', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams(data).toString()
	})
	.then(response => response.json())
	.then(data => {
		// 서버 응답 처리
		console.log('성공:', data);
	})
	.catch(error => {
		console.error('오류 발생:', error);
	});
});*/
function modify() {
    // 각 필드의 값 가져오기
    const santeiKaishi = document.getElementById('standard-period-start').value;
    const santeiShuuryou = document.getElementById('standard-period-end').value;
    const shikyuu_bi = document.getElementById('pay-date').value;
    
    const EXsantei_kaishi = document.getElementById('EXstandard-period-start').value;
    const EXsantei_shuuryou = document.getElementById('EXstandard-period-end').value;
    const EXshikyuu_bi = document.getElementById('EXpay-date').value;
    const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");
    
    console.log(santeiKaishi);
    console.log(santeiShuuryou);
    console.log(shikyuu_bi);
    console.log(EXsantei_kaishi);
    console.log(EXsantei_shuuryou);
    console.log(EXshikyuu_bi);
    console.log(koukinzei);
    // AJAX 요청 보내기
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "modifySanteiShikyuuBi.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // 데이터 문자열로 보내기
    const data = "santei_kaishi=" + santeiKaishi + "&santei_shuuryou=" + santeiShuuryou + "&shikyuu_bi=" + shikyuu_bi + "&EXsantei_kaishi=" + EXsantei_kaishi + "&EXsantei_shuuryou=" + EXsantei_shuuryou + "&EXshikyuu_bi=" + EXshikyuu_bi + "&koukinzei=" + koukinzei;
    xhr.send(data);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 요청 성공 시 처리
            alert("수정이 완료되었습니다.");
            getShainList();
        } else if (xhr.readyState === XMLHttpRequest.DONE) {
            // 요청 실패 시 처리
            alert("수정에 실패했습니다.");
        }
    };
}
</script>
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
					<option value="" disabled selected>연도 선택</option>
					<c:forEach var="year" begin="2005" end="2025">

						<option value="${year}" <c:if test="${year == param.kyuuyoNendo}">selected</c:if>>${year}년</option>
					</c:forEach>
				</select> <select name="kyuuyoGatsu" id="kyuuyoGatsu" onchange="getShainList()">
					<option value="" disabled selected>월 선택</option>
					<c:forEach var="month" begin="1" end="12">
						<c:set var="monthStr" value="${month lt 10 ? '0' + month : month}" />
						<option value="${monthStr}" <c:if test="${monthStr == param.kyuuyoGatsu}">selected</c:if>>${monthStr}월</option>
					</c:forEach>
				</select> <label for="kyuuyoJisuu">급여차수</label> <select name="kyuuyoJisuu" id="kyuuyoJisuu"
					onchange="getShainList()">
					<option value="" disabled selected>차수 선택</option>
					<c:forEach var="cycle" begin="1" end="10">
						<c:set var="cycleStr" value="${cycle lt 10 ? '0' + cycle : cycle}" />
						<option value="${cycleStr}" <c:if test="${cycleStr == param.kyuuyoJisuu}">selected</c:if>>급여-${cycleStr}차</option>
					</c:forEach>
				</select>
				<div id="modifyFormContainer">
					<label for="standard-period">정산기간</label> <input type="text" id="standard-period-start"
						name="santei_kaishi" placeholder="2024-09-01" autocomplete="off"> <span>~</span> <input
						type="text" id="standard-period-end" name="santei_shuuryou" placeholder="2024-09-30"
						autocomplete="off"> <label for="pay-date">급여지급일</label> <input type="text"
						id="pay-date" name="shikyuu_bi" placeholder="2024-10-05" autocomplete="off"> <input
						type="text" id="EXstandard-period-start" name="standard-period-start" autocomplete="off"
						style="display: block"> <input type="text" id="EXstandard-period-end"
						name="standard-period-end" autocomplete="off" style="display: block"> <input
						type="text" id="EXpay-date" name="pay-date" autocomplete="off" style="display: block">
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
					<button onclick="openPopup();getShainList()">신규추가</button>
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
							<tr onclick="getKoumokuInfo(${shain.getShain_id()})" style="cursor: pointer;"
								class="hideable">
								<td>${shain.getKubun()}</td>
								<td>${shain.getNamae_kana()}</td>
								<td>${shain.getBusho_mei()}</td>
								<td><fmt:formatNumber value="${shain.getShikyuuSougaku()}" pattern="#,##0" /></td>
								<td><fmt:formatNumber value="${shain.getKoujoSougaku()}" pattern="#,##0" /></td>
								<td><fmt:formatNumber value="${shain.getJissai_kyuuyo()}" pattern="#,##0" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<table id="keisanKirokuTable">
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
									<div class="keisan-label">${kyuuyoKoumoku.getKeisanHouhou()}</div>
									<br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="total">
							지급총액:
							<fmt:formatNumber value="${empty kyuuyoSougaku ? 0 : kyuuyoSougaku}" type="number"
								pattern="#,##0" />
						</div>
					</div>
					<div class="deduction-section">
						<div class="section-header">
							<h3>공제항목</h3>
							<button onclick="handleMButtonClick()" class="m-btn">M</button>
							<button onclick="calc(this)" class="auto-calc-btn" id="calcButton" data-value="0">자동계산</button>
						</div>

						<%-- <c:choose>
							<c:when
								test="${not empty kokumin and not empty kenkou and not empty chouki and not empty koyou}">
								<!-- 기본항목인 공제항목 들이 존재하고 비어있지 않은 경우 -->
								<label>${kokumin.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${kokumin.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${kokumin.getKoujoKoumoku_id()}">
								<div class="keisan-label">${kokumin.getKeisanHouhou()}</div>

								<label>${kenkou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${kenkou.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${kenkou.getKoujoKoumoku_id()}">
								<div class="keisan-label">${kokumin.getKeisanHouhou()}</div>

								<label>${chouki.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${chouki.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${chouki.getKoujoKoumoku_id()}">
								<div class="keisan-label">${chouki.getKeisanHouhou()}</div>

								<label>${koyou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${koyou.getKoujoGaku()}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${koyou.getKoujoKoumoku_id()}">
								<div class="keisan-label">${koyou.getKeisanHouhou()}</div>

								<label>${shotoku.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${shotokuZei}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${shotoku.getKoujoKoumoku_id()}">
								<div class="keisan-label">${shotoku.getKeisanHouhou()}</div>

								<label>${chihou.getKoujoKoumoku_mei()}</label>
								<input type="text" oninput="formatInput(this)"
									value="<fmt:formatNumber value='${chihouZei}' type='number' pattern='#,##0'/>"
									id="kihonkoujo-${chihou.getKoujoKoumoku_id()}">
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
						</c:choose> --%>
						<c:forEach var="koujoKoumoku" items="${kihonKoujoList}">
							<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
							<input type="text" oninput="formatInput(this)" value="0"
								id="kihonkoujo-${koujoKoumoku.getKoujoKoumoku_id()}">
							<div class="keisan-label">${koujoKoumoku.getKeisanHouhou()}</div>
							<br>
						</c:forEach>
						<c:forEach var="koujoKoumoku" items="${koujoList}">
							<label>${koujoKoumoku.getKoujoKoumoku_mei()}</label>
							<input type="text" oninput="formatInput(this)" value="0"
								id="koujo-${koujoKoumoku.getKoujoKoumoku_id()}">
							<div class="keisan-label">${koujoKoumoku.getKeisanHouhou()}</div>
							<br>
						</c:forEach>
						<div class="total">
							공제총액:
							<fmt:formatNumber value="${empty koujoSougaku ? 0 : koujoSougaku}" type="number"
								pattern="#,##0" />
						</div>
					</div>
				</div>
				<div class="net-pay-summary">
					<span class="net-pay-font">실지급액:</span> <span class="net-pay-value"> <fmt:formatNumber
							value="${empty jissaiSougaku ? 0 : jissaiSougaku}" type="number" pattern="#,##0원" />
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

	<div id="overlay"></div>
	<div id="popup">
		<h2>급여지급 사원선택</h2>
		<input type="text" id="searchInput" placeholder="사원검색">
		<button onclick="toggleSelectAll()">전체선택</button>
		<!-- 전체선택 버튼 추가 -->

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
				<!-- AJAX로 받은 데이터가 여기에 동적으로 추가됩니다 -->
			</tbody>
		</table>
		<button onclick="selectShain()">사원선택</button>
		<button onclick="closePopup()">선택취소</button>
	</div>
</body>
</html>