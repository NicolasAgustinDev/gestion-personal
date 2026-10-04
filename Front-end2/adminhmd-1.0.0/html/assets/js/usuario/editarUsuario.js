// ========================================== 
//  CARGAR EMPLEADO EN EL MODAL 
//  ==========================================
async function getUsuario(id) {
    try {
        const token = localStorage.getItem("token");

        const response = await fetch(`http://localhost:8080/usuario/${id}`,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        const usuario = await response.json();

        document.getElementById("editId").value =
        usuario.id;
        document.getElementById("editNombre").value =
        usuario.nombre;
        document.getElementById("editApellido").value =
        usuario.apellido;
        document.getElementById("editUsername").value =
        usuario.username;
        document.getElementById("editEmail").value =
        usuario.email;
        document.getElementById("editRol").value =
        usuario.rol;
        document.getElementById("editEstado").checked =
        usuario.estado;
    } catch (error) {
        console.error("Error al obtener Usuario:", error);
    }
}

// ==========================================
// EVENTO DEL FORMULARIO 
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {

    const formulario = document.getElementById("formEditarUsuario");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        await modificarUsuario(formulario);
    });
});

// ==========================================
// EVENTO DEL FORMULARIO 
//  ==========================================
async function modificarUsuario(formulario) {
    try{
        const id = document.getElementById("editId").value;

        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés modificar este Usuario?" );

        if(!confirmar){
            return;
        }
        //Datos actualizados
        const usuarioActualizado = {

            nombre: document.getElementById("editNombre").value,

            apellido: document.getElementById("editApellido").value,

            username: document.getElementById("editUsername").value,

            email: document.getElementById("editEmail").value,

            rol: document.getElementById("editRol").value,
            
            estado: document.getElementById("editEstado").checked
        };
        const token = localStorage.getItem("token");
        const response = await fetch(
            
            `http://localhost:8080/usuario/${id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify(usuarioActualizado)
            }
        );

        if (response.ok) {
            alert("Usuario modificado correctamente");
            //Cerrar el modal
            const modalElement = document.getElementById("modalEditar");
            const modal = bootstrap.Modal.getInstance(modalElement);
            if (modal) { modal.hide(); }
            // Limpiar formulario 
            formulario.reset();
            obtenerUsuarios();
        } else {
            alert("Error al modificar Usuario");
            alert("Ocurrió un error al comunicarse con el servidor");
        }
    }catch(error){
        console.error("Error", error);
    }
    
}