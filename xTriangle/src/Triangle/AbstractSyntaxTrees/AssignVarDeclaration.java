package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class AssignVarDeclaration extends Declaration {

  public AssignVarDeclaration(Identifier iAST, Expression eAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
    E = eAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitAssignVarDeclaration(this, o);
  }
  
  public Identifier I;
  public Expression E;
}
