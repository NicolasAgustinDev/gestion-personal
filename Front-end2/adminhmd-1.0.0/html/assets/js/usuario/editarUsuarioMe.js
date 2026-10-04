// ========================================== 
//  CARGAR EMPLEADO EN EL MODAL 
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {
    getUsuario();
})
async function getUsuario(){
    try{
        const token = localStorage.getItem("token");

        const response = await fetch("http://localhost:8080/usuario/me" ,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const usuario = await response.json();
        document.getElementById("profileUsuario").value =
        usuario.username;
        document.getElementById("profileNombre").value =
        usuario.nombre;
        document.getElementById("profileEmail").value =
        usuario.email;
        document.getElementById("profileApellido").value =
        usuario.apellido;
    }
    catch(error){
        console.error("Error al obtener Usuario:", error);
    }   
}

// ========================================== 
//  MODIFICAR USUARIO
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {
    const formulario = document.getElementById("formEditarUsuario");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        await modificarUsuario(formulario);
    });
})



async function modificarUsuario(formulario){
    try{

        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés modificar este empleado?" );

        if(!confirmar){
            return;
        }
        //Datos Actualizado
        const usuarioActualizado = {
            nombre : document.getElementById("profileNombre").value,
            apellido : document.getElementById("profileApellido").value,
            username :document.getElementById("profileUsuario").value,
            email : document.getElementById("profileEmail").value
        };

        const token = localStorage.getItem("token");

        const response = await fetch("http://localhost:8080/usuario/me" ,{
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(usuarioActualizado)
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        alert("Empleado modificado correctamente");
        formulario.classList.remove("was-validated");

        const inputs = formulario.querySelectorAll(".is-valid");

        inputs.forEach(input => {
            input.classList.remove("is-valid");
        });
    }
    catch (error){
        console.error("Error al modificar Usuario:", error);
    }
    
}