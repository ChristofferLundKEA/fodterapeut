document.addEventListener("DOMContentLoaded", function () {
    const clientInput = document.getElementById("clientInput");
    const hiddenClientId = document.getElementById("clientId");
    const form = document.getElementById("adminBookingForm");

    let clients = [];
    let dropdown;

    // Hent klienter fra backend
    fetch("/clients")
        .then(response => response.json())
        .then(data => {
            clients = data;
        })
        .catch(err => {
            console.error("Kunne ikke hente klienter:", err);
        });

    // Opret dropdown-container
    dropdown = document.createElement("div");
    dropdown.setAttribute("id", "clientDropdown");
    dropdown.style.position = "absolute";
    dropdown.style.backgroundColor = "white";
    dropdown.style.border = "1px solid #ccc";
    dropdown.style.zIndex = "1000";
    dropdown.style.maxHeight = "200px";
    dropdown.style.overflowY = "auto";
    dropdown.style.display = "none";
    dropdown.style.boxShadow = "0 2px 5px rgba(0,0,0,0.15)";
    document.body.appendChild(dropdown);

    // Håndter input og vis forslag
    clientInput.addEventListener("input", function () {
        const query = this.value.toLowerCase();
        dropdown.innerHTML = "";
        const rect = clientInput.getBoundingClientRect();
        dropdown.style.top = rect.bottom + window.scrollY + "px";
        dropdown.style.left = rect.left + window.scrollX + "px";
        dropdown.style.width = rect.width + "px";

        if (query.length < 1) {
            dropdown.style.display = "none";
            return;
        }

        const matches = clients.filter(client =>
            client.name.toLowerCase().includes(query) ||
            client.email.toLowerCase().includes(query)
        );

        if (matches.length === 0) {
            dropdown.style.display = "none";
            return;
        }

        matches.forEach(client => {
            const item = document.createElement("div");
            item.textContent = `${client.name} (${client.email})`;
            item.style.padding = "8px";
            item.style.cursor = "pointer";
            item.style.borderBottom = "1px solid #eee";
            item.addEventListener("mouseover", () => item.style.backgroundColor = "#f0f0f0");
            item.addEventListener("mouseout", () => item.style.backgroundColor = "white");

            item.addEventListener("click", function () {
                clientInput.value = client.name;
                hiddenClientId.value = client.id;
                dropdown.style.display = "none";
            });

            dropdown.appendChild(item);
        });

        dropdown.style.display = "block";
    });

    // Hvis brugeren trykker Enter
    clientInput.addEventListener("keydown", function (e) {
        if (e.key === "Enter") {
            e.preventDefault();
            const selectedClient = clients.find(client => client.name === clientInput.value);
            if (selectedClient) {
                hiddenClientId.value = selectedClient.id;
                dropdown.style.display = "none";
            } else {
                alert("Klienten blev ikke fundet. Vælg en fra listen.");
            }
        }
    });

    // Ekstra sikring ved form submission
    form.addEventListener("submit", function (e) {
        const selectedClient = clients.find(client => client.name === clientInput.value);
        if (selectedClient) {
            hiddenClientId.value = selectedClient.id;
        } else {
            e.preventDefault();
            alert("Du skal vælge en klient fra listen.");
        }
    });

    // Luk dropdown hvis man klikker udenfor
    document.addEventListener("click", function (e) {
        if (!clientInput.contains(e.target) && !dropdown.contains(e.target)) {
            dropdown.style.display = "none";
        }
    });
});
