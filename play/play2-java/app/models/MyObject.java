package models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * User: jconley
 * Date: 12/19/13
 */
public class MyObject {
//    @JsonProperty("id")
    public long id;

//    @JsonProperty("name")
    public String name;

//    public MyObject(long id, String name){
//        this.id = id;
//        this.name = name;
//    }


    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
