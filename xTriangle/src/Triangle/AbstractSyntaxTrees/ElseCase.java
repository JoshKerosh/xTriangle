package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class ElseCase extends Cases{

  public ElseCase(Command cAST, SourcePosition thePosition) {
    super(thePosition);
    C = cAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitElseCase(this, null);
  }

  public Command C;
  
}
