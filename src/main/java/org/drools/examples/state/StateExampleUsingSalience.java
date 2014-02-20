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

package org.drools.examples.state;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


/**
 * State Example: Basic behavior: forward chaining, i.e., the ability the engine 
 * has to evaluate, activate and fire rules in sequence, based on changes on the 
 * facts in the Working Memory. METHOD: Salience.
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class StateExampleUsingSalience {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();
        
        // From the container, a session is created based on  
        // its definition and configuration in the META-INF/kmodule.xml file 
        KieSession ksession = kc.newKieSession("StateSalienceKS");
        
        /*
         * 1. If the Audit View is not visible, click on "Window" and then select "Show View", 
         * then "Other..." and "Drools" and finally "Audit View".
         * 2. In the "Audit View" click the "Open Log" button and select the file:
         *    "<drools-examples-dir>/log/state.log".
         */
        
        // To setup a file based audit logger, uncomment the next line 
         KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./state" );

        
        final State a = new State( "A" );       // Set up 4 State Objects
        final State b = new State( "B" );
        final State c = new State( "C" );
        final State d = new State( "D" );

        ksession.insert( a );                   // Insert them into working memory
        ksession.insert( b );
        ksession.insert( c );
        ksession.insert( d );

        ksession.fireAllRules();                // Fire all the rules.

         logger.close();
        
        ksession.dispose(); // Statefull rule session must always be disposed when finished        
    }

} // End of Class StateExampleUsingSalience
