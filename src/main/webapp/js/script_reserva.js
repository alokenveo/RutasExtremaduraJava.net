// Cargar rutas al select
document.addEventListener("DOMContentLoaded", () => {
    const mensajeForm = document.getElementById("mensaje-form");

    const inputTelf= document.getElementById("telefono");
    inputTelf.addEventListener("input", () => {
        inputTelf.value = inputTelf.value.replace(/\D/g, "").slice(0, 9);
    });

    const InputNumDocumento = document.getElementById("num-documento");
    InputNumDocumento.addEventListener("input", () => {
        let valor = InputNumDocumento.value.toUpperCase();
        let primeraLetra = valor.charAt(0).replace(/[^A-Z]/g, "");
        let numeros = valor.slice(1).replace(/\D/g, "").slice(0, 7);
        
        InputNumDocumento.value = primeraLetra + numeros
    });

    const form = document.getElementById("form-reserva");

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        mensajeForm.textContent = "";

        const telefono = form.telefono.value.trim();
        const ruta = form.ruta.value;
        const fecha = form.fecha.value;
        const personas = parseInt(form.personas.value);

        // Validaciones básicas
        if (!nombre || !email || !telefono || !ruta || !fecha || !personas) {
            mensajeForm.textContent = "Por favor, rellena todos los campos obligatorios.";
            return;
        }

        if (personas < 1 || personas > 50) {
            mensajeForm.textContent = "El número de personas debe estar entre 1 y 50.";
            return;
        }

        mensajeForm.style.color = "green";
        mensajeForm.textContent = "¡Reserva realizada con éxito! (Simulada)";

        setTimeout(() => {
            form.reset();
            mensajeForm.style.color = "red";
            mensajeForm.textContent = "";
        }, 2000);
    });
});
