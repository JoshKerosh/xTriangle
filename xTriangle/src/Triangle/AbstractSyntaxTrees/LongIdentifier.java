package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LongIdentifier extends Identifier{

    public LongIdentifier(String theSpelling, Identifier pIAST, Identifier iAST,SourcePosition thePosition) {
        super(theSpelling, thePosition);
        pI = pIAST;  
        I = iAST; 
    }

    public Object visit(Visitor v, Object o) {
        return v.visitLongIdentifier(this, o);
    }

    public Identifier pI;
    public Identifier I;
    
}
