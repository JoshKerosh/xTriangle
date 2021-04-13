/*
 * @(#)LoopWhileCommand.java 
 * Joshua Jimenez, Marco Mendez, maria Cortez
 * < Extension de Triangulo >
 * Proyecto Compiladores e Interpretes, 2021
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopDoWhileCommand extends Command {

  public LoopDoWhileCommand (Command cAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    C = cAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopDoWhileCommand(this, o);
  }

  public Command C;
  public Expression E;
  
}
