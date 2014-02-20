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
 * This doesn't use any specific facts but instead asserts a set of 
 * java.lang.Integer objects. This is not considered "best practice" 
 * as a number is not a useful fact, but we use it here to demonstrate 
 * basic techniques before more complexity is added.
 * There simple rule to print out these numbers that also gets loaded.
 */

/**
 * Remember: The class RuleRunner is a simple harness to execute one or more DRL files 
 * 'against' a *set* of data. That's the basic illustration of this example 
 * in a nutshell. 
 * The set of data in this case are the array of integers [3,1,4,1,5]
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class BankingExample2 {
    public static void main(String[] args) {
        Number[] numbers = new Number[] {wrap(3), wrap(1), wrap(4), wrap(1), wrap(5)};
        new RuleRunner().runRules( new String[] { "Example2.drl" }, numbers );
    }
    
    /* Method to take a # and return an integer:
     * Call: Integer j=new Integer(3); will always results in a new Integer creation, 
     * in heap. This is not pooled. And hence you see that both reference are referring 
     * to different objects.
     */
    
    private static Integer wrap(int i) {
        return new Integer(i);
    }
    
}// End Class BankingExample2
