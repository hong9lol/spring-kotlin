package com.hong9lol.Todo.repository

import com.hong9lol.Todo.Database.Todo

interface TodoRepository {

    fun save(todo: Todo): Todo?
    fun saveAll(todoList: MutableList<Todo>): Boolean

//    fun update(todo: Todo): Todo
    fun delete(index: Int): Boolean

    fun findOne(index: Int): Todo?
    fun findAll(): MutableList<Todo>
}