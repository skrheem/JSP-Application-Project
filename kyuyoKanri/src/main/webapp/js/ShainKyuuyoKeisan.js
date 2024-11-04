var selectedShainIvald = 0;
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


//사원 목록 클릭 시 그 사원의 공제기록(기본항목)을 AJAX 요청
function getKoujoKoumokuInfo(shainId) {
	document.getElementById('calcButton').setAttribute('data-value', shainId);
	document.getElementById('oneDelete').setAttribute('data-value', shainId);
	document.getElementById('saveButton').setAttribute('data-value', shainId);
	// 선택된 연도, 월, 차수를 가져옵니다
	const year = document.getElementById("kyuuyoNendo").value;
	const month = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	const url = "getKoujoKirokuInfo.do?kyuuyoNendo=" + encodeURIComponent(year) +
		"&kyuuyoGatsu=" + encodeURIComponent(month) +
		"&kyuuyoJisuu=" + encodeURIComponent(jisuu) +
		"&shain_id=" + encodeURIComponent(shainId);
	// AJAX GET 요청으로 서버에 데이터 전송
	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			//renderKoujoData(response); // JSON 데이터를 해당 HTML 요소에 렌더링
			renderData(response, 'koujo');
		},
		error: function(xhr, status, error) {
			console.error("요청 실패:", status, error, xhr.responseText);
		}
	});

}

// 클릭한 사원의 공제기록(기본항목X)을 AJAX 요청
function getNormalKoujoInfo(shainId) {

}
// 클릭한 사원의 급여기록(기본급 제외)을 AJAX 요청
function getKyuuyoKoumokuInfo(shainId) {
	const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
	const kyuuyoMonth = document.getElementById("kyuuyoGatsu").value;
	console.log(kyuuyoMonth);
	if (kyuuyoMonth < 10) {
		kyuuyoMonth = "0" + kyuuyoMonth;
	}
	let kyuuyoNengappi = kyuuyoNendo + "-" + kyuuyoMonth + "-01";
	const url = "getKyuuyoKirokuInfo.do?shain_id=" + shainId + "&kyuuyoNengappi=" + kyuuyoNengappi;
	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			//renderKyuuyoData(response);
			renderData(response, 'kyuuyo');
		},
		error: function(xhr, status, error) {
			console.error("요청 실패:", status, error, xhr.responseText);
		}
	});
}
// 클릭한 사원의 기본급을 AJAX 요청
function getKihonkyuuInfo(shainId) {
	const kyuuyoGatsu = document.getElementById('kyuuyoGatsu').value;
	const url = "getKihonkyuuInfo.do?shain_id=" + shainId + "&kyuuyoGatsu=" + kyuuyoGatsu;
	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			renderKihonkyuu(response);
		},
		error: function(xhr, status, error) {
			console.error("요청 실패:", status, error, xhr.responseText);
		}
	});
}

function renderData(jsonArray, type) {
	jsonArray.forEach(item => {
		const elementId = type === "koujo" ? item.koujoKoumoku_id : item.kyuuyoKoumoku_id;
		//console.log(elementId);

		const prefix = type === "koujo" ? "kihonkoujo-" : "kyuuyo-";
		const inputElement = document.getElementById(prefix + elementId);
		//console.log(inputElement);

		if (inputElement) {
			const value = type === "koujo" ? item.koujoGaku : item.hikazeiGendogaku;

			inputElement.value = (value || 0).toLocaleString();
		}
	});
	calculateTotalIncome();
	calculateTotalDeduction();
}

function renderKihonkyuu(jsonObject) {
	const elementId = "kyuuyo-1";

	const inputElement = document.getElementById(elementId);
	//console.log(inputElement);

	if (inputElement) {
		const value = jsonObject.kihonkyuu;
		inputElement.value = (value || 0).toLocaleString();
	}
}


// 팝업 열기
function openPopup() {
	document.getElementById('overlay').style.display = 'block';
	document.getElementById('popup').style.display = 'block';
}

// 팝업 닫기
function closePopup() {
	document.getElementById('overlay').style.display = 'none';
	document.getElementById('popup').style.display = 'none';
}

// 전체선택 토글
function toggleSelectAll() {
	const checkboxes = document.querySelectorAll('#shainTable tbody input[type="checkbox"]');
	checkboxes.forEach(checkbox => {
		checkbox.checked = !checkbox.checked;
	});
}

let kintaiMeiData = []; // 전역 변수로 kintai_mei 데이터를 저장

function getKintaiMei() {
	$.ajax({
		url: 'getKintaiMei.do', // 핸들러 URL
		type: 'GET',
		dataType: 'json',
		success: function(data) {
			console.log(data);
			kintaiMeiData = data; // kintai_mei 데이터를 전역 변수에 저장
			console.log(kintaiMeiData);
			const select = document.getElementById("bulk-pay");
			select.innerHTML = ''; // 기존 옵션을 초기화

			// 기본 옵션 추가
			const defaultOption = document.createElement("option");
			defaultOption.textContent = "選択してください。";
			select.appendChild(defaultOption);

			const noneOption = document.createElement("option");
			noneOption.textContent = "なし";
			select.appendChild(noneOption);

			const bulkPayOption = document.createElement("option");
			bulkPayOption.textContent = "一括支払い";
			select.appendChild(bulkPayOption);

			// kintai_mei 데이터를 <select> 옵션으로 추가
			data.forEach(function(item) {
				const option = document.createElement("option");
				option.value = item.kintai_mei; // kintai_mei 값을 value로 설정
				option.textContent = item.kintai_mei; // kintai_mei를 text로 설정
				select.appendChild(option);
			});
		},
		error: function(xhr, status, error) {
			console.error("勤怠項目データ 로드 실패:", error);
			alert("勤怠項目 데이터를 불러오는 데 실패했습니다.");
		}
	});
}

