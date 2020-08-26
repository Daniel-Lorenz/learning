package com.daniellorenz.config;

import com.daniellorenz.repository.repo.PersistenceServiceWrapper;
import com.daniellorenz.repository.repo.Repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepoConfiguration {

    @Bean
    @Primary
    public Repository getRepository(){
        return new PersistenceServiceWrapper();
    } 
    
}