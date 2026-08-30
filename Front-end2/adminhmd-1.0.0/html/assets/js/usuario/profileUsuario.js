document.addEventListener("DOMContentLoaded", () => {
    profileUsuario();
})

async function profileUsuario() {
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
    document.getElementById("usuarioUsername").textContent=usuario.username;
    document.getElementById("usuarioRol").textContent=usuario.rol;
    const estado = document.getElementById("usuarioEstado");
    estado.textContent = usuario.estado ? "Activo" : "Inactivo";
    estado.classList.add(
        usuario.estado ? "text-bg-success" : "text-bg-danger"
    );
    document.getElementById("usuarioNombre").textContent=usuario.nombre;
    document.getElementById("usuarioEmail").textContent=usuario.email;
    
}