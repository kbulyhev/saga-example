package com.example.saga.orderservice.dto

import java.time.OffsetDateTime

data class CreateOrderDTO(
    val name: String,
    val price: String,
    val path: String,
    val validTo: OffsetDateTime
)