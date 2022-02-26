package com.example.saga.orderservice.saga.service

import com.example.saga.orderservice.repository.SagaOrderRepository
import com.example.saga.orderservice.saga.dto.SagaCompensationDTO
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class OrderCompensationService(
    private val sagaOrderRepository: SagaOrderRepository
) {
    private val logger = KotlinLogging.logger {}

    fun compensate(sagaCompensationDTO: SagaCompensationDTO) {
        logger.info { "compensation transaction orderId = ${sagaCompensationDTO.savedOrderId}" }

        sagaOrderRepository.findById(sagaCompensationDTO.savedOrderId)
            .ifPresent {
                sagaOrderRepository.delete(it)
            }
    }
}