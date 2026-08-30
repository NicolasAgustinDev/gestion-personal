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
}

