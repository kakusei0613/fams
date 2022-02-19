package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Permission;
import xyz.kakusei.fams.mapper.IPermissionMapper;
import xyz.kakusei.fams.service.IPermissionService;
import xyz.kakusei.fams.util.RequiredPermission;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper permissionMapper;

    @Autowired
    private ApplicationContext ctx;

    @Override
    public List<Permission> queryByRoleId(Integer id) {
        return permissionMapper.queryByRoleId(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }

    @Override
    public void reload() {
        //(解决方法)重新加载之前先查出数据库中的所有权限表达式
        List<String> expressions = permissionMapper.queryAllPermissionExpression();
        List<String> insertedExpressions = new ArrayList<String>();

        //2.获取容器中所有的controller类的bean对象(因为权限注解只存在于controller类的方法上,其他类中没有不需要获取)
        //这里我们根据 控制器 类上 独有的 @Controller 注解来获取这一类对象.
        //因为是通过注解(annotation)获得的是多个controller的bean对象,所以我们这里调用的方法是getBeansWithAnnotation
        //这里传递的参数是controller注解,注解本身也是一种特殊的类(可以参考我们定义的权限注解)
        //打印可知这个Map集合中 key:bean名称  value:controller对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        //System.out.println(beansWithAnnotation);
        //获取mapper集合中的所有value,即所有的bean对象
        Collection<Object> controllers = beansWithAnnotation.values();
        //3.遍历controllers , 获取每一个controller类中的所有方法
        for (Object controller : controllers) {
            //这里的controller是bean对象,我们通过反射创建对象,然后获取当前controller类中的方法
            //因为要获取当前类中的方法,而不包括父类中的方法,因此这里不能直接调用getMethod()方法,所以我们调用的是getDeclaredMethods()
            Method[] methods = controller.getClass().getDeclaredMethods();
            //System.out.println(Arrays.asList(methods));
            //4.获取方法上贴着的注解(每个方法上可能贴了多个注解,我们要找到我们需要的权限注解)
            for (Method method : methods) {
                //可能不是每个方法上都贴了注解(不是每个方法都需要权限),因此这里要判断是否存在权限注解RequiredPermission
                if (method.isAnnotationPresent(RequiredPermission.class)) {
                    //如果存在,则获取注解RequiredPermission
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    //5.获取权限注解括号内的传递的参数
                    String[] value = annotation.value();
                    //权限注解是我们设计的,因此注解中的value值对应的权限名称/表达式我们是知道的,因此直接取出即可
                    String name = value[0]; //权限名称
                    String expression = value[1];   //权限表达式


                    //(解决方法)判断当前表达式是否存在于数据库中查出来的权限表达式中,不存在才进行保存操作
                    if (!expressions.contains(expression) && !insertedExpressions.contains(expression)) {
                        //6.将获取到的参数保存到数据库中
                        //将取出的权限名称/权限表达式存入数据库中
                        insertedExpressions.add(expression);
                        Permission permission = new Permission();
                        permission.setName(name);
                        permission.setExpression(expression);
                        permissionMapper.insert(permission);
                    }
                }
            }
        }
    }
}
