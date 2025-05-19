function loadFragment(id, file) {
    const el = document.getElementById(id);
    if (!el) return;
    fetch(file)
        .then(res => res.text())
        .then(html => el.innerHTML = html)
        .catch(err => console.error(`Fejl ved indlæsning af ${file}:`, err));
}

window.addEventListener("DOMContentLoaded", () => {
    loadFragment("sidebar", "/fragments/dashSidebar.html");
});
