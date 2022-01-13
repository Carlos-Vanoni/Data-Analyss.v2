package com.carlosvanoni.challange.config;

import com.carlosvanoni.challange.dao.DataDAO;
import com.carlosvanoni.challange.repository.CustomerRepository;
import com.carlosvanoni.challange.repository.SaleRepository;
import com.carlosvanoni.challange.repository.SalemanRepository;
import com.carlosvanoni.challange.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.carlosvanoni.challange")
public class Config {

    @Bean
    public DataDAO dataDAO() {
        return new DataDAO();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    public SalemanService salemanService() {
        return new SalemanService();
    }

    @Bean
    public SaleService saleService() {
        return new SaleService();
    }

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository();
    }

    @Bean
    public SalemanRepository salemanRepository() {
        return new SalemanRepository();
    }

    @Bean
    public SaleRepository saleRepository() {
        return new SaleRepository();
    }

    @Bean
    public CharacterRefactor characterRefactor() {
        return new CharacterRefactor();
    }

    @Bean
    public AnalysisService analysisService() {
        return new AnalysisService();
    }

    @Bean
    public RunService runService() {
        return new RunService();
    }


}
