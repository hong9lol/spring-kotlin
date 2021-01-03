package com.hong9lol.todo.config

import com.hong9lol.todo.Database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean(initMethod = "init")
    fun todoDataBase(): TodoDataBase {

        return TodoDataBase()
    }
}