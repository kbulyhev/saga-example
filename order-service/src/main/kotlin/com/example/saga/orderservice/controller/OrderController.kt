package com.example.saga.orderservice.controller

import com.example.saga.orderservice.api.OrderService
import com.example.saga.orderservice.dto.CreateOrderDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    val orderService: OrderService
) {

    @PostMapping("/create-order")
    fun startSaga(@RequestBody createOrderDTO: CreateOrderDTO) {
        orderService.createOrder(createOrderDTO)
    }
}