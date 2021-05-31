package Triangle.SyntacticAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.AssignVarDeclaration;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.CaseLiteralCharacter;
import Triangle.AbstractSyntaxTrees.CaseLiteralInteger;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRange;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DotVarName;
import Triangle.AbstractSyntaxTrees.ElseCase;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.ControlVarDeclaration;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LongIdentifier;
import Triangle.AbstractSyntaxTrees.LoopDoUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopDoWhileCommand;
import Triangle.AbstractSyntaxTrees.LoopForDoCommand;
import Triangle.AbstractSyntaxTrees.LoopForUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopForWhileCommand;
import Triangle.AbstractSyntaxTrees.LoopUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopWhileCommand;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.PackageDeclaration;
import Triangle.AbstractSyntaxTrees.PackageIdentifier;
import Triangle.AbstractSyntaxTrees.PrivateDeclaration;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.RecursiveFunc;
import Triangle.AbstractSyntaxTrees.RecursiveProc;
import Triangle.AbstractSyntaxTrees.SequentialCaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialCases;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialProcFuncs;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVarName;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVarName;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.AbstractSyntaxTrees.VarNameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;

//Marcos Méndez 2021-04-15

public class XmlWriterVisitor implements Visitor{

  public XmlWriterVisitor(String fileURL){
    setFileWriter(fileURL);
  }

  @Override
  public Object visitAssignCommand(AssignCommand ast, Object o) {
    writeLine("<AssignCommand>");
    ast.V.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</AssignCommand>");
    return null;
  }

  @Override
  public Object visitCallCommand(CallCommand ast, Object o) {
    writeLine("<CallCommand>");
    ast.I.visit(this, null);
    ast.APS.visit(this, null);
    writeLine("</CallCommand>");
    return null;
  }

  @Override
  public Object visitEmptyCommand(EmptyCommand ast, Object o) {
    // commando eliminado 
    writeLine("<EmptyCommand/>");
    return null;
  }

  @Override
  public Object visitIfCommand(IfCommand ast, Object o) {
    writeLine("<IfCommand>");
    ast.E.visit(this, null);
    ast.C1.visit(this, null);
    ast.C2.visit(this, null);
    writeLine("</IfCommand>");
    return null;
  }

  @Override
  public Object visitLetCommand(LetCommand ast, Object o) {
    writeLine("<LetCommand>");
    ast.D.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LetCommand>");
    return null;
  }

  @Override
  public Object visitLoopDoUntilCommand(LoopDoUntilCommand ast, Object o) {
    writeLine("<LoopDoUntilCommand>");
    ast.C.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</LoopDoUntilCommand>");
    return null;
  }

  @Override
  public Object visitLoopDoWhileCommand(LoopDoWhileCommand ast, Object o) {
    writeLine("<LoopDoWhileCommand>");
    ast.C.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</LoopDoWhileCommand>");
    return null;
  }

  @Override
  public Object visitLoopForDoCommand(LoopForDoCommand ast, Object o) {
    writeLine("<LoopForDoCommand>");
    ast.D.visit(this, null);
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LoopForDoCommand>");
    return null;
  }

  @Override
  public Object visitLoopForUntilCommand(LoopForUntilCommand ast, Object o) {
    writeLine("<LoopForUntilCommand>");
    ast.D.visit(this, null);
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    ast.E3.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LoopForUntilCommand>");
    return null;
  }

  @Override
  public Object visitLoopForWhileCommand(LoopForWhileCommand ast, Object o) {
    writeLine("<LoopForWhileCommand>");
    ast.D.visit(this, null);
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    ast.E3.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LoopForWhileCommand>");
    return null;
  }

  @Override
  public Object visitLoopUntilCommand(LoopUntilCommand ast, Object o) {
    writeLine("<LoopUntilCommand>");
    ast.E.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LoopUntilCommand>");
    return null;
  }

  @Override
  public Object visitLoopWhileCommand(LoopWhileCommand ast, Object o) {
    writeLine("<LoopWhileCommand>");
    ast.E.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</LoopWhileCommand>");
    return null;
  }

