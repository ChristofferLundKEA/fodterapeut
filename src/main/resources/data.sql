-- Clients
INSERT INTO client (client_id, name, email, phone_number, cpr, allergies, insurance) VALUES
(1, 'Laura Andresen', 'laura@example.com', '12345678', '010101-1234', 'None', 'Yes'),
(2, 'Kasper Jensen', 'kasper@example.com', '87654321', '020202-4321', 'Pollen', 'No');

-- Admin
INSERT INTO admin (admin_id, username, password) VALUES
    (1, 'admin', 'test123');

-- Request
INSERT INTO request (name, email, phone_number, message, is_seen, created_date) VALUES
('Line Madsen', 'line@example.com', '11112222', 'Jeg vil gerne booke tid', false, CURRENT_DATE),
('Ole Hansen', 'ole@example.com', '33334444', 'Spørgsmål om behandling', true, CURRENT_DATE);

-- Booking (uden booking_id – lader databasen generere det)
INSERT INTO booking (client_id, treatment, date, start, end_time, description) VALUES
(1, 'Fodbehandling', DATE '2025-05-10', TIMESTAMP '2025-05-10 10:00:00', TIMESTAMP '2025-05-10 11:00:00', 'Første besøg'),
(2, 'Indlægskonsultation', DATE '2025-05-12', TIMESTAMP '2025-05-12 13:30:00', TIMESTAMP '2025-05-12 14:15:00', 'Allergi-anamnese');

-- Client visits
INSERT INTO client_visit (visit_id, client_id, date, notes) VALUES
(1, 1, '2025-04-15', 'Opfølgning – alt OK'),
(2, 2, '2025-04-20', 'Anbefalet nye indlæg');

-- Insole booking
INSERT INTO insole_booking (name, email, phone_number, date, start, end_time) VALUES
    ('Kasper Jensen', 'kasper@example.com', '87654321', '2025-05-18', '2025-05-18T09:00:00', '2025-05-18T09:30:00');


-- Calendar events
INSERT INTO calender_event (event_id, booking_id, insole_id, title, start, end_time, all_day, description) VALUES
(1, 1, null, 'Fodbehandling - Laura', '2025-05-10T10:00:00', '2025-05-10T11:00:00', false, 'note1'),
(2, null, 1, 'Indlæg – Kasper', '2025-05-18T09:00:00', '2025-05-18T09:30:00', false, 'note2');

