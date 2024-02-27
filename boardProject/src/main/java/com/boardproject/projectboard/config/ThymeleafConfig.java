package com.boardproject.projectboard.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean //bean을 추가하면 autoconfig이 잡힐테고, 그때 작동하는 defaultTemplateResolver에 기능을 추가한 것이다
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
//         application에 추가해줘야한다

        return defaultTemplateResolver; //"defaultTemplateResolver에 원래 가지고 있던 기능 추가해서 리턴
    }


    @RequiredArgsConstructor
    @Getter
    @ConfigurationProperties("spring.thymeleaf3")
    public static class Thymeleaf3Properties {

        private final boolean decoupledLogic;
    }

}