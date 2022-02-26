package com.example.saga.orderservice.entity

import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "saga_order_service")
class SagaOrderEntity(orderId: Long?, name: String, price: String, path: String, validTo: OffsetDateTime) :
    BaseOrderEntity(orderId, name, price, path, validTo)