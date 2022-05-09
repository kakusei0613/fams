package xyz.kakusei.fams.util;


import org.springframework.http.HttpRequest;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Permission;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/ureport/designer")
public class UreportFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Employee loginUser = (Employee) httpServletRequest.getSession().getAttribute("USER_IN_SESSION");
        if (loginUser == null) {
            httpServletResponse.sendRedirect("/login");
            return;
        }
        List<String> expressions = (List<String>) httpServletRequest.getSession().getAttribute("EXPRESSION_IN_SESSION");
        if (!loginUser.getAdmin() && expressions.contains("report:used")) {
            httpServletResponse.sendRedirect("/needPermission");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
