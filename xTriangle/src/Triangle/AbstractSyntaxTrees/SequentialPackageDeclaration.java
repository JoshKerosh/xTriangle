package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialPackageDeclaration extends Declaration {

    public SequentialPackageDeclaration(Declaration pDAST, Declaration pD2AST, SourcePosition thePosition) {
        super (thePosition);
        D1 = pDAST;
        D2 = pD2AST;
        
    }
    public Object visit(Visitor v, Object o) {
        return v.visitSequentialPackageDeclaration(this, o);
    }

    public Declaration D1, D2;
}
