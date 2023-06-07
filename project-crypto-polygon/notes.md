###JSON Deserialization

One of the common siutations when you are developing Spring Boot Application is JSON Deserialization.

Today, let’s discuss a special scenario that you should aware of.

####Problem

Let’s take a look on the below class, an non-static inner class is declared inside an outer class. A list of inner class type is a member of the outer class type. Everything sounds alright.



Expecting the result after deserialization:



But it was …



####Explaination

In Java, when deserializing JSON into objects using libraries like Jackson or Gson, the inner class (Type B) needs to be declared as static if it is a member of another class (Type A) for the following reasons:

####Accessibility
Non-static inner classes (also known as inner non-static nested classes) have an implicit reference to an instance of their enclosing class. During deserialization, the JSON library needs to create an instance of Type B independently, without an enclosing instance of Type A. Making Type B static removes this dependency, allowing it to be instantiated without an enclosing instance.

####Serialization and deserialization frameworks
Most JSON serialization and deserialization frameworks operate on the assumption that the classes being serialized or deserialized are self-contained and can be instantiated independently. Static inner classes fulfill this requirement, while non-static inner classes do not.

####Size and memory considerations
Non-static inner classes carry an implicit reference to their enclosing instance, which increases their memory footprint. By making Type B static, you eliminate this implicit reference, potentially reducing memory usage.

public class TypeA {
    private List<TypeB> typeBList;

    public class TypeB {
        // Fields and methods of TypeB
    }
}
To make TypeB static, you would modify it as follows:

public class TypeA {
    private List<TypeB> typeBList;

    public static class TypeB {
        // Fields and methods of TypeB
    }
}
However, there is another scenario …

If Type B is an object of Type A and not a list of objects, then it doesn’t necessarily need to be declared as static. In this case, Type B can be a non-static inner class, meaning it has an implicit reference to an instance of its enclosing class (Type A).

Here’s an example to clarify:

public class TypeA {
    private TypeB typeB;

    public class TypeB {
        // Fields and methods of TypeB
    }
}
In this scenario, Type B can be a non-static inner class because it represents an object that is associated with an instance of Type A. During serialization and deserialization, the JSON library will handle the inner class appropriately.

To summarize, if Type B is an object of Type A (not a list), it can be a non-static inner class without causing any issues during JSON deserialization. The requirement for the inner class to be static primarily arises when it is part of a collection, such as a list or an array, since those need to be deserialized independently from the enclosing class.
選擇 Repo