function openKyuuyoPopup() {
	document.getElementById('overlay').style.display = 'block';
	document.getElementById('popupKyuuyo').style.display = 'block';
	getKintaiMei();

	$.ajax({
		url: 'getKyuuyoKoumokuList.do', // 핸들러 URL
		type: 'GET',
		dataType: 'json',
		success: function(data) {
			kyuuyoData = data; // 급여항목 데이터를 전역 변수에 저장
			const select = document.getElementById("kyuuyoCategory");
			select.innerHTML = '<option>選択してください。</option>'; // 기본 옵션 초기화

			// 급여항목 데이터를 <select> 옵션으로 추가
			data.forEach(function(item) {
				const option = document.createElement("option");
				option.value = item.kyuuyokoumoku_id; // ID 값을 value로 설정
				option.textContent = item.kyuuyokoumoku_mei; // 이름을 text로 설정
				select.appendChild(option);
			});

			// 급여항목 선택 시 해당 항목의 세부 정보를 입력 필드에 표시하는 이벤트 리스너 추가
			select.addEventListener("change", function() {
				const selectedId = select.value;
				const selectedItem = data.find(item => item.kyuuyokoumoku_id == selectedId);

				if (selectedItem) {
					document.getElementById("kyuuyo-mei").value = selectedItem.kyuuyokoumoku_mei;
					document.getElementById("taxable").checked = (selectedItem.kazeikubun === "全体課税");
					document.getElementById("non-taxable").checked = (selectedItem.kazeikubun === "非課税");
					document.getElementById("non-tax-name").value = selectedItem.kazeikubun;
					document.getElementById("limit-amount").value = selectedItem.hikazeigendogaku;
					document.getElementById("kyuuyo-calc-method").value = selectedItem.keisanhouhou;
					document.getElementById("kyuuyo-unit").value = selectedItem.zenshadani;
					document.getElementById("bulk-amount").value = selectedItem.ikkatsushiharaigaku;

					// 디버깅: kintaierenkei와 kintai_mei 데이터 출력
					console.log("selectedItem.kintairenkei:", selectedItem.kintairenkei);
					console.log("kintaiMeiData:", kintaiMeiData);

					// kintaierenkei와 kintai_mei가 일치하는지 확인하여 설정
					const matchingKintai = kintaiMeiData.find(k =>
						k.kintai_mei && selectedItem.kintairenkei && k.kintai_mei.trim().localeCompare(selectedItem.kintairenkei.trim()) === 0
					);

					if (matchingKintai) {
						console.log("매칭된 kintai_mei:", matchingKintai.kintai_mei);
						document.getElementById("bulk-pay").value = matchingKintai.kintai_mei;
					} else {
						console.log("일치하는 kintai_mei 없음");
						document.getElementById("bulk-pay").value = "なし"; // 일치하는 값이 없으면 기본값으로 설정
					}
				}
			});
		},
		error: function(xhr, status, error) {
			console.error("급여항목 데이터 로드 실패:", error);
			alert("급여항목 데이터를 불러오는 데 실패했습니다.");
		}
	});
}

let koujoData = []; // 공제항목 데이터를 저장할 전역 변수

function openKoujoPopup() {
	document.getElementById('overlay').style.display = 'block';
	document.getElementById('popupKoujo').style.display = 'block';

	$.ajax({
		url: 'getKoujoKoumokuList.do', // 핸들러 URL
		type: 'GET',
		dataType: 'json',
		success: function(data) {
			koujoData = data; // 공제항목 데이터를 전역 변수에 저장

			const select = document.getElementById("koujoCategory");
			select.innerHTML = '<option>選択してください。</option>'; // 기본 옵션 초기화

			// 공제항목 데이터를 <select> 옵션으로 추가
			data.forEach(function(item) {
				const option = document.createElement("option");
				option.value = item.koujokoumoku_id; // ID 값을 value로 설정
				option.textContent = item.koujokoumoku_mei; // 이름을 text로 설정
				select.appendChild(option);
			});
		},
		error: function(xhr, status, error) {
			console.error("공제항목 데이터 로드 실패:", error);
			alert("공제항목 데이터를 불러오는 데 실패했습니다.");
		}
	});
}

document.addEventListener("DOMContentLoaded", function() {
	const categorySelect = document.getElementById("koujoCategory");

	if (categorySelect) {
		categorySelect.addEventListener("change", function() {
			const selectedId = this.value;

			// 선택된 ID에 맞는 데이터를 검색
			const selectedKoujo = koujoData.find(item => item.koujokoumoku_id == selectedId);

			if (selectedKoujo) {
				document.getElementById("koujo-mei").value = selectedKoujo.koujokoumoku_mei || '';
				document.getElementById("koujo-calc-method").value = selectedKoujo.keisanhouhou || '';
				document.getElementById("koujo-note").value = selectedKoujo.bikou || '';
				document.getElementById("koujo-id").value = selectedKoujo.koujokoumoku_id || '';
				const unitSelect = document.getElementById("koujo-unit");
				let matchFound = false;
				for (let option of unitSelect.options) {
					if (option.value === selectedKoujo.zenshadani) {
						option.selected = true;
						matchFound = true;
						break;
					}
				}

				// 일치하는 결산단위가 없을 경우 "없음" 옵션을 선택
				if (!matchFound) {
					unitSelect.value = "없음";
				}

				unitSelect.innerHTML = `<option>選択してください。</option>`;
				const option = document.createElement("option");
				option.value = selectedKoujo.zenshadani;
				option.textContent = selectedKoujo.zenshadani;
				option.selected = true;
				unitSelect.appendChild(option);
			}
		});
	}
});

