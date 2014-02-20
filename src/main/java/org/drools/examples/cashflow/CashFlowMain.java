package org.drools.examples.cashflow;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a simple example of cash flow modeling in Drools.
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class CashFlowMain {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Create Date Object

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession( "CashFlowKS");

        // Create new accountperiod object
        AccountPeriod acp = new AccountPeriod(date( "2013-01-01"), date( "2013-03-31"));

        Account ac = new Account(1, 0);     // Create a new account object

        // Create 4 new cashflow objects cf1-cf4
        CashFlow cf1 = new CashFlow(date( "2013-01-12"), 150, CashFlowType.CREDIT, 1 );      // Credit
        CashFlow cf2 = new CashFlow(date( "2013-02-2"),  200, CashFlowType.DEBIT, 1 );       // Debit
        CashFlow cf3 = new CashFlow(date( "2013-05-18"), 50,  CashFlowType.CREDIT, 1 );      // Credit
        CashFlow cf4 = new CashFlow(date( "2013-03-07"), 75,  CashFlowType.CREDIT, 1 );      // Credit

        FactHandle fh = ksession.insert( acp );     // Insert the new accounting period into WM.
        ksession.insert( ac );                      // Insert the new account into WM

        ksession.insert( cf1 );                     // Insert the 4 new cash flows into WM
        ksession.insert( cf2 );
        ksession.insert( cf3 );
        ksession.insert( cf4 );

        ksession.fireAllRules();                    // Fire all rules.

        acp.setStart(date( "2013-04-01"));          // Change the Start Date to 2nd QTR.
        acp.setEnd(date( "2013-06-31"));            // Change the end date  to 2nd QTR.
        ksession.update(fh, acp);                   // Update the Accounting Period, causes all calculations to change.  


        ksession.fireAllRules();                    // Fire All Rules.
    }

    
    public static Date date(String str) throws Exception {          // Create internal class for date.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse( str );
    }


} // End Class CashFlowMain
