package com.hong9lol.Todo.controller.api.todo

import com.hong9lol.Todo.model.http.TodoDto
import com.hong9lol.Todo.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

//how to do redirection: ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "URL_LINK").build()
@RestController
@RequestMapping("/api/todo")
class TodoApiController(val todoService: TodoService) {
    // R
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any?> {
        if (index != null) {
            return todoService.read(index)?.let {
                ResponseEntity.ok(it)
            }?: kotlin.run {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found")
            }
        }

        return todoService.readAll().let {
                ResponseEntity.ok(it)
        }
    }

    @GetMapping(path = ["all"])
    fun readAll(): ResponseEntity<Any?> {
        return todoService.readAll().let {
            ResponseEntity.ok(it)
        }
    }

    // C
    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    // U
    @PutMapping(path = [""]) // create 201, update = 200
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.update(todoDto)
    }

    // D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any?> {
        if(!todoService.delete(_index)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found")
        }
        return ResponseEntity.ok().build()
    }
}