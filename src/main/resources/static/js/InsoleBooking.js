document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("insoleForm");

    if (!form) return;

    form.addEventListener("submit", function (e) {
        e.preventDefault(); // Forhindrer siden i at reloade

        const dateInput = document.getElementById("date");
        const selectedDate = new Date(dateInput.value);
        const day = selectedDate.getDay(); // 2 = tirsdag, 4 = torsdag

        if (day !== 2 && day !== 4) {
            alert("Du kan kun booke en tid tirsdage eller torsdage.");
            return;
        }

        const formData = new FormData(form);

        fetch("/bookInsole", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert("Bookingen er gennemført! Du hører snart fra os.");
                    form.reset();
                } else {
                    alert("Noget gik galt – prøv igen.");
                }
            })
            .catch(() => {
                alert("Forbindelsesfejl – prøv igen senere.");
            });
    });
});
