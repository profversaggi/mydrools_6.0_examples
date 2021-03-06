/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.tutorials.banking;

/**
 * Remember: The class RuleRunner is a simple harness to execute one or more DRL files 
 * 'against' a *set* of data. That's the basic illustration of this example 
 * in a nutshell.
 * 
 * The Class Cashflow has two simple attributes, a date and an amount.
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 * 
 */

public class BankingExample4 {
    public static void main(String[] args) {
        
        Object[] cashflows = {              // Create a CashFlow Object
                                            // Insert five Cashflow objects, with varying dates and amounts.
                
            new Cashflow(new SimpleDate("01/01/2007"), 300.00),
            new Cashflow(new SimpleDate("05/01/2007"), 100.00),
            new Cashflow(new SimpleDate("11/01/2007"), 500.00),
            new Cashflow(new SimpleDate("07/01/2007"), 800.00),
            new Cashflow(new SimpleDate("02/01/2007"), 400.00),
        };
        
        new RuleRunner().runRules( new String[] { "Example4.drl" }, cashflows );  // Execute the rule file across this data set
    }
    
}// End Class BankingExample4
