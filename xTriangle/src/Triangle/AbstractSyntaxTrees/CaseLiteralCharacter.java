package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class CaseLiteralCharacter extends CaseLiteral{

  public CaseLiteralCharacter(CharacterLiteral cAST, SourcePosition thePosition) {
    super(thePosition);
    C = cAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitCaseLiteralChar(this, o);
  }
  
  public CharacterLiteral C;
}
