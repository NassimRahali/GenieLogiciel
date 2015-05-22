/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package main;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;
import antlr.Java8Lexer;
import antlr.Java8Parser;
import calculators.ATFDCalculator;
import calculators.LCOM4Calculator;
import calculators.TCCCalculator;
import calculators.WMCCalculator;

/**
 *
 * @author Nassim
 */
public class JavaParserTest {

    public static void main(String[] args) throws Exception {

        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("SuperTest.java"));
        Java8Lexer lexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();

        TCCCalculator tccc = new TCCCalculator(tree);
        System.out.println("TCC is : " + tccc.ComputeAndGet());
        System.out.println(tccc.getMessage());
        
        WMCCalculator wmcc = new WMCCalculator(tree, true);
        System.out.println("WMC is : " + wmcc.ComputeAndGet());
        System.out.println(wmcc.getMessage());
        
        ATFDCalculator atfdc = new ATFDCalculator(tree);
        System.out.println("AFTD is : " + atfdc.ComputeAndGet());
        System.out.println(atfdc.getMessage());
        
        // TODO : DEBUG 
        LCOM4Calculator lcom4c = new LCOM4Calculator(tree);
        System.out.println("LCOM4 is : " + lcom4c.ComputeAndGet());
        System.out.println(lcom4c.getMessage());
    }
}
