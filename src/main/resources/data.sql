-- Clients
INSERT INTO client (name, email, phone_number, cpr, allergies, insurance) VALUES
 ('Laura Andresen', 'laura@example.com', '12345678', '010101-1234', 'None', 'Yes'),
 ('Kasper Jensen', 'kasper@example.com', '87654321', '020202-4321', 'Pollen', 'No'),
 ('Sofie Nielsen', 'sofie.nielsen@example.com', '11223344', '030303-5678', 'Gluten', 'Yes'),
 ('Mads Kristensen', 'mads.k@example.com', '99887766', '040404-8765', 'None', 'No'),
 ('Camilla Møller', 'camilla.m@example.com', '55667788', '050505-4321', 'Penicillin', 'Yes'),
 ('Jonas Andersen', 'jonas.a@example.com', '22334455', '060606-1111', 'None', 'No'),
 ('Emma Holm', 'emma.holm@example.com', '66778899', '070707-2222', 'Græs', 'Yes');

-- Admin
INSERT INTO admin (admin_id, username, password) VALUES
    (1, 'admin', 'test123');

-- Request
INSERT INTO request (name, email, phone_number, message, is_seen, created_date) VALUES
('Line Madsen', 'line@example.com', '11112222', 'Jeg vil gerne booke tid', false, CURRENT_DATE),
('Ole Hansen', 'ole@example.com', '33334444', 'Spørgsmål om behandling', true, CURRENT_DATE),
('Maria Sørensen', 'maria@example.com', '55556666', 'Hvad koster en behandling?', false, CURRENT_DATE),
('Anders Bertelsen', 'anders@example.com', '77778888', 'Jeg har brug for en akuttid', false, CURRENT_DATE),
('Emma Frederiksen', 'emmaf@example.com', '99990000', 'Jeg har fået anbefalet jer, vil gerne høre mere', true, CURRENT_DATE),
('Thomas Lund', 'thomas@example.com', '44445555', 'Kan jeg få tilskud via min forsikring?', false, CURRENT_DATE);

INSERT INTO booking (client_id, treatment, date, start, end_time, description) VALUES
(1, 'Fodbehandling', DATE '2025-05-10', TIMESTAMP '2025-05-10 10:00:00', TIMESTAMP '2025-05-10 11:00:00', 'Første besøg'),
(2, 'Indlægskonsultation', DATE '2025-05-12', TIMESTAMP '2025-05-12 13:30:00', TIMESTAMP '2025-05-12 14:15:00', 'Allergi-anamnese'),
(3, 'Laserbehandling', DATE '2025-05-15', TIMESTAMP '2025-05-15 09:00:00', TIMESTAMP '2025-05-15 09:45:00', 'Behandling mod hælspore'),
(4, 'Fodbehandling', DATE '2025-05-16', TIMESTAMP '2025-05-16 11:00:00', TIMESTAMP '2025-05-16 12:00:00', 'Tjek af negle og hård hud'),
(5, 'Indlægskonsultation', DATE '2025-05-18', TIMESTAMP '2025-05-18 14:00:00', TIMESTAMP '2025-05-18 14:30:00', 'Opfølgning på indlæg'),
(6, 'Fodbehandling', DATE '2025-05-20', TIMESTAMP '2025-05-20 15:00:00', TIMESTAMP '2025-05-20 16:00:00', 'Rutinebesøg');

-- Client visits
INSERT INTO client_visit (visit_id, client_id, date, notes) VALUES
(1, 1, '2025-04-15', 'Opfølgning – alt OK'),
(2, 2, '2025-04-20', 'Anbefalet nye indlæg');

-- Insole booking
INSERT INTO insole_booking (name, email, phone_number, date, start, end_time) VALUES
('Kasper Jensen', 'kasper@example.com', '87654321', '2025-05-18', '2025-05-18T09:00:00', '2025-05-18T09:30:00'),
('Laura Andresen', 'laura@example.com', '12345678', '2025-05-20', '2025-05-20T10:00:00', '2025-05-20T12:00:00'),
('Maria Sørensen', 'maria@example.com', '55556666', '2025-05-22', '2025-05-22T12:00:00', '2025-05-22T14:00:00'),
('Thomas Lund', 'thomas@example.com', '44445555', '2025-05-27', '2025-05-27T10:00:00', '2025-05-27T12:00:00'),
('Emma Frederiksen', 'emmaf@example.com', '99990000', '2025-05-29', '2025-05-29T12:00:00', '2025-05-29T14:00:00');


-- Calendar events
INSERT INTO calender_event (title, start, end_time, all_day, description) VALUES
    ( 'Fodbehandling - Emma', '2025-05-20T13:00:00', '2025-05-20T14:00:00', false, 'note3'),
    ( 'Indlæg – Mads', '2025-05-21T10:00:00', '2025-05-21T10:45:00', false, 'note4'),
    ( 'Fodbehandling - Sofie', '2025-05-23T09:30:00', '2025-05-23T10:30:00', false, 'note5'),
    ( 'Indlæg – Jonas', '2025-05-27T11:00:00', '2025-05-27T11:30:00', false, 'note6'),
    ( 'Fodbehandling - Ida', '2025-05-30T14:00:00', '2025-05-30T15:00:00', false, 'note7'),
    ( 'Indlæg – Oliver', '2025-06-03T08:30:00', '2025-06-03T09:15:00', false, 'note8'),
    ( 'Fodbehandling - Anna', '2025-06-06T12:00:00', '2025-06-06T13:00:00', false, 'note9'),
    ( 'Indlæg – Freja', '2025-06-11T10:00:00', '2025-06-11T10:45:00', false, 'note10');


