package org.drools.games.numberguess;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is the main class for a simple command line number guessing game.
 * From a Drools perspective, it's a very instructive example of using 
 * agenda groups focus, modify and retract, to achieve proper game play 
 * for a simple command line based game.
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class NumberGuessMain {

    public static void main(String[] args) {
        
        /*
         * The file that links the Kbase and KSessions together in this case is
         * kmodule.xml, which is found under src/main/resources/META-INF/kmodule.xml.
         * The "kbase name" is the location of the RULES DRL, the "ksession name" is 
         * the Java Class location. So as an example it would be: 
         *   <kbase name="org.drools.examples.fibonacci" packages="org.drools.examples.fibonacci">
         *        <ksession name="FibonacciKS"/>
         *   </kbase> 
         * kbase name="org.drools.examples.fibonacci" => src/main/resources/org.drools.examples.fibonacci
         * ksession name="FibonacciKS" => src/main/java/org.drools.examples.fibonacci
         */
        
        /* After the XML defined the Kbase and KSessions it's possible to create the KieServices and the
         * KieContainer for that KieModule, and finally the KSession, which takes the ksession  XML parameter as an ID.
         * 
         */
        
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        final KieSession ksession = kc.newKieSession( "NumberGuessKS");

        
        
        ksession.insert( new GameRules( 25, 5 ) );      // Create a new instance of the GameRules object
        ksession.insert( new Game() );                  // Insert it into working memory

        ksession.fireAllRules();
    }

} // End of Class NumberGuessMain
