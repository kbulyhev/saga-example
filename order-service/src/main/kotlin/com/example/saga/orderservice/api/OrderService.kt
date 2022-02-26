package com.example.saga.orderservice.api

import com.example.saga.orderservice.dto.CreateOrderDTO

interface OrderService {
    fun createOrder(createOrderDTO: CreateOrderDTO)
}