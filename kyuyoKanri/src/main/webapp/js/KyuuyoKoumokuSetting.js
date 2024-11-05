//김찬호 金燦鎬
//급여항목 설정
//給与項目設定
// 자동 채우기 기능
function fillPayItemForm(row) {
	const itemData = row.dataset; // data-* 속성으로 데이터 접근
	document.getElementById("payItemName").value = itemData.name || ""; // 지급항목
	document.getElementById("taxable").value = itemData.taxable || "과세"; // 과세여부
	document.getElementById("nonTaxLimit").value = itemData.nonTaxLimit || ""; // 비과세한도액
	document.getElementById("calculationType").value = itemData.calculationType || "상수"; // 계산타입
	document.getElementById("attendanceLink").value = itemData.attendanceLink || ""; // 근태연결/일할지급

	// 사용여부
	const usageRadio = document.querySelector(`input[name="payItemUsage"][value="${itemData.usage || '사용'}"]`);
	if (usageRadio) {
		usageRadio.checked = true;
	}
}

function fillDeductionItemForm(row) {
	const itemData = row.dataset; // data-* 속성으로 데이터 접근
	document.getElementById("deductionItemName").value = itemData.name || ""; // 공제항목
	document.getElementById("deductionCalculationType").value = itemData.calculationType || "상수"; // 계산타입
	document.getElementById("deductionNote").value = itemData.note || ""; // 비고

	// 사용여부
	const usageRadio = document.querySelector(`input[name="deductionItemUsage"][value="${itemData.usage || '사용'}"]`);
	if (usageRadio) {
		usageRadio.checked = true;
	}
}
