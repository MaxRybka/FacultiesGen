package entities;

public class Student{

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Pair or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Student)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Student student = (Student) obj;

        return this.name.equals(student.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
