document.addEventListener("DOMContentLoaded", () => {
    reporteCategoria();
})

async function reporteCategoria() {
    const token = localStorage.getItem("token");
    const response = await fetch("http://localhost:8080/categoria/reporte" ,{
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
    }
    const categorias = await response.json();
    document.getElementById("totalEmpleados").textContent=categorias.totalCategorias;
    document.getElementById("empleadosActivos").textContent=categorias.totalCategoriasActivas;
    document.getElementById("empleadosInactivos").textContent=categorias.totalCategoriasInactivas;
    
}