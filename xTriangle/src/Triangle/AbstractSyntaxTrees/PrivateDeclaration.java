package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class PrivateDeclaration extends Declaration{

  public PrivateDeclaration(Declaration d1AST, Declaration d2AST, SourcePosition thePosition) {
    super(thePosition);
    D1 = d1AST;
    D2 = d2AST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitPrivateDeclaration(this, o);
  }
  
  public Declaration D1, D2;
}
