package com.example.hfb.security;

import com.example.hfb.model.ResponseData;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {


        }
        // Redirect page case access denied
        //response.sendRedirect(request.getContextPath() + "/access-denied");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(new Gson().toJson(new ResponseData(HttpStatus.FORBIDDEN.value(), "access denied", "")));

    }
}
