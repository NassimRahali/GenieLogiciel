/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package visitors;

import antlr.Java8BaseVisitor;
import antlr.Java8Parser;

/**
 *
 * @author Nassim
 */
public class ATFDVisitor extends Java8BaseVisitor<Void> {

    private int aftdCounter;

    public ATFDVisitor() {
        this.aftdCounter = 0;
    }

    @Override
    public Void visitMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        super.visitMethodInvocation_lf_primary(ctx);
        this.aftdCounter++;
        return null;
    }
    
    @Override
    public Void visitMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        super.visitMethodInvocation_lfno_primary(ctx);
        this.aftdCounter++;
        return null;
    }

    public double getAtfdCounter() {
        return this.aftdCounter;
    }
    
    
}
