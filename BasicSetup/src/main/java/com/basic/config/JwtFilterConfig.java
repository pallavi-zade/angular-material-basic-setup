package com.basic.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import com.basic.constant.SpringErrorCodes;
import com.basic.exception.BasicException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilterConfig extends GenericFilterBean {
	String key="boot(Spring@35123";
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException{
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (authHeader == null || !authHeader.startsWith("Bearer")) {
				throw new BasicException(SpringErrorCodes.SERVER_ERROR_CODE, "Invalid token");
			}
			
			final String token = authHeader.substring(6);
			try {
				final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				} catch (final Exception e) {
				throw new BasicException(SpringErrorCodes.SERVER_ERROR_CODE, "Invalid token");
			}
			chain.doFilter(req, res);
		}
	}
}