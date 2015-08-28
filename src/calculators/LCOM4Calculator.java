package calculators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.LCOM4Visitor;

/**
 *
 * @author nsm
 */
public class LCOM4Calculator extends MetricCalculator {

    private double lcom4 = 0.0;
    private LCOM4Visitor lcom4Visitor;

    public LCOM4Calculator(ParseTree pt) {
        this.threshold = 1;
        this.lcom4Visitor = new LCOM4Visitor();
        this.lcom4Visitor.visit(pt);
    }

    private int determineLCOM4() {
        List<String> methods = new ArrayList<>(
                this.lcom4Visitor.getMethodFieldAccess().keySet());
        int methodCount = methods.size();
        Set<String> relatedMethods = new HashSet<>();

        if (methodCount > 1) {
            for (int i = 0; i < methodCount; i++) {
                for (int j = i + 1; j < methodCount; j++) {
                    String firstMethodName = methods.get(i);
                    String secondMethodName = methods.get(j);

                    Set<String> accessesOfMethod = this.lcom4Visitor
                            .getMethodFieldAccess()
                            .get(firstMethodName);

                    accessesOfMethod.retainAll(this.lcom4Visitor.getFields());

                    Set<String> accessesOfSecondMethod = this.lcom4Visitor
                            .getMethodFieldAccess()
                            .get(secondMethodName);

                    accessesOfSecondMethod.retainAll(this.lcom4Visitor.getFields());

                    Set<String> combinedAccesses = new HashSet<>();
                    combinedAccesses.addAll(accessesOfMethod);
                    combinedAccesses.addAll(accessesOfSecondMethod);

                    if (combinedAccesses.size()
                            < (accessesOfMethod.size()
                            + accessesOfSecondMethod.size())) {
                        relatedMethods.add(firstMethodName);
                        relatedMethods.add(secondMethodName);
                    }
                }
            }
        }
        return methods.size() - relatedMethods.size() - 1;
    }

    @Override
    public double ComputeAndGet() {
        this.lcom4 = determineLCOM4();
        return this.lcom4;
    }

    @Override
    public String getMessage() {
        if (this.threshold == this.lcom4) {
            return "LCOM4 SEEMS OK";
        } else if (this.threshold < this.lcom4) {
            return "LCOM4 SEEMS HIGH";
        } else {
            return "LCOM4 SEEMS OK";
        }
    }
}
