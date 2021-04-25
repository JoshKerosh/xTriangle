package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PackageIdentifier extends Identifier{

    public PackageIdentifier(String theSpelling, Identifier iAST, SourcePosition thePosition) {
        super(theSpelling, thePosition);
        I = iAST;
    }


    public Object visit(Visitor v, Object o) {
        return v.visitPackageIdentifier(this, o);
    }

    public Identifier I;
    
}
