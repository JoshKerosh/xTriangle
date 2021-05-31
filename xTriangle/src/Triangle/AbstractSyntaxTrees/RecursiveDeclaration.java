package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class RecursiveDeclaration extends Declaration{

  public RecursiveDeclaration(ProcFuncs pfAST, SourcePosition thePosition) {
    super(thePosition);
    PF = pfAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitRecursiveDeclaration(this, o);
  }

  public ProcFuncs PF;
}
