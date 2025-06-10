package EXAMEN;

import examen.utils.GestorExamen;

public class Main {
    public static void main(String[] args) {
        GestorExamen gestor = new GestorExamen();

        try {
            gestor.carregaDades();

            gestor.mostraDepartments(gestor.getDepartments() ); // CORRECCI�: faltaba a?adir el par�metro
            gestor.mostraEmployees(gestor.getEmployees()); // CORRECCI�: faltaba a?adir el par�metro
            gestor.mostraDepartmentsXEmployees(gestor.getDepartmentsXemployees()); // CORRECCI�: faltaba a?adir el par�metro
            
            gestor.desaDepartmentsXEmpleatsCSV("c:\\temp\\departmentXEmpleats.cvs");

            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

