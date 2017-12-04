package com.TeamAlexKong.compiler;

import org.antlr.v4.runtime.ANTLRInputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;

import com.TeamAlexKong.parser.*;
import com.pcl2.parser.Pass1Visitor;
import com.pcl2.parser.Pass2Visitor;
import com.pcl2.parser.Pcl2Lexer;
import com.pcl2.parser.Pcl2Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TeamAlexKong 
{
    public static void main(String[] args) throws Exception 
    {
        String inputFile = null;
        
        if (args.length > 0) inputFile = args[0];
        InputStream is = (inputFile != null)
                                ? new FileInputStream(inputFile)
                                : System.in;
        
        ANTLRInputStream input = new ANTLRInputStream(is);
        HelloLexer lexer = new HelloLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.compilationUnit();
        
        TeamAlexKongVisitor1 visitor1 = new TeamAlexKongVisitor1();
        visitor1.visit(tree);
        
        PrintWriter jFile = visitor1.getAssemblyFile();
        
        TeamAlexKongVisitor2 visitor2 = new TeamAlexKongVisitor2(jFile);
        visitor2.visit(tree);
    }
}
