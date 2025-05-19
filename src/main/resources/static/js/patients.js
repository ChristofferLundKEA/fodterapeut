let allClients = [];

document.addEventListener("DOMContentLoaded", () => {
    loadClients();

    document.getElementById("searchInput").addEventListener("input", function () {
        const searchTerm = this.value.toLowerCase();
        const filteredClients = allClients.filter(client =>
            client.name.toLowerCase().includes(searchTerm)
        );
        displayClients(filteredClients);
    });

    document.getElementById("openModalBtn").addEventListener("click", () => {
        document.getElementById("patientModal").classList.remove("hidden");
    });

    document.getElementById("closeModal").addEventListener("click", () => {
        document.getElementById("patientModal").classList.add("hidden");
    });
});

function loadClients() {
    fetch("/api/clients")
        .then(response => response.json())
        .then(clients => {
            const listDiv = document.getElementById("patientList");
            listDiv.innerHTML = "";

            clients.forEach(client => {
                const div = document.createElement("div");
                div.className = "patient-item";
                div.textContent = client.name;

                div.addEventListener("click", () => {
                    document.querySelectorAll(".patient-item").forEach(item => item.classList.remove("selected"));
                    div.classList.add("selected");
                    showDetails(client);
                });

                listDiv.appendChild(div);
            });
        });
}

function displayClients(clients) {
    const listDiv = document.getElementById("patientList");
    listDiv.innerHTML = "";

    clients.forEach(client => {
        const div = document.createElement("div");
        div.className = "patient-item";
        div.textContent = client.name;
        div.addEventListener("click", () => showDetails(client));
        listDiv.appendChild(div);
    });
}

function showDetails(client) {
    const details = document.getElementById("patientDetails");
    details.innerHTML = `
        <h2>Patientens detaljer</h2>
        <label>Navn:</label><br>
        <input type="text" id="editName" value="${client.name}"><br>

        <label>Email:</label><br>
        <input type="text" id="editEmail" value="${client.email}"><br>

        <label>Telefon:</label><br>
        <input type="text" id="editPhone" value="${client.phone_number}"><br>
        
        <label>Cpr:</label><br>
        <input type="text" id="editCpr" value="${client.cpr}"><br>
        
        <label>Allergier:</label><br>
        <input type="text" id="editAllergies" value="${client.allergies}"><br>

        <label>Forsikring:</label><br>
        <select id="editInsurance">
            <option value="true" ${client.insurance ? 'selected' : ''}>Ja</option>
            <option value="false" ${!client.insurance ? 'selected' : ''}>Nej</option>
        </select><br><br>

        <div class="btn-group">
            <button onclick="updateClient(${client.id})">💾 Gem</button>
            <button onclick="deleteClient(${client.id})">🗑️ Slet</button>
        </div>

        <div class="tab-content" id="historyTab">
            <h3>Historik</h3>
            <ul id="historyList"></ul>
            <input type="date" id="historyDate">
            <input type="text" id="historyNote" placeholder="Tilføj note">
            <button class="add-history-btn" onclick="addHistory(${client.id})">➕ Tilføj historik</button>
        </div>
    `;

    loadHistory(client.id);
}

function updateClient(clientId) {
    const updatedClient = {
        name: document.getElementById("editName").value,
        email: document.getElementById("editEmail").value,
        phone_number: document.getElementById("editPhone").value,
        cpr: document.getElementById("editCpr").value,
        allergies: document.getElementById("editAllergies").value,
        insurance: document.getElementById("editInsurance").value === "true"
    };

    fetch(`/api/clients/${clientId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedClient)
    }).then(response => {
        if (response.ok) {
            alert("Patient opdateret!");
            loadClients();
        } else {
            alert("Fejl under opdatering");
        }
    });
}

function deleteClient(clientId) {
    const confirmed = confirm("Er du sikker på, at du vil slette denne patient?");
    if (!confirmed) return;

    fetch(`/api/clients/${clientId}`, {
        method: "DELETE"
    }).then(response => {
        if (response.ok) {
            alert("Patient slettet!");
            loadClients();
            document.getElementById("patientDetails").innerHTML = "<p>Vælg en patient</p>";
        } else {
            alert("Fejl under sletning");
        }
    });
}

function createNewClient() {
    const newClient = {
        name: document.getElementById("newName").value,
        email: document.getElementById("newEmail").value,
        phone_number: document.getElementById("newPhone").value,
        cpr: document.getElementById("newCpr").value,
        allergies: document.getElementById("newAllergies").value,
        insurance: document.getElementById("newInsurance").value === "true"
    };

    fetch("/api/clients", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newClient)
    }).then(response => {
        if (response.ok) {
            alert("Ny patient oprettet!");
            document.getElementById("patientModal").classList.add("hidden");
            loadClients();
        } else {
            alert("Fejl under oprettelse");
        }
    });
}

function loadHistory(clientId) {
    fetch(`/api/clients/${clientId}/history`)
        .then(response => response.json())
        .then(history => {
            const list = document.getElementById("historyList");
            list.innerHTML = "";

            history.forEach(entry => {
                const formattedDate = formatDate(entry.date);
                const li = document.createElement("li");
                li.textContent = `${formattedDate} – ${entry.note}`;
                list.appendChild(li);
            });
        });
}

function addHistory(clientId) {
    const date = document.getElementById("historyDate").value;
    const note = document.getElementById("historyNote").value;

    if (!date || !note) {
        alert("Udfyld både dato og note!");
        return;
    }

    const historyEntry = { date, note };

    fetch(`/api/clients/${clientId}/history`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(historyEntry)
    }).then(response => {
        if (response.ok) {
            loadHistory(clientId);
            document.getElementById("historyDate").value = "";
            document.getElementById("historyNote").value = "";
        } else {
            alert("Fejl ved oprettelse af historik");
        }
    });
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString("da-DK", {
        day: "2-digit",
        month: "long",
        year: "numeric"
    });
}
