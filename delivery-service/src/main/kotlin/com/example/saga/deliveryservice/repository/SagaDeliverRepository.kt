package com.example.saga.deliveryservice.repository

import com.example.saga.deliveryservice.entity.SagaDeliveryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SagaDeliverRepository: JpaRepository<SagaDeliveryEntity, Long> {
}