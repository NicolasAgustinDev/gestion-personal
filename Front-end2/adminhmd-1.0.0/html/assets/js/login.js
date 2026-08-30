document.addEventListener("DOMContentLoaded", () => {
    const formulario = document.getElementById("login");
    formulario.addEventListener("submit", async (e) => {
        e.preventDefault();
        const usuario = {
            username : document.getElementById("loginuser").value,
            password : document.getElementById("loginPassword").value
        };
        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(usuario)
            });

            if (response.ok) {
                // Guardar los tokens
                const data = await response.json();
                localStorage.setItem("token", data.token);
                localStorage.setItem("tokenRefresh", data.tokenRefresh);

                console.log("token:", data.token);
                console.log("tokenRefresh:", data.tokenRefresh);
                alert("Inicio de Sesion correcto");
                window.location.href = "index.html";
            } else {
                alert("Error al ingresar al sistema");
            }
        } catch (error) {

            console.error("Error:", error);
        }
    });
});
