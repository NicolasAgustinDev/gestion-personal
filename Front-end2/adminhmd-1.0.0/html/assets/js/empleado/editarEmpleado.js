// ========================================== 
//  CARGAR EMPLEADO EN EL MODAL 
//  ==========================================

async function getEmpleado(id) {
    try {
        const token = localStorage.getItem("token");

        const response = await fetch(`http://localhost:8080/employees/${id}`,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        const empleado = await response.json();

        document.getElementById("editId").value =
        empleado.id;
        document.getElementById("editNombre").value =
        empleado.nombre;
        document.getElementById("editApellido").value =
        empleado.apellido;
        document.getElementById("editDni").value =
        empleado.dni;
        document.getElementById("editTelefono").value =
        empleado.telefono;
        document.getElementById("editEmail").value =
        empleado.email;
        document.getElementById("editFechaIngreso").value =
        empleado.fechaingreso;
        document.getElementById("editCategoria").value =
        empleado.categoriaId;
        document.getElementById("editEstado").checked =
        empleado.estado;

    } catch (error) {
        console.error("Error al obtener empleado:", error);
    }
}

// ==========================================
// EVENTO DEL FORMULARIO 
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {

    const formulario = document.getElementById("formEditarEmpleado");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        await modificarEmpleado(formulario);

    });
});



// ==========================================
// MODIFICAR EMPLEADO
//  ==========================================

async function modificarEmpleado(formulario) {

    try {

        const id = document.getElementById("editId").value;

        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés modificar este empleado?" );

        if(!confirmar){
            return;
        }


        //Datos actualizados
        const empleadoActualizado = {

            nombre: document.getElementById("editNombre").value,

            apellido: document.getElementById("editApellido").value,

            dni: document.getElementById("editDni").value,

            telefono: document.getElementById("editTelefono").value,

            email: document.getElementById("editEmail").value,

            fechaingreso: document.getElementById("editFechaIngreso").value,

            categoriaId: parseInt(
                document.getElementById("editCategoria").value
            ),
            estado: document.getElementById("editEstado").checked
        };

        const token = localStorage.getItem("token");
        const response = await fetch(
            
            `http://localhost:8080/employees/${id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify(empleadoActualizado)
            }
        );

        if (response.ok) {
            alert("Empleado modificado correctamente");
            //Cerrar el modal
            const modalElement = document.getElementById("modalEditar");
            const modal = bootstrap.Modal.getInstance(modalElement);
            if (modal) { modal.hide(); }
            // Limpiar formulario 
            formulario.reset();
            obtenerEmpleados();
        } else {
            alert("Error al modificar empleado");
            alert("Ocurrió un error al comunicarse con el servidor");
        }

    } catch (error) {
        console.error("Error:", error);
    }
}