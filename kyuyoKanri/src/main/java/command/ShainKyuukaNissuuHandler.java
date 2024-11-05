package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import kihonkankyousettei.model.ShainKyuukaNissuu;
import mvc.command.CommandHandler;
import service.ShainKyuukaNissuuService;
import jdbc.connection.ConnectionProvider; // Connection 관리를 위한 ConnectionProvider import

/**
 * ShainKyuukaNissuuHandler는 사원별 휴가일수 관련 요청을 처리하는 핸들러 클래스입니다.
 * ShainKyuukaNissuuHandlerは社員別休暇日数に関するリクエストを処理するハンドラークラスです。
 */
public class ShainKyuukaNissuuHandler implements CommandHandler {

    private ShainKyuukaNissuuService service = new ShainKyuukaNissuuService();

    private static final String ACTION_REGISTER = "register";
    private static final String ACTION_GET = "getKyuukaNissuu";
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_DELETE = "delete";
    private static final String VIEW_PAGE = "/WEB-INF/view/ShainKyuukaNissuu.jsp"; // JSP 경로 설정

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            return VIEW_PAGE; // action이 없는 경우 VIEW_PAGE로 이동
        }

        if (request.getMethod().equalsIgnoreCase("GET")) {
            return processForm(request, response, action);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(request, response, action);
        } else {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return VIEW_PAGE;
        }
    }

    private String processForm(HttpServletRequest request, HttpServletResponse response, String action)
            throws IOException {
        response.setContentType("application/json; charset=UTF-8");

        try (Connection conn = ConnectionProvider.getConnection()) {
            if (ACTION_GET.equals(action)) {
                return getKyuukaNissuu(request, response, conn);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                return VIEW_PAGE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return VIEW_PAGE;
        }
    }

    private String processSubmit(HttpServletRequest request, HttpServletResponse response, String action)
            throws IOException {
        response.setContentType("application/json; charset=UTF-8");

        try (Connection conn = ConnectionProvider.getConnection()) {
            switch (action) {
            case ACTION_REGISTER:
                return registerKyuukaNissuu(request, response, conn);
            case ACTION_UPDATE:
                return updateKyuukaNissuu(request, response, conn);
            case ACTION_DELETE:
                return deleteKyuukaNissuu(request, response, conn);
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                return VIEW_PAGE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return VIEW_PAGE;
        }
    }

    private String registerKyuukaNissuu(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws SQLException, IOException {
        int shainId = Integer.parseInt(request.getParameter("shainId"));
        int kyuukaKoumokuId = Integer.parseInt(request.getParameter("kyuukaKoumokuId"));
        int kyuukaNissuu = Integer.parseInt(request.getParameter("kyuukaNissuu"));

        ShainKyuukaNissuu shainKyuukaNissuu = new ShainKyuukaNissuu(shainId, kyuukaKoumokuId, kyuukaNissuu);

        boolean result = service.registerShainKyuukaNissuu(conn, shainKyuukaNissuu);
        JSONObject jsonResponse = new JSONObject().put("success", result);
        sendJsonResponse(response, jsonResponse);
        return null;
    }

    private String getKyuukaNissuu(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws SQLException, IOException {
        int shainId = Integer.parseInt(request.getParameter("shainId"));

        ArrayList<ShainKyuukaNissuu> list = service.getKyuukaNissuuByShainId(conn, shainId);
        JSONArray jsonArray = new JSONArray(list);
        sendJsonResponse(response, jsonArray);
        return null;
    }

    private String updateKyuukaNissuu(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws SQLException, IOException {
        int shainId = Integer.parseInt(request.getParameter("shainId"));
        int kyuukaKoumokuId = Integer.parseInt(request.getParameter("kyuukaKoumokuId"));
        int kyuukaNissuu = Integer.parseInt(request.getParameter("kyuukaNissuu"));

        ShainKyuukaNissuu shainKyuukaNissuu = new ShainKyuukaNissuu(shainId, kyuukaKoumokuId, kyuukaNissuu);

        boolean result = service.updateShainKyuukaNissuu(conn, shainKyuukaNissuu);
        JSONObject jsonResponse = new JSONObject().put("success", result);
        sendJsonResponse(response, jsonResponse);
        return null;
    }

    private String deleteKyuukaNissuu(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws SQLException, IOException {
        int shainId = Integer.parseInt(request.getParameter("shainId"));
        int kyuukaKoumokuId = Integer.parseInt(request.getParameter("kyuukaKoumokuId"));

        boolean result = service.deleteShainKyuukaNissuu(conn, shainId, kyuukaKoumokuId);
        JSONObject jsonResponse = new JSONObject().put("success", result);
        sendJsonResponse(response, jsonResponse);
        return null;
    }

    private void sendJsonResponse(HttpServletResponse response, Object jsonResponse) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
