function loadFragment(id, file) {
  fetch(file)
    .then(res => res.text())
    .then(html => document.getElementById(id).innerHTML = html)
    .catch(err => console.error(`Fejl ved indlæsning af ${file}:`, err));
}

window.addEventListener("DOMContentLoaded", () => {
  loadFragment("topbar", "/fragments/topbar.html");
  loadFragment("navbar", "/fragments/navbar.html");
  loadFragment("footer", "/fragments/footer.html");
});


