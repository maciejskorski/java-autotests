package autotester;

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