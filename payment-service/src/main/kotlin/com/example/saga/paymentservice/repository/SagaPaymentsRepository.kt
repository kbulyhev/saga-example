package com.example.saga.paymentservice.repository

import com.example.saga.paymentservice.entity.SagaPaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SagaPaymentsRepository : JpaRepository<SagaPaymentEntity, Long> {
}