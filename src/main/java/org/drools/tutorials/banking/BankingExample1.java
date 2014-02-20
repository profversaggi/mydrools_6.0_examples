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
 * This is a simple class to illustrate the execution of a Drools DRL file in a StatefulKnowledgeSession. 
 * The rules  in Example1.drl do no more that print out a message indicating things executed normally. 
 * There is no date inserted in this example.
 * 
 * Note: Since there are no facts being inserted into WM, no message will be displayed indicating such.
 *  
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class BankingExample1 {
    public static void main(String[] args) {
        new RuleRunner().runRules( new String[] { "Example1.drl" }, new Object[0] );
    }
} // End Class BankingExample1
