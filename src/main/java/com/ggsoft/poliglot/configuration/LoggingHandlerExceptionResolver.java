package com.ggsoft.poliglot.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



public class LoggingHandlerExceptionResolver 
implements HandlerExceptionResolver, Ordered {
    public int getOrder() {
        return Integer.MIN_VALUE; // we're first in line, yay!
    }

    public ModelAndView resolveException(
        HttpServletRequest aReq, HttpServletResponse aRes,
        Object aHandler, Exception anExc
    ) {
        anExc.printStackTrace(); // again, you can do better than this ;)
        return null; // trigger other HandlerExceptionResolver's
    }
} 