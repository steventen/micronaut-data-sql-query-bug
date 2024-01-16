create table if not exists companies (
    id text not null primary key,
    org_id varchar(100),
    process_date date,
    company_name text not null,
    company_value numeric
);

insert into companies (id, org_id, process_date, company_name, company_value)
values
    ('a', 'abc', '2023-01-01','company a', 100.01),
    ('b', 'abc', '2023-01-01','company b', 100.01),
    ('c', 'abc', '2023-01-01','company c', 100.01),
    ('cx', 'abc', '2023-02-01','company c', 100.01),
    ('cxx', 'abc', '2023-03-01','company c', 100.01),
    ('d', 'abc', '2023-01-01','company d', 100.01),
    ('dx', 'abc', '2023-02-01','company d', 100.01),
    ('e', 'abc', '2023-01-01','company e', 100.01)
on conflict do nothing;