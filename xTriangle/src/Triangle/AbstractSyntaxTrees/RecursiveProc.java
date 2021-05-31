package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

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
