-- 테이블의 제약조건 삭제
alter table density drop foreign key const_foreign_key_density_to_store;

alter table store drop foreign key const_foreign_key_store_to_business_days;

alter table store drop foreign key const_foreign_key_store_to_business_hours;

-- ========================================================================

-- 테이블 삭제
drop table if exists business_days;

drop table if exists business_hours;

drop table if exists density;

drop table if exists store;

-- ========================================================================

-- 테이블 생성
create table business_days (
    business_days_id bigint not null auto_increment,
    sunday varchar(255),
    monday varchar(255),
    tuesday varchar(255),
    wednesday varchar(255),
    thursday varchar(255),
    friday varchar(255),
    saturday varchar(255),
    primary key (business_days_id)
) engine=InnoDB;

create table business_hours (
    business_hours_id bigint not null auto_increment,
    open_business_hour integer not null,
    close_business_hour integer not null,
    primary key (business_hours_id)
) engine=InnoDB;

create table density (
    density_id bigint not null auto_increment,
    density_rate integer,
    calculated_time datetime(6),
    store_id bigint,
    primary key (density_id)
) engine=InnoDB;

create table store (
    store_id bigint not null auto_increment,
    store_name varchar(255),
    address varchar(255),
    thum_url varchar(255),
    latitude double precision not null,
    longitude double precision not null,
    password varchar(255),
    seat_count integer,
    business_days_id bigint,
    business_hours_id bigint,
    primary key (store_id)
) engine=InnoDB;


-- ========================================================================

-- 테이블 제약조건 생성
alter table density add constraint const_foreign_key_density_to_store
    foreign key (store_id) references store (store_id);

alter table store add constraint const_foreign_key_store_to_business_days
    foreign key (business_days_id) references business_days (business_days_id);

alter table store add constraint const_foreign_key_store_to_business_hours
    foreign key (business_hours_id) references business_hours (business_hours_id);