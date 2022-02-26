package com.example.saga.deliveryservice.saga.model

import com.example.saga.deliveryservice.entity.SagaDeliveryEntity

data class SagaDeliverTransactionResponse(
    val savedDeliverEntity: SagaDeliveryEntity
)