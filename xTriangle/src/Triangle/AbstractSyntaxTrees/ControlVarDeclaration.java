/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Josh
 */
public class ControlVarDeclaration extends Declaration {

  public ControlVarDeclaration(Identifier iAST, SourcePosition thePosition) {
    super(thePosition);
    I = iAST;
    T = new IntTypeDenoter(thePosition);
  }

  @Override
  public Object visit(Visitor v, Object o) {
    return v.visitControlVarDeclaration(this, o);
  }
  
  public Identifier I;
  public TypeDenoter T;
}
