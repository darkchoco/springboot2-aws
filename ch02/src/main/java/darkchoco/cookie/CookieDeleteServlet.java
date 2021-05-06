package darkchoco.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/deletecookie")
public class CookieDeleteServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>cookie 삭제</title></head><body>");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cloudscape")) {
                    // Cookie 자체를 삭제하는 API는 없기 때문에 유효시간을 0으로 설정해서 Cookie 값을 무효화한다.
                    Cookie deletedCookie = new Cookie("cloudscape", "");
                    deletedCookie.setMaxAge(0);
                    res.addCookie(deletedCookie);
                }
            }
        }
        out.println("<a href='/readcookie'>Read cookie</a></body></html>");
    }
}
