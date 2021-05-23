package com.ascm.tour.controller

import com.ascm.tour.entities.Promocao
import com.ascm.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/promocoes")
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun findId(@PathVariable id: Long) = this.promocaoService.getById(id)

    @PostMapping
    fun create(@RequestBody promocao: Promocao) {
       this.promocaoService.create(promocao)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        this.promocaoService.delete(id)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        this.promocaoService.update(id, promocao)
    }
    @GetMapping
    fun findAll(@RequestParam(required = false, defaultValue = "") localFilter: String) =
    this.promocaoService.searchByLocal(localFilter)
    }


