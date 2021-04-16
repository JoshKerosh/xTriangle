/*
 * @(#)LoopWhileCommand.java 
 * Joshua Jimenez, Marco Mendez, maria Cortez
 * < Extension de Triangulo >
 * Proyecto Compiladores e Interpretes, 2021
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopUntilCommand extends Command {

  public LoopUntilCommand (Expression eAST, Command cAST, SourcePosition thePosition) {
    super (thePosition);
    E = eAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitLoopUntilCommand(this, o);
  }

  public Expression E;
  public Command C;
}
