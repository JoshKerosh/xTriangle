/*
 * @(#)Parser.java                        2.1 2003/10/07
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
 */

package Triangle.SyntacticAnalyzer;

import Triangle.ErrorReporter;
import Triangle.AbstractSyntaxTrees.ActualParameter;
import Triangle.AbstractSyntaxTrees.ActualParameterSequence;
import Triangle.AbstractSyntaxTrees.ArrayAggregate;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.AssignVarDeclaration;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.CaseLiteralCharacter;
import Triangle.AbstractSyntaxTrees.CaseLiteralInteger;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.CaseRange;
import Triangle.AbstractSyntaxTrees.Cases;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.Command;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.DotVarName;
import Triangle.AbstractSyntaxTrees.ElseCase;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.Expression;
import Triangle.AbstractSyntaxTrees.FieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.FormalParameter;
import Triangle.AbstractSyntaxTrees.FormalParameterSequence;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.ControlVarDeclaration;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
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
import Triangle.AbstractSyntaxTrees.ProcFuncs;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordAggregate;
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
import Triangle.AbstractSyntaxTrees.TypeDenoter;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.VarName;
import Triangle.AbstractSyntaxTrees.VarNameExpression;
import Triangle.AbstractSyntaxTrees.Vname;

public class Parser {

  private Scanner lexicalAnalyser;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePosition previousTokenPosition;

  public Parser(Scanner lexer, ErrorReporter reporter) {
    lexicalAnalyser = lexer;
    errorReporter = reporter;
    previousTokenPosition = new SourcePosition();
  }

// accept checks whether the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.position;
      currentToken = lexicalAnalyser.scan();
    } else {
      syntacticError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  void acceptIt() {
    previousTokenPosition = currentToken.position;
    currentToken = lexicalAnalyser.scan();
  }

// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

///////////////////////////////////////////////////////////////////////////////
//
// PROGRAMS
//
///////////////////////////////////////////////////////////////////////////////

  public Program parseProgram() {

    Program programAST = null;

    previousTokenPosition.start = 0;
    previousTokenPosition.finish = 0;
    currentToken = lexicalAnalyser.scan();

    try {

      if(currentToken.kind == Token.PACKAGE){
        Declaration pDAST = parsePackageDeclaration();
        accept(Token.SEMICOLON);
        while(currentToken.kind == Token.PACKAGE){
           Declaration pDAST2 = parsePackageDeclaration();
           accept(Token.SEMICOLON);
           pDAST = new SequentialPackageDeclaration(pDAST, pDAST2, previousTokenPosition);
           
        }
        Command cAST = parseCommand();
        programAST = new Program(pDAST, cAST, previousTokenPosition);
      }
      else {
        Command cAST = parseCommand();
        programAST = new Program(cAST, previousTokenPosition);
      }

    
      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) { return null; }
    return programAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// LITERALS
//
///////////////////////////////////////////////////////////////////////////////

// parseIntegerLiteral parses an integer-literal, and constructs
// a leaf AST to represent it.

  IntegerLiteral parseIntegerLiteral() throws SyntaxError {
    IntegerLiteral IL = null;

    if (currentToken.kind == Token.INTLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      IL = new IntegerLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      IL = null;
      syntacticError("integer literal expected here", "");
    }
    return IL;
  }

// parseCharacterLiteral parses a character-literal, and constructs a leaf
// AST to represent it.

  CharacterLiteral parseCharacterLiteral() throws SyntaxError {
    CharacterLiteral CL = null;

    if (currentToken.kind == Token.CHARLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      CL = new CharacterLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      CL = null;
      syntacticError("character literal expected here", "");
    }
    return CL;
  }

// parseIdentifier parses an identifier, and constructs a leaf AST to
// represent it.

  Identifier parseIdentifier() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      I = null;
      syntacticError("identifier expected here", "");
    }
    return I;
  }

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
//  PACKAGE-IDENTIFIER 
//
///////////////////////////////////////////////////////////////////////////////

Identifier parsePackageIdentifier() throws SyntaxError {
  Identifier packageIdentifierAST = null;

  SourcePosition packageIdentifierPos = new SourcePosition();
  start(packageIdentifierPos);
  
  if (currentToken.kind == Token.IDENTIFIER) {
    Identifier iAST = parseIdentifier();
    finish(packageIdentifierPos);
    packageIdentifierAST = new PackageIdentifier(iAST.spelling, iAST, packageIdentifierPos);
  }
  
  return packageIdentifierAST;

}

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
//  LONG-IDENTIFIER 
//
///////////////////////////////////////////////////////////////////////////////

