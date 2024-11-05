//김찬호 金燦鎬
//기본환경설정 사용자정보
//基本環境設定 使用者情報
$(document).ready(function() {
	// 우편번호 검색 팝업
	function openPostcodePopup() {
		new daum.Postcode({
			oncomplete: function(data) {
				$('#zipcode').val(data.zonecode);
				$('#address').val(data.address);
			}
		}).open();
	}

	// 팝업 열기
	function openManagePopup() {
		$('#managePopupOverlay').css('display', 'flex');
	}

	// 팝업 닫기
	function closeManagePopup() {
		$('#managePopupOverlay').css('display', 'none');
	}

	// 부서 추가 함수
	function addBusho() {
		const bushoName = $('#bushoName').val();
		if (!bushoName) {
			alert("부서 이름을 입력해 주세요.");
			return;
		}

		const data = { busho_mei: bushoName };
		$.ajax({
			url: '/kyuyoKanri/shiyousha.do?action=addBusho',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data),
			success: function(response) {
				alert(response.message || '부서가 추가되었습니다.');
			},
			error: function(xhr, status, error) {
				alert("서버와의 통신에 문제가 발생했습니다.");
			}
		});
	}

	// 부서 수정 함수
	function updateBusho() {
		const bushoName = $('#bushoName').val();
		if (!bushoName) {
			alert("수정할 부서 이름을 입력해 주세요.");
			return;
		}

		$.ajax({
			url: '/kyuyoKanri/bushoRequest.do?action=updateBusho',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ busho_mei: bushoName }),
			success: function(response) {
				alert(response.message || '부서가 수정되었습니다.');
				closeManagePopup();
			},
			error: function(xhr, status, error) {
				alert("서버와의 통신에 문제가 발생했습니다.");
			}
		});
	}

	// 부서 삭제 함수
	function deleteBusho() {
		const bushoName = $('#bushoName').val();
		if (!bushoName) {
			alert("삭제할 부서 이름을 입력해 주세요.");
			return;
		}

		$.ajax({
			url: '/kyuyoKanri/bushoRequest.do?action=deleteBusho',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ busho_mei: bushoName }),
			success: function(response) {
				alert(response.message || '부서가 삭제되었습니다.');
				closeManagePopup();
			},
			error: function(xhr, status, error) {
				alert("서버와의 통신에 문제가 발생했습니다.");
			}
		});
	}
	
	// 모달 열기 함수 - 모달 타이틀을 설정하고 모달 표시
		function openModal(title) {
			document.getElementById("modalTitle").innerText = title; // 모달 타이틀 설정
			document.getElementById("modalOverlay").style.display = "flex";
		}

		// 모달 닫기 함수
		function closeModal() {
			document.getElementById("modalOverlay").style.display = "none";
		}

	// 함수들을 전역으로 등록
	window.openPostcodePopup = openPostcodePopup;
	window.openManagePopup = openManagePopup;
	window.closeManagePopup = closeManagePopup;
	window.addBusho = addBusho;
	window.updateBusho = updateBusho;
	window.deleteBusho = deleteBusho;
});
