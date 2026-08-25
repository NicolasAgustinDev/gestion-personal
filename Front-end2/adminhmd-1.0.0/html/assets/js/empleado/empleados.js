document.addEventListener("DOMContentLoaded", () => {
    obtenerEmpleados();
    // ========================================== 
    // EVENTOS DE LOS BOTONES DE EMPLEADOS 
    // ==========================================
    document.addEventListener("click", (e) => {
        const botonEditar = e.target.closest(".btn-editar");

        if (botonEditar) {
            const id = botonEditar.dataset.id;
            getEmpleado(id);
            return;
        }

        const botonEliminar = e.target.closest(".btn-eliminar");

        if (botonEliminar) {
            const id = botonEliminar.dataset.id;
            eliminarEmpleado(id);
        }
    })
});
async function obtenerEmpleados() {
    try {
        const token = localStorage.getItem("token");

        const payload = JSON.parse(atob(token.split(".")[1]));

        const username = payload.sub;

        const response = await fetch("http://localhost:8080/employees" ,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        
        const empleados = await response.json();

        mostrarEmpleados(empleados);

        document.getElementById("totalEmpleados")
        .innerText = empleados.length;

        const empleadosActivos = empleados.filter(
            empleado => empleado.estado === true
        ).length;

        document.getElementById("empleadosActivos")
        .innerText=empleadosActivos;

        const empleadosInactivos = empleados.filter(
            empleado => empleado.estado === false
        ).length;
        
        document.getElementById("empleadosInactivos")
        .innerText=empleadosInactivos;

    } catch (error) {
        console.error("Error al obtener empleados:", error);
    }
}
function mostrarEmpleados(empleados) {
    const tabla = document.getElementById("tablaEmpleados");

    tabla.innerHTML = "";

    empleados.forEach(empleado => {

        tabla.innerHTML += `
            <tr>
                <td>${empleado.nombre}</td>
                <td>${empleado.apellido}</td>
                <td>${empleado.dni}</td>
                <td>
                    ${
                        empleado.sueldo
                            ? `$${empleado.sueldo.toLocaleString("es-AR")}`
                            : "Sin sueldo"
                    }
                </td>
                <td>${empleado.telefono}</td>
                <td class="email-cell">${empleado.email}</td>
                <td class="categoria">
                    ${
                        obtenerCategoria(empleado.categoriaId)
                    }
                </td>
                <td class="text-end" >${empleado.fechaingreso}</td>
                <td>
                    ${
                        empleado.estado
                            ? '<span class="badge bg-success">Activo</span>'
                            : '<span class="badge bg-danger">Inactivo</span>'
                    }
                </td>
                <td class="text-end">
                    <div>
                        <button 
                            class="btn btn-warning btn-sm btn-editar"
                            data-bs-toggle="modal"
                            data-bs-target="#modalEditar"
                            data-id="${empleado.id}"
                        >
                        Editar
                        </button>
                        <button
                            class="btn btn-danger btn-sm btn-eliminar"
                            data-id="${empleado.id}"
                        >
                        Eliminar
                        </button>
                    </div>   
                </td>
            </tr>
        `;
    });
}
