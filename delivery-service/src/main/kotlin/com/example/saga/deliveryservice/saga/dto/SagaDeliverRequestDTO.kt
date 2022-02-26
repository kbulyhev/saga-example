package com.example.saga.deliveryservice.saga.dto

import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime

data class SagaDeliverRequestDTO(

    val paymentId: Long,

    val cost: String,

    val timestamp: OffsetDateTime,

    val paymentNumber: String,

    val orderId: Long,

    val orderValidTo: OffsetDateTime
) : BaseServiceDTO()