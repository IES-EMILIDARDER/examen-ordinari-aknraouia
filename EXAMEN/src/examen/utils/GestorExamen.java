package examen.utils;

import EXAMEN.model.*;
import java.io.BufferedWriter;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.NoSuchElementException;


public class GestorExamen {
    private Set<Department> departments = new HashSet<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<Department, List<Employee>> departmentsXemployees = new HashMap<>();
    
    final String MYSQL_CON = "c:\\temp\\mysql.con";
    GestorBBDD gestorBBDD = new GestorBBDD(MYSQL_CON);

    public void carregaDades()  throws SQLException, IOException {
        
        carregaDepartment(this.departments);
        carregaEmployees(this.employees);
        carregadepartmentsXempleats(this.departmentsXemployees);

       
        
    }      
    public void carregaDepartment(Set<Department> departments) throws SQLException, IOException {

         String sql = """
                     SELECT email, first_name, last_name, e.department_id, department_name
                     FROM departments d, employees e
                     WHERE d.department_id = e.department_id
                     """;

        try (Connection conn = gestorBBDD.getConnectionFromFile();  
             ResultSet resultSet = gestorBBDD.executaQuerySQL(conn, sql) )  {

            while (resultSet.next()) {
                agregaDepartment(departments, new Department(resultSet.getInt("departmentId"),
                                                             resultSet.getString("name") ));
            }

        } catch (SQLException e) {
            System.err.println("Error carregant Departments BBDD: " + e.getMessage());
        }
    }


    public void agregaDepartment(Set<Department> departments, Department d) {
     departments.add(d);
    }
    

    public void carregaEmployees(Map<String, Employee> employees) throws SQLException, IOException {
        String sql = """
                     SELECT email, first_name, last_name, e.department_id, department_name
                     FROM departments d, employees e
                     WHERE d.department_id = e.department_id
                     """;
         
        try(Connection conn = gestorBBDD.getConnectionFromFile();  
             ResultSet rs = gestorBBDD.executaQuerySQL(conn, sql) )  {
            
            Employee employee = null;
            while (rs.next()) {
                employee = employees.get(rs.getString("email"));
                if( employee == null)
                    employee = new Employee (rs.getString("email"),
                                            rs.getString("firstName"),
                                            rs.getString("lastName"),
                                            cercaDepartment (new Department(rs.getInt("departmentId"),null)));
                
                agregaEmployee(employees, employee);
            }
             
        } catch (SQLException e) {
            System.err.println("Error carregant Employees BBDD: " + e.getMessage());
    }
        
    }  
        public void agregaEmployee(Map<String, Employee> employees, Employee employee) {
        employees.put(employee.getEmail(), employee);
    }


    public Department cercaDepartment(Department d) {
       Department department =this.departments.stream().filter(d1 -> d1.equals(d)).findFirst().orElse(null);
       
       if(department != null)
           return department;
       else
           throw new NoSuchElementException("department no trobat a la llista.");
    }


    public void carregadepartmentsXempleats(Map<Department, List<Employee>> departmentsXemployees ) throws SQLException, IOException {
        String sql = """
                     SELECT email, first_name, last_name, e.department_id, department_name
                     FROM departments d, employees e
                     WHERE d.department_id = e.department_id
                     """;
         
        try(Connection conn = gestorBBDD.getConnectionFromFile();  
             ResultSet rs = gestorBBDD.executaQuerySQL(conn, sql) )  {
            
            while (rs.next()) {
               
                
                Employee employee = new Employee(rs.getString("email"), 
                                                 rs.getString("firstName"), 
                                                 rs.getString("lastName"), 
                                                 cercaDepartment (new Department(rs.getInt("departmentId"),rs.getString("name"))));
            }
            
        } catch (SQLException e) {
            System.err.println("Error carregant departamentsXempleats BBDD: " + e.getMessage());
        }
    }
    
    
    public void mostraDepartments(Set<Department> departments) {
       /* List<Department> listaDept = new ArrayList<> (departments);
        Collections.sort(listaDept);
        for( Department c : listaDept){
             System.out.println("DEPARTMENTS"); 
        }*/

        departments.stream().sorted().forEach(System.out::println);
    }   
    
    public void mostraEmployees(Map<String, Employee> employees) {
        /*for (Map.Entry<String, Employee> fact : employees.entrySet()) {
           System.out.println("EMPLOYEES"); 
        }*/
        
        employees.entrySet().stream()
                .sorted() 
                .forEach(entry -> { String key = entry.getKey();
                    Employee e = entry.getValue();
                    System.out.println("Employees :" + key + e.getEmail());
                });
    
    } 
    
    public void mostraDepartmentsXEmployees(Map<Department, List<Employee>> departmentsXemployees) {
        List<Department> listadepXemp = new ArrayList<> (departmentsXemployees.keySet());
        Collections.sort(listadepXemp);
       
        for (Map.Entry<Department, List<Employee>> fact1 : departmentsXemployees.entrySet()) {
            Department d = fact1.getKey();
            List<Employee> e = fact1.getValue();
            System.out.println("Clau:" + fact1.getKey() + "Valor :" + fact1.getValue());
        
        System.out.println("DEPARTMENTS X EMPLOYEES");
        }
    } 
    
    public void desaDepartmentsXEmpleatsCSV(String path) throws IOException {
        try (BufferedWriter br = Files.newBufferedWriter(Paths.get(path))) {
           
            
            for( Employee e : employees.values()) {
                String text = e.getEmail() + "," + e.getFirstName() + "," + e.getLastName() + ",";
                for (Department d : e.getDepartment()) {
                    text += d.getDepartmentId() + "," + d.getName() + ",";
                    br.write(text);
                    br.newLine();
                }
            } 
            
        } catch (IOException | NumberFormatException e) {
          System.err.println("Error descarregant vendes CSV: " + e.getMessage());
    }

    }
    
    
}
