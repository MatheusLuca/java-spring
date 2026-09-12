create table t_jdbc_produto (
        cd_produto number(9,0) primary key,
        nm_produto varchar(80) not null,
        ds_produto varchar(255) not null,
        vl_produto number(9,2) not null,
        st_estoque number(1,0)
);

create sequence sq_t_jdbc_produto start with 1 increment by 1 nocache;

-- 1:N
create table t_jdbc_categoria (
    cd_categoria number(9,0) primary key,
    nm_categoria varchar(50) not null
);

create sequence sq_t_jdbc_categoria start with 1 increment by 1 nocache;
