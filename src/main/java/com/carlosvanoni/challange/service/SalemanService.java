package com.carlosvanoni.challange.service;

import com.carlosvanoni.challange.model.Saleman;
import com.carlosvanoni.challange.repository.SalemanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SalemanService {

    @Autowired
    private SalemanRepository repository;

    @Autowired
    private CharacterRefactor refactor;


    public List<Saleman> getList() {
        return repository.getList();
    }

    public List<Saleman> getWorsesSaleman() {
        double worsesSalePrice = getList().stream().sorted(Comparator.comparing(Saleman::getPriceOfSales)).collect(Collectors.toList()).get(0).getPriceOfSales();
        List<Saleman> worsesSaleman = getList().stream().filter(o -> o.getPriceOfSales() == worsesSalePrice).collect(Collectors.toList());
        return worsesSaleman;
    }

    public String getSalesmanAmountString() {
        return String.valueOf(getList().size());
    }

    public void processData(String[] lineSplited) {
        String salemanName = refactor.removeCedilhaOnPersonName(lineSplited);
        String salemanSalary = lineSplited[lineSplited.length - 1];
        Saleman salemanRegistred = new Saleman(Long.parseLong(lineSplited[0]), salemanName, Double.parseDouble(salemanSalary));
        if (!repository.getList().contains(salemanRegistred))
            repository.save(salemanRegistred);
    }
}
