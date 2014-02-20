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

package org.drools.examples.fibonacci;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/*
 * Official Drools V6 Examples Online Docs: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e19211
 */


/**
 * The Fibonacci Example demonstrates recursion and conflict resolution with salience values, two
 * vitally important concept to grasp in route to understanding the power of Drools. 
 *  
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class FibonacciExample {

    public static void main(final String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("FibonacciKS");

        // KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "log/fibonacci.log");

        ksession.insert( new Fibonacci( 50 ) ); // This is all that's need to start the recursion calculating the #'s.
        ksession.fireAllRules();

        // logger.close();
        ksession.dispose(); // Stateful rule session must always be disposed when finished
    }

    
    
    // This is an internal class but it could have been defined in it's own file just as well.
    // It's a Standard POJO. Note: The single fact class Fibonacci is used in this example. 
    // It has two fields, sequence and value. The sequence field is used to indicate the 
    // position of the object in the Fibonacci number sequence. The value field shows the value 
    // of that Fibonacci object for that sequence position, using -1 to indicate a value that 
    // still needs to be computed.
    
    public static class Fibonacci {
        private int  sequence;
        private long value;

        public Fibonacci() {                    // Standard POJO constructor w/no arguments

        }

        public Fibonacci(final int sequence) {  // Standard POJO with a sequence as an argument
            this.sequence = sequence;
            this.value = -1;
        }

        // Standard POJO's Setters and Getters
        
        public int getSequence() {
            return this.sequence;
        }

        public void setValue(final long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }

        public String toString() { // Method Never used .... safe to ignore.
            return "Fibonacci(" + this.sequence + "/" + this.value + ")";
        }
    }

} // End Class Fibonacci 
