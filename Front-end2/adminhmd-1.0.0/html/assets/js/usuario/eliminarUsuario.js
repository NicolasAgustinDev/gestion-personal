// ========================================== 
//  EVENTO DE FORMULARIO
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {
    obtenerUsuarios();
});
// ========================================== 
//  ELIMINAR USUARIO  
//  ==========================================
async function eliminarUsuario(id){
    try{

        // Confirmación antes de Eliinar
        const confirmar = confirm( "¿Estás seguro de que querés eliminar este Usuario?" );

        if(!confirmar){
            return;
        }

        const token = localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/usuario/${id}`,
            {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );
        if (response.ok) {
            alert("Usuario eliminado correctamente");
            obtenerUsuarios();
        } else {
            alert("Error al Eliminar Usuario");
            alert("Ocurrió un error al comunicarse con el servidor");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}