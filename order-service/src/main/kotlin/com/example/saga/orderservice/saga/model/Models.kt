package com.example.saga.orderservice.saga.model

import com.example.saga.orderservice.entity.SagaOrderEntity

data class SagaTransactionModelRequest(
    val orderEntity: SagaOrderEntity,
)

data class SagaTransactionModelResponse(
    val savedOrder: SagaOrderEntity
)