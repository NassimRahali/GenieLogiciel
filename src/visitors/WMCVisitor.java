/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package visitors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import antlr.Java8BaseVisitor;
import antlr.Java8Parser;

/**
 *
 * @author Nassim
 */
public class WMCVisitor extends Java8BaseVisitor<Void> {

    private String currentMethod;
    private int methodCounter;
    private Map<String, AtomicInteger> mapMethodComplexity;

    public WMCVisitor() {
        this.methodCounter = 0;
        this.mapMethodComplexity = new HashMap<>();
    }

    @Override
    public Void visitConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {
        super.visitConstructorDeclarator(ctx);
        this.methodCounter++;
        this.currentMethod = ctx.getText();
        this.mapMethodComplexity.put(currentMethod, new AtomicInteger(1));
        return null;
    }

    @Override
    public Void visitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        super.visitMethodDeclarator(ctx);
        this.methodCounter++;
        this.currentMethod = ctx.getText();
        this.mapMethodComplexity.put(currentMethod, new AtomicInteger(1));
        return null;
    }

    @Override
    public Void visitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        super.visitIfThenStatement(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    @Override
    public Void visitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        super.visitIfThenElseStatement(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    @Override
    public Void visitSwitchLabel(Java8Parser.SwitchLabelContext ctx) {
        super.visitSwitchLabel(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    @Override
    public Void visitDoStatement(Java8Parser.DoStatementContext ctx) {
        super.visitDoStatement(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    @Override
    public Void visitWhileStatement(Java8Parser.WhileStatementContext ctx) {
        super.visitWhileStatement(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    @Override
    public Void visitForStatement(Java8Parser.ForStatementContext ctx) {
        super.visitForStatement(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

//    @Override
//    public Void visitConditionalOrExpression(Java8Parser.ConditionalOrExpressionContext ctx) {
//        super.visitConditionalOrExpression(ctx);
//        this.mapMethodComplexity.get(this.currentMethod).incrementAndGet();
//        return null;
//    }
//
//    @Override
//    public Void visitConditionalAndExpression(Java8Parser.ConditionalAndExpressionContext ctx) {
//        super.visitConditionalAndExpression(ctx);
//        this.mapMethodComplexity.get(this.currentMethod).incrementAndGet();
//        return null;
//    }

    @Override
    public Void visitCatchClause(Java8Parser.CatchClauseContext ctx) {
        super.visitCatchClause(ctx);
        this.mapMethodComplexity.get(currentMethod).incrementAndGet();
        return null;
    }

    public int getMethodCounter() {
        return methodCounter;
    }

    public HashMap<String, AtomicInteger> getMapMethodComplexity() {
        return (HashMap<String, AtomicInteger>) mapMethodComplexity;
    }

}
