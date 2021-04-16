/*
  Marcos Mendez Hidalgo 2021-04-11
*/

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveProc extends ProcFuncs{
  public RecursiveProc(Identifier iAST, FormalParameterSequence fpsAST, Command cAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
    FPS = fpsAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitRecursiveProc(this, o);
  }

  public Identifier I;
  public FormalParameterSequence FPS;
  public Command C;

}
