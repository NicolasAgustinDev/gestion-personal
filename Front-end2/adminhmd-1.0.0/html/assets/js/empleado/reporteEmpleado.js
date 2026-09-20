document.addEventListener("DOMContentLoaded", () => {
    reporteEmpleado();
})

async function reporteEmpleado(){
    const token = localStorage.getItem("token");
    const response = await fetch("http://localhost:8080/employees/reporte" ,{
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
    }
    const empleados = await response.json();
    document.getElementById("empleadosTotal").textContent=empleados.totalEmpleados;
    document.getElementById("empleadosActivos").textContent=empleados.totalEmpleadosActivos;
    document.getElementById("empleadosInactivos").textContent=empleados.totalEmpleadosInactivos;
}