Identifier parseLongIdentifier() throws SyntaxError {
  Identifier longIdentifierAST = null;

  SourcePosition longIdentifierPos = new SourcePosition();
  start(longIdentifierPos);
  
  Identifier iAST = parseIdentifier();
  if(currentToken.kind == Token.DOLAR){
    accept(Token.DOLAR);
    Identifier iAST2 = parseIdentifier();
    longIdentifierAST = new LongIdentifier(currentToken.spelling, iAST2, iAST, longIdentifierPos);
  }
  else if(currentToken.kind == Token.IDENTIFIER){
    syntacticError("$ expected here",
    currentToken.spelling);
  }

  longIdentifierAST = iAST;

  return longIdentifierAST;
}

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
//  PACKAGE DECLARATION 
//
///////////////////////////////////////////////////////////////////////////////

Declaration parsePackageDeclaration() throws SyntaxError {
  Declaration packageDeclarationAST = null;

  SourcePosition packageDeclarationPos = new SourcePosition();
  start(packageDeclarationPos);
  
  accept(Token.PACKAGE);
  Identifier pIAST = parsePackageIdentifier();
  accept(Token.IS);
  Declaration dAST = parseDeclaration();
  accept(Token.END);

  finish(packageDeclarationPos);

  packageDeclarationAST = new PackageDeclaration(pIAST, dAST, packageDeclarationPos);

  return packageDeclarationAST;


}


// parseOperator parses an operator, and constructs a leaf AST to
// represent it.

  Operator parseOperator() throws SyntaxError {
    Operator O = null;

    if (currentToken.kind == Token.OPERATOR) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      O = new Operator(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      O = null;
      syntacticError("operator expected here", "");
    }
    return O;
  }

///////////////////////////////////////////////////////////////////////////////
//
// COMMANDS
//
///////////////////////////////////////////////////////////////////////////////

// parseCommand parses the command, and constructs an AST
// to represent its phrase structure.

  Command parseCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();

    start(commandPos);
    commandAST = parseSingleCommand();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Command c2AST = parseSingleCommand();
      finish(commandPos);
      commandAST = new SequentialCommand(commandAST, c2AST, commandPos);
    }
    return commandAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
