<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>급여 입력 관리</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    <script>
    $(document).ready(function() {
        // 요청을 보낼 URL 및 파라미터 설정
        const url = "kanri.do";
        const params = {
            kyuuyoNendo: 2024,
            kyuuyoGatsu: 10,
            kyuuyoJisuu: 1,
            koukinzei: 0
        };

        // AJAX 요청 보내기
        $.ajax({
            url: url,
            method: 'GET',
            data: params,
            dataType: 'json',
            success: function(response) {
                renderData(response);
            },
            error: function(xhr, status, error) {
            	alert("!!");
                console.error("데이터 로드 실패:", error);
            }
        });

        function renderData(data) {
            let skListTable = $("#skListTable tbody");
            skListTable.empty();
            $.each(data.skList, function(index, item) {
                let row = "<tr>" +
                    "<td>" + (item.shain_id || "") + "</td>" +
                    "<td>" + (item.kubun || "") + "</td>" +
                    "<td>" + (item.name || "") + "</td>" +
                    "<td>" + (item.bushoMei || "") + "</td>" +
                    "<td>" + (item.shikyuuSougaku || "") + "</td>" +
                    "<td>" + (item.koujoSougaku || "") + "</td>" +
                    "<td>" + (item.jissaiKyuuyo || "") + "</td>" +
                    "<td>" + (item.kyuuyoSanteiKaishi || "") + "</td>" +
                    "<td>" + (item.kyuuyoSanteiShuuryou || "") + "</td>" +
                    "<td>" + (item.koukinzeiKubun || "") + "</td>" +
                    "</tr>";
                skListTable.append(row);
            });
        }
    });
    </script>
</head>
<body>
    <h2>급여 입력 관리</h2>

    <table id="skListTable" border="1">
        <thead>
            <tr>
                <th>사원 ID</th>
                <th>구분</th>
                <th>이름</th>
                <th>부서명</th>
                <th>지급 총액</th>
                <th>공제 총액</th>
                <th>실지급액</th>
                <th>급여 산정 시작</th>
                <th>급여 산정 종료</th>
                <th>공금세 구분</th>
            </tr>
        </thead>
        <tbody>
            <!-- JSON 데이터를 통해 채워질 기본 행 -->
            <tr>
                <td colspan="10" style="text-align: center;">데이터를 불러오는 중입니다...</td>
            </tr>
        </tbody>
    </table>
</body>
</html>