function closeKPopup() {
	document.getElementById('overlay').style.display = 'none';
	document.getElementById('popupKyuuyo').style.display = 'none';

	document.getElementById('koujo-id').value = '';
	document.getElementById('koujo-mei').value = '';
	document.getElementById('koujo-calc-method').value = '';
	document.getElementById('koujo-unit').value = '選択してください。';
	document.getElementById('koujo-note').value = '';

	document.getElementById("kyuuyo-mei").value = '';
	document.getElementById("taxable").checked = false;
	document.getElementById("non-taxable").checked = false;
	document.getElementById("non-tax-name").value = '';
	document.getElementById("limit-amount").value = '';
	document.getElementById("kyuuyo-calc-method").value = '';
	document.getElementById("kyuuyo-unit").value = '選択してください。';
	document.getElementById("bulk-amount").value = '';

	document.getElementById('popupKoujo').style.display = 'none';
}

function getShainTsuikaList() {
	const santeiKaishi = document.getElementById('standard-period-start').value;
	const santeiShuuryou = document.getElementById('standard-period-end').value;
	const shikyuu_bi = document.getElementById('pay-date').value;
	const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");
	const url = "shainTsuikaList.do?" + "&santei_kaishi=" + santeiKaishi + "&santei_shuuryou=" + santeiShuuryou + "&shikyuu_bi=" + shikyuu_bi + "&koukinzei=" + koukinzei;
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
				checkbox.classList.add("shain-checkbox");
				checkbox.value = shain.shain_id;
				checkboxCell.appendChild(checkbox);
				row.appendChild(checkboxCell);

				["kubun", "shain_id", "name", "busho_mei", "yakushoku_mei", "jyoutai"].forEach(key => {
					const cell = document.createElement("td");
					cell.textContent = shain[key];
					row.appendChild(cell);
				});

				["kihonkyuu", "koukinzei_kubun"].forEach(key => {
					const hiddenCell = document.createElement("td");
					hiddenCell.textContent = shain[key];
					hiddenCell.style.display = "none"; // Hide these cells
					row.appendChild(hiddenCell);
				});

				tbody.appendChild(row);
			});
		},
		error: function(status, error) {
			console.error("데이터 로드 실패:", status, error);
		}
	});
}

let isAllChecked = false; // 전체 선택 여부를 추적하는 변수

function selectShain() {
	// 선택된 체크박스들 가져오기
	const selectedCheckboxes = document.querySelectorAll("#shainTable tbody .shain-checkbox:checked");

	// 선택된 체크박스의 값을 배열로 수집 (사원 ID)
	const selectedIds = Array.from(selectedCheckboxes).map(checkbox => checkbox.value);
	//console.log(selectedIds);

	// 선택된 사원이 없을 경우 요청을 보내지 않음
	if (selectedIds.length === 0) {
		alert("선택된 사원이 없습니다.");
		return;
	}

	// kyuuyoNendo와 kyuuyoGatsu 값을 가져옴
	const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
	const kyuuyoGatsu = document.getElementById("kyuuyoGatsu").value;
	const kyuuyoJisuu = document.getElementById("kyuuyoJisuu").value;
	const santeiKaishi = document.getElementById('standard-period-start').value;
	const santeiShuuryou = document.getElementById('standard-period-end').value;
	const shikyuu_bi = document.getElementById('pay-date').value;
	// 서버에 전송할 데이터 구성
	const dataToSend = {
		shainIds: selectedIds,
		kyuuyoNendo: kyuuyoNendo,
		kyuuyoGatsu: kyuuyoGatsu,
		kyuuyoJisuu: kyuuyoJisuu,
		santeiKaishi: santeiKaishi,
		santeiShuuryou: santeiShuuryou,
		shikyuu_bi: shikyuu_bi
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
			getShainList();
		},
		error: function(status, error) {
			console.error("AJAX 요청 실패:", status, error);
			alert("서버와의 통신에 문제가 발생했습니다.");
		}
	});
}

window.onload = function() {
	getShainList(); // 페이지가 로드될 때 호출
}

function prepare() {
	const url = "getKeisanKiroku.do?kyuuyoNendo=2024&kyuuyoGatsu=09&kyuuyoJisuu=1&koukinzei=0";

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			console.log("요청 넣기");
			const tbody = document.getElementById("keisanKirokuTable").getElementsByTagName("tbody")[0];
			tbody.innerHTML = "";

			response.forEach(shain => {
				const row = document.createElement("tr");

				// `shain_id`를 `data-shain-id` 속성으로 추가
				row.setAttribute("data-shain-id", shain.shain_id);
				row.style.cursor = "pointer"; // 커서 모양을 포인터로 설정 (선택 가능하게 보이도록)

				row.addEventListener("click", function() {
					const shainId = $(this).data('shain-id');
					getKoujoKoumokuInfo(shainId);
					getKihonkyuuInfo(shainId);
					getKyuuyoKoumokuInfo(shainId);
				});

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
		error: function(status, error) {
			console.error("AJAX 요청 실패:", status, error);
			alert("서버와의 통신에 문제가 발생했습니다.");
		}
	});
}

function submitForm() {
	const year = document.getElementById("kyuuyoNendo").value;
	const month = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	const koukinzei2 = document.getElementById("incomeDiv").getAttribute("data-value");
	// 쿼리 스트링 구성 후 페이지 이동
	const queryString = "getKeisanKiroku.do?kyuuyoNendo=" + year + "&kyuuyoGatsu=" + month + "&kyuuyoJisuu=" + jisuu + "&koukinzei=" + koukinzei2;
	window.location.href = queryString;
}