//  Single Command modified  
//
///////////////////////////////////////////////////////////////////////////////

  Command parseSingleCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();
    start(commandPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier liAST = parseLongIdentifier(); // modified to longIdentifier
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(commandPos);
          commandAST = new CallCommand(liAST, apsAST, commandPos);

        } else {

          VarName vAST = parseRestOfVarName(liAST);
          accept(Token.BECOMES);
          Expression eAST = parseExpression();
          finish(commandPos);
          commandAST = new AssignCommand(vAST, eAST, commandPos);
        }
      }
      break;

    //Nuevo Comando Let    
    case Token.LET:
    {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        //cambia parseSingleComand por parseComand
        Command cAST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
    } 
      break;
    //Nuevo Comando If
    case Token.IF:
    case Token.ELSIF:
    {
       acceptIt();
        Expression eAST = parseExpression();
        accept(Token.THEN);
        Command c1AST = parseCommand();
        //agrega ELSIF
        Command c2AST;
        switch(currentToken.kind){
            case Token.ELSIF:
            {
                c2AST = parseCommand();
                finish(commandPos);
                commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
            }
            break;
            case Token.ELSE:
            {
                accept(Token.ELSE); 
                c2AST = parseCommand();
                accept(Token.END);
                finish(commandPos);
                commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
            }
            break;
            default:
            syntacticError("\"%\": expecting else/elsif",
                                currentToken.spelling);
            break;
                   
        }
    }  
    break; 
    //Nuevo Comando Loop
    case Token.LOOP:
    {
       acceptIt();
        switch(currentToken.kind){
            case Token.WHILE:
            {
               acceptIt();
                Expression eAST = parseExpression();
                accept(Token.DO);
                Command cAST = parseCommand();
                accept(Token.END);
                finish(commandPos);
                commandAST = new LoopWhileCommand(eAST, cAST, commandPos); 
            }
                break;
            case Token.UNTIL:
            {
                acceptIt();
                Expression eAST = parseExpression();
                accept(Token.DO);
                Command cAST = parseCommand();
                accept(Token.END);
                finish(commandPos);
                commandAST = new LoopUntilCommand(eAST, cAST, commandPos);
            }
            break;
            case Token.DO:
            {
               acceptIt();
                Command cAST = parseCommand();
                switch(currentToken.kind){
                    case Token.WHILE:
                    {
                        acceptIt();
                        Expression eAST = parseExpression();
                        accept(Token.END);
                        finish(commandPos);
                        commandAST = new LoopDoWhileCommand(cAST, eAST, commandPos);
                    }
                    break;
                    case Token.UNTIL:
                    {
                        acceptIt();
                        Expression eAST = parseExpression();
                        accept(Token.END);
                        finish(commandPos);
                        commandAST = new LoopDoUntilCommand(cAST, eAST, commandPos);
                    }
                    break;
                    default:
                    {
                        syntacticError("\"%\": expencting while/until",
                                currentToken.spelling);
                    }
                    break;
                }
            }
            break;
            case Token.FOR:
            {
                acceptIt();
                Declaration dAST = parseDeclaration();
                accept(Token.FROM);
                Expression e1AST = parseExpression();
                accept(Token.TO);
                Expression e2AST = parseExpression();
                switch(currentToken.kind){
                    case Token.DO:
                    {
                        acceptIt();
                        Command cAST = parseCommand();
                        accept(Token.END);
                        finish(commandPos);
                        commandAST = new LoopForDoCommand(dAST, e1AST, e2AST, cAST, commandPos);
                    }
                    break;
                    case Token.WHILE:
                    {
                        acceptIt();
                        Expression e3AST = parseExpression();
                        accept(Token.DO);
                        Command cAST = parseCommand();
                        accept(Token.END);
                        finish(commandPos);
                        commandAST = new LoopForWhileCommand(dAST, e1AST, e2AST, e3AST, cAST, commandPos);  
                    }
                    break;
                    case Token.UNTIL:
                    {
                        acceptIt();
                        Expression e3AST = parseExpression();
                        accept(Token.DO);
                        Command cAST = parseCommand();
                        accept(Token.END);
                        finish(commandPos);
                        commandAST = new LoopForUntilCommand(dAST, e1AST, e2AST, e3AST, cAST, commandPos);
                    }
                    break;    
                    default:
                        syntacticError("\"%\": expencting while/until",
                                currentToken.spelling);
                    break;
                } 
            } 
            break;
            default:
                syntacticError("\"%\" cannot declare a loop command",
                                currentToken.spelling);
            break;
        }
        
    }
    break; 
    case Token.NOTHING:
    {
        acceptIt();
        commandAST = new EmptyCommand(commandPos);
        finish(commandPos);  
    }
    break;
    //EXTRA
    case Token.CHOOSE:
    {
      acceptIt();
      Expression eAST = parseExpression();
      accept(Token.FROM);
      Cases cAST = parseCases();
      accept(Token.END);
      finish(commandPos);
      commandAST = new ChooseCommand(eAST, cAST, commandPos);
    }
    break;

    default:
      syntacticError("\"%\" cannot start a command",
        currentToken.spelling);
      break;

    }

    return commandAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// EXPRESSIONS
//
///////////////////////////////////////////////////////////////////////////////

  Expression parseExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();

    start (expressionPos);

    switch (currentToken.kind) {

    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Expression eAST = parseExpression();
        finish(expressionPos);
        expressionAST = new LetExpression(dAST, eAST, expressionPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression e1AST = parseExpression();
        accept(Token.THEN);
        Expression e2AST = parseExpression();
        accept(Token.ELSE);
        Expression e3AST = parseExpression();
        finish(expressionPos);
        expressionAST = new IfExpression(e1AST, e2AST, e3AST, expressionPos);
      }
      break;

    default:
      expressionAST = parseSecondaryExpression();
      break;
    }
    return expressionAST;
  }

  Expression parseSecondaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    expressionAST = parsePrimaryExpression();
    while (currentToken.kind == Token.OPERATOR) {
      Operator opAST = parseOperator();
      Expression e2AST = parsePrimaryExpression();
      expressionAST = new BinaryExpression (expressionAST, opAST, e2AST,
        expressionPos);
    }
    return expressionAST;
  }
