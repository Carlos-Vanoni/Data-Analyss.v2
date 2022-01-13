package com.carlosvanoni.challange.service;

public class CharacterRefactor {

    public CharacterRefactor() {
    }

    public String removeCedilhaOnPersonName(String[] lineSplited) {
        StringBuilder salemanName = new StringBuilder(lineSplited[2]);
        if (lineSplited.length > 4) {
            for (int i = 3; i < lineSplited.length - 1; i++) {
                salemanName.append("ç").append(lineSplited[i]);
            }
        }
        return salemanName.toString();
    }

    public String removeCedilhaOnSale(String[] lineSplited) {
        StringBuilder salemanNameOnSaleNote = new StringBuilder(lineSplited[3]);
        if (lineSplited.length > 4) {
            for (int i = 4; i < lineSplited.length - 1; i++) {
                salemanNameOnSaleNote.append("ç").append(lineSplited[i]);
            }
        }
        return salemanNameOnSaleNote.toString();
    }
}
