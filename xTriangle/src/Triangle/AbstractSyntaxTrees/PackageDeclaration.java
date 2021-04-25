package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageDeclaration extends Declaration{

    public PackageDeclaration(Identifier pIAST, Declaration dAST, SourcePosition thePosition) {
        super(thePosition);
        pI = pIAST;
        D = dAST;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitPackageDeclaration(this, o);
    }

    public Identifier pI;
    public Declaration D;
    
}
