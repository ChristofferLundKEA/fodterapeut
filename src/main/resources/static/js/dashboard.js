window.addEventListener("DOMContentLoaded", async () => {
    const res = await fetch( "/getAllRequests");
    const data = await res.json();

    const unseenCount = data.filter(req => !req.is_seen).length;

    const notification = document.getElementById("request_notification");
    notification.innerText = `(${unseenCount})`;
});


document.getElementById("card1").addEventListener("click", () => {
    window.location.href = "/html/requests.html"
})

document.getElementById("request_notification").addEventListener("click", () => {

})