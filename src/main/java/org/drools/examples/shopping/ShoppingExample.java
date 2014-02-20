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

package org.drools.examples.shopping;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * This is a very simple example of rules governing a simple purchase process, it's discounts, and transactions. 
 * It's instructive as a beginning exercise in understanding the beginning transaction processing model. 
 * However, it's very useful for examining 'accumulate' function in the rules section. 
 * 
 * The official DOC for all the Drools V6 example is here: 
 * http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html/ch19.html#d0e20175
 */

public class ShoppingExample {

    public static final void main(String[] args) {
        
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
        System.out.println(kc.verify().getMessages().toString());
        KieSession ksession = kc.newKieSession("ShoppingKS");

        
        Customer mark = new Customer( "mark", 0 );      // Create new customer and insert it into working memory.
        ksession.insert( mark );

        Product shoes = new Product( "shoes", 60 );     // Create new product and insert it into working memory.
        ksession.insert( shoes );

        Product gloves = new Product( "gloves", 20 );   // Create new product and insert it into working memory.
        ksession.insert( gloves );
        
        Product hat = new Product( "hat", 60 );         // Create new product and insert it into working memory.
        ksession.insert( hat );

        ksession.insert( new Purchase( mark, shoes ) );  // Create new purchase and insert into working memory.
        ksession.insert( new Purchase( mark, gloves ) ); // Create new purchase and insert into working memory.

                                                        // Create new purchase and insert into working memory.
                                                        // Get the handle on that purchase for later modification.
        FactHandle hatPurchaseHandle = ksession.insert( new Purchase( mark, hat ) );

        ksession.fireAllRules();                        // Fire all rules.

        ksession.delete( hatPurchaseHandle );           // Use handle to delete the hat purchase.
        System.out.println( "Customer mark has returned the hat" );
        ksession.fireAllRules();
    }
    

    
    /**
     * This is an internal class to represent a customer,
     * which takes a name and a discount as inputs.  
     */
    
    public static class Customer {
        private String name;
        private int    discount;

        public Customer(String name, int discount) {
            this.name = name;
            this.discount = discount;
        }

        public String getName() {
            return name;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

    }

    
    /**
     * This is an internal class to represent a discount, 
     * which takes a customer and an amount as inputs.  
     */
    
    public static class Discount {
        private Customer customer;
        private int      amount;

        public Discount(Customer customer, int amount) {
            this.customer = customer;
            this.amount = amount;
        }

        public Customer getCustomer() {
            return customer;
        }

        public int getAmount() {
            return amount;
        }
        
    }

    
    /**
     * This is an internal class to represent a Product, 
     * which takes a name and price as inputs. 
     */
    
    public static class Product {
        private String name;
        private float  price;

        public Product(String name,
                       float price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public float getPrice() {
            return price;
        }

    }

    
    /**
     * This is an internal class to represent a Purchase, 
     * which takes a customer and product as inputs.
     */
    
    public static class Purchase {
        private Customer customer;
        private Product  product;

        public Purchase(Customer customer, Product product) {
            this.customer = customer;
            this.product = product;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Product getProduct() {
            return product;
        }
    }
    
    
} // End Class ShoppingExample
