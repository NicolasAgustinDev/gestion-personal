document.addEventListener("DOMContentLoaded", () => {

    const formulario = document.getElementById("anadirCategoria");

    formulario.addEventListener("submit", async (e) => {

        e.preventDefault();

        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés agregar esta categoria?" );

        if(!confirmar){
            return;
        }

        const categoria = {
            codigo: document.getElementById("codigo").value,
            descripcion: document.getElementById("categoria").value,
            sueldo: document.getElementById("sueldo").value
        };

        try {
            const token = localStorage.getItem("token");
            const response = await fetch("http://localhost:8080/categoria", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify(categoria)
            });

            if (response.ok) {
                alert("Categoria agregado correctamente");
                // Limpiar formulario
                formulario.reset();
            } else {
                alert("Error al guardar categoria");
                alert("Ocurrió un error al comunicarse con el servidor");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    });
});