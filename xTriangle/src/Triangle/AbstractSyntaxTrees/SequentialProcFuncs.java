package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

//Marcos Méndez
//AST Nuevo

public class SequentialProcFuncs extends ProcFuncs{
  
  public SequentialProcFuncs(ProcFuncs PF1, ProcFuncs PF2, SourcePosition thePosition) {
    super (thePosition);
    this.PF1 = PF1;
    this.PF2 = PF2;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitSequentialProcFuncs(this, o);
  }

  public ProcFuncs PF1, PF2;

  @Override
  public Object visitRec(Visitor v, Object o) {
    return v.visitSequentialProcFuncsRec(this, o);
  }
}
