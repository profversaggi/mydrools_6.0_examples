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

package org.drools.examples.golfing;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/*
 * Official Drools V6 Examples Online Docs: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e19211
 */


/**
 * This is an attempt at solving the Golfers Riddle:
 * You look at the blackboard. It’s one of those word puzzles, the logic kind.
 * A foursome of golfers is standing at a tee, in a line from left to right.
 * Each golfer wears different colored pants; one is wearing red pants.
 * The golfer to Fred’s immediate right is wearing blue pants. Joe is second in line.
 * Bob is wearing plaid pants. Tom isn’t in position one or four, and he isn’t wearing 
 * the hideous orange pants. In what order will the four golfers play, and what color 
 * are each golfer’s pants?
 * 
 * Note: This is a classic Prolog problem, a solution to which the Prolog code can be 
 * found here: http://rbs.gernotstarke.de/samples/samples/prolog-golfer.html
 * 
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */


public class GolfingExample {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        // Standard Drools infrastructure to setup named KBases and KSessions via the kmodule.xml file
        
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        KieSession ksession = kc.newKieSession("GolfingKS");

        
        
        // Create Java arrays for each attribute: Name, Color and Position.
        
        String[] names = new String[]{"Fred", "Joe", "Bob", "Tom"};
        String[] colors = new String[]{"red", "blue", "plaid", "orange"};
        int[] positions = new int[]{1, 2, 3, 4};

        
        for ( int n = 0; n < names.length; n++ ) {
            for ( int c = 0; c < colors.length; c++ ) {
                for ( int p = 0; p < positions.length; p++ ) {
                    ksession.insert( new Golfer( names[n], colors[c], positions[p] ) ); // Insert all possibilities into the KB.
                }
            }
        }

        ksession.fireAllRules();
        ksession.dispose();             // Statefull sessions *must* be properly disposed of...

    }

    
    /**
     * This is the Golfer Class which houses each golfers attributes: name, color and position of each Golfer.
     */
    
    public static class Golfer {
        private String name;
        private String color;
        private int    position;

        
        // Standard empty POJO constructor 
        public Golfer() {

        }

     // Standard POJO constructor 
        public Golfer(String name, String color, int position) {
            super();
            this.name = name;
            this.color = color;
            this.position = position;
        }

        
        // Getters ....
        
        /**
         * @return the color
         */
        public String getColor() {
            return this.color;
        }

        /**
         * @return the name
         */
        public String getName() {
            return this.name;
        }

        /**
         * @return the name
         */
        public int getPosition() {
            return this.position;
        }

    }
} // End Class Golfer
