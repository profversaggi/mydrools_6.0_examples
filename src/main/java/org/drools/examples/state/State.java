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



/*
 * Support for implement 'PropertyChangeSupport' as defined by the JavaBeans specification.
 * For a definitive reference on this topic, begin reading at Example 19.15. Salience State
 * in the Drools Docs: http://docs.jboss.org/drools/release/6.0.1.Final/drools-docs/html_single/index.html
 */
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * This is the state object which has 2 fields, name and state. The two possible states 
 * for each object are NOTRUN and FINISHED. 
 */

public class State {
    public static final int       NOTRUN   = 0;
    public static final int       FINISHED = 1;

    /*
     * Another notable concept in this example is the use of dynamic facts, 
     * based on PropertyChangeListener objects. As described in the documentation, 
     * in order for the engine to see and react to changes of fact properties, 
     * the application must tell the engine that changes occurred. This can be done 
     * explicitly in the rules by using the 'modify' statement, or implicitly by letting 
     * the engine know that the facts implement 'PropertyChangeSupport' as defined by 
     * the JavaBeans specification.
     */
    
    private final PropertyChangeSupport changes  = new PropertyChangeSupport( this );

    private String                name;
    private int                   state;

    public State() {
        
    }
    
    public State(final String name) {
        this.name = name;
        this.state = State.NOTRUN;
    }

    public String getName() {
        return this.name;
    }

    public int getState() {
        return this.state;
    }

    
    // Setter Example with PropertyChangeSupport:
    // When using PropertyChangeListener objects, each setter must implement a little extra code for the notification.
    
    public void setState(final int newState) {
        final int oldState = this.state;
        this.state = newState;
        this.changes.firePropertyChange( "state", oldState, newState ); // <== This is that code.
    }

    public boolean inState(final String name, final int state) {
        return this.name.equals( name ) && this.state == state;
    }

    
    public String toString() {
        switch ( this.state ) {
            case NOTRUN :
                return this.name + "[" + "NOTRUN" + "]";
            case FINISHED :
            default :
                return this.name + "[" + "FINISHED" + "]";
        }
    }

    
    
    /*
     * Dynamic facts based on PropertyChangeListener objects - key infrastructure  
     * to implementing implicit rules engine notification that data has changed. 
     */
    
    public void addPropertyChangeListener(final PropertyChangeListener l) {
        this.changes.addPropertyChangeListener( l );
    }

    public void removePropertyChangeListener(final PropertyChangeListener l) {
        this.changes.removePropertyChangeListener( l );
    }
    
}// End of Class State
