document.addEventListener("DOMContentLoaded", function () {
    const clientSelect = document.getElementById("clientSelect");
    const form = document.getElementById("adminBookingForm");

    // Hent klienter og tilføj som options
    fetch("/clients")
        .then(response => response.json())
        .then(clients => {
            clients.forEach(client => {
                const option = document.createElement("option");
                option.value = client.id; // vigtigt!
                option.textContent = `${client.name} (${client.email})`;
                clientSelect.appendChild(option);
            });
        })
        .catch(err => {
            console.error("Fejl ved hentning af klienter:", err);
        });

    // Sikring: tjek om en gyldig klient er valgt
    form.addEventListener("submit", function (e) {
        const selectedId = clientSelect.value;
        if (!selectedId || isNaN(parseInt(selectedId))) {
            e.preventDefault();
            alert("⛔ Du skal vælge en klient fra listen!");
        }
    });
});
