package com.hong9lol.todo.repository

import com.hong9lol.todo.Database.Todo
import com.hong9lol.todo.config.AppConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "TEST"
            this.schedule = LocalDateTime.now()
        }
        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("TEST", result?.description)
    }

    @Test
    fun saveAllTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "TEST"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "TEST"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정"
                this.description = "TEST"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "TEST1"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "TEST2"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "TEST3"
                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)
        val result = todoRepositoryImpl.findOne(2)
        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun updateTest() {
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "TEST"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "업데이트 테스트 일정"
            this.description = "업데이트 TEST"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(newTodo)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(insertTodo?.index, result?.index)
        Assertions.assertEquals("업데이트 테스트 일정", result?.title)
        Assertions.assertEquals("업데이트 TEST", result?.description)
    }

    @Test
    fun deleteTest() {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트 일정1"
                this.description = "TEST1"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정2"
                this.description = "TEST2"
                this.schedule = LocalDateTime.now()

            },
            Todo().apply {
                this.title = "테스트 일정3"
                this.description = "TEST3"
                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)
        val result = todoRepositoryImpl.delete(2)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(true, result)
    }
}