document.addEventListener("DOMContentLoaded", function () {
    var scrollToTopButton = document.getElementById("scrollToTopButton");

    // Mostra o botão quando o usuário rola para baixo
    window.addEventListener("scroll", function () {
        if (window.scrollY > 500) {
            scrollToTopButton.style.display = "block";
        } else {
            scrollToTopButton.style.display = "none";
        }
    });

    // Role suavemente para o topo quando o botão é clicado
    scrollToTopButton.addEventListener("click", function () {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        });
    });
});