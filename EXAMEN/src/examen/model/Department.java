package EXAMEN.model;

public class Department implements Comparable<Department>{
    private int departmentId;
    private String name;
    
    public Department(int departmentId, String name) {
        if(departmentId != 0) {
          this.departmentId = departmentId;  
        }
        
        this.name = name;
    }

    @Override
    public int compareTo(Department d) {
        return Integer.compare(this.departmentId, d.departmentId); //BORRA COPIADO
    }


    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.departmentId;
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
        final Department other = (Department) obj;
        return this.departmentId == other.departmentId;
    }
    
    

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", name=" + name + '}';
    }
    
}
