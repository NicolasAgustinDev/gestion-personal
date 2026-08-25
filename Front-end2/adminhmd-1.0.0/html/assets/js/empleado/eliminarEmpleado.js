// ========================================== 
//  EVENTO DE FORMULARIO
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {
    obtenerEmpleados();
});
// ========================================== 
//  ELIMINAR EMPLEADO  
//  ==========================================
async function eliminarEmpleado(id){
    try{

        // Confirmación antes de Eliinar
        const confirmar = confirm( "¿Estás seguro de que querés eliminar este empleado?" );

        if(!confirmar){
            return;
        }

        const token = localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/employees/${id}`,
            {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            }
        );
        if (response.ok) {
            alert("Empleado eliminado correctamente");
            obtenerEmpleados();
        } else {
            alert("Error al Eliminar empleado");
            alert("Ocurrió un error al comunicarse con el servidor");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}
