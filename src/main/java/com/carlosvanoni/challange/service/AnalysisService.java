package com.carlosvanoni.challange.service;

import com.carlosvanoni.challange.exception.AcessRepositoryException;
import com.carlosvanoni.challange.exception.DataProcessException;
import com.carlosvanoni.challange.model.Sale;
import com.carlosvanoni.challange.model.Saleman;
import com.carlosvanoni.challange.dao.DataDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class AnalysisService {

    private static final Logger logger = (Logger) LogManager.getLogger(AnalysisService.class);

    @Autowired
    private DataDAO dataDAO;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SalemanService salemanService;

    @Autowired
    private SaleService saleService;


    private String getDataInbox(String fileName) {
        try {
            return dataDAO.readInbox(fileName);
        } catch (AcessRepositoryException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IndexOutOfBoundsException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    private void setDataOutbox(String fileName, String dataOutput) {
        try {
            dataDAO.writeOutBox(fileName, dataOutput);
        } catch (AcessRepositoryException e) {
            logger.error(e.getMessage());
        }
    }

    private String separatorDataOnSameLine(String data) {
        String dataRefactoredOne = data.replaceAll("001", "\n001");
        String dataRefactoredTwo = dataRefactoredOne.replaceAll("002", "\n002");
        String dataRefactoredTree = dataRefactoredTwo.replaceAll("003", "\n003");
        String[] dataWithSpaces = dataRefactoredTree.split("\n");
        StringBuilder dataRefactored = new StringBuilder();
        for (String line : dataWithSpaces) {
            if (!line.trim().equals(""))
                dataRefactored.append(line).append("\n");
        }
        return dataRefactored.toString();
    }

    private String writeDataOuput() {
        StringBuilder dataOutput;
        dataOutput = new StringBuilder("Amount customers: " + customerService.getCustomerAmountString() + "\n"
                + "Amount saleman: " + salemanService.getSalesmanAmountString() + "\n");
        dataOutput.append("Most expansive sale(s):" + "\n");
        List<Sale> mostExpanssivesSales = saleService.getExpansiveSale();
        for (Sale saleData : mostExpanssivesSales) {
            dataOutput.append("ID: ").append(saleData.getId()).append(" - Price: ").append(saleData.getPrice()).append("\n");
        }
        dataOutput.append("Worse saleman(s):" + "\n");
        List<Saleman> worsesSaleman = salemanService.getWorsesSaleman();
        for (Saleman saleman : worsesSaleman) {
            dataOutput.append("Saleman: ").append(saleman.getName()).append(" - price of sale: ").append(saleman.getPriceOfSales());
        }
        return dataOutput.toString();
    }

    private List<String> getFilesWithoutMatch() {
        try {
            List<String> fileNamesInbox = dataDAO.listFileNamesInbox();
            List<String> fileNamesOutbox = dataDAO.listFileNamesOutbox();
            fileNamesInbox.removeAll(fileNamesOutbox);
            return fileNamesInbox;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public String processData(String data) {
        try {
            String Semicolon = data.replaceAll("ç", ";");
            String dataDiferentLines = separatorDataOnSameLine(Semicolon);
            String[] dataSplited = dataDiferentLines.split("\n");
            for (String line : dataSplited) {
                String[] lineSplited = line.split(";");
                switch (lineSplited[0]) {
                    case "001":
                        salemanService.processData(lineSplited);
                        break;
                    case "002":
                        customerService.processData(lineSplited);
                        break;
                    case "003":
                            saleService.processData(lineSplited);
                        break;
                    default:
                        logger.error("processData: line format is incorrect");
                }
            }
            return writeDataOuput();
        } catch (Exception e) {
            logger.error("error reported in process");
            return "The content of this file is badly formatted or does not meet the standards for analysis";
        }
    }

    public void execute() {
        List<String> filesWithoutMatch = getFilesWithoutMatch();
        for (String fileName : filesWithoutMatch) {
            String dataInbox = getDataInbox(fileName);
            String dataProcessed = processData(dataInbox);
            setDataOutbox(fileName, dataProcessed);
        }
    }
}
