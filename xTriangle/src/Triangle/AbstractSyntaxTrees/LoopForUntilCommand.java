/*
 * @(#)LoopWhileCommand.java 
 * Joshua Jimenez, Marco Méndez, maria Cortez
 * < Extension de Triangulo >
 * Proyecto Compiladores e Interpretes, 2021
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopForUntilCommand extends Command {

  public LoopForUntilCommand (Declaration dAST, Expression e1AST, Expression e2AST, Expression e3AST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    D = dAST;
    E1 = e1AST;
    E2 = e2AST;
    E3 = e3AST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopForUntilCommand(this, o);
  }
  
  public Declaration D;
  public Expression E1;
  public Expression E2;
  public Expression E3;
  public Command C;
  
}