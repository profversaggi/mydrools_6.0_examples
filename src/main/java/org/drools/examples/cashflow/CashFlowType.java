package org.drools.examples.cashflow;

/*
 * Primer on 'enum' types:
 * An enum type is a special data type that enables for a variable to be a set of predefined constants. 
 * The variable must be equal to one of the values that have been predefined for it. Common examples 
 * include compass directions (values of NORTH, SOUTH, EAST, and WEST) and the days of the week.
 * Because they are constants, the names of an enum type's fields are in uppercase letters.
 * In the Java programming language, you define an enum type by using the enum keyword.
 */


/**
 * This a class that creates the constants CREDIT and DEBIT as legitimate Java Types. 
 */

public enum CashFlowType {
    CREDIT, DEBIT;
}
