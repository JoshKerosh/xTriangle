/*
  Marcos Mendez Hidalgo 2021-04-11
*/

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

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
