package com.carlosvanoni.challange.repository;

import com.carlosvanoni.challange.model.Sale;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleRepository {

    private final List<Sale> saleList = new ArrayList<>();

    public SaleRepository() {
    }

    public Boolean save(Sale saleData) {
        return saleList.add(saleData);
    }

    public List<Sale> getList() {
        return saleList;
    }

    public void clear() {
        saleList.clear();
    }
}