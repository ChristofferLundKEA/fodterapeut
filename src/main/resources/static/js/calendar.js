document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendarDiv');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek',
        locale: 'da',
        events: '/getAllEvents',
        slotMinTime: "07:00:00",
        slotMaxTime: "19:00:00",
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        height: 'auto',
        
        eventClick: function(info) {
            const event = info.event;

            document.getElementById("eventId").value = event.id;
            document.getElementById("eventTitle").value = event.title;
            document.getElementById("eventStart").value = event.start.toISOString().slice(0, 16);
            document.getElementById("eventEnd").value = event.end.toISOString().slice(0, 16);
            document.getElementById("eventDescription").value = event.extendedProps.description;

            document.getElementById("eventModal").classList.remove("hidden");
        }
    });

    calendar.render();
});

function closeModal() {
    document.getElementById("eventModal").classList.add("hidden");
}

function updateEvent() {
    const id = document.getElementById("eventId").value;

    const updatedEvent = {
        title: document.getElementById("eventTitle").value,
        start: document.getElementById("eventStart").value,
        end: document.getElementById("eventEnd").value,
        description: document.getElementById("eventDescription").value
    };

    fetch(`/api/events/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedEvent)
    })
        .then(() => {
            closeModal();
            location.reload(); // Opdater kalenderen
        });
}

function deleteEvent() {
    const id = document.getElementById("eventId").value;
    console.log("Sletter event med id:", id); // ← tilføj denne linje

    fetch(`/api/events/${id}`, {
        method: "DELETE"
    })
        .then(() => {
            closeModal();
            location.reload();
        });
}

