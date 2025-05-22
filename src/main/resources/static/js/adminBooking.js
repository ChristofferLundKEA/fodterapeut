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

});
