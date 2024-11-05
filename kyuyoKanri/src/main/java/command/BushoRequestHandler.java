package command;
//김찬호 金燦鎬
//기본환경설정 담당자 정보 부서 관리 버튼
//担当者情報 部署管理
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import kihonkankyousettei.model.Busho;
import mvc.command.CommandHandler;
import service.ShiyoushaJouhouService;

/**
 * BushoRequestHandler는 부서 관련 요청을 처리하는 핸들러 클래스
 * JSON 데이터를 사용하여 부서 추가 요청을 받고 결과를 JSON 형식으로 응답
 * BushoRequestHandlerは部門関連リクエストを処理するハンドラークラスです。
 * JSONデータを使用して部門追加リクエストを受け取り、結果をJSON形式で応答します。
 */
public class BushoRequestHandler implements CommandHandler {
    private ShiyoushaJouhouService shiyoushaService = new ShiyoushaJouhouService();

    /**
     * 요청 처리 메서드로, `handleBushoRequest` 메서드를 호출하여 구체적인 처리를 수행
     * リクエスト処理メソッドで、`handleBushoRequest`メソッドを呼び出して具体的な処理を実行します。
     *
     * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
     * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
     * @return 처리 결과 문자열 (현재는 null을 반환하며 직접 응답을 작성) / 処理結果の文字列（現在はnullを返し、直接応答を作成）
     * @throws Exception 처리 중 발생할 수 있는 예외 / 処理中に発生する可能性のある例外
     */
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleBushoRequest(request, response);
    }

    /**
     * 부서 관련 요청을 처리하는 메서드로, JSON 데이터를 파싱하여 부서 추가 작업을 수행
     * 部門関連リクエストを処理するメソッドで、JSONデータを解析して部門追加作業を実行します。
     *
     * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
     * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
     * @return null (직접 JSON 응답을 작성) / null（直接JSON応答を作成）
     * @throws IOException 입력/출력 중 발생할 수 있는 예외 / 入出力中に発生する可能性のある例外
     */
    public String handleBushoRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action"); // 요청된 작업(action) 파라미터를 가져옴 / 要求された作業(action)パラメータを取得
        response.setContentType("application/json");    // 응답을 JSON 형식으로 설정 / 応答をJSON形式に設定
        response.setCharacterEncoding("UTF-8");         // 응답 인코딩을 UTF-8로 설정 / 応答エンコーディングをUTF-8に設定

        JSONObject jsonResponse = new JSONObject();
        try {
            // JSON 데이터를 읽어들이기 위해 버퍼 초기화
            // JSONデータを読み取るためにバッファを初期化
            StringBuilder jsonBuffer = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuffer.append(line); // 요청의 JSON 데이터를 한 줄씩 읽어서 버퍼에 추가 / リクエストのJSONデータを1行ずつ読み込んでバッファに追加
                }
            }

            System.out.println("Received JSON Data: " + jsonBuffer.toString()); // 받은 JSON 데이터를 출력 (디버깅용) / 受け取ったJSONデータを出力（デバッグ用）

            // JSON 데이터가 비어있는지 확인
            // JSONデータが空であるか確認
            if (jsonBuffer.length() == 0) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "데이터가 없습니다."); // 데이터가 없을 경우 메시지 설정 / データがない場合はメッセージを設定
                response.getWriter().write(jsonResponse.toString());
                return null;
            }

            // JSON 데이터 파싱
            // JSONデータを解析
            JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
            String bushoName = jsonObject.optString("busho_mei", null); // 부서 이름 필드를 가져옴 / 部門名フィールドを取得

            // 부서 이름이 유효한지 확인
            // 部門名が有効か確認
            if (bushoName == null || bushoName.trim().isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "부서 이름을 입력해 주세요."); // 부서 이름이 없을 경우 메시지 설정 / 部門名がない場合はメッセージを設定
            } else {
                // 부서 객체 생성 및 설정
                // 部門オブジェクトを生成して設定
                Busho busho = new Busho();
                busho.setBusho_mei(bushoName);

                boolean isSuccess;
                switch (action) {
                    case "addBusho": // 부서 추가 작업인 경우 / 部門追加作業の場合
                        isSuccess = shiyoushaService.addBusho(busho);
                        jsonResponse.put("success", isSuccess);
                        jsonResponse.put("message", isSuccess ? "부서가 추가되었습니다." : "부서 추가에 실패했습니다。");
                        jsonResponse.put("message", isSuccess ? "部門が追加されました。" : "部門の追加に失敗しました。");
                        break;

                    default: // 알 수 없는 요청에 대한 처리 / 不明なリクエストの処理
                        jsonResponse.put("success", false);
                        jsonResponse.put("message", "알 수 없는 요청입니다。"); // 알 수 없는 요청 메시지 설정 / 不明なリクエストメッセージを設定
                        jsonResponse.put("message", "不明なリクエストです。");
                        break;
                }
            }
        } catch (Exception e) {
            // 예외 처리 시 메시지와 로그 출력
            // 例外処理時にメッセージとログを出力
            jsonResponse.put("success", false);
            jsonResponse.put("message", "처리 중 오류 발생: " + e.getMessage());
            jsonResponse.put("message", "処理中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }

        // 최종 JSON 응답 전송
        // 最終JSON応答の送信
        response.getWriter().write(jsonResponse.toString());
        return null;
    }
}
