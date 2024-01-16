package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "companies")
@Serdeable
data class Company(
    @Id
    val orgId: String,

    val name: String,

    val value: BigDecimal?,

    val valueRank: Int
)