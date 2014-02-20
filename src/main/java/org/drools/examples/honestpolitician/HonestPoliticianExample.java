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

package org.drools.examples.honestpolitician;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 *  The Honest Politician example demonstrates 'truth maintenance with logical assertions'. 
 *  The basic premise is that an object can only exist while a statement is true. A rule's 
 *  consequence can logically insert an object with the insertLogical() method. This means 
 *  the object will only remain in the Working Memory as long as the rule that logically 
 *  inserted it remains true. When the rule is no longer true the object is automatically 
 *  retracted. That's the core of the truth maintenance concept.
 *  
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class HonestPoliticianExample {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        KieSession ksession = kc.newKieSession("HonestPoliticianKS");

        // In this example there is the class Politician, with a name and a boolean 
        // value for being honest. Four politicians with honest state set to true are 
        // inserted.
        
        final Politician p1 = new Politician( "President of Umpa Lumpa", true );
        final Politician p2 = new Politician( "Prime Minster of Cheeseland", true );
        final Politician p3 = new Politician( "Tsar of Pringapopaloo", true );
        final Politician p4 = new Politician( "Omnipotence Om", true );

        ksession.insert( p1 );
        ksession.insert( p2 );
        ksession.insert( p3 );
        ksession.insert( p4 );

        ksession.fireAllRules();

        ksession.dispose();
    }

}// End Class HonestPoliticianExample





