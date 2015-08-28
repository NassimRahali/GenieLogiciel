package calculators;

import org.antlr.v4.runtime.tree.ParseTree;
import visitors.WMCVisitor;

/**
 *
 * @author nsm
 */
public class WMCCalculator extends MetricCalculator {

    private static final String WMC_TOO_HIGH = "WMC SEEMS TOO HIGH";
    private static final String WMC_OK = "WMC SEEMS OK";
    private boolean isMcCabe = false;
    private double result;
    private final WMCVisitor wmcVisitor;

    public WMCCalculator(ParseTree pt, boolean mode) {
        this.threshold = 47.0;
        this.isMcCabe = mode;
        this.wmcVisitor = new WMCVisitor();
        this.wmcVisitor.visit(pt);
    }

    @Override
    public double ComputeAndGet() {
        if (this.isMcCabe) {
            this.wmcVisitor.getMapMethodComplexity().
                    forEach((key, value) -> {this.result += value.get();});
        } else {
            this.result = this.wmcVisitor.getMapMethodComplexity().size();
        }
        return result;
    }

    @Override
    public String getMessage() {
        return this.result >= this.threshold ? WMC_TOO_HIGH : WMC_OK;
    }
}
