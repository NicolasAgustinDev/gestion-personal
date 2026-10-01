document.getElementById("btnLogout").addEventListener("click", (e) => {
    e.preventDefault();
    logout();
});

async function logout() {
    const tokenRefresh = localStorage.getItem("tokenRefresh");
    try{
        const response = await fetch("http://localhost:8080/auth/logout", {
                method: "POST",
                headers: {
                    "Authorization" : `Bearer ${tokenRefresh}`
                }
        });
        if(response.ok){
            localStorage.removeItem("token");
            localStorage.removeItem("tokenRefresh");
            window.location.href = "login.html";

        }
    } catch (error) {
        console.error("Error:", error);
    }
}