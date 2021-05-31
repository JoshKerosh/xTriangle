/*
 * @(#)Token.java                        2.1 2003/10/07
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


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Marcos Méndez 2021-04-10
  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY   = 4,
    CHOOSE  = 5,//new
    CONST   = 6,
    DO      = 7,
    ELSE    = 8,
    ELSIF  = 9,//nuevo
    END     = 10,
    FOR     = 11,//nuevo
    FROM    = 12,//nuevo
    FUNC		= 13,
    IF			= 14,
    IN			= 15,
    LET			= 16,
    LOOP    = 17,//nuevo
    NOTHING = 18,//nuevo  
    OF			= 19,
    PACKAGE = 20,//nuevo
    PRIVATE = 21,//nuevo
    PROC		= 22,
    RECORD	= 23,
    RECURSIVE = 24,//nuevo
    THEN		= 25,
    TO      = 26,//nuevo
    TYPE		= 27,
    UNTIL   = 28,//nuevo
    VAR			= 29,
    WHEN    = 30,//nuevo
    WHILE		= 31,

    // punctuation...
    DOT			= 32,
    COLON		= 33,
    SEMICOLON = 34,
    COMMA		= 35,
    BECOMES	= 36,
    IS			= 37,

    // brackets...
    LPAREN		= 38,
    RPAREN		= 39,
    LBRACKET  = 40,
    RBRACKET  = 41,
    LCURLY		= 42,
    RCURLY		= 43,

    // special tokens...
    EOT			= 44,
    ERROR		= 45,

    // < Extended >
    DDOT    = 46,//nuevo
    DOLAR   = 47,
    PIPE    = 48;//nuevo
  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "choose", //new
    "const",
    "do",
    "else",
    "elsif", //new
    "end",
    "for",  //new
    "from", //new
    "func",
    "if",
    "in",
    "let",
    "loop", //new
    "nothing",  //new
    "of",
    "package",  //new
    "private",  //new
    "proc",
    "record",
    "recursive",  //new
    "then",
    "to", //new
    "type",
    "until",  //new
    "var",
    "when", //new
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>",
    "..", //new
    "$",  //new
    "|",  //new
  };

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;

}
