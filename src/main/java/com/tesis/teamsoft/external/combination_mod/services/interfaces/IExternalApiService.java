package com.tesis.teamsoft.external.combination_mod.services.interfaces;


import org.springframework.stereotype.Repository;

@Repository
public interface IExternalApiService<I, O> {
    O fetchData(I InputDTO);
}
