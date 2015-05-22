/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculators.interfaces;

/**
 *
 * @author nsm
 */
public interface IMetric {
    public double ComputeAndGet();
    public void setThreshold(double t);
    public String getMessage();

}
