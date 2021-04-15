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
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
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
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialProcFuncs;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;

//Marcos Mendez 2021-04-15

public class XmlWriterVisitor implements Visitor{

  FileWriter fileWriter;
  final String HEADER = "<?xml version= 1.0 standalone=false ?>\n";

  public XmlWriterVisitor(String fileURL){
    setFileWriter(fileURL);
  }

  @Override
  public Object visitAssignCommand(AssignCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitCallCommand(CallCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitEmptyCommand(EmptyCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIfCommand(IfCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLetCommand(LetCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopDoUntilCommand(LoopDoUntilCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopDoWhileCommand(LoopDoWhileCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopForDoCommand(LoopForDoCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopForUntilCommand(LoopForUntilCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopForWhileCommand(LoopForWhileCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopUntilCommand(LoopUntilCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLoopWhileCommand(LoopWhileCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSequentialCommand(SequentialCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitWhileCommand(WhileCommand ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitArrayExpression(ArrayExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitBinaryExpression(BinaryExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitCallExpression(CallExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitCharacterExpression(CharacterExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitEmptyExpression(EmptyExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIfExpression(IfExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIntegerExpression(IntegerExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitLetExpression(LetExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecordExpression(RecordExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitUnaryExpression(UnaryExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitVnameExpression(VnameExpression ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitVarDeclaration(VarDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitVarActualParameter(VarActualParameter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIdentifier(Identifier ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitOperator(Operator ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitDotVname(DotVname ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSimpleVname(SimpleVname ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSubscriptVname(SubscriptVname ast, Object o) {
    // TODO Auto-generated method stub
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitSequentialProcFuncsSelf(SequentialProcFuncs ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecursiveProc(RecursiveProc ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecursiveProcSelf(RecursiveProc ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecursiveFunc(RecursiveFunc ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecursiveFuncSelf(RecursiveFunc ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitPrivateDeclaration(PrivateDeclaration ast, Object o) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object visitAssignVarDeclaration(AssignVarDeclaration ast, Object o) {
    // TODO Auto-generated method stub
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
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
