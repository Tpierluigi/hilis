/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

/**
 *
 * @author pier
 */
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class Trend {

    private final Connection connection;
    private String name;
    /**
     * il nome della famiglia di colonne per i dati
     */
    private static final byte[] DATI = Bytes.toBytes("dati");

    public Connection getConnection() {
        return this.connection;
    }

    public Trend(Connection connection) {
        this.connection = connection;
    }

    public void setName(String nomeTrend) {
        name = nomeTrend;
    }

    /**
     *
     * @param dataUtc
     * @param data
     * @param ingressi
     */
    public void log(Date dataUtc, Date data, Map<Integer, Double> ingressi) throws IOException {
        Table table = this.connection.getTable(TableName.valueOf(this.name));
        Put datiDaInserire = new Put(new SimpleDateFormat("yyyyMMddHHmmss").format(data).getBytes());
        try {
            for (Map.Entry<Integer, Double> riga : ingressi.entrySet()) {
                datiDaInserire.add(Bytes.toBytes("dati"),
                        Bytes.toBytes(String.format("%07d", riga.getKey())),
                        Bytes.toBytes(riga.getValue()));

            }
            table.put(datiDaInserire);
        } catch (IOException e) {
            throw e;
        } finally {
            table.close();
        }
    }
}
