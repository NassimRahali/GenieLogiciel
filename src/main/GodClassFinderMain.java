/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package main;

import java.io.FileInputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import antlr.Java8Lexer;
import antlr.Java8Parser;
import calculators.MetricCalculator;
import calculators.MetricCalculatorFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 *
 * @author Nassim
 */
public class GodClassFinderMain {

    public static void main(String[] args) {
        try {

            ArgumentParser argumentParser = ArgumentParsers
                    .newArgumentParser("M18 Projet génie logiciel", true)
                    .defaultHelp(true)
                    .description("Java Parser and indications class");

            // Java file
            argumentParser
                    .addArgument("-f", "--file")
                    .required(true)
                    .type(String.class)
                    .help("Java file (required).");

            // ATFD
            argumentParser
                    .addArgument("-a", "--atfd")
                    .action(Arguments.storeTrue())
                    .help("Access to foreign data.");

            // LCOM4
            argumentParser
                    .addArgument("-l", "--lcom4")
                    .action(Arguments.storeTrue());

            // TCC
            argumentParser
                    .addArgument("-t", "--tcc")
                    .action(Arguments.storeTrue())
                    .help("Tight class cohesion.");

            // WMC1
            argumentParser
                    .addArgument("-w1", "--wmc1")
                    .action(Arguments.storeTrue())
                    .help("Weighted method count (method count");
            // WMC1
            argumentParser
                    .addArgument("-w2", "--wmc2")
                    .action(Arguments.storeTrue())
                    .help("Weighted method count (method count");
            
            Namespace namespace = null;
            try {
                namespace = argumentParser.parseArgs(args);
            } catch (ArgumentParserException ex) {
                argumentParser.handleError(ex);
                System.exit(1);
            }

            String filename = namespace.getString("file");

            boolean atfd = namespace.get("atfd");
            boolean lcom4 = namespace.get("lcom4");
            boolean tcc = namespace.get("tcc");
            boolean wmc1 = namespace.get("wmc1");
            boolean wmc2 = namespace.get("wmc2");
            
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filename));
            Java8Lexer lexer = new Java8Lexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Java8Parser parser = new Java8Parser(tokens);
            ParseTree tree = parser.compilationUnit();

            System.out.println("==================================");
            System.out.println("=== Your file has been parsed. ===");
            System.out.println("==================================\n");
            System.out.printf("Results for : %s\n\n", filename);
            
            MetricCalculatorFactory mcFactory = new MetricCalculatorFactory();           
            MetricCalculator mc;
            
            if (atfd) {
                mc = mcFactory.newInstance(tree, "atfd");
                System.out.println("=== AFTD = " + mc.ComputeAndGet());
                System.out.println("=== AFTD message = " + mc.getMessage() + '\n');
            }
            if (lcom4) {
                mc = mcFactory.newInstance(tree, "lcom4");
                System.out.println("=== LCOM4 = " + mc.ComputeAndGet());
                System.out.println("=== LCOM4 message = " + mc.getMessage() + '\n');
            }
            if (tcc) {
                mc = mcFactory.newInstance(tree, "tcc");
                System.out.println("=== TCC = " + mc.ComputeAndGet());
                System.out.println("=== TCC message = " + mc.getMessage() + '\n');
            }
            if (wmc1) {
                mc = mcFactory.newInstance(tree, "wmc1");
                System.out.println("=== WMC (methods count) = " + mc.ComputeAndGet());
                System.out.println("=== WMC message = " + mc.getMessage() + '\n');
            }
            if (wmc2) {
                mc = mcFactory.newInstance(tree, "wmc2");
                System.out.println("=== WMC (McCabe) = " + mc.ComputeAndGet());
                System.out.println("=== WMC message = " + mc.getMessage() + '\n');
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GodClassFinderMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GodClassFinderMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
