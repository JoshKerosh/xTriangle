/*
  Marcos Mendez Hidalgo 2021-04-10
*/

package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public abstract class ProcFuncs extends Declaration{

  public ProcFuncs(SourcePosition thePosition) {
    super(thePosition);
  }
  
  public abstract Object visitSelf(Visitor v, Object o);
}
