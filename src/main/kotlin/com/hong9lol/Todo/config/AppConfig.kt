package com.hong9lol.Todo.config

import com.hong9lol.Todo.Database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean(initMethod = "init")
    fun todoDataBase(): TodoDataBase {

        return TodoDataBase()
    }
}