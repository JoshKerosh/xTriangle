package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseRange extends Expression{

  public CaseRange(Expression e1AST, Expression e2AST, SourcePosition thePosition) {
    super(thePosition);
    E1 = e1AST;
    E2 = e2AST;
  }
  
  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCaseRange(this, o);
  }

  public Expression E1, E2;
}
