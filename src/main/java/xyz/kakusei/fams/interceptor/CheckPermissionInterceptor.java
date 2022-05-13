package xyz.kakusei.fams.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        Employee user = (Employee) httpSession.getAttribute("USER_IN_SESSION");
//          当前访问的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.info("User:" + user.getName() +  " IP:" + request.getRemoteAddr() + " execute:" + method.getName());

//        是管理员，放行
        if (user.getAdmin() == true) {
            return true;
        }
//        方法上没有注释，放行
        if (!method.isAnnotationPresent(RequiredPermission.class)) {
            return true;
        }
//        编辑自己的信息，放行
        Class employeeControllerClass = EmployeeController.class;
        Method myInfo = employeeControllerClass.getMethod("saveOrUpdate", Employee.class, Byte[].class);
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
