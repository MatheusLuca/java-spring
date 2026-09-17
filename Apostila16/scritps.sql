create table t_api_imovel (
                              cd_imovel number(9,0) primary key,
                              ds_imovel varchar(255) not null,
                              nr_dimensao number(9,2),
                              vl_imovel number (9,2) not null
);

create sequence sq_t_api_imovel start with 1 increment by 1 nocache;