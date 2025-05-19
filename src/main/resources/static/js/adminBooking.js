document.addEventListener("DOMContentLoaded", function () {
    const clientInput = document.getElementById("clientInput");
    const datalist = document.getElementById("clientList");
    const hiddenClientId = document.getElementById("clientId");

    let clients = [];

    //Hent klienter fra backend
    fetch("/api/clients")
        .then(response => response.json())
        .then(data => {
            clients = data;
            data.forEach(client => {
                const option = document.createElement("option");
                option.value = client.name;
                option.dataset.id = client.client_id;
                datalist.appendChild(option);
            });
        })
        .catch(err => {
            console.error("Kunne ikke hente klienter:", err);
        });


    clientInput.addEventListener("change", () => {
        const selectedClient = clients.find(client => client.name === clientInput.value);
        hiddenClientId.value = selectedClient ? selectedClient.client_id : "";
    });
});
