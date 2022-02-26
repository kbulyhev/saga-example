package com.example.saga.orderservice.repository

import com.example.saga.orderservice.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, Long> {
}