document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendarDiv');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek',
        locale: 'da',
        events: '/getAllEvents',
        slotMinTime:"07:00:00",
        slotMaxTime: "19:00:00",
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        height: 'auto'
    });
    calendar.render();
});