/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import visitors.ATFDVisitor;
import calculators.interfaces.IMetric;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author nsm
 */
public class ATFDCalculator implements IMetric {

    private static final String HIGH_ATFD = "ATFD SEEMS HIGH";
    private static final String OK_ATFD = "ATFD SEEMS OK";
    
    private double result;
    private final ATFDVisitor atfdVisitor;
    private double threshold;
    
    public ATFDCalculator(ParseTree tree) {
        this.threshold = 8;
        this.atfdVisitor = new ATFDVisitor();
        this.atfdVisitor.visit(tree);
    }

    @Override
    public double ComputeAndGet() {
        this.result = this.atfdVisitor.getAtfdCounter();
        return this.result;
    }

    @Override
    public void setThreshold(double t) {
        this.threshold = t;
    }

    @Override
    public String getMessage() {
        return this.result < this.threshold ? OK_ATFD : HIGH_ATFD;
    }
}
