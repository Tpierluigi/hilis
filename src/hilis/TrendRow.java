/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author pier
 */
class TrendRow {
    String key;
    Date data;
    Date dataUtc;
    Map<Integer, Double> ingressi;

    public TrendRow(String key, Date dataCampionamento, Date dataUtc, Map<Integer, Double> ingressi) {
        this.key = key;
        this.data = dataCampionamento;
        this.dataUtc = dataUtc;
        this.ingressi = ingressi;
    }
     public TrendRow( Date dataCampionamento, Date dataUtc, Map<Integer, Double> ingressi) {
        this.key = "";
        this.data = dataCampionamento;
        this.dataUtc = dataUtc;
        this.ingressi = ingressi;
    }
       
    public void setKey(String key) {
        this.key = key;
    }

    public TrendRow() {
    }

    public void setData(Date dataCampionamento) {
        this.data = dataCampionamento;
    }

    public void setDataUtc(Date dataUtc) {
        this.dataUtc = dataUtc;
    }

    public String getKey() {
        return key;
    }

    public Date getData() {
        return data;
    }

    public Date getDataUtc() {
        return dataUtc;
    }
    public Double getIngresso(Integer numero){
        return ingressi.get(numero);
    }
    public void setIngresso(Integer numero, Double Valore){
        ingressi.put(numero, Valore);
    }

    public Map<Integer,Double> getIngressi() {
        return ingressi;
    }
}

