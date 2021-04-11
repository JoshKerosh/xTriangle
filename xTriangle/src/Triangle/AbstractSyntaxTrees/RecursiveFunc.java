package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class RecursiveFunc extends ProcFuncs{

  public RecursiveFunc(Identifier iAST, FormalParameterSequence fAST, TypeDenoter tAST, Expression eAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
    FPS = fAST;
    TD = tAST;
    E = eAST;

  }

  public Object visit(Visitor v, Object o) { 
    return v.visitRecursiveFunc(this, o); 
  }

  public Identifier I;
  public FormalParameterSequence FPS;
  public TypeDenoter TD;
  public Expression E;
}
