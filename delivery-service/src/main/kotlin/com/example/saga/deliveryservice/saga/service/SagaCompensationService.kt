package com.example.saga.deliveryservice.saga.service

import com.example.saga.deliveryservice.repository.SagaDeliverRepository
import com.example.saga.deliveryservice.saga.dto.SagaDeliverCompensationDTO
import org.springframework.stereotype.Service

@Service
class SagaCompensationService(
    private val sagaDeliverRepository: SagaDeliverRepository
) {
    fun compensate(sagaDeliverCompensationDTO: SagaDeliverCompensationDTO) {
        sagaDeliverRepository.deleteById(sagaDeliverCompensationDTO.delverId)
    }
}