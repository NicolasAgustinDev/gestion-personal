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
        document.getElementById("profileEstado").checked =
        usuario.estado;
    }
    catch(error){
        console.error("Error al obtener Usuario:", error);
    }
    
    
}