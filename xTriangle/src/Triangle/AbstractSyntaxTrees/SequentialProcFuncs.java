/*
  Marcos Mendez Hidalgo 2021-04-10
  Class to represent a new AST SequentialProcFuncs.
*/

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class SequentialProcFuncs extends ProcFuncs{
  
  public SequentialProcFuncs(ProcFuncs PF1, ProcFuncs PF2, SourcePosition thePosition) {
    super (thePosition);
    this.PF1 = PF1;
    this.PF2 = PF2;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSequentialProcFuncs(this, o);
  }

  @Override
  public Object visitSelf(Visitor v, Object o) {
    return v.visitSequentialProcFuncsSelf(this, o);
  }

  public ProcFuncs PF1;
  public ProcFuncs PF2;


}
