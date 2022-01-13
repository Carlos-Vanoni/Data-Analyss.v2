package com.carlosvanoni.challange.service;

import com.carlosvanoni.challange.exception.DataProcessException;
import com.carlosvanoni.challange.model.Sale;
import com.carlosvanoni.challange.model.Saleman;
import com.carlosvanoni.challange.repository.SaleRepository;
import com.carlosvanoni.challange.repository.SalemanRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SalemanRepository salemanRepository;

    @Autowired
    private CharacterRefactor refactor;

    private static final Logger logger = (Logger) LogManager.getLogger(SaleService.class);

    public List<Sale> getList() {
        return saleRepository.getList();
    }

    public List<Sale> getExpansiveSale() {
        double expansiveSalePrice = getList().stream().sorted(Comparator.comparing(Sale::getPrice).reversed()).collect(Collectors.toList()).get(0).getPrice();
        List<Sale> expansiveSales = getList().stream().filter(o -> o.getPrice() == expansiveSalePrice).collect(Collectors.toList());
        return expansiveSales;
    }

    private boolean idContainsInTheList(String id) {
        long idExpected = Long.parseLong(id);
        for(Sale sale : getList()) {
            if(sale.getId() == idExpected)
                return true;
        }
        return false;
    }

    public void processData(String[] lineSplited) {
        if(idContainsInTheList(lineSplited[1])) {
            logger.error("this id " + lineSplited[1] + " already exists");
            return;
        }
        String salemanNameOnSaleNote = refactor.removeCedilhaOnSale(lineSplited);
        String salesWithBrackets = lineSplited[2];
        String sales = salesWithBrackets.substring(2, salesWithBrackets.length() - 2);
        String[] saleNotesSplited = sales.split(",");
        double priceOfSale = 0;
        for (String sale : saleNotesSplited) {
            String[] saleSplited = sale.split("-");
            if (saleSplited.length != 3) {
                logger.error("error processing data: missing information for the product sold");
            } else {
                priceOfSale += Double.parseDouble(saleSplited[1]) * Double.parseDouble(saleSplited[2]);
            }
        }
        Saleman saleman = salemanRepository.searchSaleman(salemanNameOnSaleNote);
        if (saleman == null) {
            logger.error("seleman related on data sale, is not registered: " + salemanNameOnSaleNote);
        }
        saleman.addPriceOfSale(priceOfSale);
        saleRepository.save(new Sale(Long.parseLong(lineSplited[1]), priceOfSale, lineSplited[3]));
    }
}