function getShainList() {
	const year = document.getElementById("kyuuyoNendo").value;
	const month = document.getElementById("kyuuyoGatsu").value;
	let yearcopy = year;
	let yearcopy2 = parseInt(yearcopy, 10) + 1;
	let monthcopy = parseInt(month, 10); // month를 정수로 변환
	let monthcopy2 = monthcopy + 1; // 다음 달 설정
	let lastday = "-31";
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");
	const santeiKaishi = document.getElementById('standard-period-start');
	const santeiShuuryou = document.getElementById('standard-period-end');
	const shikyuubi = document.getElementById("pay-date");

	// 윤년 계산 및 마지막 날 설정
	if (monthcopy === 2) { // 2월의 경우
		if ((year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)) {
			lastday = "-29";
		} else {
			lastday = "-28";
		}
	} else if (monthcopy === 4 || monthcopy === 6 || monthcopy === 9 || monthcopy === 11) { // 30일인 달
		lastday = "-30";
	}

	// monthcopy가 10보다 작을 때만 "0" 추가
	monthcopy = monthcopy < 10 ? "0" + monthcopy : monthcopy;
	monthcopy2 = monthcopy2 < 10 ? "0" + monthcopy2 : monthcopy2;

	santeiKaishi.value = yearcopy + "-" + monthcopy + "-01";
	santeiShuuryou.value = yearcopy + "-" + monthcopy + lastday;

	// monthcopy2가 13 이상이면 연도 증가 및 monthcopy2를 "01"로 설정
	if (monthcopy2 > 12) {
		yearcopy = yearcopy2;
		monthcopy2 = "01"; // 다음 해의 1월로 설정
	}

	shikyuubi.value = yearcopy + "-" + monthcopy2 + "-05";

	const url = `getKeisanKiroku.do?kyuuyoNendo=${year}&kyuuyoGatsu=${monthcopy}&kyuuyoJisuu=${jisuu}&koukinzei=${koukinzei}`;
	console.log(url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		success: function(response) {
			console.log("요청 성공");
			const tbody = document.getElementById("keisanKirokuTable").getElementsByTagName("tbody")[0];
			tbody.innerHTML = "";

			let totalRowCount = response.length || 0;
			let totalAllShikyuuSougaku = 0;
			let totalAllKoujoSougaku = 0;
			let totalAllJissaiKyuuyo = 0;

			if (response.length > 0) {
				response.forEach(shain => {
					const row = document.createElement("tr");

					// shain_id 값을 data 속성으로 추가
					row.setAttribute("data-shain-id", shain.shain_id);

					// 클릭 이벤트 리스너 추가
					row.addEventListener("click", function() {
						const shainId = this.getAttribute("data-shain-id");
						getKoujoKoumokuInfo(shainId);
						getKihonkyuuInfo(shainId);
						getKyuuyoKoumokuInfo(shainId);
					});

					// 테이블에 표시할 데이터 설정
					["kubun", "name", "busho_mei"].forEach(key => {
						const cell = document.createElement("td");
						cell.textContent = shain[key];
						row.appendChild(cell);
					});

					// 금액 데이터에 .toLocaleString() 적용 및 합계 계산
					const shikyuuSougakuCell = document.createElement("td");
					const shikyuuSougaku = shain.shikyuuSougaku || 0;
					shikyuuSougakuCell.textContent = shikyuuSougaku.toLocaleString();
					row.appendChild(shikyuuSougakuCell);
					totalAllShikyuuSougaku += shikyuuSougaku;

					const koujoSougakuCell = document.createElement("td");
					const koujoSougaku = shain.koujoSougaku || 0;
					koujoSougakuCell.textContent = koujoSougaku.toLocaleString();
					row.appendChild(koujoSougakuCell);
					totalAllKoujoSougaku += koujoSougaku;

					const jissaiKyuuyoCell = document.createElement("td");
					const jissaiKyuuyo = shain.jissai_kyuuyo || 0;
					jissaiKyuuyoCell.textContent = jissaiKyuuyo.toLocaleString();
					row.appendChild(jissaiKyuuyoCell);
					totalAllJissaiKyuuyo += jissaiKyuuyo;

					// kyuuyoGatsu 데이터 추가 (숨김)
					const hiddenCell = document.createElement("td");
					hiddenCell.textContent = shain.kyuuyoGatsu;
					hiddenCell.id = "kyuuyoGatsu";
					hiddenCell.style.display = "none";
					row.appendChild(hiddenCell);

					tbody.appendChild(row);
				});
			}

			// 총합 계산 결과를 DOM에 출력
			document.getElementById("totalRowCount").textContent = totalRowCount;
			document.getElementById("totalAllShikyuuSougaku").textContent = totalAllShikyuuSougaku.toLocaleString();
			document.getElementById("totalAllKoujoSougaku").textContent = totalAllKoujoSougaku.toLocaleString();
			document.getElementById("totalAllJissaiKyuuyo").textContent = totalAllJissaiKyuuyo.toLocaleString();

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
	reset();
	// div의 값을 1로 변경
	document.getElementById('incomeDiv').setAttribute('data-value', '0');
	// 버튼의 상태 변경
	document.getElementById('generalIncome').classList.add('active');
	document.getElementById('generalIncome').classList.remove('inactive');
	document.getElementById('businessIncome').classList.add('inactive');
	document.getElementById('businessIncome').classList.remove('active');

	getShainList();
}

function businessList() {
	reset();
	// div의 값을 1로 변경
	document.getElementById('incomeDiv').setAttribute('data-value', '1');
	// 버튼의 상태 변경
	document.getElementById('businessIncome').classList.add('active');
	document.getElementById('businessIncome').classList.remove('inactive');
	document.getElementById('generalIncome').classList.add('inactive');
	document.getElementById('generalIncome').classList.remove('active');

	getShainList();
}
function calc(button) {
	const dataValue = button.getAttribute("data-value");
	//console.log(dataValue);
	getKoujoKoumokuInfo(dataValue);
}
function modify() {
	// 각 필드의 값 가져오기
	const santeiKaishi = document.getElementById('standard-period-start').value;
	const santeiShuuryou = document.getElementById('standard-period-end').value;
	const shikyuu_bi = document.getElementById('pay-date').value;

	const EXsantei_kaishi = document.getElementById('EXstandard-period-start').value;
	const EXsantei_shuuryou = document.getElementById('EXstandard-period-end').value;
	const EXshikyuu_bi = document.getElementById('EXpay-date').value;
	const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");
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
function calculateAndDisplayTotals() {
	console.log("호출");
	// 총합을 저장할 변수 초기화
	let totalAllShikyuuSougaku = 0;
	let totalAllKoujoSougaku = 0;
	let totalAllJissaiKyuuyo = 0;

	// 테이블의 각 행을 순회하며 값 합산
	const rows = document.querySelectorAll("#keisanKirokuTable tbody tr");
	const rowCount = rows.length; // 총 객체 수
	console.log(rows);
	console.log(rowCount);
	rows.forEach(row => {
		const shikyuuSougakuCell = row.cells[3]; // 지급총액 셀
		const koujoSougakuCell = row.cells[4];   // 공제총액 셀
		const jissaiKyuuyoCell = row.cells[5];    // 실지급액 셀

		totalAllShikyuuSougaku += parseFloat(shikyuuSougakuCell.textContent.replace(/[^0-9.-]+/g, "")) || 0;
		totalAllKoujoSougaku += parseFloat(koujoSougakuCell.textContent.replace(/[^0-9.-]+/g, "")) || 0;
		totalAllJissaiKyuuyo += parseFloat(jissaiKyuuyoCell.textContent.replace(/[^0-9.-]+/g, "")) || 0;
	});

	console.log("총 객체 수:", rowCount);
	console.log("급여 총액 합계:", totalAllShikyuuSougaku);
	console.log("공제 총액 합계:", totalAllKoujoSougaku);
	console.log("실지급액 합계:", totalAllJissaiKyuuyo);

	// 총합을 출력하는 함수 호출
	displayTotalAmounts(rowCount, totalAllShikyuuSougaku, totalAllKoujoSougaku, totalAllJissaiKyuuyo);
}

// 총합을 표시하는 함수
function displayTotalAmounts(rowCount, shikyuuSougaku, koujoSougaku, jissaiKyuuyo) {
	document.getElementById("totalRowCount").textContent = rowCount + " 件";
	document.getElementById("totalAllShikyuuSougaku").textContent = Math.round(shikyuuSougaku) + " 円";
	document.getElementById("totalAllKoujoSougaku").textContent = Math.round(koujoSougaku) + " 円";
	document.getElementById("totalAllJissaiKyuuyo").textContent = Math.round(jissaiKyuuyo) + " 円";
}

// 내용 지우기 버튼
function reset() {
	resetValues("kihonkoujo-"); // 공제 항목 초기화
	resetValues("koujo-");
	resetValues("kyuuyo-");      // 지급 항목 초기화

	// 지급총액, 공제총액, 실지급액도 0으로 초기화
	calculateTotalIncome();
	calculateTotalDeduction();
}
function resetValues(prefix) {
	const inputs = document.querySelectorAll(`input[id^='${prefix}']`);
	inputs.forEach(input => {
		input.value = "0";
	});
}

// 단일 선택 삭제 버튼
function deleteOne() {
	const button = document.getElementById("oneDelete");
	const shain_id = button.getAttribute("data-value");
	const year = document.getElementById("kyuuyoNendo").value;
	let month = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;

	if (shain_id == 0) {
		alert("선택된 사원이 없습니다.");
		return;
	}

	if (month < 10) {
		month = "0" + month;
	}
	let kyuuyoGatsu = year + "-" + month + "-01";
	const url = "deleteKeisanKiroku.do?shain_id=" + shain_id + "&kyuuyoGatsu=" + kyuuyoGatsu + "&kyuuyoJisuu=" + jisuu;


	$.ajax({
		url: url,
		type: 'GET', // GET 메서드를 사용
		dataType: 'json',
		success: function() {
			console.log("삭제 성공");
			alert("삭제가 완료되었습니다.");
			getShainList();
		},
		error: function() {
			alert("삭제에 실패했습니다.");
			getShainList();
		}
	});
}

function deleteAll() {

	const userConfirmed = confirm("전체 급여입력 정보를 삭제하시겠습니까?");
	const userConfirmed2 = confirm("삭제된 급여입력 정보는 복구할 수 없습니다.\n삭제하시겠습니까?")

	const year = document.getElementById("kyuuyoNendo").value;
	let month = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;

	if (userConfirmed) {
		if (userConfirmed2) {
			if (month < 10) {
				month = "0" + month;
			}
			let kyuuyoGatsu = year + "-" + month + "-01";
			const url = "deleteAllKeisanKiroku.do?kyuuyoGatsu=" + kyuuyoGatsu + "&kyuuyoJisuu=" + jisuu;


			$.ajax({
				url: url,
				type: 'GET', // GET 메서드를 사용
				dataType: 'json',
				success: function() {
					console.log("삭제 성공");
					alert("삭제가 완료되었습니다.");
					getShainList();
				},
				error: function() {
					alert("삭제에 실패했습니다.");
					getShainList();
				}
			});
		} else {
			return;
		}
	} else {
		return;
	}

}

// 지급총액 계산 함수
function calculateTotalIncome() {
	let total = 0;

	// kyuuyo-로 시작하는 모든 요소 선택 및 값 합산
	document.querySelectorAll("input[id^='kyuuyo-']").forEach(input => {
		const value = parseFloat(input.value.replace(/,/g, '')) || 0; // 천 단위 구분 제거 및 값이 없으면 0으로 처리
		total += value;
	});

	// 합계를 지급총액 요소에 업데이트
	document.getElementById("totalKyuuyoSougaku").textContent = total.toLocaleString();

	// 실지급액 업데이트
	updateNetPay();
}

// 공제총액 계산 함수
function calculateTotalDeduction() {
	let total = 0;

	// kihonkoujo-와 koujo-로 시작하는 모든 요소 선택 및 값 합산
	document.querySelectorAll("input[id^='kihonkoujo-'], input[id^='koujo-']").forEach(input => {
		const value = parseFloat(input.value.replace(/,/g, '')) || 0; // 천 단위 구분 제거 및 값이 없으면 0으로 처리
		total += value;
	});

	// 합계를 공제총액 요소에 업데이트
	document.getElementById("totalKoujoSougaku").textContent = total.toLocaleString();

	// 실지급액 업데이트
	updateNetPay();
}

// 실지급액 계산 및 업데이트 함수
function updateNetPay() {
	const totalKyuuyoSougaku = parseFloat(document.getElementById("totalKyuuyoSougaku").textContent.replace(/[^0-9.-]+/g, "")) || 0;
	const totalKoujoSougaku = parseFloat(document.getElementById("totalKoujoSougaku").textContent.replace(/[^0-9.-]+/g, "")) || 0;
	const netPay = totalKyuuyoSougaku - totalKoujoSougaku;

	// 실지급액 요소에 업데이트
	document.getElementById("totalJissaiKyuuyo").textContent = netPay.toLocaleString() + " 円";
}

// 이벤트 리스너 추가 함수
function addListeners() {
	// 지급총액 관련 입력 필드에 이벤트 리스너 추가
	document.querySelectorAll("input[id^='kyuuyo-']").forEach(input => {
		input.addEventListener("input", calculateTotalIncome);
	});

	// 공제총액 관련 입력 필드에 이벤트 리스너 추가
	document.querySelectorAll("input[id^='kihonkoujo-'], input[id^='koujo-']").forEach(input => {
		input.addEventListener("input", calculateTotalDeduction);
	});
}

// DOM이 로드된 후 이벤트 리스너 추가 및 초기 계산
document.addEventListener("DOMContentLoaded", () => {
	addListeners();
	calculateTotalIncome(); // 초기 지급총액 계산
	calculateTotalDeduction(); // 초기 공제총액 계산
});

function saveInfo() {
	const button = document.getElementById("saveButton");
	const shain_id = button.getAttribute("data-value");
	const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
	let kyuuyoMonth = document.getElementById("kyuuyoGatsu").value;

	const jisuu = document.getElementById("kyuuyoJisuu").value;
	const totalKyuuyoSougakuText = document.getElementById("totalKyuuyoSougaku").textContent;
	const totalKoujoSougakuText = document.getElementById("totalKoujoSougaku").textContent;
	const totalJissaiKyuuyoText = document.getElementById("totalJissaiKyuuyo").textContent;

	const totalKyuuyoSougakuNumber = parseFloat(totalKyuuyoSougakuText.replace(/[^0-9.-]+/g, ""));
	const totalKoujoSougakuNumber = parseFloat(totalKoujoSougakuText.replace(/[^0-9.-]+/g, ""));
	const totalJissaiKyuuyoNumber = parseFloat(totalJissaiKyuuyoText.replace(/[^0-9.-]+/g, ""));

	if (kyuuyoMonth < 10) {
		kyuuyoMonth = "0" + kyuuyoMonth;
	}
	let kyuuyoGatsu = kyuuyoNendo + "-" + kyuuyoMonth + "-01";

	console.log(kyuuyoNendo);
	console.log(kyuuyoGatsu);
	console.log(totalKoujoSougakuNumber);
	console.log(totalJissaiKyuuyoNumber);

	const url1 = "saveKyuuyoKeisanKiroku.do?shain_id=" + shain_id + "&kyuuyoGatsu=" + kyuuyoGatsu + "&kyuuyoJisuu=" + jisuu + "&kyuuyoSougaku=" + totalKyuuyoSougakuNumber + "&koujoSougaku=" + totalKoujoSougakuNumber + "&jissaiKyuuyo=" + totalJissaiKyuuyoNumber;
	$.ajax({
		url: url1,
		type: 'GET', // GET 메서드를 사용
		dataType: 'json',
		success: function(response) {
			console.log("급여계산기록 데이터 저장 성공:", response);
			alert("저장이 완료되었습니다.");
			getShainList();
		},
		error: function(error) {
			console.error("급여계산기록 데이터 저장 실패:", error);
			alert("저장에 실패했습니다.");
			getShainList();
		}
	});
}

function saveKoujoKiroku() {
	const button = document.getElementById("saveButton");
	const shain_id = button.getAttribute("data-value");
	const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
	let kyuuyoMonth = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	console.log(kyuuyoMonth);
	if (kyuuyoMonth < 10) {
		kyuuyoMonth = "0" + kyuuyoMonth;
	}
	let kyuuyoNengappi = kyuuyoNendo + "-" + kyuuyoMonth + "-01";

	const data = {};

	document.querySelectorAll("input[id^='koujo-'], input[id^='kihonkoujo-']").forEach(input => {
		const id = input.id;
		const number = id.split('-')[1]; // 'koujo-' 또는 'kihonkoujo-' 이후의 숫자 부분 추출
		const value = input.value.replace(/,/g, '') || "0"; // 천 단위 구분 제거 및 값이 없으면 0으로 처리

		// 데이터 객체에 추가
		data[`${number}`] = value;
	});

	console.log(data);

	const url = "insertShainKoujoKiroku.do?shain_id=" + shain_id + "&kyuuyoNengappi=" + kyuuyoNengappi + "&kyuuyoJisuu=" + jisuu;
	$.ajax({
		url: url,
		type: 'GET', // GET 메서드를 사용
		dataType: 'json',
		data: data,
		success: function(response) {
			console.log("공제기록 데이터 저장 성공:", response);
			getShainList();
		},
		error: function(error) {
			console.error("공제기록 데이터 저장 실패:", error);
			getShainList();
		}
	});


}

function saveKyuuyoKiroku() {
	const button = document.getElementById("saveButton");
	const shain_id = button.getAttribute("data-value");
	const kyuuyoNendo = document.getElementById("kyuuyoNendo").value;
	let kyuuyoMonth = document.getElementById("kyuuyoGatsu").value;
	const jisuu = document.getElementById("kyuuyoJisuu").value;
	console.log(kyuuyoMonth);
	if (kyuuyoMonth < 10) {
		kyuuyoMonth = "0" + kyuuyoMonth;
	}
	let kyuuyoNengappi = kyuuyoNendo + "-" + kyuuyoMonth + "-01";
	const data = {};

	// 모든 kyuuyo-로 시작하는 입력 요소 선택
	document.querySelectorAll("input[id^='kyuuyo-']").forEach(input => {
		const id = input.id;
		const number = id.split('-')[1]; // 'kyuuyo-' 이후의 숫자 부분 추출
		const value = input.value.replace(/,/g, '') || "0"; // 천 단위 구분 제거 및 값이 없으면 0으로 처리

		// 데이터 객체에 추가
		data[`${number}`] = value;
	});

	console.log(data);
	const url = "insertShainKyuuyoKiroku.do?shain_id=" + shain_id + "&kyuuyoNengappi=" + kyuuyoNengappi + "&kyuuyoJisuu=" + jisuu;
	// AJAX 요청 보내기
	$.ajax({
		url: url, // 서버의 엔드포인트 URL
		type: 'GET',
		dataType: 'json',
		data: data, // JSON 문자열 대신 객체로 전송
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("급여기록 데이터 저장 성공:", response);
		},
		error: function(error) {
			console.error("급여기록 데이터 저장 실패:", error);
		}
	});
}

function formatWithCommas(input) {
	// 입력된 값을 문자열로 가져온 후 기존 쉼표 제거
	const value = input.value.replace(/,/g, '');

	// 숫자로 변환 후 다시 천 단위 쉼표 추가하여 문자열로 변환
	const formattedValue = parseFloat(value).toLocaleString();

	// 입력 필드에 포맷팅된 값 설정
	input.value = formattedValue;
}

function pastKyuuyoKeisan() {
	const url = "pastKyuuyoKeisanKiroku.do";

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("지난 급여 기록 불러오기 성공:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("지난 급여 기록 불러오기 실패:", error);
		}
	});
}

