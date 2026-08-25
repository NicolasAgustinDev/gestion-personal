function obtenerCategoria(categoriaId){
    switch(categoriaId){
        case 2:{
            return '<span class="badge bg-dark">Oficial Multiple</span>'
        }
        case 3:{
            return'<span class="badge bg-dark">Operario Especializado</span>'
        }
        case 4:{
            return'<span class="badge bg-dark">Ingresante</span>'
        }
        case 5:{
            return'<span class="badge bg-dark">Medio Oficial</span>'
        }
        case 6:{
            return'<span class="badge bg-dark">Operario Multiple</span>'
        }
    }
}