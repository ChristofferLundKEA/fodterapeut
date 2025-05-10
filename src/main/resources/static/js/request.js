const url = "http://localhost:8080"

window.addEventListener("DOMContentLoaded", getAllRequests);

async function getAllRequests() {
    const res = await fetch(url + "/getAllRequests");
    const data = await res.json();

    const unseenBody = document.querySelector("#unseen-requests tbody");
    const seenBody = document.querySelector("#seen-requests tbody");

    data.forEach(req => {
        const row = document.createElement("tr");
        row.innerHTML = `
      <td>${req.name}</td>
      <td>${req.email}</td>
      <td>${req.phone_number}</td>
      <td>${req.message}</td>
      <td>${req.created_date}</td>
    `;
        if (req.is_seen) {
            seenBody.appendChild(row);
        } else {
            unseenBody.appendChild(row);
        }
    });
}


