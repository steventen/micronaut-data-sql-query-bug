package com.example

import io.micronaut.context.annotation.Mapper
import io.micronaut.data.annotation.Embeddable
import io.micronaut.data.annotation.EmbeddedId
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import java.math.BigDecimal

@Embeddable
data class CompanyId(
    @Column(name = "org_id")
    val orgId: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "value_rank")
    val valueRank: Int,
)

@MappedEntity("companies")
@Serdeable
data class Company(
    @EmbeddedId
    val companyId: CompanyId,

    val name: String,

    val value: BigDecimal?,

    val valueRank: Int
)

@Serdeable
data class CompanyDto(
    val name: String,
    val valueRank: Int
)

interface CompanyMapper {
    @Mapper
    fun toCompanyDto(company: Company): CompanyDto
}