function renderPastKiroku(data) {
	const select = document.getElementById("yearMonthSelectPast");
	select.innerHTML = '<option value="">귀속년월 차수 선택</option>'; // 기본 옵션 설정

	data.forEach(item => {
		const option = document.createElement("option");
		option.value = `${item.originalKyuuyo_gatsu}-${item.originalKyuuyo_jisuu}`; // ex: "2025-01-02"
		option.textContent = `${item.kyuuyo_gatsu}`;
		select.appendChild(option);
	});
}

// 팝업 열기
function openPastPopup() {
	document.getElementById("overlay").style.display = "block";
	document.getElementById("popupPast").style.display = "block";
}

// 팝업 닫기 함수
function closePastPopup() {
	document.getElementById("overlay").style.display = "none";
	document.getElementById("popupPast").style.display = "none";
}

// 팝업 닫기
function closeAllPopup(event) {
	if (event.target.id === "overlay") {
		closePastPopup();
		closePopup();
		closeKPopup()
	}
}

function loadPastSalaryInfo() {
	const select = document.getElementById("yearMonthSelectPast");
	const selectedValue = select.value;
	const koukinzei = document.getElementById('incomeDiv').getAttribute("data-value");

	if (selectedValue) {
		const [kyuuyoNendo, kyuuyoGatsu, day, kyuuyoJisuu] = selectedValue.split("-"); // 값 분리
		console.log(kyuuyoJisuu);
		// 円하는 URL 생성
		const url = `kanri.do?kyuuyoNendo=${kyuuyoNendo}&kyuuyoGatsu=${kyuuyoGatsu}&kyuuyoJisuu=${kyuuyoJisuu}&koukinzei=${koukinzei}`;
		// 페이지 이동
		window.location.href = url;
	} else {
		alert("귀속년월 차수를 선택해주세요."); // 옵션이 선택되지 않았을 경우 경고 메시지
	}
}

