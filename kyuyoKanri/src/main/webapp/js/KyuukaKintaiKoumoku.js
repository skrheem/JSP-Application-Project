document.addEventListener("DOMContentLoaded", function() {
	
	// 연차휴가 자동 계산 함수
	function calculateAnnualLeave() {
		const joinDate = new Date(document.getElementById("joinDate").value);
		const endDate = new Date(document.getElementById("endDate").value);
		const yearsOfService = endDate.getFullYear() - joinDate.getFullYear();
		let annualLeave;

		if (yearsOfService < 1) {
			const workingDays = Math.floor((endDate - joinDate) / (1000 * 60 * 60 * 24));
			annualLeave = Math.floor((15 * workingDays) / 365);
		} else if (yearsOfService >= 1 && yearsOfService < 3) {
			annualLeave = 15;
		} else {
			annualLeave = 15 + Math.floor((yearsOfService - 1) / 2);
			if (annualLeave > 25) annualLeave = 25;
		}

		document.getElementById("annualLeave").value = annualLeave + " 일";
	}

	// 휴가 항목 추가
	function addVacationItem() {
		const vacationItem = document.getElementById("vacationItem").value;
		const startDate = document.getElementById("startDate").value;
		const endDate = document.getElementById("endDate").value;
		const usage = document.querySelector('input[name="attendanceUsage"]:checked').value;

		if (!vacationItem || !startDate || !endDate) {
			alert("모든 필드를 입력하세요.");
			return;
		}

		const newRow = document.createElement('tr');
		newRow.dataset.name = vacationItem;
		newRow.dataset.start = startDate;
		newRow.dataset.end = endDate;
		newRow.dataset.usage = usage;

		newRow.innerHTML = `
            <td>${vacationItem}</td>
            <td>${startDate} ~ ${endDate}</td>
            <td><button class="btn btn-gray" onclick="openVacationModal()">管理</button></td>
            <td><span class="usage-status">${usage}</span></td>
        `;

		document.querySelector(".table-section tbody").appendChild(newRow);
		alert("휴가 항목이 추가되었습니다.");
	}

	// 휴가 항목 수정
	function updateVacationItem() {
		const selectedRow = document.querySelector(".table-section tbody tr.selected");

		if (!selectedRow) {
			alert("수정할 항목을 선택하세요.");
			return;
		}

		const vacationItem = document.getElementById("vacationItem").value;
		const startDate = document.getElementById("startDate").value;
		const endDate = document.getElementById("endDate").value;
		const usage = document.querySelector('input[name="attendanceUsage"]:checked').value;

		selectedRow.dataset.name = vacationItem;
		selectedRow.dataset.start = startDate;
		selectedRow.dataset.end = endDate;
		selectedRow.dataset.usage = usage;

		selectedRow.children[0].textContent = vacationItem;
		selectedRow.children[1].textContent = `${startDate} ~ ${endDate}`;
		selectedRow.children[3].querySelector('.usage-status').textContent = usage;

		alert("휴가 항목이 수정되었습니다.");
	}

	// 휴가 항목 삭제
	function deleteVacationItem() {
		const selectedRow = document.querySelector(".table-section tbody tr.selected");

		if (!selectedRow) {
			alert("삭제할 항목을 선택하세요.");
			return;
		}

		selectedRow.remove();
		alert("휴가 항목이 삭제되었습니다.");
	}

	// 폼 내용 지우기
	function clearForm() {
		document.getElementById("vacationItem").value = "";
		document.getElementById("startDate").value = "";
		document.getElementById("endDate").value = "";
		document.querySelector('input[name="attendanceUsage"][value="사용"]').checked = true;
	}

	// 버튼 색상 변경 함수
	function highlightButton(button) {
		const originalColor = button.style.backgroundColor;
		button.style.backgroundColor = '#ffcc00';

		setTimeout(() => {
			button.style.backgroundColor = originalColor;
		}, 200);
	}

	// 버튼 클릭 이벤트 연결
	const addButton = document.querySelector(".btn-add");
	const updateButton = document.querySelector(".btn-update");
	const deleteButton = document.querySelector(".btn-delete");
	const clearButton = document.querySelector(".btn-clear");

	if (addButton) {
		addButton.addEventListener("click", function() {
			highlightButton(this);
			addVacationItem();
		});
	}

	if (updateButton) {
		updateButton.addEventListener("click", function() {
			highlightButton(this);
			updateVacationItem();
		});
	}

	if (deleteButton) {
		deleteButton.addEventListener("click", function() {
			highlightButton(this);
			deleteVacationItem();
		});
	}

	if (clearButton) {
		clearButton.addEventListener("click", function() {
			highlightButton(this);
			clearForm();
		});
	}

	// 모달 열기 및 닫기 함수
	function openGroupModal() {
		document.getElementById("groupModal").style.display = "block";
	}

	function closeGroupModal() {
		document.getElementById("groupModal").style.display = "none";
	}

	function openVacationModal() {
		loadshainList();
		document.getElementById("vacationModal").style.display = "block";
	}

	function closeVacationModal() {
		document.getElementById("vacationModal").style.display = "none";
	}

	window.onclick = function(event) {
		if (event.target == document.getElementById("groupModal")) {
			closeGroupModal();
		} else if (event.target == document.getElementById("vacationModal")) {
			closeVacationModal();
		}
	};
});
