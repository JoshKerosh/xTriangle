package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public abstract class CaseLiteral extends Expression{

  public CaseLiteral(SourcePosition thePosition) {
    super(thePosition);
  }
}