// 추가 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function addKoujo() {
	if (document.getElementById('koujo-id').value == '') {
		alert("先ず項目を選択してください。");
		return;
	}
	const koujokoumoku_id = document.getElementById('koujo-id').value;
	const koujokoumoku_mei = document.getElementById('koujo-mei').value;
	const keisanHouhou = document.getElementById('koujo-calc-method').value;
	const zenshadani = document.getElementById('koujo-unit').value;
	const bikou = document.getElementById('koujo-note').value;

	const url = "insertKoujoKoumoku.do?koujokoumoku_id=" + koujokoumoku_id + "&koujokoumoku_mei=" + koujokoumoku_mei + "&keisanHouhou=" + keisanHouhou + "&zenshadani=" + zenshadani + "&bikou=" + bikou;
	console.log(url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("지난 급여 기록 불러오기 성공:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("지난 급여 기록 불러오기 실패:", error);
		}
	});
}

// 수정 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function editKoujo() {
	if (document.getElementById('koujo-id').value == '') {
		alert("先ず項目を選択してください。");
		return;
	}
	const koujokoumoku_id = document.getElementById('koujo-id').value;
	const koujokoumoku_mei = document.getElementById('koujo-mei').value;
	const keisanHouhou = document.getElementById('koujo-calc-method').value;
	const zenshadani = document.getElementById('koujo-unit').value;
	const bikou = document.getElementById('koujo-note').value;

	const url = "updateKoujoKoumoku.do?koujokoumoku_id=" + koujokoumoku_id + "&koujokoumoku_mei=" + koujokoumoku_mei + "&keisanHouhou=" + keisanHouhou + "&zenshadani=" + zenshadani + "&bikou=" + bikou;
	console.log(url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("지난 급여 기록 불러오기 성공:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("지난 급여 기록 불러오기 실패:", error);
		}
	});
}

