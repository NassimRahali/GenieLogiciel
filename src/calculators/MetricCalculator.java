/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators;

/**
 *
 * @author nsm
 */
public abstract class MetricCalculator {

    protected double threshold;

    public abstract double ComputeAndGet();

    protected void setThreshold(double t) {
        this.threshold = t;
    }

    public abstract String getMessage();
}
