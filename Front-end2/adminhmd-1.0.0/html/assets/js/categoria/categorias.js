document.addEventListener("DOMContentLoaded", () => {
    obtenerCategorias();
});

async function obtenerCategorias() {
    try {
        const token = localStorage.getItem("token");

        const response = await fetch("http://localhost:8080/categoria",{
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const categorias = await response.json();

        const contenedor =
            document.getElementById(
                "contenedor-categorias"
        );

        contenedor.innerHTML = "";

        categorias.forEach(categoria => {

            contenedor.innerHTML += `
            
            <div class="col-xl-3 col-lg-4 col-md-6 mb-4">

                <div class="card-categoria">

                    <div class="header-card">

                        <div class="icono">
                            <i class="bi bi-briefcase-fill"></i>
                        </div>

                        <div class="info">
                            <h5>${categoria.codigo}</h5>
                            <p>${categoria.descripcion}</p>
                        </div>

                    </div>

                    <div class="estado">
                        ${
                            categoria.estado
                                ? '<span class="badge bg-success">Activo</span>'
                                : '<span class="badge bg-danger">Inactivo</span>'
                        }
                    </div>

                    <div class="datos-categoria">

                        <div>
                            <small>Empleados</small>
                            <h6>
                                ${categoria.cantidadEmpleados}
                            </h6>
                        </div>

                        <div>
                            <small>Sueldo Base</small>
                            <h6>
                                ${
                                    categoria.sueldo
                                        ?`$${categoria.sueldo.toLocaleString("es-AR")}`
                                        :"Sin sueldo"
                                }
                            </h6>
                        </div>

                    </div>

                    <div class="acciones">

                        <button
                            class="btn btn-warning btn-sm"
                            data-bs-toggle="modal"
                            data-bs-target="#modalEditar"
                            onclick="getCategoria(${categoria.id})">
                            Editar
                        </button>

                        <button
                            onclick="eliminarCategoria(${categoria.id})">
                            Eliminar
                        </button>

                    </div>

                </div>

            </div>
            `;
        });
    } catch(error) {
        console.error(
            "Error al obtener categorias:",
            error
        );
    }
}