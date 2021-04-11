package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class AssignedDeclaration extends Declaration {

  public AssignedDeclaration(Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
    E = eAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    v.visitAssignedDeclaration(this, o);
    return null;
  }
  
  public Identifier I;
  public Expression E;
}
