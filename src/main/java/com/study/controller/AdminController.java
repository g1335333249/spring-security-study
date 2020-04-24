package com.study.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guanpeng
 * @description 管理员控制器
 * @date 2020/4/23 10:26 上午
 * @since
 */
@RestController
@RequestMapping("admin")
@PreAuthorize(value = "hasAnyRole('ADMIN')")
public class AdminController {

    @GetMapping("echo")
    public String echo(String echo) {
        return echo;
    }
}
