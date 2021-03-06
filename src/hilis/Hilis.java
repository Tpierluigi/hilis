/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author pier
 */
public class Hilis {

    static Logger logger = Logger.getLogger(Hilis.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // You need a configuration object to tell the client where to connect.
        // When you create a HBaseConfiguration, it reads in whatever you've set
        // into your hbase-site.xml and in hbase-default.xml, as long as these
        // can be found on the CLASSPATH
        Configuration config = HBaseConfiguration.create();
        TrendRow riga;
        String chiave;
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(config);
            Trend t = new Trend(connection);
            t.setName("trend");
            HashMap<Integer, Double> dati = new HashMap<>();
            Date tempo= new Date();
            dati.put(1, Math.random());
            try {
                riga = new TrendRow(dati);
                t.log(riga);
                chiave = riga.getKey();
                logger.info("Fine inserimento: " + Long.toString(new Date().getTime() - tempo.getTime()));
                riga = null;
                riga = t.getRow(chiave);
                logger.log(Level.INFO, "riga: chiave = " + riga.getKey() + " valore = " + riga.getIngresso(1).toString());
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Hilis.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            // close everything down
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException ex) {
                    Logger.getLogger(Hilis.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
