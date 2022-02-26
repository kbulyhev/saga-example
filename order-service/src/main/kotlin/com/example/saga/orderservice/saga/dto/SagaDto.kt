package com.example.saga.orderservice.saga.dto

import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime

data class SagaCompensationDTO(
    val savedOrderId: Long
)

data class PaymentRequestDTO(
    var orderId: Long?,

    var name: String,

    var price: String,

    var path: String,

    var validTo: OffsetDateTime
) : BaseServiceDTO() {
    constructor() : this(null, "", "", "", OffsetDateTime.now())
}