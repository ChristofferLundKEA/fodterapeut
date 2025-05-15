document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("insoleForm");

    if (!form) return;

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const dateInput = document.getElementById("date");
        const selectedDate = new Date(dateInput.value);
        const day = selectedDate.getDay(); // 2 = tirsdag, 4 = torsdag

        if (day !== 2 && day !== 4) {
            alert("Du kan kun booke en tid tirsdag eller torsdag.");
            return;
        }

        const formData = new FormData(form);

        fetch("/bookInsole", {
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(result => {
                if (result === "OK") {
                    alert("Tak! Din booking er gennemført.");
                    form.reset();
                } else {
                    alert("Noget gik galt – prøv igen.");
                }
            })
            .catch(() => {
                alert("Fejl ved forbindelse – prøv igen senere.");
            });
    });
});
