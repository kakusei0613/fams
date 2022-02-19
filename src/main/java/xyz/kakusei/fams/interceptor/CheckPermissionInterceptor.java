package xyz.kakusei.fams.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.kakusei.fams.controller.EmployeeController;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.util.RequiredPermission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

public class CheckPermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        Employee user = (Employee) httpSession.getAttribute("USER_IN_SESSION");
//        是管理员，放行
        if (user.getAdmin() == true) {
            return true;
        }
//        方法上没有注释，放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (!method.isAnnotationPresent(RequiredPermission.class)) {
            return true;
        }
//        编辑自己的信息，放行
        Class employeeControllerClass = EmployeeController.class;
        Method myInfo = employeeControllerClass.getMethod("employee");
        if (method == myInfo) {
            Long id = Long.parseLong(request.getParameter("id"));
            if (user.getId() == id) {
                return true;
            }
        }

        RequiredPermission requiredPermission = method.getAnnotation(RequiredPermission.class);
        String expression = requiredPermission.value()[1];
        List<String> expressions = (List<String>) httpSession.getAttribute("EXPRESSION_IN_SESSION");
        if (expressions.contains(expression)) {
            return true;
        }
        response.sendRedirect("/needPermission");
        return false;
    }
}