// 삭제 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function deleteKoujo() {
	if (document.getElementById('koujo-id').value == '') {
		alert("先ず項目を選択してください。");
		return;
	}
	const koujokoumoku_id = document.getElementById('koujo-id').value;
	const koujokoumoku_mei = document.getElementById('koujo-mei').value;

	const url = "deleteKoujoKoumoku.do?koujokoumoku_id=" + koujokoumoku_id + "&koujokoumoku_mei=" + koujokoumoku_mei;
	console.log(url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("지난 급여 기록 불러오기 성공:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("지난 급여 기록 불러오기 실패:", error);
		}
	});

}

// 추가 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function addKyuuyo() {
	const kyuuyokoumoku_id = document.getElementById("kyuuyoCategory").value;
	const kyuuyokoumoku_mei = document.getElementById("kyuuyo-mei").value;
	const kazeikubun = document.querySelector('input[name="tax"]:checked') ? document.querySelector('input[name="tax"]:checked').value : "";
	const non_tax_name = document.getElementById("non-tax-name").value;
	const hikazeigendogaku = document.getElementById("limit-amount").value;
	const keisanhouhou = document.getElementById("kyuuyo-calc-method").value;
	const zenshadani = document.getElementById("kyuuyo-unit").value;
	const kintairenkei = document.getElementById("bulk-pay").value;
	const ikkatsushiharaigaku = document.getElementById("bulk-amount").value;

	const queryString = "kyuuyokoumoku_id=" + kyuuyokoumoku_id +
		"&kyuuyokoumoku_mei=" + kyuuyokoumoku_mei +
		"&kazeikubun=" + kazeikubun +
		"&non_tax_name=" + non_tax_name +
		"&hikazeigendogaku=" + hikazeigendogaku +
		"&keisanhouhou=" + keisanhouhou +
		"&zenshadani=" + zenshadani +
		"&kintairenkei=" + kintairenkei +
		"&ikkatsushiharaigaku=" + ikkatsushiharaigaku;

	// 최종 URL
	const url = "insertKyuuyoKoumoku.do?" + queryString;

	console.log("Request URL:", url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("給与項目の削除成功:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("給与項目の削除失敗:", error);
		}
	});


}

