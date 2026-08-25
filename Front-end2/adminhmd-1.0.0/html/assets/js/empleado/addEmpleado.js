document.addEventListener("DOMContentLoaded", () => {

    const formulario = document.getElementById("anadirEmpleados");

    formulario.addEventListener("submit", async (e) => {

        e.preventDefault();

        // Confirmación antes de guardar
        const confirmar = confirm( "¿Estás seguro de que querés agregar este empleado?" );

        if(!confirmar){
            return;
        }

        const empleado = {
            nombre: document.getElementById("nombre").value,
            apellido: document.getElementById("apellido").value,
            dni: document.getElementById("dni").value,
            telefono: document.getElementById("telefono").value,
            email: document.getElementById("email").value,
            categoriaId: parseInt(document.getElementById("categoria").value)
        };

        try {
            const token = localStorage.getItem("token");
            const response = await fetch("http://localhost:8080/employees", {
                method: "POST",

                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },

                body: JSON.stringify(empleado)
            });

            if (response.ok) {
                alert("Empleado agregado correctamente");
                // Limpiar formulario
                formulario.reset();
            } else {
                alert("Error al guardar empleado");
                alert("Ocurrió un error al comunicarse con el servidor");
            }
        } catch (error) {
            console.error("Error:", error);
        }
    });
});