///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
//  Primary Expression modified
//
///////////////////////////////////////////////////////////////////////////////

  Expression parsePrimaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    switch (currentToken.kind) {

    case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(expressionPos);
        expressionAST = new IntegerExpression(ilAST, expressionPos);
      }
      break;

    case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(expressionPos);
        expressionAST = new CharacterExpression(clAST, expressionPos);
      }
      break;

    case Token.LBRACKET:
      {
        acceptIt();
        ArrayAggregate aaAST = parseArrayAggregate();
        accept(Token.RBRACKET);
        finish(expressionPos);
        expressionAST = new ArrayExpression(aaAST, expressionPos);
      }
      break;

    case Token.LCURLY:
      {
        acceptIt();
        RecordAggregate raAST = parseRecordAggregate();
        accept(Token.RCURLY);
        finish(expressionPos);
        expressionAST = new RecordExpression(raAST, expressionPos);
      }
      break;

    case Token.IDENTIFIER:
      {
        Identifier iAST= parseLongIdentifier(); //modified to long identifier
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(iAST, apsAST, expressionPos);

        } else {
          VarName vAST = parseRestOfVarName(iAST);
          finish(expressionPos);
          expressionAST = new VarNameExpression(vAST, expressionPos);
        }
      }
      break;

    case Token.OPERATOR:
      {
        Operator opAST = parseOperator();
        Expression eAST = parsePrimaryExpression();
        finish(expressionPos);
        expressionAST = new UnaryExpression(opAST, eAST, expressionPos);
      }
      break;

    case Token.LPAREN:
      acceptIt();
      expressionAST = parseExpression();
      accept(Token.RPAREN);
      break;

    default:
      syntacticError("\"%\" cannot start an expression",
        currentToken.spelling);
      break;

    }
    return expressionAST;
  }

  RecordAggregate parseRecordAggregate() throws SyntaxError {
    RecordAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Identifier iAST = parseIdentifier();
    accept(Token.IS);
    Expression eAST = parseExpression();

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      RecordAggregate aAST = parseRecordAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleRecordAggregate(iAST, eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleRecordAggregate(iAST, eAST, aggregatePos);
    }
    return aggregateAST;
  }

  ArrayAggregate parseArrayAggregate() throws SyntaxError {
    ArrayAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Expression eAST = parseExpression();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ArrayAggregate aAST = parseArrayAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleArrayAggregate(eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleArrayAggregate(eAST, aggregatePos);
    }
    return aggregateAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// VALUE-OR-VARIABLE NAMES
//
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
// V-NAME (Modified)
//
///////////////////////////////////////////////////////////////////////////////
VarName parseVname () throws SyntaxError {
  VarName VnameAST = null; // in case there's a syntactic error
  
  SourcePosition VnamePos = new SourcePosition();
  start(VnamePos);
  

  if(currentToken.kind == Token.IDENTIFIER){
    //Identifier pIAST = parsePackageIdentifier();
    //accept(Token.DOLAR);

    //VarName VnameAST = parseVarName();
    VnameAST = parseVarName();
    finish(VnamePos);

    //VnameAST = new Vname(pIAST, vNAST, VnamePos);

  }

  else {
    VnameAST = parseVarName();
    finish(VnamePos);
  }

  return VnameAST;
}


///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
// VAR-NAME (Modified)
//
///////////////////////////////////////////////////////////////////////////////
  VarName parseVarName () throws SyntaxError {
    VarName VarNameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    VarNameAST = parseRestOfVarName(iAST);
    return VarNameAST;
  }

  VarName parseRestOfVarName(Identifier identifierAST) throws SyntaxError {
    SourcePosition VarNamePos = new SourcePosition();
    VarNamePos = identifierAST.position;
    VarName vAST = new SimpleVarName(identifierAST, VarNamePos);

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        if(currentToken.kind == Token.DOLAR){
          syntacticError("$ not expected here",
          currentToken.spelling);
        }
        vAST = new DotVarName(vAST, iAST, VarNamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        if(currentToken.kind == Token.DOLAR){
          syntacticError("$ not expected here",
          currentToken.spelling);
        }
        finish(VarNamePos);
        vAST = new SubscriptVarName(vAST, eAST, VarNamePos);
      }
    }
    return vAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// Marcos Méndez 2021-04-11
// DECLARATIONS MODIFIED
//
///////////////////////////////////////////////////////////////////////////////

  Declaration parseDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseCompoundDeclaration(); //adjusted
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Declaration d2AST = parseCompoundDeclaration(); //adjusted
      finish(declarationPos);
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  }

  // Marcos Méndez 2021-04-11
  Declaration parseSingleDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);

    switch (currentToken.kind) {

    case Token.CONST:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new ConstDeclaration(iAST, eAST, declarationPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        if(currentToken.kind == Token.BECOMES){ //"var" Identifier ":=" Expression
          acceptIt();
          Expression eAST = parseExpression();
          finish(declarationPos);
          declarationAST = new AssignVarDeclaration(iAST, eAST, declarationPos);
        }else{
          accept(Token.COLON);
          TypeDenoter tAST = parseTypeDenoter();
          finish(declarationPos);
          declarationAST = new VarDeclaration(iAST, tAST, declarationPos);
        }
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseSingleCommand();
        accept(Token.END); //added "end"
        finish(declarationPos);
        declarationAST = new ProcDeclaration(iAST, fpsAST, cAST, declarationPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(declarationPos);
        declarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST,
          declarationPos);
      }
      break;

    case Token.TYPE:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        TypeDenoter tAST = parseTypeDenoter();
        finish(declarationPos);
        declarationAST = new TypeDeclaration(iAST, tAST, declarationPos);
      }
      break;
      
    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        finish(declarationPos);
        declarationAST = new ControlVarDeclaration(iAST, declarationPos);
      }
      
      break;
    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return declarationAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// PARAMETERS
