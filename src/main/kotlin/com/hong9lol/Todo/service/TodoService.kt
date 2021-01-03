package com.hong9lol.Todo.service

import com.hong9lol.Todo.Database.Todo
import com.hong9lol.Todo.Database.convertTodo
import com.hong9lol.Todo.model.http.TodoDto
import com.hong9lol.Todo.model.http.convertTodoDto
import com.hong9lol.Todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

/* when TodoDto <-> Todo converting find abd use below things
 * model mapper
 * kotlin reflection
 */

@Service
class TodoService(val todoRepositoryImpl: TodoRepositoryImpl) {
    // C
    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // R
    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll().map {
          TodoDto().convertTodoDto(it)
        }.toMutableList()
    }

    // U
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}