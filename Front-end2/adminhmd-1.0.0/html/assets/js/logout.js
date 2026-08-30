document.getElementById("btnLogout").addEventListener("click", (e) => {
    e.preventDefault();

    logout();
});

function logout() {

    localStorage.removeItem("token");
    localStorage.removeItem("tokenRefresh");

    window.location.href = "login.html";
}