//
///////////////////////////////////////////////////////////////////////////////

  FormalParameterSequence parseFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST;

    SourcePosition formalsPos = new SourcePosition();

    start(formalsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(formalsPos);
      formalsAST = new EmptyFormalParameterSequence(formalsPos);

    } else {
      formalsAST = parseProperFormalParameterSequence();
    }
    return formalsAST;
  }

  FormalParameterSequence parseProperFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST = null; // in case there's a syntactic error;

    SourcePosition formalsPos = new SourcePosition();
    start(formalsPos);
    FormalParameter fpAST = parseFormalParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FormalParameterSequence fpsAST = parseProperFormalParameterSequence();
      finish(formalsPos);
      formalsAST = new MultipleFormalParameterSequence(fpAST, fpsAST,
        formalsPos);

    } else {
      finish(formalsPos);
      formalsAST = new SingleFormalParameterSequence(fpAST, formalsPos);
    }
    return formalsAST;
  }

  FormalParameter parseFormalParameter() throws SyntaxError {
    FormalParameter formalAST = null; // in case there's a syntactic error;

    SourcePosition formalPos = new SourcePosition();
    start(formalPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new ConstFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new VarFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        finish(formalPos);
        formalAST = new ProcFormalParameter(iAST, fpsAST, formalPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new FuncFormalParameter(iAST, fpsAST, tAST, formalPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a formal parameter",
        currentToken.spelling);
      break;

    }
    return formalAST;
  }


  ActualParameterSequence parseActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST;

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(actualsPos);
      actualsAST = new EmptyActualParameterSequence(actualsPos);

    } else {
      actualsAST = parseProperActualParameterSequence();
    }
    return actualsAST;
  }

  ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST = null; // in case there's a syntactic error

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    ActualParameter apAST = parseActualParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ActualParameterSequence apsAST = parseProperActualParameterSequence();
      finish(actualsPos);
      actualsAST = new MultipleActualParameterSequence(apAST, apsAST,
        actualsPos);
    } else {
      finish(actualsPos);
      actualsAST = new SingleActualParameterSequence(apAST, actualsPos);
    }
    return actualsAST;
  }

  ActualParameter parseActualParameter() throws SyntaxError {
    ActualParameter actualAST = null; // in case there's a syntactic error

    SourcePosition actualPos = new SourcePosition();

    start(actualPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
    case Token.INTLITERAL:
    case Token.CHARLITERAL:
    case Token.OPERATOR:
    case Token.LET:
    case Token.IF:
    case Token.LPAREN:
    case Token.LBRACKET:
    case Token.LCURLY:
      {
        Expression eAST = parseExpression();
        finish(actualPos);
        actualAST = new ConstActualParameter(eAST, actualPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        VarName vAST = parseVname();
        finish(actualPos);
        actualAST = new VarActualParameter(vAST, actualPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new ProcActualParameter(iAST, actualPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new FuncActualParameter(iAST, actualPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start an actual parameter",
        currentToken.spelling);
      break;

    }
    return actualAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// María José Cortés
// TYPE-DENOTERS modified
// 
///////////////////////////////////////////////////////////////////////////////

  TypeDenoter parseTypeDenoter() throws SyntaxError {
    TypeDenoter typeAST = null; // in case there's a syntactic error
    SourcePosition typePos = new SourcePosition();

    start(typePos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER: 
      {
        Identifier liAST = parseLongIdentifier();
        finish(typePos);
        typeAST = new SimpleTypeDenoter(liAST, typePos);
      }
      break;

    case Token.ARRAY:
      {
        acceptIt();
        IntegerLiteral ilAST = parseIntegerLiteral();
        accept(Token.OF);
        TypeDenoter tAST = parseTypeDenoter();
        finish(typePos);
        typeAST = new ArrayTypeDenoter(ilAST, tAST, typePos);
      }
      break;

    case Token.RECORD:
      {
        acceptIt();
        FieldTypeDenoter fAST = parseFieldTypeDenoter();
        accept(Token.END);
        finish(typePos);
        typeAST = new RecordTypeDenoter(fAST, typePos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a type denoter",
        currentToken.spelling);
      break;

    }
    return typeAST;
  }

  FieldTypeDenoter parseFieldTypeDenoter() throws SyntaxError {
    FieldTypeDenoter fieldAST = null; // in case there's a syntactic error

    SourcePosition fieldPos = new SourcePosition();

    start(fieldPos);
    Identifier iAST = parseIdentifier();
    accept(Token.COLON);
    TypeDenoter tAST = parseTypeDenoter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FieldTypeDenoter fAST = parseFieldTypeDenoter();
      finish(fieldPos);
      fieldAST = new MultipleFieldTypeDenoter(iAST, tAST, fAST, fieldPos);
    } else {
      finish(fieldPos);
      fieldAST = new SingleFieldTypeDenoter(iAST, tAST, fieldPos);
    }
    return fieldAST;
  }


///////////////////////////////////////////////////////////////////////////////
//
// Marcos Méndez 2021-04-11
// COMPOUND-DECLARATION 
//
///////////////////////////////////////////////////////////////////////////////

  Declaration parseCompoundDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    
    switch(currentToken.kind){
      case Token.RECURSIVE:
      {
        acceptIt();
        ProcFuncs procFuncsAST = parseProcFuncs();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new RecursiveDeclaration(procFuncsAST, declarationPos);
      }
      break;
      case Token.PRIVATE:
      {
        acceptIt();
        Declaration declaration1AST = parseDeclaration();
        accept(Token.IN);
        Declaration declaration2AST = parseDeclaration();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new PrivateDeclaration(declaration1AST, declaration2AST, declarationPos);
      }
      break;
      default:
        return declarationAST = parseSingleDeclaration();
    }
    return declarationAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// Marcos Méndez 2021-04-11
// PROC-FUNCS
//
///////////////////////////////////////////////////////////////////////////////

  ProcFuncs parseProcFuncs() throws SyntaxError{
    ProcFuncs ProcFuncsAST = null; // in case there's a syntactic error

    SourcePosition propFuncsPos = new SourcePosition();
    start(propFuncsPos);
    ProcFuncs secondProcFuncsAST = parseProcFunc();
    do {
      accept(Token.PIPE);
      ProcFuncs thirdProcFuncsAST = parseProcFunc();
      secondProcFuncsAST = new SequentialProcFuncs(secondProcFuncsAST, thirdProcFuncsAST, propFuncsPos);
    } while (currentToken.kind == Token.PIPE); //"|"
    finish(propFuncsPos);
    ProcFuncsAST = secondProcFuncsAST;
    return ProcFuncsAST;
  }

  ProcFuncs parseProcFunc() throws SyntaxError {
    ProcFuncs procFuncsAST = null; // in case there's a syntactic error

    SourcePosition procFuncPos = new SourcePosition();
    start(procFuncPos);
    switch(currentToken.kind){
      case Token.PROC:
        {
          acceptIt();
          Identifier iAST = parseIdentifier();
          accept(Token.LPAREN);
          FormalParameterSequence fpsAST = parseFormalParameterSequence();
          accept(Token.RPAREN);
          accept(Token.IS);
          Command cmdAST = parseCommand();
          accept(Token.END);
          finish(procFuncPos);
          procFuncsAST = new RecursiveProc(iAST, fpsAST, cmdAST, procFuncPos);
        }
        break;
      case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        procFuncsAST = new RecursiveFunc(iAST, fpsAST, tAST, eAST, procFuncPos);
      }
      break;
      default:
      syntacticError("\"%\" cannot start a type denoter",
                    currentToken.spelling);
    }
    return procFuncsAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// Marcos Méndez 2021-04-20
// CASES (EXTRA)
//
///////////////////////////////////////////////////////////////////////////////
  Expression parseCaseRange() throws SyntaxError{
    Expression caseRangeAST = null; // in case there's a syntactic error

    SourcePosition caseRangePos = new SourcePosition();
    start(caseRangePos);

    caseRangeAST = parseCaseLiteral();
    finish(caseRangePos);

    if(currentToken.kind == Token.DDOT){ //..
      acceptIt();
      Expression e2AST = parseCaseLiteral();
      finish(caseRangePos);
      caseRangeAST = new CaseRange(caseRangeAST, e2AST, caseRangePos);
    }
    return caseRangeAST;
  }

  Expression parseCaseLiteral() throws SyntaxError{
    Expression caseLiteralAST = null; // in case there's a syntactic error

    SourcePosition caseLiteralPos = new SourcePosition();
    start(caseLiteralPos);

    switch (currentToken.kind) {
      case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(caseLiteralPos);
        caseLiteralAST = new CaseLiteralInteger(ilAST, caseLiteralPos);
      }
      break;
      case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(caseLiteralPos);
        caseLiteralAST = new CaseLiteralCharacter(clAST, caseLiteralPos);
      }
      break;
      default:
        syntacticError("Case-Literal expected here.", "");
    }
    return caseLiteralAST;
  }

  Expression parseCaseLiterals() throws SyntaxError{
    Expression caseLiteralsAST = null;

    SourcePosition caseLiteralsPos = new SourcePosition();
    start(caseLiteralsPos);

    Expression caseRangeAST = parseCaseRange();

    finish(caseLiteralsPos);
    caseLiteralsAST = new CaseLiterals(caseRangeAST, caseLiteralsPos);
    while(currentToken.kind == Token.PIPE){
      acceptIt();
      caseRangeAST = parseCaseRange();
      Expression caseLiteralsAST2 = new CaseLiterals(caseRangeAST, caseLiteralsPos);
      finish(caseLiteralsPos);
      caseLiteralsAST = new SequentialCaseLiterals(caseLiteralsAST, caseLiteralsAST2, caseLiteralsPos);
    }
    return caseLiteralsAST;
  }

  Cases parseElseCase() throws SyntaxError{
    Cases elseCaseAST = null;

    SourcePosition elseCasePos = new SourcePosition();
    start(elseCasePos);

    Command cAST = parseCommand();
    finish(elseCasePos);
    elseCaseAST = new ElseCase(cAST, elseCasePos);

    return elseCaseAST;
  }

  Cases parseCase() throws SyntaxError{
    Cases caseAST = null;

    SourcePosition casePos = new SourcePosition();
    start(casePos);

    Expression eAST = parseCaseLiterals();
    accept(Token.THEN);
    Command cAST = parseCommand();
    finish(casePos);

    caseAST = new Case(eAST, cAST, casePos);
    return caseAST;
  }

  Cases parseCases() throws SyntaxError{
    Cases casesAST = null;

    SourcePosition casesPos = new SourcePosition();
    start(casesPos);

    accept(Token.WHEN);
    casesAST = parseCase();

    while(currentToken.kind == Token.WHEN){
      acceptIt();
      Cases caseAST = parseCase();
      finish(casesPos);
      casesAST = new SequentialCases(casesAST, caseAST, casesPos);
    }

    if(currentToken.kind == Token.ELSE){
      acceptIt();
      Cases elseCaseAST = parseElseCase();
      finish(casesPos);
      casesAST = new SequentialCases(casesAST, elseCaseAST, casesPos);
    }

    return casesAST;
  }
}