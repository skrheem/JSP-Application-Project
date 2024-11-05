package command;
//김찬호 金燦鎬
//기본환경설정 사용자정보 담당자 정보 직위
//基本環境設定 使用者情報担当者情報 職位
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import kihonkankyousettei.model.Yakushoku;
import mvc.command.CommandHandler;
import service.ShiyoushaJouhouService;

/**
 * YakushokuHandler는 직책 관련 요청을 처리하는 핸들러 클래스입니다.
 * YakushokuHandlerは役職関連リクエストを処理するハンドラークラスです。
 */
public class YakushokuHandler implements CommandHandler {
    private ShiyoushaJouhouService shiyoushaService = new ShiyoushaJouhouService();

    /**
     * 요청 처리 메서드로, handleYakushokuRequest 메서드를 호출하여 요청을 처리합니다.
     * リクエスト処理メソッドで、handleYakushokuRequestメソッドを呼び出してリクエストを処理します。
     *
     * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
     * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
     * @return 처리 결과 문자열 / 処理結果の文字列
     * @throws Exception 처리 중 발생할 수 있는 예외 / 処理中に発生する可能性のある例外
     */
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleYakushokuRequest(request, response);
    }

    /**
     * 직책 관련 요청을 처리하는 메서드로, JSON 데이터를 파싱하여 직책 추가 작업을 수행합니다.
     * 役職関連リクエストを処理するメソッドで、JSONデータを解析して役職追加作業を実行します。
     *
     * @param request  클라이언트로부터의 HTTP 요청 객체 / クライアントからのHTTPリクエストオブジェクト
     * @param response 클라이언트로 보낼 HTTP 응답 객체 / クライアントに送るHTTPレスポンスオブジェクト
     * @return null (직접 JSON 응답을 작성) / null（直接JSON応答を作成）
     * @throws IOException 입력/출력 중 발생할 수 있는 예외 / 入出力中に発生する可能性のある例外
     */
    public String handleYakushokuRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action"); // 요청된 작업(action) 파라미터를 가져옴 / リクエストされたアクションパラメータを取得
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try {
            // JSON 데이터 파싱 / JSONデータの解析
            StringBuilder jsonBuffer = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    jsonBuffer.append(line); // 요청의 JSON 데이터를 읽어와 버퍼에 추가 / リクエストのJSONデータを読み込みバッファに追加
                }
            }

            System.out.println("Received JSON Data: " + jsonBuffer.toString()); // 받은 JSON 데이터 출력 / 受け取ったJSONデータを出力

            // JSON 데이터가 비어있는지 확인 / JSONデータが空であるか確認
            if (jsonBuffer.length() == 0) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "데이터가 없습니다。/ データがありません。");
                response.getWriter().write(jsonResponse.toString());
                return null;
            }

            JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
            String yakushokuName = jsonObject.optString("yakushoku_mei", null); // 직책 이름을 가져옴 / 役職名を取得

            // 직책 이름이 유효한지 확인 / 役職名が有効か確認
            if (yakushokuName == null || yakushokuName.trim().isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "직책 이름을 입력해 주세요。/ 役職名を入力してください。");
            } else {
                Yakushoku yakushoku = new Yakushoku();
                yakushoku.setYakushoku_mei(yakushokuName);

                boolean isSuccess;
                switch (action) {
                    case "addYakushoku": // 직책 추가 작업 / 役職追加作業
                        isSuccess = shiyoushaService.addYakushoku(yakushoku);
                        jsonResponse.put("success", isSuccess);
                        jsonResponse.put("message", isSuccess ? "직책이 추가되었습니다。" : "직책 추가에 실패했습니다。");
                        jsonResponse.put("message", isSuccess ? "役職が追加されました。" : "役職の追加に失敗しました。");
                        break;

                    default: // 알 수 없는 요청 / 不明なリクエスト
                        jsonResponse.put("success", false);
                        jsonResponse.put("message", "알 수 없는 요청입니다。/ 不明なリクエストです。");
                        break;
                }
            }
        } catch (Exception e) {
            // 예외 처리 / 例外処理
            jsonResponse.put("success", false);
            jsonResponse.put("message", "처리 중 오류 발생: " + e.getMessage() + " / 処理中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }

        // 최종 JSON 응답 전송 / 最終JSON応答を送信
        response.getWriter().write(jsonResponse.toString());
        return null;
    }
}
