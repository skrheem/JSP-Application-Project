package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {

	// MVC 패턴1에서는 JSP가 컨트롤러의 역할을 맡았지만
	// MVC 패턴2에서는 서블릿이 컨트롤러의 역할을 맡는다.
	// 클라이언트의 요청을 컨트롤러가 어떻게 파악할 것인지
	// 이동 경로를 어떻게 잡을 것인지, 무엇을 실행할지 처리하는 프로그램
	
	// Map<키, 값> : 키로 String형 데이터를, 값으로 CommandHandler 타입의 인스턴스를 받는 맵
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	// 서블릿이 초기화됐을 때 한번만 실행되는 메서드
	public void init() throws ServletException {
		
		// web.xml에 정의해둔 configFile이라는 이름의 파라미터를 가져온다.
		// 이 파라미터에는 파일의 주소(/WEB-INF/commandHandler.properties)가 담겨있다.
		String configFile = getInitParameter("configFile");
		
		// Properties는 (키 : String, 값 : String) 형태로 데이터를 저장하는 컬렉션 클래스이다.
		Properties prop = new Properties();
		
		// /WEB-INF 경로에 있는 commandHandler.properties 파일의 실제 경로를 가져옴
		// getRealPath() : 가상 경로를 절대 경로로 변환하는 메서드
		
		// 주어진 가상 경로 : configFile에 담긴 가상 경로를 서버 내 실제 경로로 변환한다.
		
		// configFile : /WEB-INF/commandHandler.properties
		// configFilePath : C:\java_work\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\240919-2&&20240924\WEB-INF\commandHandler.properties
		String configFilePath = getServletContext().getRealPath(configFile);
		
		// FileReader : 텍스트 파일을 읽을 때 사용하는 클래스
		// 파일을 한 글자씩 바이트 단위로 읽는다.
		// configFilePath의 파일을 읽어들이는 FileReader 클래스의 인스턴스를 생성
		try (FileReader fis = new FileReader(configFilePath)) {
			// Properties 클래스의 load 메서드
			// FileReader 인스턴스를 통해 파일을 읽어들여
			// <String(키), String(값)> 형태로 prop에 저장
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		// Iterator 클래스는 Java Collections Framework에서 컬렉션 클래스(ArrayList, HashSet, HashMap, Properties 등)의
		// 요소를 반복(iterate)하는 표준 인터페이스를 제공한다.
		// hasNext() 등의 메서드를 제공한다.
		// prop.keySet() : prop에 저장된 key 값들을 Set으로 반환한다.
		// .iterator() : Set 객체에 대한 iterator를 반환한다.
		// 즉, Iterator keyIter = prop.keySet().iterator()는
		// prop의 key 값을 Set 형태로 반환하고 이를 순회할 iterator를 반환한다.
		Iterator keyIter = prop.keySet().iterator();
		
		// key 값이 남아있다면 true, 없다면 false 반환
		while (keyIter.hasNext()) {
			// Iterator로 Set을 순회하며 그 값을 읽어 String 형으로 변환하여 command 변수에 저장
			String command = (String) keyIter.next();
			System.out.println("!!!!!!" + command);
			// command 변수에 담긴 key에 해당하는 값을 읽어들여 handlerClassName 변수에 저장
			String handlerClassName = prop.getProperty(command);
			try {
				// Class.forName(String className) : 주어진 클래스 이름을 사용하여 해당 클래스를 동적으로 할당한다.
				// 클래스가 메모리에 할당되고, 해당 클래스의 Class 객체를 반환한다.
				// handlerClassName에는 mvc.hello.HelloHandler가 있으며
				// HelloHandler의 Class 객체를 반환한다.
				Class<?> handlerClass = Class.forName(handlerClassName);
				
				// HelloHandler의 Class 객체로부터 HelloHandler의 인스턴스를 생성하고,
				// 그 인스턴스를 CommandHandler 타입으로 형변환하여 handlerInstance 참조 변수에 저장한다.
				// HelloHandler는 CommandHandler 인터페이스를 구현하고 있기 때문에 가능한 일.
				// 인터페이스의 다형성 
				// 1 : 관계가 없는 클래스끼리 연결 지을 수 있다
				// 2 : 인터페이스를 구현하는 클래스를 통해 인터페이스의 인스턴스를 만들 수 있다.
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				// 위에서 만든 commandHandlerMap에 key 값이 담긴 command 변수와 CommandHandler의 인스턴스를 저장한다.
				commandHandlerMap.put(command, handlerInstance);
				// 따라서 commandHandlerMap에는
				// key : hello
				// value : handlerInstance가 담긴다.
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 영역에 있는 cmd라는 이름의 파라미터 값을 가져와 command 변수에 저장
		//String command = request.getParameter("cmd");
		
		String command = request.getRequestURI();
		// request.getContextPath() : 웹 프로젝트의 경로를 가져온다.
		// http://localhost:8080/project/list.jsp
		// return : /project
		// indexOf(String) : 주어진 문자열의 위치를 찾아 그 자릿수를 정수형으로 반환하는 메서드
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());
		}
		
		// command와 동일한 값을 지니는 key의 값을 commandHandlerMap으로부터 가져온다.
		// commandHandlerMap에는 hello(key)에 해당하는 CommandHandler 인스턴스가 담겨있다.
		CommandHandler handler = commandHandlerMap.get(command);
		
		// 인스턴스가 없다면
		if(handler==null) {
			// CommandHandler를 구현하고 있는 NullHandler 인스턴스를 저장
			handler = new NullHandler();
		}
		
		
		String viewPage = null;
		try {
			// HelloHandler에는 process() 메서드가 있는데
			// 이 메서드의 역할은 request 영역의 hello 속성에 값을 설정하고
			// hello.jsp의 경로를 문자열 값으로 반환한다.
			// viewPage에는 "/WEB-INF/view/hello.jsp"가 저장된다.
			viewPage = handler.process(request, response);
			System.out.println(viewPage);
		} catch(Exception e) {
			throw new ServletException(e);
		}
		if(viewPage!=null) {
			// RequesetDispatcher 클래스는 HTTP 요청을 다른 서블릿이나 jsp로 포워딩시키는 메서드를 제공하는 클래스이다.
			// request.getRequestDispatcher(viewPage) : viewPage로 request를 포워딩시키는 RequestDispatcher 인스턴스를 생성
			System.out.println(viewPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			
			// viewPage에 요청과 응답을 포워딩시킨다.
			dispatcher.forward(request, response);
		}
	}
}
