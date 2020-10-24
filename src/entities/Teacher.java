package entities;

public class Teacher {

    private String name;

    public Teacher(String name) {
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
        if (!(obj instanceof Teacher)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Teacher teacher = (Teacher) obj;

        return this.name.equals(teacher.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
