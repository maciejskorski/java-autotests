![Java](https://img.shields.io/badge/java-%23ED8B00.svg?&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?&logo=Gradle&logoColor=white)
[![codecov](https://codecov.io/gh/maciejskorski/java-autotests/branch/main/graph/badge.svg?token=6PYW0BVHE5)](https://codecov.io/gh/maciejskorski/java-autotests)

# java-autotests
The repo shows how to automate tests for Java classes, run them on GitHub Actions, and present CodeCoverage.

The idea is to use reflection and dynamically construct and invoke getters and setters, based on extracted fields and their types.


```java
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
}
```
