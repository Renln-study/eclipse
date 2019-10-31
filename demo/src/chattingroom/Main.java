package chattingroom;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Main extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public Main() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

  
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         doPost(request,response);
    }

   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.setContentType("text/html;charset=utf-8"); 
        //�˳�ע������Ϊʹ��CodeFilter���������Servlet��ת������
        //request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String checkBox = request.getParameter("save_password");

        System.out.println("userName:"+userName+"\n"+"passWord:"+passWord);
        request.getSession().setAttribute("nameSession", userName);   //���û�������session��
        String[] name_one = {"1","2","3","4","5"};
        String[] pwd_one = {"1","2","3","4","5"};
        String name_two = "";
        String pwd_two = "";
        boolean login_test = false;
        for(int i=0;i<5;i++){
            name_two = name_one[i];
            pwd_two = pwd_one[i];
            if(userName.equals(name_two) && passWord.equals(pwd_two))
                login_test = true;                           
        }
        
        if(login_test) {
            if ("save".equals(checkBox)) {
                //Cookie��ȡʱ��URLEncoder.encode���б���(PS:��ȡʱURLDecoder.decode���н���)
                String name = URLEncoder.encode(userName,"UTF-8");
                //��������Cookie����
                Cookie nameCookie = new Cookie("username", name);
                //����Cookie����Ч��Ϊ3��
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                String pwd = URLEncoder.encode(passWord,"UTF-8");
                Cookie pwdCookie = new Cookie("password", pwd);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
             }
             request.getRequestDispatcher("welcome.jsp").forward(request, response);           
        }   
        else{
             response.sendRedirect("loginFail.jsp");      
              // request.getRequestDispatcher("loginFail.jsp").forward(request, response);             
        }
            
       
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}