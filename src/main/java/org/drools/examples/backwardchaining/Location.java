package org.drools.examples.backwardchaining;

/*
 * Support for "positional" syntax has been added for more compact code. By default the declared 
 * type order in the type declaration matches the argument position. But it possible to override 
 * these using the @position annotation. This allows patterns to be used with positional arguments, 
 * instead of the more verbose named arguments.
 * 
 * The @Position annotation, in the org.drools.definition.type package, can be used to annotate 
 * original polo on the classpath. Currently only fields on classes can be annotated. Inheritance 
 * of classes is supported, but not interfaces or methods.
 * 
 * The isContainedIn query in the rule demonstrates the use of positional arguments in a pattern; 
 * Location(x, y;) instead of Location( thing == x, location == y).
 */

import org.kie.api.definition.type.Position;



/**
 * This is a sample class to create a POJO which represents the concept of 'location' in an adventure game, 
 * with typical POJO setters and getters. However, Drools recommend using property access ex: (age) over 
 * using typical POJO getters explicitly ex: (getAge()), because of performance enhancements through field 
 * indexing.
 * 
 * The calls to this class will be of the sort: ksession.insert( new Location("Office", "House") );
 * Argument 1 is considered an "item" regardless of if its something the size of a room. Argument 2
 * is considered an actual location per this class location.
 */

public class Location {
    @Position(0)            // See Position notes above the import statement
    private String item;

    @Position(1)            // See Position notes above the import statement
    private String location;

    public Location(String item, String location) { // Traditional POJO constructor function (item, location).
        this.item = item;
        this.location = location;
    }

    public String getItem() {           // POJO getters and setters are discouraged by Drools in favor of their
        return item;                    // using their property access - ex: (age) over using POJO getters explicitly 
    }                                   // ex: (getAge()). So even if these methods exist, they aren't typically used.

    public void setItem(String item) {
        this.item = item;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // These 3 methods are simple POJO overrides used to refine the existing method definitions of 
    // equals,  hashCode, and toString. Since this particular object was created for a different 
    // program, these three method overrides were never used in this example, and can safely be 
    // ignored.
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Location location1 = (Location) o;

        if (item != null ? !item.equals(location1.item) : location1.item != null) { return false; }
        if (location != null ? !location.equals(location1.location) : location1.location != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
               "item='" + item + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
    
    
} // End of Class Location
