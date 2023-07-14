// use reflection to automatically test Hava getters and setters

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.assertThat;

// tester class, comes first to compile under onecompiler.com
class TestClassFields {
  
    private static final Class[] classToTest = new Class[]{ 
        // the list of classes to test
        PersonClass.class, AnimalClass.class
    };

    public static void main(String[] args) {
      for (Class aClass : classToTest) {
        Object instance;
        try {      
           instance = aClass.getDeclaredConstructor().newInstance();
           Field[] declaredFields = aClass.getDeclaredFields();
           for(Field f: declaredFields) {
              // get the field getter and setter, following the Java naming convention (!)
              // www.theserverside.com/feature/Java-naming-conventions-explained
              String name = f.getName();
              name = name.substring(0,1).toUpperCase() + name.substring(1);
              String getterName = "get" + name;
              String setterName = "set" + name;
              Method getterMethod = aClass.getMethod(getterName);
              Method setterMethod = aClass.getMethod(setterName, getterMethod.getReturnType());
              // prepare a test value based on the filed type 
              Object testVal = null;
              Class<?> fType = f.getType();
              if (fType.isAssignableFrom(Integer.class)) {
                  testVal = 1234;
              } else if (fType.isAssignableFrom(String.class)) {
                  testVal = "abcd";
              }
              // test by composing the setter and getter
              setterMethod.invoke(instance, testVal);
              Object result = getterMethod.invoke(instance);
              System.out.printf("Testing class=%s field=%s...\n", aClass.getName(), f.getName());
              assertThat(result).as("in class %s fields %s", aClass.getName(), f.getName()).isEqualTo(testVal);
           }
        } catch(Exception e) {
           System.out.println(e.toString());
        }
      }
   }
}

// example class to be tested
class PersonClass {
 
    private String firstName;

    public String getFirstName() { return firstName; }
 
    public void setFirstName(String N)
    {
        this.firstName = N;
    }
    
    private String lastName;

    public String getLastName() { return lastName; }
 
    public void setLastName(String N)
    {
        this.lastName = N;
    }
    
    private Integer age;

    public Integer getAge() { return age; }
 
    public void setAge(Integer i)
    {
        this.age = i;
    }
    
}

// example class to be tested
class AnimalClass {
 
    private String name;

    public String getName() { return name; }
 
    public void setName(String N)
    {
        this.name = N;
    }
    
    private Integer age;

    public Integer getAge() { return age; }
 
    public void setAge(Integer i)
    {
        this.age = i;
    }
    
}