<!DOCTYPE html>
 <!-- 김찬호 金燦鎬 -->
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員リスト</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>社員リスト</h1>
    <div id="shainList"></div>

    <script>
        $(document).ready(function() {
            $.ajax({
                url: '/kyuyoKanri/shain.do', // ハンドラーのURL
                method: 'GET',
                data: {
                    command: 'ShainHandler',
                    action: 'getAllShain'
                },
                dataType: 'json',
                success: function(data) {
                    // サーバーから受け取ったデータが空でない場合、各社員IDを表示
                    if (data && data.length > 0) {
                        let htmlContent = '<ul>';
                        data.forEach(function(shain) {
                            htmlContent += '<li>社員ID: ' + shain.shain_id + '</li>';
                        });
                        htmlContent += '</ul>';
                        $('#shainList').html(htmlContent);
                    } else {
                        $('#shainList').html('<p>社員データがありません。</p>');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('AJAXエラー:', error);
                    $('#shainList').html('<p>データを取得する際にエラーが発生しました。</p>');
                }
            });
        });
    </script>
</body>
</html>
