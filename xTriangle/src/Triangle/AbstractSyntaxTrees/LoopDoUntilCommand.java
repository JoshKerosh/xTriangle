/*
 * @(#)LoopWhileCommand.java 
 * Joshua Jimenez, Marco Méndez, maria Cortez
 * < Extension de Triangulo >
 * Proyecto Compiladores e Interpretes, 2021
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopDoUntilCommand extends Command {

  public LoopDoUntilCommand (Command cAST, Expression eAST, SourcePosition thePosition) {
    super (thePosition);
    C = cAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopDoUntilCommand(this, o);
  }
  
  public Command C;
  public Expression E;
  
}
