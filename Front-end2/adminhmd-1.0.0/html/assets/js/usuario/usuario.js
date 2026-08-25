document.addEventListener("DOMContentLoaded", () => {
    mostrarUsuario();
})
async function mostrarUsuario(){
    const token = localStorage.getItem("token");

    if (!token) {
        return;
    }

    const payload = JSON.parse(atob(token.split(".")[1]));

    const username = payload.sub;

    const profileName = document.querySelector(".profile-name").textContent = username;
    const sidebarUsername = document.querySelector(".sidebar-username").textContent = username;

    if (profileName) {
        profileName.textContent = username;
    }

    if (sidebarUsername) {
        sidebarUsername.textContent = username;
    }
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

