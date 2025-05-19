const url = "http://localhost:8080";

window.addEventListener("DOMContentLoaded", () => {
    getAllRequests();

    // Add listener to update button
    document.getElementById("update-seen").addEventListener("click", updateSeenStatus);
});

async function getAllRequests() {
    const res = await fetch(url + "/getAllRequests");
    const data = await res.json();

    const unseenBody = document.querySelector("#unseen-requests tbody");
    const seenBody = document.querySelector("#seen-requests tbody");

    unseenBody.innerHTML = "";
    seenBody.innerHTML = "";

    data.forEach(req => {
        const row = document.createElement("tr");

        row.innerHTML = `
      <td>${req.name}</td>
      <td>${req.email}</td>
      <td>${req.phone_number}</td>
      <td>${req.message}</td>
      <td>${req.created_date}</td>
      <td style="text-align: center;">
        <input type="checkbox" class="toggle-seen" data-id="${req.request_id}" ${req.is_seen ? "checked" : ""}>
      </td>
    `;

        (req.is_seen ? seenBody : unseenBody).appendChild(row);
    });
}

function updateSeenStatus() {
    const checkboxes = document.querySelectorAll(".toggle-seen");

    const updates = Array.from(checkboxes).map(checkbox => ({
        request_id: parseInt(checkbox.dataset.id),
        is_seen: checkbox.checked
    }));

    fetch(url + "/updateSeenStatus", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updates)
    })
        .then(res => {
            if (res.ok) {
                alert("Opdateret!");
                getAllRequests(); // reload updated table
            } else {
                alert("Noget gik galt!");
            }
        })
        .catch(err => {
            console.error(err);
            alert("Fejl under opdatering");
        });
}
