package com.example.saga.orderservice.entity

import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "order_service")
 class OrderEntity(orderId: Long?, name: String, price: String, path: String, validTo: OffsetDateTime) :
    BaseOrderEntity(orderId, name, price, path, validTo)
