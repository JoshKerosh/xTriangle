/*
 * @(#)LoopWhileCommand.java 
 * Joshua Jimenez, Marco Mendez, maria Cortez
 * < Extension de Triangulo >
 * Proyecto Compiladores e Interpretes, 2021
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopForDoCommand extends Command {

  public LoopForDoCommand (Declaration dAST, Expression e1AST, Expression e2AST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    D = dAST;
    E1 = e1AST;
    E2 = e2AST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopForDoCommand(this, o);
  }
  
  public Declaration D;
  public Expression E1;
  public Expression E2;
  public Command C;
  
}