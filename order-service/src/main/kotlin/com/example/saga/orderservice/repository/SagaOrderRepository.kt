package com.example.saga.orderservice.repository

import com.example.saga.orderservice.entity.SagaOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SagaOrderRepository: JpaRepository<SagaOrderEntity, Long> {
}