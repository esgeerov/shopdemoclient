package com.samir.shopdemoclient.controller;

import com.samir.shopdemoclient.dto.request.ReqLogin;
import com.samir.shopdemoclient.dto.response.ResProduct;
import com.samir.shopdemoclient.dto.response.RespCategory;
import com.samir.shopdemoclient.dto.response.RespUser;
import com.samir.shopdemoclient.dto.response.Response;
import com.samir.shopdemoclient.service.CategoryService;
import com.samir.shopdemoclient.service.LoginService;
import com.samir.shopdemoclient.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final LoginService loginService;

    @GetMapping({"/", "/loginView"})
    public ModelAndView loginView() {
        ModelAndView model=new ModelAndView("/login");
        return model;
    }
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView login(ReqLogin reqLogin, HttpServletRequest request) {
        RedirectView redirectView = null;
        Response<RespUser> response = loginService.login(reqLogin);
        if (response.getRespStatus().getCode() == 1) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", response.getT());
            redirectView = new RedirectView("/client/GetCategoryList");
        } else {
            redirectView = new RedirectView("/client/login");
        }
        return redirectView;
    }

    @GetMapping("/GetCategoryList")
    public ModelAndView getCategoryList() {
        ModelAndView model = new ModelAndView("category");
        try {
            Response<List<RespCategory>> response = categoryService.getCategoryList();
            if (response.getRespStatus().getCode() == 1) {
                model.addObject("result", response.getT());
            } else {
                model.addObject("msg", response.getRespStatus().getMessage());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return model;
    }

    @GetMapping("/GetProductList/{id}")
    public ModelAndView getProductList(@PathVariable(value = "id") Long categoryId) {
        ModelAndView model = new ModelAndView("product");
        try {
            Response<List<ResProduct>> response = productService.getProductList(categoryId);
            if (response.getRespStatus().getCode() == 1) {
                model.addObject("result", response.getT());

            } else {
                model.addObject("msg", response.getRespStatus().getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
}
