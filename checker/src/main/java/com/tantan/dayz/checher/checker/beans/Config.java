package com.tantan.dayz.checher.checker.beans;

import com.tantan.dayz.checher.checker.Const;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Configuration
public class Config {
    @Bean
    @PostConstruct
    @Scope("singleton")
    public JDA bot(){
        return JDABuilder.createDefault(Const.TOKEN)
                .setActivity(Activity.playing("tantan"))
                .build();
    }
}
