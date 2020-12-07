package com.beijing.vagrancy.poistudy.controller;

import com.beijing.vagrancy.poistudy.service.PoiService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuhai on 2020/12/4 15:44
 */
@RestController
public class PoiController1 {
    @Autowired
    PoiService1 PoiService1;
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        PoiService1.exportExcel(response);
    }

}
