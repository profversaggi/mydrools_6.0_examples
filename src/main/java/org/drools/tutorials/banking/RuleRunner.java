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

import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.api.io.ResourceType;

import java.util.Collection;


/**
 * The class RuleRunner is a simple harness to execute one or more DRL files 
 * 'against' a *set* of data. That's the basic illustration of this example 
 * in a nutshell.  
 * 
 * It compiles the Packages and creates the KnowledgeBase for each execution, 
 * allowing us to easily execute each scenario and inspect the outputs. In 
 * reality this is not a good solution for a production system, where the 
 * Knowledge Base should be built just once and cached, but for the purposes 
 * of this tutorial it shall suffice.
 */

public class RuleRunner {

    public RuleRunner() {
    }

    public void runRules(String[] rules, Object[] facts) {

        // Build the Kbase and it's container, load the rules ...
        
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        for ( int i = 0; i < rules.length; i++ ) {
            String ruleFile = rules[i];
            System.out.println( "Loading file: " + ruleFile );     // Indicate the Loading of a rule file.
            kbuilder.add( ResourceFactory.newClassPathResource( ruleFile, RuleRunner.class ), ResourceType.DRL );
        }

        
        // Build the stateful Ksession
        
        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addKnowledgePackages( pkgs );
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        for ( int i = 0; i < facts.length; i++ ) {
            Object fact = facts[i];
            System.out.println( "Inserting fact: " + fact );      // Indicating the insertion of facts into WM.
            ksession.insert( fact );
        }

        ksession.fireAllRules();        // Execute the rules.
        
    } // End method runRules
    
}// End Class RuleRunner
