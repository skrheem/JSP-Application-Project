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
// 달력 출력 함수
$(function() {
	$.datepicker.setDefaults($.datepicker.regional["ja"]);

	$("#standard-period-start, #standard-period-end, #pay-date").datepicker({
		dateFormat: "yy-mm-dd", // Set date format to "YYYY-MM-DD"
		changeMonth: true,      // Enable month dropdown
		changeYear: true,       // Enable year dropdown
		showButtonPanel: true,  // Show buttons like "Today" and "Close"
		showOtherMonths: true,  // Show dates from other months
		selectOtherMonths: true // Allow selection of dates from other months
	});
});


/*

// 산정개시종료일, 급여지급일 수정하는 폼 제출 메서드
$(document).ready(function() {
    $("#modifyForm").on("submit", function(event) {
        event.preventDefault(); // 폼 기본 제출 방지

        $.ajax({
            type: "POST",
            url: "/modifySanteiShikyuuBi.do",
            data: $(this).serialize(), // 폼 데이터 직렬화하여 전송
            success: function(response) {
                // 서버 응답의 message를 alert로 표시
                alert(response.message);
            },
            error: function() {
                alert("서버 오류가 발생했습니다.");
            }
        });
    });
});

*/
