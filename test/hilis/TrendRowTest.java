/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilis;

import java.util.Date;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pier
 */
public class TrendRowTest {
    
    public TrendRowTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setKey method, of class TrendRow.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        String key = "";
        TrendRow instance = new TrendRow();
        instance.setKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class TrendRow.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        Date dataCampionamento = null;
        TrendRow instance = new TrendRow();
        instance.setData(dataCampionamento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataUtc method, of class TrendRow.
     */
    @Test
    public void testSetDataUtc() {
        System.out.println("setDataUtc");
        Date dataUtc = new Date();
        TrendRow instance = new TrendRow();
        try{
            instance.setDataUtc(dataUtc);
        }
        catch(Exception e){
            fail();
        }
       
    }
    @Test
    public void testSetDataUtcNoNull() {
        System.out.println("setDataUtc");
        Date dataUtc = null;
        TrendRow instance = new TrendRow();
        try{
            instance.setDataUtc(dataUtc);
        }
        catch(Exception e){
            return;
        }
        
    }

    /**
     * Test of getKey method, of class TrendRow.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        TrendRow instance = new TrendRow();
        String expResult = "";
        String result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class TrendRow.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        TrendRow instance = new TrendRow();
        Date expResult = null;
        Date result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataUtc method, of class TrendRow.
     */
    @Test
    public void testGetDataUtc() {
        System.out.println("getDataUtc");
        TrendRow instance = new TrendRow();
        Date expResult = null;
        Date result = instance.getDataUtc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIngresso method, of class TrendRow.
     */
    @Test
    public void testGetIngresso() {
        System.out.println("getIngresso");
        Integer numero = null;
        TrendRow instance = new TrendRow();
        Double expResult = null;
        Double result = instance.getIngresso(numero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIngresso method, of class TrendRow.
     */
    @Test
    public void testSetIngresso() {
        System.out.println("setIngresso");
        Integer numero = null;
        Double Valore = null;
        TrendRow instance = new TrendRow();
        instance.setIngresso(numero, Valore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIngressi method, of class TrendRow.
     */
    @Test
    public void testGetIngressi() {
        System.out.println("getIngressi");
        TrendRow instance = new TrendRow();
        Map<Integer, Double> expResult = null;
        Map<Integer, Double> result = instance.getIngressi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
