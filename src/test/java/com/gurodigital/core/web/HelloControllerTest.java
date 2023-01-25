package com.gurodigital.core.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킨다.
        // 여기서는 SpringRunner 라는 스프링 시행자를 사용한다.
        // 즉, SprigBoot 테스트와 JUnit 사이에 연결자 역할을 한다.

@WebMvcTest(controllers = HelloController.class)// 스프링 테스트 어노테이션 중 Web(Spring MVC) 에 집중할
        // 수 있는 어노테이션 입니다.
        // 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있습니다.
        // 단 @Service, @Component, @Repository 등은 사용할 수 없습니다.

class HelloControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void hello_return()throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }
}