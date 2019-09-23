package superbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import superbook.dao.BookDao;


public class test extends HttpServlet{ //继承了HttpServlet

    public void doPost(HttpServletRequest request,
                        HttpServletResponse response) 
                            throws IOException, ServletException{ 
        
        String c = request.getParameter("color"); //获取请求中的color属性
        BookDao bd = new BookDao();
        List result = bd.selectByTitle(c);

        response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("Beer Selection Advice <br>");
        
        Iterator it = result.iterator();
        while(it.hasNext()) {
           out.print("<br> try: " + it.next());
        }
   
    }
}
