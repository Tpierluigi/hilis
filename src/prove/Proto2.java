/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prove;

import hilis.Trend;
import hilis.TrendRow;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 *
 * @author pier
 */
public class Proto2 {

    static Logger logger = Logger.getLogger(Proto2.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // You need a configuration object to tell the client where to connect.
        // When you create a HBaseConfiguration, it reads in whatever you've set
        // into your hbase-site.xml and in hbase-default.xml, as long as these
        // can be found on the CLASSPATH
        Configuration config = HBaseConfiguration.create();

        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(config);
            Trend t = new Trend(connection);
            t.setName("trend");
            HashMap<Integer, Double> dati = new HashMap<>();
            Date tempo= new Date();
            for(int i=0;i<1000;i++){
                dati.put(i, Math.random()*i);
            }
            logger.info("Fine preparazione: " + Long.toString(new Date().getTime() - tempo.getTime()));
            tempo=new Date();
            try {
                t.log(new TrendRow(new Date(), new Date(), dati));
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        logger.info("Fine inserimento: " + Long.toString(new Date().getTime() - tempo.getTime()));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            // close everything down
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException ex) {
                    Logger.getLogger(Proto2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
