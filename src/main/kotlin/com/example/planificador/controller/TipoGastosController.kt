package com.example.planificador.controller

import com.example.planificador.error.exception.BadRequestException
import com.example.planificador.model.TipoGasto
import com.example.planificador.service.TipoGastoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipos")
class TipoGastosController {

    @Autowired
    private lateinit var tipoGastoService: TipoGastoService

    @PostMapping("/")
    fun newTipo(
        @RequestBody tipo: TipoGasto?
    ): ResponseEntity<TipoGasto>{

        if (tipo == null){
            throw BadRequestException("Debe añadir un tipo de gasto, no puede dejarlo vacío.")
        }

        val tipoGasto = tipoGastoService.newTipo(tipo)

        return ResponseEntity(tipoGasto, HttpStatus.CREATED)
    }

    @GetMapping("/{nameTipo}")
    fun getByName(
        @PathVariable nameTipo: String
    ): ResponseEntity<TipoGasto>{
        val tipoGasto = tipoGastoService.getTipoGasto(nameTipo)
        return ResponseEntity(tipoGasto, HttpStatus.OK)
    }

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<TipoGasto>>{
        val list = tipoGastoService.getAll()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @DeleteMapping("/{name}")
    fun delete(
        @PathVariable name: String
    ): ResponseEntity<TipoGasto>{
        val tipoGasto = tipoGastoService.delete(name)
        return ResponseEntity(tipoGasto, HttpStatus.OK)
    }

    @PutMapping("/update")
    fun update(
        @RequestBody tipoGasto: TipoGasto?
    ): ResponseEntity<TipoGasto>{
        if (tipoGasto == null){
            throw BadRequestException("El tipo de gasto no puede estar vacío.")
        }

        val tipo = tipoGastoService.update(tipoGasto)

        return ResponseEntity(tipo, HttpStatus.CREATED)
    }
}