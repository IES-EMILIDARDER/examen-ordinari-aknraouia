package EXAMEN;

import examen.utils.GestorExamen;

public class Main {
    public static void main(String[] args) {
        GestorExamen gestor = new GestorExamen();

        try {
            gestor.carregaDades();

            gestor.mostraDepartments(gestor.getDepartments() ); // CORRECCIÓ: faltaba a?adir el paràmetro
            gestor.mostraEmployees(gestor.getEmployees()); // CORRECCIÓ: faltaba a?adir el paràmetro
            gestor.mostraDepartmentsXEmployees(gestor.getDepartmentsXemployees()); // CORRECCIÓ: faltaba a?adir el paràmetro
            
            gestor.desaDepartmentsXEmpleatsCSV("c:\\temp\\departmentXEmpleats.cvs");

            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

