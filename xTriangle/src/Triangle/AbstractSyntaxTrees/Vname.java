package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class Vname extends VarName {

    public Vname(Identifier pIAST, VarName vNAST, SourcePosition thePosition) {
        super(thePosition);
        pI = pIAST;
        vN = vNAST;
    }

    @Override
    public Object visit(Visitor v, Object o) {
        return v.visitVname(this, o);
    }

    public Identifier pI;
    public VarName vN;
    
}
