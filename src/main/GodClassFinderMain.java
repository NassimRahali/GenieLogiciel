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
import calculators.ATFDCalculator;
import calculators.LCOM4Calculator;
import calculators.TCCCalculator;
import calculators.WMCCalculator;
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

    public static void main(String[] args) throws Exception {

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

        // WMC
        argumentParser
                .addArgument("-w", "--wmc")
                .type(Integer.class)
                .setDefault("1")
                .help("Weighted method count "
                        + "("
                        + "mode 1 = number of methods, "
                        + "mode 2 = sum of cyclomatic complexity"
                        + ")");

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
        int wmc = namespace.getInt("wmc");

        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filename));
        Java8Lexer lexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();

        System.out.println("==================================");
        System.out.println("=== Your file has been parsed. ===");
        System.out.println("==================================\n");
        System.out.println("Results :\n");

        if (atfd) {
            ATFDCalculator atfdc = new ATFDCalculator(tree);
            System.out.println("=== AFTD = " + atfdc.ComputeAndGet());
            System.out.println("=== AFTD message = " + atfdc.getMessage() + '\n');
        }
        if (lcom4) {
            LCOM4Calculator lcom4c = new LCOM4Calculator(tree);
            System.out.println("=== LCOM4 = " + lcom4c.ComputeAndGet());
            System.out.println("=== LCOM4 message = " + lcom4c.getMessage() + '\n');
        }
        if (tcc) {
            TCCCalculator tccc = new TCCCalculator(tree);
            System.out.println("=== TCC = " + tccc.ComputeAndGet());
            System.out.println("=== TCC message = " + tccc.getMessage() + '\n');
        }
        if (wmc == 1) {
            WMCCalculator wmcc = new WMCCalculator(tree, false);
            System.out.println("=== WMC = " + wmcc.ComputeAndGet());
            System.out.println("=== WMC message = " + wmcc.getMessage());
        }
        if (wmc == 2) {
            WMCCalculator wmcc = new WMCCalculator(tree, true);
            System.out.println("=== WMC = " + wmcc.ComputeAndGet());
            System.out.println("=== WMC message = " + wmcc.getMessage());
        }
    }
}
