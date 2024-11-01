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
    
    // 각 input 필드에 대해 날짜 변경 시 이전 값을 EX 필드에 저장
    $("#standard-period-start, #standard-period-end, #pay-date").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        showOtherMonths: true,
        selectOtherMonths: true,

        // 달력이 닫힐 때(날짜가 선택되었을 때) 이전 값을 EX 필드에 저장
        beforeShow: function() {
            let exId = "EX" + this.id; // 현재 요소의 ID를 사용하여 대응되는 EX input ID 생성
            $("#" + exId).val($(this).val()); // EX 필드에 현재 값을 저장
        }
    });
});


//사원 목록 클릭 시 그 사원의 급여기록, 공제기록을 AJAX 요청
function getKoumokuInfo(shainId) {
	document.getElementById('calcButton').setAttribute('data-value', shainId);
	// 선택된 연도, 월, 차수를 가져옵니다
	const year = document.getElementById("kyuuyoNendo").value;
	const month = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	const url = "getKoumokuInfo.do?kyuuyoNendo=" + encodeURIComponent(year) +
		"&kyuuyoGatsu=" + encodeURIComponent(month) +
		"&kyuuyoJisuu=" + encodeURIComponent(jisuu) +
		"&shain_id=" + encodeURIComponent(shainId);
	// AJAX GET 요청으로 서버에 데이터 전송
	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			renderKoujoData(response); // JSON 데이터를 해당 HTML 요소에 렌더링
		},
		error: function(xhr, status, error) {
			console.error("요청 실패:", status, error, xhr.responseText);
		}
	});
}

function renderKoujoData(jsonArray) {
	jsonArray.forEach(item => {
		const elementId = item.koujoKoumoku_id;
		console.log(elementId);
		const inputElement = document.getElementById("kihonkoujo-" + elementId);
		console.log(inputElement);
		if (inputElement) {
			inputElement.value = item.koujoGaku;
		}
	});
}

function openPopup() {
    document.getElementById("overlay").style.display = "block";
    document.getElementById("popup").style.display = "block";
}

function closePopup() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("popup").style.display = "none";
}

function getShainList() {
    const url = "shainTsuikaList.do"; // 요청할 URL
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            const tbody = document.getElementById("shainTable").getElementsByTagName("tbody")[0];
            tbody.innerHTML = ""; // 테이블 초기화

            response.forEach(shain => {
                const row = document.createElement("tr");

                // 체크박스 셀 추가
                const checkboxCell = document.createElement("td");
                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.classList.add("shain-checkbox"); // 클래스 추가 (필요한 경우)
                checkbox.value = shain.shain_id; // 사원의 ID를 체크박스의 value로 설정
                checkboxCell.appendChild(checkbox);
                row.appendChild(checkboxCell);

                // 다른 셀들 추가
                ["kubun", "shain_id", "name", "busho_mei", "yakushoku_mei", "jyoutai"].forEach(key => {
                    const cell = document.createElement("td");
                    cell.textContent = shain[key];
                    row.appendChild(cell);
                });

                tbody.appendChild(row); // 행을 테이블 본문에 추가
            });
        },
        error: function(xhr, status, error) {
            console.error("데이터 로드 실패:", status, error);
        }
    });
}

let isAllChecked = false; // 전체 선택 여부를 추적하는 변수

function toggleSelectAll() {
    const checkboxes = document.querySelectorAll("#shainTable tbody .shain-checkbox");
    checkboxes.forEach(checkbox => {
        checkbox.checked = !isAllChecked; // 현재 상태에 따라 선택 또는 해제
    });
    isAllChecked = !isAllChecked; // 전체 선택 여부를 반전시킴
}

function selectShain() {
    // 선택된 체크박스들 가져오기
    const selectedCheckboxes = document.querySelectorAll("#shainTable tbody .shain-checkbox:checked");

    // 선택된 체크박스의 값을 배열로 수집 (사원 ID)
    const selectedIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);
    console.log(selectedIds);

    // 선택된 사원이 없을 경우 요청을 보내지 않음
    if (selectedIds.length === 0) {
        alert("선택된 사원이 없습니다.");
        return;
    }

    // kyuuyoNendo와 kyuuyoGatsu 값을 가져옴
    const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
    const kyuuyoGatsu = document.getElementById("kyuuyoGatsu").value;
	const kyuuyoJisuu = document.getElementById("kyuuyoJisuu").value;
	const koukinzei = document.getElementById("incomeDiv").getAttribute("data-value");
    // 서버에 전송할 데이터 구성
    const dataToSend = {
        shainIds: selectedIds,
        kyuuyoNendo: kyuuyoNendo,
        kyuuyoGatsu: kyuuyoGatsu,
        kyuuyoJisuu: kyuuyoJisuu
    };

    // AJAX 요청을 통해 서버로 데이터 전송
    $.ajax({
        url: 'shainTsuika.do', // 서버의 엔드포인트 URL
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dataToSend), // JSON 형식으로 변환하여 전송
        success: function(response) {
            console.log("서버 응답:", response);
            // 서버에서 처리 후 응답을 받은 내용을 바탕으로 추가 작업 수행 가능
            closePopup();
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 실패:", status, error);
            alert("서버와의 통신에 문제가 발생했습니다.");
        }
    });
}