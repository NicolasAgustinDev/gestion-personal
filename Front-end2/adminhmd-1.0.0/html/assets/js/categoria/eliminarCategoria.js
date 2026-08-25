// ========================================== 
//  EVENTO DE FORMULARIO
//  ==========================================
document.addEventListener("DOMContentLoaded", () => {
    obtenerCategorias();
});
// ========================================== 
//  ELIMINAR CATEGORIA
//  ==========================================
async function eliminarCategoria(id){
    try{

        // Confirmación antes de Eliinar
        const confirmar = confirm( "¿Estás seguro de que querés eliminar esta categoria?" );

        if(!confirmar){
            return;
        }

        const token = localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/categoria/${id}`,
            {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
            }
        );
        if (response.ok) {
            alert("Categoria eliminada correctamente");
            obtenerCategorias();
        } else {
            alert("Error al eliminar categoria");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}