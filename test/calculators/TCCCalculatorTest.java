/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import antlr.Java8Lexer;
import antlr.Java8Parser;
import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nsm
 */
public class TCCCalculatorTest {

    org.antlr.v4.runtime.tree.ParseTree tree;

    public TCCCalculatorTest() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("Fichiers/SuperTest.java"));
        Java8Lexer lexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        tree = parser.compilationUnit();
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

    @Test
    public void testComputeAndGet() {
        System.out.println("ComputeAndGet");
        TCCCalculator instance = new TCCCalculator(tree);
        double expResult = 0.2222222222222222;
        double result = instance.ComputeAndGet();
        assertEquals(expResult, result, 0.0);
        System.out.println("End ComputeAndGet");
    }

    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        TCCCalculator instance = new TCCCalculator(tree);
        String expResult = "COHESION SEEMS LOW";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        System.out.println("End testGetMessage");
    }

    @Test
    public void testSetThreshold() {
        System.out.println("setThreshold");
        double t = 0.1;
        TCCCalculator instance = new TCCCalculator(tree);
        instance.setThreshold(t);
        instance.ComputeAndGet();
        String expResult = "COHESION SEEMS OK";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        System.out.println("End testGetMessage");
    }

}
