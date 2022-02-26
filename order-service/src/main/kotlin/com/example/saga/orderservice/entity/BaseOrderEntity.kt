package com.example.saga.orderservice.entity

import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.SequenceGenerator

@MappedSuperclass
abstract class BaseOrderEntity(
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    @SequenceGenerator(name = "seq_generator", sequenceName = "order_id_seq", allocationSize = 1)
    var orderId: Long?,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "price", nullable = false)
    var price: String,

    @Column(name = "path", nullable = false)
    var path: String,

    @Column(name = "validTo", nullable = false)
    var validTo: OffsetDateTime
) {
    constructor() : this(null, "", "", "", OffsetDateTime.now())
}