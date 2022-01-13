package com.carlosvanoni.challange.repository;

import com.carlosvanoni.challange.model.Saleman;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SalemanRepository {

    private final List<Saleman> salemanList = new ArrayList<>();

    public SalemanRepository() {
    }

    public Boolean save(Saleman saleman) {
        return salemanList.add(saleman);
    }

    public List<Saleman> getList() {
        return salemanList;
    }

    public void clear() {
        salemanList.clear();
    }

    public Saleman searchSaleman(String name) {
        List<Saleman> list = salemanList.stream().filter(o -> o.getName().contains(name)).collect(Collectors.toList());
        if (list.isEmpty())
            return null;
        else return list.get(0);
    }
}

