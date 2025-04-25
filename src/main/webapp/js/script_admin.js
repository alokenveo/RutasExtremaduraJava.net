document.addEventListener('DOMContentLoaded', () => {
    const menuItems = document.querySelectorAll('.menu-item');
    const sections = document.querySelectorAll('.admin-section');
    const modalUsuario = document.getElementById('modal-usuario');
    const btnAddUsuario = document.getElementById('add-usuario');
    const btnCloseModalUsuario = document.getElementById('modal-close-usuario');
    const btnCancelUsuario = document.getElementById('cancel-button-usuario');

    const modalRuta = document.getElementById('modal-ruta');
    const btnAddRuta = document.getElementById('add-ruta');
    const btnCloseModalRuta = document.getElementById('modal-close-ruta');
    const btnCancelRuta = document.getElementById('cancel-button-ruta');

    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            // Remover clase active de todos los items y secciones
            menuItems.forEach(i => i.classList.remove('active'));
            sections.forEach(s => s.classList.remove('active'));

            // Añadir clase active al item clicado y su sección correspondiente
            item.classList.add('active');
            const sectionId = item.id.replace('ver-', '') + '-admin';
            document.getElementById(sectionId).classList.add('active');
        });
    });

    // Abrir modal
    btnAddUsuario.addEventListener('click', () => {
        modalUsuario.style.display = 'flex';
    });
    btnAddRuta.addEventListener('click', () => {
        modalRuta.style.display = 'flex';
    });

    // Cerrar modal con la X
    btnCloseModalUsuario.addEventListener('click', () => {
        modalUsuario.style.display = 'none';
    });
    btnCloseModalRuta.addEventListener('click', () => {
        modalRuta.style.display = 'none';
    });

    // Cerrar modal con el botón Cancelar
    btnCancelUsuario.addEventListener('click', () => {
        modalUsuario.style.display = 'none';
    });
    btnCancelRuta.addEventListener('click', () => {
        modalRuta.style.display = 'none';
    });

    // Cerrar modal al hacer clic fuera del contenido
    modalUsuario.addEventListener('click', (e) => {
        if (e.target === modalUsuario) {
            modalUsuario.style.display = 'none';
        }
    });
    modalRuta.addEventListener('click', (e) => {
        if (e.target === modalRuta) {
            modalRuta.style.display = 'none';
        }
    });

    // Opcional: Limpiar formulario al cerrar
    const formUsuario = document.getElementById('form-usuario');
    btnCancel.addEventListener('click', () => {
        formUsuario.reset();
    });
    btnCloseModal.addEventListener('click', () => {
        formUsuario.reset();
    });

    const formRuta = document.getElementById('form-ruta');
    btnCancel.addEventListener('click', () => {
        formRuta.reset();
    });
    btnCloseModal.addEventListener('click', () => {
        formRuta.reset();
    });
});