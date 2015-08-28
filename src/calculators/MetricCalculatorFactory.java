/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author nsm
 */
public class MetricCalculatorFactory {

    public MetricCalculatorFactory() {
    }

    // Return the correct instance of calculator.
    // Type of calculator :
    // ATFD, LCOM4, WMC1, WMC2 (McCabe), TCC.
    public MetricCalculator newInstance(ParseTree t, String type) {
        switch (type) {
            case "atfd":
                return new ATFDCalculator(t);
            case "lcom4":
                return new LCOM4Calculator(t);
            case "wmc1":
                return new WMCCalculator(t, false);
            case "wmc2":
                return new WMCCalculator(t, true);
            case "tcc":
                return new TCCCalculator(t);
            default:
                break;
        }
        return null;
    }
}
