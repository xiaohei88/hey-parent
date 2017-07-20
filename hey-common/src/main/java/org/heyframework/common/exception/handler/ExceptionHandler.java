package org.heyframework.common.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.heyframework.common.util.JsonUtils;
import org.heyframework.common.util.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionHandler extends SimpleMappingExceptionResolver {

    private String errorPage;

    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {

        // 1、根据不同的异常获取不同的消息
        Map<String, Object> map = _getMessage(exception);

        String accept = request.getHeader("Accept");
        if (!StringUtils.isEmpty(accept) && (!accept.startsWith("text/html") || "ios".equals(accept))) {
            // 2、处理ajax请求
            _out(response, map);
            return null;
        } else {
            String uri = request.getRequestURI();
            ModelAndView mv = new ModelAndView();
            mv.addAllObjects(map);
            /** .h=.html;.j=*.jsp;.f=.form **/
            if (accept.startsWith("text/html, */*") || uri.endsWith(".h") || uri.endsWith("*.j") || uri.endsWith(".f")) {
                // 3、处理页面请求
                mv.setViewName(errorPage);
            } else {
                // 4、处理form请求
                mv.setViewName("errorJson");
            }
            return mv;
        }
    }

    /**
     * 根据不同的异常获取不同的信息
     *
     * @param exception
     * @return
     */
    private Map<String, Object> _getMessage(Exception exception) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        int code = 0;
        String msg = "UNKNOWN ERROR.";
        if (exception instanceof ClassNotFoundException) {
            msg = "can't find this class " + exception.getMessage() + ".";
        } else if (exception instanceof NullPointerException) {
            msg = "the method has null pointer.";
        } else if (exception instanceof DataIntegrityViolationException) {
            msg = exception.getCause().getMessage();
        } else {
            msg = exception.getMessage();
        }

        if (!StringUtils.isEmpty(msg)) {
            // 去除换行的问题
            msg = msg.replaceAll("\r|\n", "");
            // 去除双引号的问题
            msg = msg.replace("\"", "\\\"");
        }
        map.put("code", code);
        map.put("msg", "reason:" + msg);
        return map;
    }

    /**
     * 错误信息输出
     *
     * @param response
     * @param map
     */
    private void _out(HttpServletResponse response, Map<String, Object> map) {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            JsonUtils.writeValue(pw, map);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}
