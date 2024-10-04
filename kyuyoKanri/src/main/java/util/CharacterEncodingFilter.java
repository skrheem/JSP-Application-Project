package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

// 인코딩 방식을 UTF-8로 바꿔주는 필터!!!
public class CharacterEncodingFilter extends HttpFilter implements Filter {

	private String encoding;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("ebcoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
