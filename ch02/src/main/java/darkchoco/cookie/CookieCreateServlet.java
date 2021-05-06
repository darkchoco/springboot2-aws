package darkchoco.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/newcookie")
public class CookieCreateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>cookie 예제</title></head><body>");
//        Cookie cookie = new Cookie("cloudscape", "books");
        Cookie cookie = new Cookie("cloudscape", URLEncoder.encode("구름풍경", "UTF-8"));
        cookie.setMaxAge(3600);
        resp.addCookie(cookie);
        out.println("<a href='/readcookie'>Read cookie</a></body></html>");
    }
}
