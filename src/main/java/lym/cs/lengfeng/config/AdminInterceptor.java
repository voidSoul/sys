package lym.cs.lengfeng.config;

import lym.cs.lengfeng.model.User;
import lym.cs.lengfeng.service.UserService;
import lym.cs.lengfeng.utils.Constants;
import lym.cs.lengfeng.utils.JsonUtil;
import lym.cs.lengfeng.utils.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_KEY);
        if (user == null || userService.findById(user.getId()).getRole() != 1) {
            writeError(response);
            return false;
        }
        return true;
    }

    private void writeError(HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        pw.write(JsonUtil.toJson(ResponseFactory.fail(Constants.UN_AUTHORIZED, Constants.NOT_AUTHORIZED)));
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
