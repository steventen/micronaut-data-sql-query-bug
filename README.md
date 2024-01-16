## Micronaut data with using native query

1. Start db: `docker-compose up`
2. Run server: `./gradlew run`
3. Visit http://localhost:8080/companies

The sql schema and data are in `/config/init.sql`.

It is excepted to get 5 records, but only 1 record is returned.

It works as expected in Micronaut v4.1.x

Essentially, it calls the following sql query to get the data:
```sql
select
    v_sum.org_id,
    v_sum.company_name as name,
    v_sum.value,
    rank() over (order by v_sum.value desc) as value_rank
from (
    select v.org_id, v.company_name, sum(v.company_value) as value
    from companies v
    where
        v.org_id = 'abc'
        and v.process_date >= '2023-01-01'
        and v.process_date <= '2023-05-01'
        group by v.org_id, v.company_name
) v_sum order by value_rank limit 5
```