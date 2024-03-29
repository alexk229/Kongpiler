package wci.intermediate.symtabimpl;

import java.util.ArrayList;

import wci.frontend.pascal.parsers.VariableDeclarationsParser;
import wci.intermediate.*;
import wci.intermediate.symtabimpl.*;

import static wci.intermediate.symtabimpl.DefinitionImpl.*;
import static wci.intermediate.symtabimpl.SymTabKeyImpl.*;
import static wci.intermediate.symtabimpl.RoutineCodeImpl.*;
import static wci.intermediate.typeimpl.TypeFormImpl.*;
import static wci.intermediate.typeimpl.TypeKeyImpl.*;

/**
 * <h1>Predefined</h1>
 *
 * <p>Enter the predefined Pascal types, identifiers, and constants
 * into the symbol table.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class Predefined
{
    // Predefined types.
	public static TypeSpec localIntegerType;
	public static TypeSpec localRealType;
	public static TypeSpec localStringType;
	public static TypeSpec localBoolType;
	public static TypeSpec localFloatType;
	public static TypeSpec localDoubleType;
	public static TypeSpec localCharType;
	public static TypeSpec localLongType;
	
    public static TypeSpec integerType;
    public static TypeSpec realType;
    public static TypeSpec complexType;
    public static TypeSpec booleanType;
    public static TypeSpec charType;
    public static TypeSpec undefinedType;
    public static TypeSpec voidType;
    public static TypeSpec stringType;
    public static TypeSpec floatType;
    public static TypeSpec doubleType;
    public static TypeSpec longType;

    // Predefined identifiers.
    public static SymTabEntry localIntegerId;
    public static SymTabEntry localRealId;
    public static SymTabEntry localStringId;
    public static SymTabEntry localBoolId;
    public static SymTabEntry localFloatId;
    public static SymTabEntry localDoubleId;
    public static SymTabEntry localCharId;
    public static SymTabEntry localLongId;
    
    public static SymTabEntry voidId;
    public static SymTabEntry stringId;
    public static SymTabEntry integerId;
    public static SymTabEntry complexId;
    public static SymTabEntry realId;
    public static SymTabEntry re;
    public static SymTabEntry im;
    public static SymTabEntry booleanId;
    public static SymTabEntry charId;
    public static SymTabEntry arrayId;
    public static SymTabEntry floatId;
    public static SymTabEntry doubleId;
    public static SymTabEntry longId;
    
    public static SymTabEntry falseId;
    public static SymTabEntry trueId;
    public static SymTabEntry readId;
    public static SymTabEntry readlnId;
    public static SymTabEntry writeId;
    public static SymTabEntry writelnId;
    public static SymTabEntry absId;
    public static SymTabEntry arctanId;
    public static SymTabEntry chrId;
    public static SymTabEntry cosId;
    public static SymTabEntry eofId;
    public static SymTabEntry eolnId;
    public static SymTabEntry expId;
    public static SymTabEntry lnId;
    public static SymTabEntry oddId;
    public static SymTabEntry ordId;
    public static SymTabEntry predId;
    public static SymTabEntry roundId;
    public static SymTabEntry sinId;
    public static SymTabEntry sqrId;
    public static SymTabEntry sqrtId;
    public static SymTabEntry succId;
    public static SymTabEntry truncId;

    /**
     * Initialize a symbol table stack with predefined identifiers.
     * @param symTab the symbol table stack to initialize.
     */
    public static void initialize(SymTabStack symTabStack)
    {
        initializeTypes(symTabStack);
        initializeConstants(symTabStack);
        initializeStandardRoutines(symTabStack);
    }

    /**
     * Initialize the predefined types.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeTypes(SymTabStack symTabStack)
    {
        // Type integer.
        integerId = symTabStack.enterLocal("integer");
        integerType = TypeFactory.createType(SCALAR);
        integerType.setIdentifier(integerId);
        integerId.setDefinition(DefinitionImpl.TYPE);
        integerId.setTypeSpec(integerType);
        
        localIntegerId = symTabStack.enterLocal("local integer");
        localIntegerType = TypeFactory.createType(SCALAR);
        localIntegerType.setIdentifier(localIntegerId);
        localIntegerId.setDefinition(DefinitionImpl.TYPE);
        localIntegerId.setTypeSpec(localIntegerType);
        
        localRealId = symTabStack.enterLocal("local real");
        localRealType = TypeFactory.createType(SCALAR);
        localRealType.setIdentifier(localRealId);
        localRealId.setDefinition(DefinitionImpl.TYPE);
        localRealId.setTypeSpec(localRealType);
        
        localStringId = symTabStack.enterLocal("local string");
        localStringType = TypeFactory.createType(SCALAR);
        localStringType.setIdentifier(localStringId);
        localStringId.setDefinition(DefinitionImpl.TYPE);
        localStringId.setTypeSpec(localStringType);
        
        localBoolId = symTabStack.enterLocal("local boolean");
        localBoolType = TypeFactory.createType(SCALAR);
        localBoolType.setIdentifier(localBoolId);
        localBoolId.setDefinition(DefinitionImpl.TYPE);
        localBoolId.setTypeSpec(localBoolType);
        
        localFloatId = symTabStack.enterLocal("local float");
        localFloatType = TypeFactory.createType(SCALAR);
        localFloatType.setIdentifier(localFloatId);
        localFloatId.setDefinition(DefinitionImpl.TYPE);
        localFloatId.setTypeSpec(localFloatType);
        
        localDoubleId = symTabStack.enterLocal("local double");
        localDoubleType = TypeFactory.createType(SCALAR);
        localDoubleType.setIdentifier(localDoubleId);
        localDoubleId.setDefinition(DefinitionImpl.TYPE);
        localDoubleId.setTypeSpec(localDoubleType);
        
        localCharId = symTabStack.enterLocal("local char");
        localCharType = TypeFactory.createType(SCALAR);
        localCharType.setIdentifier(localCharId);
        localCharId.setDefinition(DefinitionImpl.TYPE);
        localCharId.setTypeSpec(localCharType);
        
        localLongId = symTabStack.enterLocal("local long");
        localLongType = TypeFactory.createType(SCALAR);
        localLongType.setIdentifier(localLongId);
        localLongId.setDefinition(DefinitionImpl.TYPE);
        localLongId.setTypeSpec(localLongType);
        
        // Type real.
        realId = symTabStack.enterLocal("real");
        realType = TypeFactory.createType(SCALAR);
        realType.setIdentifier(realId);
        realId.setDefinition(DefinitionImpl.TYPE);
        realId.setTypeSpec(realType);
        
        // Type complex.
        complexId = symTabStack.enterLocal("complex");
        complexType = TypeFactory.createType(RECORD);
        complexType.setIdentifier(complexId);
        complexId.setDefinition(DefinitionImpl.TYPE);
        complexId.setTypeSpec(complexType);
        
        // Push a symbol table for the complex type specification.
        complexType.setAttribute(RECORD_SYMTAB, symTabStack.push());
     
        // push 2 entry to local simple table re and im
        re = symTabStack.enterLocal("re");
        re.setTypeSpec(realType);
        re.setDefinition(DefinitionImpl.FIELD);
        
        im = symTabStack.enterLocal("im");
        im.setTypeSpec(realType);
        im.setDefinition(DefinitionImpl.FIELD);
        
        // Pop off the complex's symbol table.
        symTabStack.pop();

        // Type boolean.
        booleanId = symTabStack.enterLocal("boolean");
        booleanType = TypeFactory.createType(ENUMERATION);
        booleanType.setIdentifier(booleanId);
        booleanId.setDefinition(DefinitionImpl.TYPE);
        booleanId.setTypeSpec(booleanType);

        // Type char.
        charId = symTabStack.enterLocal("char");
        charType = TypeFactory.createType(SCALAR);
        charType.setIdentifier(charId);
        charId.setDefinition(DefinitionImpl.TYPE);
        charId.setTypeSpec(charType);
        
        // Type string
        stringId = symTabStack.enterLocal("string");
        stringType = TypeFactory.createType(SCALAR);
        stringType.setIdentifier(stringId);
        stringId.setDefinition(DefinitionImpl.TYPE);
        stringId.setTypeSpec(stringType);
        
        // Type void
        voidId = symTabStack.enterLocal("void");
        voidType = TypeFactory.createType(SCALAR);
        voidType.setIdentifier(voidId);
        voidId.setDefinition(DefinitionImpl.TYPE);
        voidId.setTypeSpec(voidType);
        
        floatId = symTabStack.enterLocal("float");
        floatType = TypeFactory.createType(SCALAR);
        floatType.setIdentifier(floatId);
        floatId.setDefinition(DefinitionImpl.TYPE);
        floatId.setTypeSpec(floatType);
        
        doubleId = symTabStack.enterLocal("double");
        doubleType = TypeFactory.createType(SCALAR);
        doubleType.setIdentifier(doubleId);
        doubleId.setDefinition(DefinitionImpl.TYPE);
        doubleId.setTypeSpec(doubleType);

        longId = symTabStack.enterLocal("long");
        longType = TypeFactory.createType(SCALAR);
        longType.setIdentifier(longId);
        longId.setDefinition(DefinitionImpl.TYPE);
        longId.setTypeSpec(longType);
        
        // Type Array
        

        // Undefined type.
        undefinedType = TypeFactory.createType(SCALAR);
        
    }

    /**
     * Initialize the predefined constant.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeConstants(SymTabStack symTabStack)
    {
        // Boolean enumeration constant false.
        falseId = symTabStack.enterLocal("false");
        falseId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        falseId.setTypeSpec(booleanType);
        falseId.setAttribute(CONSTANT_VALUE, new Integer(0));

        // Boolean enumeration constant true.
        trueId = symTabStack.enterLocal("true");
        trueId.setDefinition(DefinitionImpl.ENUMERATION_CONSTANT);
        trueId.setTypeSpec(booleanType);
        trueId.setAttribute(CONSTANT_VALUE, new Integer(1));

        // Add false and true to the boolean enumeration type.
        ArrayList<SymTabEntry> constants = new ArrayList<SymTabEntry>();
        constants.add(falseId);
        constants.add(trueId);
        booleanType.setAttribute(ENUMERATION_CONSTANTS, constants);
    }

    /**
     * Initialize the standard procedures and functions.
     * @param symTabStack the symbol table stack to initialize.
     */
    private static void initializeStandardRoutines(SymTabStack symTabStack)
    {
        readId    = enterStandard(symTabStack, PROCEDURE, "read",    READ);
        readlnId  = enterStandard(symTabStack, PROCEDURE, "readln",  READLN);
        writeId   = enterStandard(symTabStack, PROCEDURE, "write",   WRITE);
        writelnId = enterStandard(symTabStack, PROCEDURE, "writeln", WRITELN);

        absId    = enterStandard(symTabStack, FUNCTION, "abs",    ABS);
        arctanId = enterStandard(symTabStack, FUNCTION, "arctan", ARCTAN);
        chrId    = enterStandard(symTabStack, FUNCTION, "chr",    CHR);
        cosId    = enterStandard(symTabStack, FUNCTION, "cos",    COS);
        eofId    = enterStandard(symTabStack, FUNCTION, "eof",    EOF);
        eolnId   = enterStandard(symTabStack, FUNCTION, "eoln",   EOLN);
        expId    = enterStandard(symTabStack, FUNCTION, "exp",    EXP);
        lnId     = enterStandard(symTabStack, FUNCTION, "ln",     LN);
        oddId    = enterStandard(symTabStack, FUNCTION, "odd",    ODD);
        ordId    = enterStandard(symTabStack, FUNCTION, "ord",    ORD);
        predId   = enterStandard(symTabStack, FUNCTION, "pred",   PRED);
        roundId  = enterStandard(symTabStack, FUNCTION, "round",  ROUND);
        sinId    = enterStandard(symTabStack, FUNCTION, "sin",    SIN);
        sqrId    = enterStandard(symTabStack, FUNCTION, "sqr",    SQR);
        sqrtId   = enterStandard(symTabStack, FUNCTION, "sqrt",   SQRT);
        succId   = enterStandard(symTabStack, FUNCTION, "succ",   SUCC);
        truncId  = enterStandard(symTabStack, FUNCTION, "trunc",  TRUNC);
    }

    /**
     * Enter a standard procedure or function into the symbol table stack.
     * @param symTabStack the symbol table stack to initialize.
     * @param defn either PROCEDURE or FUNCTION.
     * @param name the procedure or function name.
     */
    private static SymTabEntry enterStandard(SymTabStack symTabStack,
                                             Definition defn, String name,
                                             RoutineCode routineCode)
    {
        SymTabEntry procId = symTabStack.enterLocal(name);
        procId.setDefinition(defn);
        procId.setAttribute(ROUTINE_CODE, routineCode);

        return procId;
    }
}
