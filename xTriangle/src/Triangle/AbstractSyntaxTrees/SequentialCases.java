package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialCases extends Cases{

  public SequentialCases(Cases c1AST, Cases c2AST, SourcePosition thePosition) {
    super(thePosition);
    C1 = c1AST;
    C2 = c2AST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitSequentialCases(this, o);
  }
  
  public Cases C1, C2;
}
