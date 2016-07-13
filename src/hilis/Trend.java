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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.NavigableMap;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
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
     * @param riga
     * @throws IOException 
     */
    public void log(TrendRow riga) throws IOException {
        Table table = this.connection.getTable(TableName.valueOf(this.name));
        riga.setKey(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(riga.getDataUtc()));
        Put datiDaInserire = new Put(riga.getKey().getBytes());
        try {
            for (Map.Entry<Integer, Double> rigaDati : riga.getIngressi().entrySet()) {
                datiDaInserire.add(Bytes.toBytes("ingressi"),
                        Bytes.toBytes(String.format("%07d", rigaDati.getKey())),
                        Bytes.toBytes(rigaDati.getValue().toString()));

            }
            datiDaInserire.add(Bytes.toBytes("dataCampionamento"),
                    Bytes.toBytes("data"), 
                    Bytes.toBytes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(riga.getData())));
            datiDaInserire.add(Bytes.toBytes("dataCampionamento"),
                    Bytes.toBytes("dataUtc"), 
                    Bytes.toBytes(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(riga.getDataUtc())));

            table.put(datiDaInserire);
        } catch (IOException e) {
            throw e;
        } finally {
            table.close();
        }
    }
    public TrendRow getRow(String key) throws IOException, ParseException,Exception{
        TrendRow riga;
        Result r;
        byte [] valori;
        NavigableMap<byte[], byte[]> mappa;
        riga = new TrendRow();
        Table table = this.connection.getTable(TableName.valueOf(this.name));
        r=table.get(new Get(Bytes.toBytes(key)));
        valori = r.getValue(Bytes.toBytes("dataCampionamento"),Bytes.toBytes("data"));
        riga.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Bytes.toString(valori))); //bytes -> stringa -> data
        valori = r.getValue(Bytes.toBytes("dataCampionamento"),Bytes.toBytes("dataUtc"));
        riga.setDataUtc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Bytes.toString(valori)));
        //bytes -> stringa -> data
        mappa=r.getFamilyMap(Bytes.toBytes("ingressi"));
        for (Map.Entry<byte[], byte[]> ingresso : mappa.entrySet()) {
            riga.setIngresso(Integer.parseInt(Bytes.toString(ingresso.getKey())), Bytes.toDouble(ingresso.getValue()));
        }
        riga.setKey(key);
        return riga;
        
    }
}
