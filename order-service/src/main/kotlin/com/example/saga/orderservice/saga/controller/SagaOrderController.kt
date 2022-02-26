package com.example.saga.orderservice.saga.controller

import com.example.saga.orderservice.saga.dto.SagaCompensationDTO
import com.example.saga.orderservice.saga.service.OrderCompensationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SagaOrderController {

    @Autowired
    private lateinit var orderCompensationService: OrderCompensationService

    @PostMapping("/saga/orders/compensation")
    fun orderCompensation(@RequestBody sagaCompensationDTO: SagaCompensationDTO) {
        orderCompensationService.compensate(sagaCompensationDTO)
    }

}