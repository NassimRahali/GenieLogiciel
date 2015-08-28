package calculators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.TCCVisitor;

/**
 *
 * @author nsm
 */
public class TCCCalculator extends MetricCalculator {

    private static final String LOW_COHESION = "COHESION SEEMS LOW";
    private static final String OK_COHESION = "COHESION SEEMS OK";

    private double tcc = 0.0;
    private final TCCVisitor tccVisitor;

    public TCCCalculator(ParseTree pt) {
        this.threshold = 1.0 / 3.0;
        tccVisitor = new TCCVisitor();
        tccVisitor.visit(pt);
    }

    private double calculateTotalMethodPairs() {
        int methodCount = this.tccVisitor.getMethodFieldAccess().size();
        int n = methodCount - 1;
        double totalMethodPairs = n * (n + 1) / 2.0;
        return totalMethodPairs;
    }

    private int determineMethodPairs() {
        List<String> methods = new ArrayList<>(
                this.tccVisitor.getMethodFieldAccess().keySet());
        int methodCount = methods.size();
        int pairs = 0;

        if (methodCount > 1) {
            for (int i = 0; i < methodCount; i++) {
                for (int j = i + 1; j < methodCount; j++) {
                    String firstMethodName = methods.get(i);
                    String secondMethodName = methods.get(j);

                    Set<String> accessesOfFirstMethod = this.tccVisitor
                            .getMethodFieldAccess()
                            .get(firstMethodName);

                    accessesOfFirstMethod.retainAll(this.tccVisitor.getFields());

                    Set<String> accessesOfSecondMethod = this.tccVisitor
                            .getMethodFieldAccess()
                            .get(secondMethodName);

                    accessesOfSecondMethod.retainAll(this.tccVisitor.getFields());

                    Set<String> combinedAccesses = new HashSet<>();

                    combinedAccesses.addAll(accessesOfFirstMethod);
                    combinedAccesses.addAll(accessesOfSecondMethod);

                    if (combinedAccesses.size()
                            < (accessesOfFirstMethod.size()
                            + accessesOfSecondMethod.size())) {
                        pairs++;
                    }
                }
            }
        }
        return pairs;
    }

    @Override
    public double ComputeAndGet() {
        int methodPairs = determineMethodPairs();
        double totalMethodPairs = calculateTotalMethodPairs();
        if (totalMethodPairs > 0) {
            this.tcc = methodPairs / totalMethodPairs;
        }
        return this.tcc;
    }

    @Override
    public String getMessage() {
        return this.tcc < this.threshold ? LOW_COHESION : OK_COHESION;
    }
}
