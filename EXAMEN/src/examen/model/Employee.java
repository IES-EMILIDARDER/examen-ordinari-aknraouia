package EXAMEN.model;

import java.util.Objects;

public class Employee implements Comparable <Employee> {
    private String firstName;
    private String lastName;
    private String email;
    private Department department;

    public Employee(String fisrtName, String lastName, String email, Department department) {
        this.firstName = fisrtName;
        this.lastName = lastName;
        if(email!= null) {
           this.email = email; 
        }
        this.department = department;
    }

    @Override
    public int compareTo(Employee e) {
        int first = this.firstName.compareTo(e.firstName);  //LO DE ABAJO FUNCIONA TODO
        if (first != 0) { //si es 0, significa que son iguales, por tanto comparamos apellido
            return first;
        }
        return this.lastName.compareTo(e.lastName);

    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this.email, other.email);
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", department=" + department + '}';
    }
            
    
}
