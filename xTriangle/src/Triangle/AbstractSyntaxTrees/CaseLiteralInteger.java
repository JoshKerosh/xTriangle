package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiteralInteger extends CaseLiteral{

  public CaseLiteralInteger(IntegerLiteral iAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCaseLiteralInteger(this, o);
  }
  
  public IntegerLiteral I;
}
