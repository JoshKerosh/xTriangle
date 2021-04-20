package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiterals extends Expression{

  public CaseLiterals(Expression eAST, SourcePosition thePosition) {
    super(thePosition);
    E = eAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCaseLiterals(this, o);
  }

  public Expression E;
  
}
