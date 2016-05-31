/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

import java.io.IOException;
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
        System.setProperty("hadoop.home.dir", "D:\\download\\programmazione\\hbase\\hadoop-2.6.0");
        Configuration config = HBaseConfiguration.create();

        BasicConfigurator.configure();
        Connection connection = null;
        try {
            logger.info("connessione");
            connection = ConnectionFactory.createConnection(config);
            logger.info("inserimento dati");
            Trend t = new Trend(connection);
            t.setName("trend");
            HashMap<Integer, Double> dati = new HashMap<>();
            dati.put(1, 10.0);

            try {
                t.log(new Date(), new Date(), dati);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
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
