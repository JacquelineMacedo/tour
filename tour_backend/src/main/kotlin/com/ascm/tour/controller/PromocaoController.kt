package com.ascm.tour.controller

import com.ascm.tour.advice.ErrorMessage
import com.ascm.tour.entities.Promocao
import com.ascm.tour.entities.RespostaJson
import com.ascm.tour.exception.PromocaoException
import com.ascm.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/promocoes")
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun findId(@PathVariable id: Long): ResponseEntity<Any> {
        var promocao = this.promocaoService.getById(id)
        return if (promocao != null)
              return ResponseEntity(promocao, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promocao Não Localizada","Promocao ${id} não localizado"), HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJson> {
        this.promocaoService.create(promocao)
       var respostaJson = RespostaJson(message = "OK", date = Date())
        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun findAll(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Promocao>> {
        var status = HttpStatus.OK
        var listPromocao = this.promocaoService.searchByLocal(localFilter)
        if (listPromocao.size == 0) {
            status = HttpStatus.NOT_FOUND
        }
        return ResponseEntity(listPromocao, status)
    }
}


