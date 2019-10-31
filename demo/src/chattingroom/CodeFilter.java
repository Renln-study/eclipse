package chattingroom;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//采用注解方式配置URL，此处就不必再在web.xml文件中配置。如果两者均配置的话，系统将以web.xml文件中配置信息为准
@WebFilter(filterName="CodeFilter",urlPatterns={"/*"})      

public class CodeFilter implements Filter{
        
    
         @Override
         public void destroy() {
             System.out.println("destroy---CodeFilter");                          
         }
     
         @Override
         public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
                 throws IOException, ServletException {
             System.out.println("start----doFilter--CodeFilter");
     
             HttpServletRequest request =(HttpServletRequest) arg0;
             HttpServletResponse response =(HttpServletResponse) arg1;
             //以上是强制内型转换，使用request对象和response对象
             
             request.setCharacterEncoding("utf-8");          //设置过滤页面提取数据的编码
             response.setContentType("text/html;charset=utf-8");  //设置过滤页面显示数据的编码
             
             arg2.doFilter(arg0, arg1);//在页面跳转之前执行此语句前面的代码，执行完页面的代码之后，在执行后面的语句
             System.out.println("第一个字符过滤器");
             System.out.println("end------doFilter--CodeFilter");    
             }
     
         @Override
         public void init(FilterConfig filterConfig) throws ServletException {
             System.out.println("init----CodeFilter");
         }

}