/*
 * RAHALI Nassim
 * M18
 * 2014-2015
 */
package visitors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import antlr.Java8BaseVisitor;
import antlr.Java8Parser.ExpressionNameContext;
import antlr.Java8Parser.FieldAccessContext;
import antlr.Java8Parser.FieldDeclarationContext;
import antlr.Java8Parser.MethodDeclaratorContext;
import antlr.Java8Parser.MethodModifierContext;
import antlr.Java8Parser.VariableDeclaratorContext;
import antlr.Java8Parser.VariableDeclaratorListContext;

/**
 *
 * @author Nassim
 */
public class TCCVisitor extends Java8BaseVisitor<Void> {

    private Map<String, Set<String>> methodAccess;
    private Set<String> classFields;
    private String currentMethodName;
    private boolean isPublicMethod = false;

    public TCCVisitor() {
        this.classFields = new HashSet<>();
        this.methodAccess = new HashMap<>();
    }

    @Override
    public Void visitFieldDeclaration(FieldDeclarationContext ctx) {
        super.visitFieldDeclaration(ctx);
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof VariableDeclaratorListContext) {
                for (int j = 0; j < ctx.getChild(i).getChildCount(); j++) {
                    // We also have terminal nodes
                    ParseTree pTree = ctx.getChild(i).getChild(j);
                    if (pTree instanceof VariableDeclaratorContext) {
                        classFields.add(ctx.getChild(i).getChild(j).getText());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Void visitMethodModifier(MethodModifierContext ctx) {
        super.visitMethodModifier(ctx);
        // True if it's a public method
        this.isPublicMethod = ctx.getText().equals("public");
        return null;
    }

    @Override
    public Void visitMethodDeclarator(MethodDeclaratorContext ctx) {
        super.visitMethodDeclarator(ctx);
        // Method name
        if (this.isPublicMethod) {
            this.currentMethodName = ctx.getText();
            this.methodAccess.put(currentMethodName, new HashSet<>());
        }
        return null;
    }

    @Override
    public Void visitExpressionName(ExpressionNameContext ctx) {
        super.visitExpressionName(ctx);
        if (this.isPublicMethod && this.currentMethodName != null) {
            Set<String> methodAccess
                    = this.methodAccess.get(this.currentMethodName);
            methodAccess.add(ctx.getText());

        }

        return null;
    }

    // To deal with this.FIELD_NAME
    @Override
    public Void visitFieldAccess(FieldAccessContext ctx) {
        super.visitFieldAccess(ctx);
        if (this.isPublicMethod && this.currentMethodName != null) {
            Set<String> methodAccess
                    = this.methodAccess.get(this.currentMethodName);

            for (int i = 0; i < ctx.getChildCount(); i++) {
                if (ctx.getChild(i) instanceof TerminalNodeImpl
                        && !ctx.getChild(i).getText().equals(".")) {
                    classFields.add(ctx.getChild(i).getText());
                    methodAccess.add(ctx.getChild(i).getText());
                }
            }
        }
        return null;
    }

    public Set<String> getFields() {
        return classFields;
    }

    public Map<String, Set<String>> getMethodFieldAccess() {
        return methodAccess;
    }
}
