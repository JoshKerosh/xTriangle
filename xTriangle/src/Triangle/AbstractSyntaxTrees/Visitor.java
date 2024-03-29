/*
 * @(#)Visitor.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 *
 */

package Triangle.AbstractSyntaxTrees;

public interface Visitor {

  // Commands
  public abstract Object visitAssignCommand(AssignCommand ast, Object o);
  public abstract Object visitCallCommand(CallCommand ast, Object o);
  public abstract Object visitEmptyCommand(EmptyCommand ast, Object o);
  public abstract Object visitIfCommand(IfCommand ast, Object o);
  public abstract Object visitLetCommand(LetCommand ast, Object o);
  //< extended> ojo Checker, TableVisitor, TreeVisitor, LayoutVisitor, Encoder
  public abstract Object visitLoopDoUntilCommand(LoopDoUntilCommand ast, Object o);//nuevo
  public abstract Object visitLoopDoWhileCommand(LoopDoWhileCommand ast, Object o);//nuevo
  public abstract Object visitLoopForDoCommand(LoopForDoCommand ast, Object o);//nuevo
  public abstract Object visitLoopForUntilCommand(LoopForUntilCommand ast, Object o);//nuevo
  public abstract Object visitLoopForWhileCommand(LoopForWhileCommand ast, Object o);//nuevo
  public abstract Object visitLoopUntilCommand(LoopUntilCommand ast, Object o);//nuevo
  public abstract Object visitLoopWhileCommand(LoopWhileCommand ast, Object o);//nuevo
  //< extended>
  public abstract Object visitSequentialCommand(SequentialCommand ast, Object o);
  public abstract Object visitWhileCommand(WhileCommand ast, Object o);

  // Expressions
  public abstract Object visitArrayExpression(ArrayExpression ast, Object o);
  public abstract Object visitBinaryExpression(BinaryExpression ast, Object o);
  public abstract Object visitCallExpression(CallExpression ast, Object o);
  public abstract Object visitCharacterExpression(CharacterExpression ast, Object o);
  public abstract Object visitEmptyExpression(EmptyExpression ast, Object o);
  public abstract Object visitIfExpression(IfExpression ast, Object o);
  public abstract Object visitIntegerExpression(IntegerExpression ast, Object o);
  public abstract Object visitLetExpression(LetExpression ast, Object o);
  public abstract Object visitRecordExpression(RecordExpression ast, Object o);
  public abstract Object visitUnaryExpression(UnaryExpression ast, Object o);
  public abstract Object visitVarNameExpression(VarNameExpression ast, Object o);

  // Declarations
  public abstract Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o);
  public abstract Object visitConstDeclaration(ConstDeclaration ast, Object o);
  public abstract Object visitFuncDeclaration(FuncDeclaration ast, Object o);
  public abstract Object visitProcDeclaration(ProcDeclaration ast, Object o);
  public abstract Object visitSequentialDeclaration(SequentialDeclaration ast, Object o);
  public abstract Object visitTypeDeclaration(TypeDeclaration ast, Object o);
  public abstract Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o);
  public abstract Object visitVarDeclaration(VarDeclaration ast, Object o);
  public abstract Object visitControlVarDeclaration(ControlVarDeclaration ast, Object o);

  // Array Aggregates
  public abstract Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o);
  public abstract Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o);

  // Record Aggregates
  public abstract Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o);
  public abstract Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o);

  // Formal Parameters
  public abstract Object visitConstFormalParameter(ConstFormalParameter ast, Object o);
  public abstract Object visitFuncFormalParameter(FuncFormalParameter ast, Object o);
  public abstract Object visitProcFormalParameter(ProcFormalParameter ast, Object o);
  public abstract Object visitVarFormalParameter(VarFormalParameter ast, Object o);

  public abstract Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o);
  public abstract Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o);
  public abstract Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o);

  // Actual Parameters
  public abstract Object visitConstActualParameter(ConstActualParameter ast, Object o);
  public abstract Object visitFuncActualParameter(FuncActualParameter ast, Object o);
  public abstract Object visitProcActualParameter(ProcActualParameter ast, Object o);
  public abstract Object visitVarActualParameter(VarActualParameter ast, Object o);

  public abstract Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o);
  public abstract Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o);
  public abstract Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o);

  // Type Denoters
  public abstract Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o);
  public abstract Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o);
  public abstract Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o);
  public abstract Object visitCharTypeDenoter(CharTypeDenoter ast, Object o);
  public abstract Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o);
  public abstract Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o);
  public abstract Object visitIntTypeDenoter(IntTypeDenoter ast, Object o);
  public abstract Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o);

  public abstract Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o);
  public abstract Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o);

  // Literals, Identifiers and Operators
  public abstract Object visitCharacterLiteral(CharacterLiteral ast, Object o);
  public abstract Object visitIdentifier(Identifier ast, Object o);
  public abstract Object visitIntegerLiteral(IntegerLiteral ast, Object o);
  public abstract Object visitOperator(Operator ast, Object o);
  //María José Cortés
  public abstract Object visitPackageIdentifier(PackageIdentifier ast, Object o);
  public abstract Object visitLongIdentifier(LongIdentifier ast, Object o);
  public abstract Object visitPackageDeclaration(PackageDeclaration ast, Object o);
  public abstract Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o);

  // Value-or-variable names
  public abstract Object visitDotVarName(DotVarName ast, Object o);
  public abstract Object visitSimpleVarName(SimpleVarName ast, Object o);
  public abstract Object visitSubscriptVarName(SubscriptVarName ast, Object o);
  public abstract Object visitVname(Vname ast, Object o);

  // Programs
  public abstract Object visitProgram(Program ast, Object o);

  //SequentialProcFuncs Marcos Méndez 2021-04-11
  public abstract Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o);
  public abstract Object visitSequentialProcFuncsRec(SequentialProcFuncs ast, Object o);

  //RecursiveProc Marcos Méndez 2021-04-11
  public abstract Object visitRecursiveProc(RecursiveProc ast, Object o);
  public abstract Object visitRecursiveProcRec(RecursiveProc ast, Object o);
  
  //RecursiveFunc Marcos Méndez 2021-04-11
  public abstract Object visitRecursiveFunc(RecursiveFunc ast, Object o);
  public abstract Object visitRecursiveFuncRec(RecursiveFunc ast, Object o);
  //RecursiveDeclaration Marcos Méndez 2021-04-11
  public abstract Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o);

  //PrivateDeclaration Marcos Méndez 2021-04-11
  public abstract Object visitPrivateDeclaration(PrivateDeclaration ast, Object o);

  //AssignDeclaration Marcos Méndez 2021-04-11
  public abstract Object visitAssignVarDeclaration(AssignVarDeclaration ast, Object o);

  //////////////////////////
  //
  //Marcos Méndez 2021-04-20
  //Cases(Extra)
  //
  /////////////////////////

  public abstract Object visitCaseLiteralInteger(CaseLiteralInteger ast, Object o);
  public abstract Object visitCaseLiteralChar(CaseLiteralCharacter ast, Object o);
  public abstract Object visitCaseRange(CaseRange ast, Object o);
  public abstract Object visitCaseLiterals(CaseLiterals ast, Object o);
  public abstract Object visitSequentialCaseLiterals(SequentialCaseLiterals ast, Object o);
  public abstract Object visitElseCase(ElseCase ast, Object o);
  public abstract Object visitCase(Case ast, Object o);
  public abstract Object visitSequentialCases(SequentialCases ast, Object o);
  public abstract Object visitChooseCommand(ChooseCommand ast, Object o);
}
