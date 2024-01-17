package com.example

import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import java.time.LocalDate

@JdbcRepository(dialect = Dialect.POSTGRES)
interface CompanyRepository : PageableRepository<Company, CompanyId> {
    @Query(
        """
        select
            v_sum.org_id,
            v_sum.company_name as name,
            v_sum.value,
            rank() over (order by v_sum.value desc) as value_rank
        from (
            select v.org_id, v.company_name, sum(v.company_value) as value
            from companies v
            where
                v.org_id = :orgId
                and v.process_date >= :from
                and v.process_date <= :to
                group by v.org_id, v.company_name
        ) v_sum order by value_rank limit :limit
    """
    )
    fun getAllLimit(orgId: String, from: LocalDate, to: LocalDate, limit: Int = 5): List<Company>
}