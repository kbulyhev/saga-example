package com.example.saga.paymentservice.saga.service

import com.example.saga.paymentservice.repository.SagaPaymentsRepository
import com.example.saga.paymentservice.saga.dto.SagaPaymentCompensationDTO
import org.springframework.stereotype.Service

@Service
class SagaPaymentCompensationService(
    private val sagaPaymentsRepository: SagaPaymentsRepository
) {
    fun compensatePayment(sagaPaymentCompensationDTO: SagaPaymentCompensationDTO) {
        sagaPaymentsRepository.findById(sagaPaymentCompensationDTO.paymentId)
            .ifPresent {
                sagaPaymentsRepository.delete(it)
            }

    }
}