  @Override
  public Object visitSequentialCommand(SequentialCommand ast, Object o) {
    writeLine("<SequentialCommand>");
    ast.C1.visit(this, null);
    ast.C2.visit(this, null);
    writeLine("</SequentialCommand>");
    return null;
  }

  @Override
  public Object visitWhileCommand(WhileCommand ast, Object o) {
    writeLine("<WhileCommand>");
    ast.E.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</WhileCommand>");
    return null;
  }

  @Override
  public Object visitArrayExpression(ArrayExpression ast, Object o) {
    writeLine("<ArrayExpression>");
    ast.AA.visit(this, null);
    writeLine("</ArrayExpression>");
    return null;
  }

  @Override
  public Object visitBinaryExpression(BinaryExpression ast, Object o) {
    writeLine("<BinaryExpression>");
    ast.E1.visit(this, null);
    ast.O.visit(this, null);
    ast.E2.visit(this, null);
    writeLine("</BinaryExpression>");
    return null;
  }

  @Override
  public Object visitCallExpression(CallExpression ast, Object o) {
    writeLine("<CallExpression>");
    ast.I.visit(this, null);
    ast.APS.visit(this, null);
    writeLine("</CallExpression>");
    return null;
  }

  @Override
  public Object visitCharacterExpression(CharacterExpression ast, Object o) {
    writeLine("<CharacterExpression>");
    ast.CL.visit(this, null);
    writeLine("</CharacterExpression>");
    return null;
  }

  @Override
  public Object visitEmptyExpression(EmptyExpression ast, Object o) {
    writeLine("<EmptyExpression/>");
    return null;
  }

  @Override
  public Object visitIfExpression(IfExpression ast, Object o) {
    writeLine("<IfExpression>");
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    ast.E3.visit(this, null);
    writeLine("</IfExpression>");
    return null;
  }

  @Override
  public Object visitIntegerExpression(IntegerExpression ast, Object o) {
    writeLine("<IntegerExpression>");
    ast.IL.visit(this, null);
    writeLine("</IntegerExpression>");
    return null;
  }

  @Override
  public Object visitLetExpression(LetExpression ast, Object o) {
    writeLine("<LetExpression>");
    ast.D.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</LetExpression>");
    return null;
  }

  @Override
  public Object visitRecordExpression(RecordExpression ast, Object o) {
    writeLine("<RecordExpression>");
    ast.RA.visit(this, null);
    writeLine("</RecordExpression>");
    return null;
  }

  @Override
  public Object visitUnaryExpression(UnaryExpression ast, Object o) {
    writeLine("<UnaryExpression>");
    ast.O.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</UnaryExpression>");
    return null;
  }

  @Override
  public Object visitVarNameExpression(VarNameExpression ast, Object o) {
    writeLine("<VarNameExpression>");
    ast.V.visit(this, null);
    writeLine("</VarNameExpression>");
    return null;
  }

