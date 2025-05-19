document.getElementById("create_request").addEventListener("click", () => {
    fetch("/createRequest", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            phone_number: document.getElementById("phone_number").value,
            message: document.getElementById("message").value,
        })
    })
        .then(res => {
            if (res.ok) {
                alert("Beskeden er sendt til klinikken")
            }else {
                alert("Hov, noget gik galt. Prøv igen")
            }
        })
        .catch(err => {
            console.error("Request failed", err)
            alert("Hov, noget gik galt. Prøv igen")
        })
})