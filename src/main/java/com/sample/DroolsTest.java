package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/*
 * Official Drools V6 Examples Online Docs: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e19211
 */

/**
 * This is a sample class to launch a rule, and print out it's behavior. Check the rule located here: 
 * /resources/rules/Sample.drl file for the comments regarding the rule.
 * This is also the stock class that gets built when creating a new Drools Project. It's *not* part of the 
 * Drools examples suite, but there is one like this in the examples suite. 
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class DroolsTest {

    public static final void main(String[] args) {
        try {  // ALways do a try..catch block for the main code of the class....
            
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
            
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // Create a Message Object, set the message to "Hello World", instantiate the message status,
        	// insert the message into the KB and fire all of the matching rules.
        	
            Message message = new Message();
            message.setMessage("Hello Good World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // This is an internal class but it could have been defined in it's own file just as well.
    // It's a Standard POJO. Note the use of an integer status field both here and in the rule.
    // That's one of those neat programming tricks of the trade that comes in handy later on.
    // This POJO will match the LHS of the rule: Message( status == Message.HELLO, myMessage : message ),
    // when the status is set to HELLO and the variable message is set to something.
    
    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

} // End Class DroolsTest
