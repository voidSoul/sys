package lym.cs.lengfeng.config;

import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.JsonUtil;
import lym.cs.lengfeng.utils.ResponseFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute(Constants.SESSION_KEY) != null) {
            return true;
        }

        writeError(response);
        return false;
    }

    private void writeError(HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        pw.write(JsonUtil.toJson(ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.UN_LOGIN)));
        pw.flush();
        pw.close();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
