document.addEventListener('DOMContentLoaded', () => {
    const menuItems = document.querySelectorAll('.menu-item');
    const sections = document.querySelectorAll('.admin-section');

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
});