  @Override
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
    writeLine("<BinaryOperatorDeclaration>");
    ast.O.visit(this, null);
    ast.ARG1.visit(this, null);
    ast.ARG2.visit(this, null);
    ast.RES.visit(this, null);
    writeLine("</BinaryOperatorDeclaration>");
    return null;
  }

  @Override
  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
    writeLine("<ConstDeclaration>");
    ast.I.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</ConstDeclaration>");
    return null;
  }

  @Override
  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
    writeLine("<FuncDeclaration>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    ast.T.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</FuncDeclaration>");
    return null;
  }

  @Override
  public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
    writeLine("<ProcDeclaration>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</ProcDeclaration>");
    return null;
  }

  @Override
  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
    writeLine("<SequentialDeclaration>");
    ast.D1.visit(this, null);
    ast.D2.visit(this, null);
    writeLine("</SequentialDeclaration>");
    return null;
  }

  @Override
  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
    writeLine("<TypeDeclaration>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</TypeDeclaration>");
    return null;
  }

  @Override
  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
    writeLine("<UnaryOperatorDeclaration>");
    ast.O.visit(this, null);
    ast.ARG.visit(this, null);
    ast.RES.visit(this, null);
    writeLine("</UnaryOperatorDeclaration>");
    return null;
  }

  @Override
  public Object visitVarDeclaration(VarDeclaration ast, Object o) {
    writeLine("<VarDeclaration>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</VarDeclaration>");
    return null;
  }
    @Override
    public Object visitControlVarDeclaration(ControlVarDeclaration ast, Object o) {
        writeLine("<ControlVarDeclaration>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</ControlVarDeclaration>");
        return null;
    }
    
  @Override
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
    writeLine("<MultipleArrayAggregate>");
    ast.E.visit(this, null);
    ast.AA.visit(this, null);
    writeLine("</MultipleArrayAggregate>");
    return null;
  }

  @Override
  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
    writeLine("<SingleArrayAggregate>");
    ast.E.visit(this, null);
    writeLine("</SingleArrayAggregate>");
    return null;
  }

  @Override
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
    writeLine("<MultipleRecordAggregate>");
    ast.I.visit(this, null);
    ast.E.visit(this, null);
    ast.RA.visit(this, null);
    writeLine("</MultipleRecordAggregate>");
    return null;
  }

  @Override
  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
    writeLine("<SingleRecordAggregate>");
    ast.I.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</SingleRecordAggregate>");
    return null;
  }

  @Override
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
    writeLine("<ConstFormalParameter>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</ConstFormalParameter>");
    return null;
  }

  @Override
  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
    writeLine("<FuncFormalParameter>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</FuncFormalParameter>");
    return null;
  }

  @Override
  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
    writeLine("<ProcFormalParameter>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    writeLine("</ProcFormalParameter>");
    return null;
  }

  @Override
  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
    writeLine("<VarFormalParameter>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</VarFormalParameter>");
    return null;
  }

  @Override
  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
    writeLine("<EmptyFormalParameterSequence/>");
    return null;
  }

  @Override
  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
    writeLine("<MultipleFormalParameterSequence>");
    ast.FP.visit(this, null);
    ast.FPS.visit(this, null);
    writeLine("</MultipleFormalParameterSequence>");
    return null;
  }

  @Override
  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
    writeLine("<SingleFormalParameterSequence>");
    ast.FP.visit(this, null);
    writeLine("</SingleFormalParameterSequence>");
    return null;
  }

  @Override
  public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
    writeLine("<ConstActualParameter>");
    ast.E.visit(this, null);
    writeLine("</ConstActualParameter>");
    return null;
  }

  @Override
  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
    writeLine("<FuncActualParameter>");
    ast.I.visit(this, null);
    writeLine("</FuncActualParameter>");
    return null;
  }

  @Override
  public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
    writeLine("<ProcActualParameter>");
    ast.I.visit(this, null);
    writeLine("</ProcActualParameter>");
    return null;
  }

  @Override
  public Object visitVarActualParameter(VarActualParameter ast, Object o) {
    writeLine("<VarActualParameter>");
    ast.V.visit(this, null);
    writeLine("</VarActualParameter>");
    return null;
  }

  @Override
  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
    writeLine("<EmptyActualParameterSequence/>");
    return null;
  }

  @Override
  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
    writeLine("<MultipleActualParameterSequence>");
    ast.AP.visit(this, null);
    ast.APS.visit(this, null);
    writeLine("</MultipleActualParameterSequence>");
    return null;
  }

  @Override
  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
    writeLine("<SingleActualParameterSequence>");
    ast.AP.visit(this, null);
    writeLine("</SingleActualParameterSequence>");
    return null;
  }

  @Override
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
    writeLine("<AnyTypeDenoter/>");
    return null;
  }

  @Override
  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
    writeLine("<RecordTypeDenoter>");
    ast.IL.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</RecordTypeDenoter>");
    return null;
  }

  @Override
  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
    writeLine("<BoolTypeDenoter/>");
    return null;
  }

  @Override
  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
    writeLine("<CharTypeDenoter/>");
    return null;
  }

  @Override
  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
    writeLine("<ErrorTypeDenoter/>");
    return null;
  }

  @Override
  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
    writeLine("<RecordTypeDenoter>");
    ast.I.visit(this, null);
    writeLine("</RecordTypeDenoter>");
    return null;
  }

  @Override
  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
    writeLine("<IntTypeDenoter/>");
    return null;
  }

  @Override
  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
    writeLine("<RecordTypeDenoter>");
    ast.FT.visit(this, null);
    writeLine("</RecordTypeDenoter>");
    return null;
  }

  @Override
  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
    writeLine("<MultipleFieldTypeDenoter>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    ast.FT.visit(this, null);
    writeLine("</MultipleFieldTypeDenoter>");
    return null;
  }

  @Override
  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
    writeLine("<SingleFieldTypeDenoter>");
    ast.I.visit(this, null);
    ast.T.visit(this, null);
    writeLine("</SingleFieldTypeDenoter>");
    return null;
  }

  @Override
  public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
    writeLine("<Identifier value=\""+ast.spelling+"\"/>");
    return null;
  }

  @Override
  public Object visitIdentifier(Identifier ast, Object o) {
    writeLine("<IntegerLiteral value=\""+ast.spelling+"\"/>");
    return null;
  }

  @Override
  public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
    writeLine("<IntegerLiteral value=\""+ast.spelling+"\"/>");
    return null;
  }

  @Override
  public Object visitOperator(Operator ast, Object o) {
    writeLine("<Operator value=\""+ast.spelling+"\"/>");
    return null;
  }

  @Override
  public Object visitDotVarName(DotVarName ast, Object o) {
    writeLine("<DotVarName>");
    ast.I.visit(this, null);
    ast.V.visit(this, null);
    writeLine("</DotVarName>");
    return null;
  }

  @Override
  public Object visitSimpleVarName(SimpleVarName ast, Object o) {
    writeLine("<SimpleVarName>");
    ast.I.visit(this, null);
    writeLine("</SimpleVarName>");
    return null;
  }

  @Override
  public Object visitSubscriptVarName(SubscriptVarName ast, Object o) {
    writeLine("<SubscriptVarName>");
    ast.V.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</SubscriptVarName>");
    return null;
  }

  @Override
  public Object visitProgram(Program ast, Object o) {
    writeLine("<Program>");
    ast.C.visit(this, null);
    writeLine("</Program>");
    return null;
  }

  @Override
  public Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o) {
    writeLine("<SequentialProcFuncs>");
    ast.PF1.visit(this, null);
    ast.PF2.visit(this, null);
    writeLine("</SequentialProcFuncs>");
    return null;
  }

  @Override
  public Object visitRecursiveProc(RecursiveProc ast, Object o) {
    writeLine("<RecursiveProc>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</RecursiveProc>");
    return null;
  }

  @Override
  public Object visitRecursiveFunc(RecursiveFunc ast, Object o) {
    writeLine("<RecursiveFunc>");
    ast.I.visit(this, null);
    ast.FPS.visit(this, null);
    ast.TD.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</RecursiveFunc>");
    return null;
  }

  @Override
  public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
    writeLine("<RecursiveDeclaration>");
    ast.PF.visit(this, null);
    writeLine("</RecursiveDeclaration>");
    return null;
  }

  @Override
  public Object visitPrivateDeclaration(PrivateDeclaration ast, Object o) {
    writeLine("<PrivateDeclaration>");
    ast.D1.visit(this, null);
    ast.D2.visit(this, null);
    writeLine("</PrivateDeclaration>");
    return null;
  }

  @Override
  public Object visitAssignVarDeclaration(AssignVarDeclaration ast, Object o) {
    writeLine("<AssignVarDeclaration>");
    ast.I.visit(this, null);
    ast.E.visit(this, null);
    writeLine("</AssignVarDeclaration>");
    return null;
  }

  //////////////////////////
  //
  //Cases(Extra)
  //
  /////////////////////////

  @Override
  public Object visitCaseLiteralInteger(CaseLiteralInteger ast, Object o) {
    writeLine("<CaseLiteralInteger>");
    ast.I.visit(this, null);
    writeLine("</CaseLiteralInteger>");
    return null;
  }

  @Override
  public Object visitCaseLiteralChar(CaseLiteralCharacter ast, Object o) {
    writeLine("<CaseLiteralCharacter>");
    ast.C.visit(this, null);
    writeLine("</CaseLiteralCharacter>");
    return null;
  }

  @Override
  public Object visitCaseRange(CaseRange ast, Object o) {
    writeLine("<CaseRange>");
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    writeLine("</CaseRange>");
    return null;
  }

  @Override
  public Object visitCaseLiterals(CaseLiterals ast, Object o) {
    writeLine("<CaseLiterals>");
    ast.E.visit(this, null);
    writeLine("</CaseLiterals>");
    return null;
  }

  @Override
  public Object visitSequentialCaseLiterals(SequentialCaseLiterals ast, Object o){
    writeLine("<SequentialCaseLiterals>");
    ast.E1.visit(this, null);
    ast.E2.visit(this, null);
    writeLine("</SequentialCaseLiterals>");
    return null;
  }

  @Override
  public Object visitElseCase(ElseCase ast, Object o){
    writeLine("<ElseCase>");
    ast.C.visit(this, null);
    writeLine("</ElseCase>");
    return null;
  }

  @Override
  public Object visitCase(Case ast, Object o){
    writeLine("<Case>");
    ast.E.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</Case>");
    return null;
  }

  @Override
  public Object visitSequentialCases(SequentialCases ast, Object o){
    writeLine("<SequentialCases>");
    ast.C1.visit(this, null);
    ast.C2.visit(this, null);
    writeLine("</SequentialCases>");
    return null;
  }

  @Override
  public Object visitChooseCommand(ChooseCommand ast, Object o){
    writeLine("<ChooseCommand>");
    ast.E.visit(this, null);
    ast.C.visit(this, null);
    writeLine("</ChooseCommand>");
    return null;
  }

  @Override
  public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
    writeLine("<PackageIdentifier>");
    ast.I.visit(this, null);
    writeLine("</PackageIdentifier>");
    return null;
  }

  @Override
  public Object visitLongIdentifier(LongIdentifier ast, Object o) {
    writeLine("<LongIdentifier>");
    ast.pI.visit(this, null);
    ast.I.visit(this, null);
    writeLine("</LongIdentifier>");
    return null;
  }

  @Override
  public Object visitPackageDeclaration(PackageDeclaration ast, Object o) {
    writeLine("<PackageDeclaration>");
    ast.pI.visit(this, null);
    ast.D.visit(this, null);
    writeLine("</PackageDeclaration>");
    return null;
  }

  @Override
  public Object visitVname(Vname ast, Object o) {
    writeLine("<Vname>");
    ast.pI.visit(this, null);
    ast.vN.visit(this, null);
    writeLine("</Vname>");
    return null;
  }

  @Override
  public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
    writeLine("<SequentialPackageDeclaration>");
    ast.D1.visit(this, null);
    ast.D2.visit(this, null);
    writeLine("</SequentialPackageDeclaration>");
    return null;
  }

  ////////////////////
  //
  //File functions
  //
  ////////////////////

  private void setFileWriter(String fileURL){
    try {
      fileWriter = new FileWriter(new File(fileURL));
      fileWriter.write(HEADER);
      this.fileURL = fileURL;
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void writeLine(String text){
    try{
      fileWriter.write(text+"\n");
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }
  
  public void end(){
    try {
      fileWriter.close();
      System.out.println("Archivo Xml generado en el siguiente directorio: " + fileURL + "\n");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }


  FileWriter fileWriter;
  final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
  private String fileURL;

}
