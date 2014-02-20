package org.drools.examples.backwardchaining;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.SimpleDateFormat;

/*
 * Official Drools V6 Examples Online Docs: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e19211
 */


/**
 * This is a sample class to create a POJO which represents provides a very nice illustration of how the 
 * process of "Backward Chaining" is implemented in Drools. Each of the rules "go - go5" illustrate a 
 * variant of the process of backward chaining and recursion, which are very instructional of computation 
 * power logic programming techniques can bring to the table. There is also a YouTube video explaining this 
 * code at: http://www.youtube.com/watch?v=fCjIRVSRFvA , and I highly recommend watching it as well as 
 * interrogating this code.
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class HouseOfDoomMain {

    public static void main(String[] args) 
            throws Exception { // ALways do a some form of the try..catch block for the main code of the class.
        
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
        KieSession ksession = kc.newKieSession( "HouseOfDoomKS");

        
        // Create 8 new POJO's, instantiate their values, and insert them into the KB.
        ksession.insert( new Location("Office", "House") );
        ksession.insert( new Location("Kitchen", "House") );
        ksession.insert( new Location("Knife", "Kitchen") );
        ksession.insert( new Location("Cheese", "Kitchen") );
        ksession.insert( new Location("Desk", "Office") );
        ksession.insert( new Location("Chair", "Office") );
        ksession.insert( new Location("Computer", "Desk") );
        ksession.insert( new Location("Draw", "Desk") );

        // These are the 5 go examples which illustrate the variants on backward chaining and recursion. These are simple
        // insertions of the text "goX" into the KB (which match backward chaining rules), firing of the rules, and the
        // printing of a simple divider line. The real magic happens in the rule: BC-Example.drl .
        ksession.insert( "go1" );
        ksession.fireAllRules();
        System.out.println("---");

        ksession.insert( "go2" );
        ksession.fireAllRules();
        System.out.println("---");

        ksession.insert( "go3" );
        ksession.fireAllRules();
        System.out.println("---");

        ksession.insert( new Location("Key", "Draw") );
        ksession.fireAllRules();
        System.out.println("---");

        ksession.insert( "go4" );
        ksession.fireAllRules();
        System.out.println("---");

        ksession.insert( "go5" );
        ksession.fireAllRules();
        
    } // End of throw block 
    
    
}// End of Class HouseOfDoomMain
