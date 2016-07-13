/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pier
 */
public class TrendRow {
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
     public TrendRow(Map<Integer, Double> ingressi){
        this.key = "";
        this.data = new Date();
        try {
            this.dataUtc = Utility.GetUTCdatetimeAsDate();
        } catch (ParseException ex) {
            Logger.getLogger(TrendRow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ingressi = ingressi;
     }
       
    public void setKey(String key) {
        this.key = key;
    }

    public TrendRow() {
        key="";
        data=null;
        dataUtc=null;
        ingressi=new HashMap<>();
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

