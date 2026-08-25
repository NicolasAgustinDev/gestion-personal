// ========================================== 
//  CARGAR CATEGORIA EN EL MODAL 
//  ==========================================
async function getCategoria(id) {
    try {
        const token = localStorage.getItem("token");
        const response = await fetch(`http://localhost:8080/categoria/${id}`,{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const categoria = await response.json();

        document.getElementById("editId").value =
        categoria.id;
        document.getElementById("editCodigo").value =
        categoria.codigo;
        document.getElementById("editDescripcion").value =
        categoria.descripcion;
        document.getElementById("editSueldo").value =
        categoria.sueldo;
        document.getElementById("editEstado").value =
        categoria.estado;

    } catch (error) {
        console.error("Error al obtener la categoria:", error);
    }
}

// ========================================== 
// EVENTO DEL FORMULARIO 
// ========================================== 
  document.addEventListener("DOMContentLoaded", () => {

    const formulario = document.getElementById("modalEditarCategoria");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        await editarCategoria(formulario);
    });
});


// ========================================== 
//  EDITAR CATEGORIA
//  ==========================================
async function editarCategoria(formulario) {
    try {
        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés modificar esta categoria?" );

        if(!confirmar){
            return;
        }

        const id = document.getElementById("editId").value;

        const categoriaActualizado = {

            codigo: document.getElementById("editCodigo").value,

            descripcion: document.getElementById("editDescripcion").value,

            sueldo: document.getElementById("editSueldo").value,

            estado: document.getElementById("editEstado").checked
        };
        const token = localStorage.getItem("token");
        const response = await fetch(
            `http://localhost:8080/categoria/${id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify(categoriaActualizado)
            }
        );

        if (response.ok) {
            alert("Categoria modificado correctamente");
            //Cerrar el modal
            const modalElement = document.getElementById("modalEditar");
            const modal = bootstrap.Modal.getInstance(modalElement);
            if (modal) { modal.hide(); }
            // Limpiar formulario 
            formulario.reset();
            obtenerCategorias();

        } else {
            alert("Error al modificar Categoria");
        }

    } catch (error) {

        console.error("Error:", error);
    }
    
}