// 수정 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function editKyuuyo() {
	const kyuuyokoumoku_id = document.getElementById("kyuuyoCategory").value;
	const kyuuyokoumoku_mei = document.getElementById("kyuuyo-mei").value;
	const kazeikubun = document.querySelector('input[name="tax"]:checked') ? document.querySelector('input[name="tax"]:checked').value : "";
	const non_tax_name = document.getElementById("non-tax-name").value;
	const hikazeigendogaku = document.getElementById("limit-amount").value;
	const keisanhouhou = document.getElementById("kyuuyo-calc-method").value;
	const zenshadani = document.getElementById("kyuuyo-unit").value;
	const kintairenkei = document.getElementById("bulk-pay").value;
	const ikkatsushiharaigaku = document.getElementById("bulk-amount").value;

	const queryString = "kyuuyokoumoku_id=" + kyuuyokoumoku_id +
		"&kyuuyokoumoku_mei=" + kyuuyokoumoku_mei +
		"&kazeikubun=" + kazeikubun +
		"&non_tax_name=" + non_tax_name +
		"&hikazeigendogaku=" + hikazeigendogaku +
		"&keisanhouhou=" + keisanhouhou +
		"&zenshadani=" + zenshadani +
		"&kintairenkei=" + kintairenkei +
		"&ikkatsushiharaigaku=" + ikkatsushiharaigaku;

	// 최종 URL
	const url = "updateKyuuyoKoumoku.do?" + queryString;

	console.log("Request URL:", url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("給与項目の削除成功:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("給与項目の削除失敗:", error);
		}
	});
}

// 삭제 버튼 클릭 시 input에 있는 정보들을 console.log로 출력하는 함수
function deleteKyuuyo() {
	const kyuuyokoumoku_id = document.getElementById("kyuuyoCategory").value;
	const kyuuyokoumoku_mei = document.getElementById("kyuuyo-mei").value;


	const queryString = "kyuuyokoumoku_id=" + kyuuyokoumoku_id +
		"&kyuuyokoumoku_mei=" + kyuuyokoumoku_mei;

	// 최종 URL
	const url = "deleteKyuuyoKoumoku.do?" + queryString;

	console.log("Request URL:", url);

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(response) {
			console.log("給与項目の削除成功:", response);
			renderPastKiroku(response);
		},
		error: function(error) {
			console.error("給与項目の削除失敗:", error);
		}
	});

}