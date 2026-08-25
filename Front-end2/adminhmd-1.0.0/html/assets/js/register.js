document.addEventListener("DOMContentLoaded", () => {
    const formulario = document.getElementById("register");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        const usuario = {
            nombre : document.getElementById("nombre").value,
            apellido : document.getElementById("apellido").value,
            username : document.getElementById("username").value,
            email : document.getElementById("email").value,
            password : document.getElementById("password").value
        };
        try {
            const response = await fetch("http://localhost:8080/auth/register", {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(usuario)
            });

            if (response.ok) {
                alert("Registro de usuario correcto");
                window.location.href = "index.html";
            } else {
                alert("Error al registrar el usuario");
            }
        } catch (error) {

            console.error("Error:", error);
        }
    });

})