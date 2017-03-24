package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import bean.User;

public class LoginService extends HttpServlet {

  /**
   * Constructor of the object.
   */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);//如果提交方式为GET，跳转到dopost执行
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");//获取操作
        System.out.print(action);


        //用户登陆
            this.login(request, response);
            System.out.println("1");
        
    }
//用户登录

    private void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        User f = new User();//这里我写了一个实体类，可以不用实体类，直接付给调用的函数参数即可
        request.setCharacterEncoding("UTF-8");//防止乱码
        f.setUsername(request.getParameter("username"));
        f.setPassword(request.getParameter("password"));
        //System.out.println("UserName:"+username+";"+"PassWord:"+password);
        boolean r = UserDAO.login(f);//userdao是UserDao类的实例，在servlet的一开始生成
        //r是返回的用户id，根据你自己生成的id，修改下方if判断条件
        boolean flag = false;//默认登录不成功
        if(r){//用户登陆成功
            HttpSession session = request.getSession();
            session.setAttribute("username", f.getUsername());
            session.setAttribute("id", r);
            //session.setAttribute("identify", f.getId());
            flag = true;
        }else{
            flag = false;
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(flag);//返回登录信息
        out.flush();
        out.close();
    }

}

