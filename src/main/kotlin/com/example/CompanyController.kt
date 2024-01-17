package com.example

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import java.time.LocalDate

@Controller("/companies")
class CompanyController(
    private val companyRepository: CompanyRepository,
    private val mapper: CompanyMapper,
) {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index(): List<CompanyDto> {
        val companies = companyRepository.getAllLimit(
            "abc",
            LocalDate.parse("2023-01-01"),
            LocalDate.parse("2023-05-01"),
        )
        return companies.map { mapper.toCompanyDto(it) }
    }
}