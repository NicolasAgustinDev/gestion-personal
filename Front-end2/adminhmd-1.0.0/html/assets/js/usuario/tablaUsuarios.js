document.addEventListener("DOMContentLoaded", () => {
    obtenerUsuarios();
    // ========================================== 
    // EVENTOS DE LOS BOTONES DE Usuarios 
    // ==========================================
    document.addEventListener("click", (e) => {
        const botonEditar = e.target.closest(".btn-editar");
        
        if (botonEditar) {
            const id = botonEditar.dataset.id;
            getUsuario(id);
            return;
        }

        const botonEliminar = e.target.closest(".btn-eliminar");

        if (botonEliminar) {
            const id = botonEliminar.dataset.id;
            eliminarUsuario(id);
        }
    })
});

async function obtenerUsuarios(){
    try{
        const token = localStorage.getItem("token");
        const response = await fetch("http://localhost:8080/usuario" ,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        const usuarios = await response.json(response); 
        mostrarUsuarios(usuarios);

    }catch(error){
        console.error("Error al obtener los usuarios: ",error);
    }
}

function mostrarUsuarios(usuarios){
    const tabla = document.getElementById("tablaUsuarios")
    tabla.innerHTML = "";
    usuarios.forEach(usuario => {
        tabla.innerHTML += `
            <tr>
                <td>${usuario.nombre} ${usuario.apellido}</td>
                <td>${usuario.username}</td>
                <td class="email-cell">${usuario.email}</td>
                <td>
                    ${usuario.rol}
                </td>
                <td>
                    ${
                        usuario.estado
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
                            data-id="${usuario.id}"
                        >
                        Editar
                        </button>
                        <button
                            class="btn btn-danger btn-sm btn-eliminar"
                            data-id="${usuario.id}"
                        >
                        Eliminar
                        </button>
                    </div>   
                </td>
            </tr>
        `;
    });
}