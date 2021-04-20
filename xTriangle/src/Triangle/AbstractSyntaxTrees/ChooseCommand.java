package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class ChooseCommand extends Command{

  public ChooseCommand(Expression eAST, Cases cAST, SourcePosition thePosition) {
    super(thePosition);
    E = eAST;
    C = cAST;
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitChooseCommand(this, o);
  }

  public Expression E;
  public Cases C;
  
}
