package com.gurodigital.core.web;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void helloDto_return() throws Exception {
        //given
        String name = "hello";
        int amount = 1000;

        //when
        //then
        mvc.perform(
                get("/hello/dto")
                .param("name", name) // API 테스트 할 때 사용될 요청 파라미터를 설정 합니다.
                                            // 단, 값은 String만 허용 합니다.
                .param("amount", String.valueOf(amount))) // 그래서 숫자/날짜 등의 데이터를 등록할 때는 문자열로 변경해야만 합니다
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath : json 응답값을 필드별로 검증할 수 있는 메소드 입니다.
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명을 명시 합니다.
                                                    // 여기서는 name과 amount를 검증하니 $.name , $.amount로 검증 합니다.

        // is
        // hamcrest란?
        //hamcrest는 JUnit에 사용되는 Matcher 라이브러리이다.
        //테스트 표현식을 작성할 때 좀 더 문맥적으로 자연스럽고 우아한 문장을 만들 수 있도록 도와준다.
        //예를들어 assertEquals(expedted, actual)이라는 코드보다 assertThat(expected, is(actual))라는 코드가 훨씬 보기 쉬울 것이다.
        //* Mactcher 라이브러리 : 필터나 검색등을 위해 값을 비교할 때 좀 더 편리하게 사용하도록 도와주는 라이브러리
        // https://www.crocus.co.kr/1658

    }
}