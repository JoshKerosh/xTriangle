package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class CaseLiteralCharacter extends CaseLiteral{

  public CaseLiteralCharacter(CharacterLiteral cAST, SourcePosition thePosition) {
    super(thePosition);
    C = cAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCaseLiteralChart(this, o);
  }
  
  public CharacterLiteral C;
}
