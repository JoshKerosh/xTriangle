package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class Case extends Cases{

  public Case(Expression eAST, Command cAST, SourcePosition thePosition) {
    super(thePosition);
    E = eAST;
    C = cAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCase(this, o);
  }

  public Expression E;
  public Command C;
  
}
