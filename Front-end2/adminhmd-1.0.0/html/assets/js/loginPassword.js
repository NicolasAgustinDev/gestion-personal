const togglePassword = document.getElementById("togglePassword");
const password = document.getElementById("password");
togglePassword.addEventListener("click", () =>{
    if(password.type === "password"){
        password.type = "text";
        togglePassword.innerHTML = '<i class="bi bi-eye-slash"></i>';
        togglePassword.setAttribute(
            "aria-label",
            "Ocultar contraseña"
        );
    }else{
        password.type = "password";
        togglePassword.innerHTML = '<i class="bi bi-eye"></i>';
        togglePassword.setAttribute(
            "aria-label",
            "Mostrar contraseña"
        );

    }


});