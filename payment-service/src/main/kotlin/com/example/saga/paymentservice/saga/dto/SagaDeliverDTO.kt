package com.example.saga.paymentservice.saga.dto

import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime

data class SagaDeliverDTO(

    val paymentId: Long,

    val cost: String,

    val timestamp: OffsetDateTime,

    val paymentNumber: String,

    val orderId: Long,

    val orderValidTo: OffsetDateTime
) : BaseServiceDTO()