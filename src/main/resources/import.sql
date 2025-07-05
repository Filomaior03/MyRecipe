INSERT INTO Credenziali (id, username, ruolo, password) VALUES (1, 'Filippo', 'ADMIN', '$2a$12$mFhNU83/DlaPPTT3CsPl4eS97wD0tUptVjUBqsloC/uqcaiEq8hwO'); -- pw: adminPassword!
INSERT INTO Credenziali (id, username, ruolo, password) VALUES (2, 'Lorenzo', 'UTENTE', '$2a$12$XLtySgpbpbvcItXcnRXXFOt8UosfWDesGmanuypP34eftkYdLyp3K'); -- pw: Lorenzo
INSERT INTO Credenziali (id, username, ruolo, password) VALUES (3, 'Michela', 'UTENTE', '$2a$12$H.3Sf/hYFaD8dykHy3eIxu6RPjbYJCcaZdyXjggJb7/NRf3qFWVty'); -- pw: Michela

-- UTENTI
INSERT INTO Utente (id, credenziali_id, nome, cognome, email) VALUES (1, 1, 'Filippo', 'Maiorino', 'filo@example.com');
INSERT INTO Utente (id, credenziali_id, nome, cognome, email) VALUES (2, 2, 'Lorenzo', 'Ricciardi', 'lore@example.com');
INSERT INTO Utente (id, credenziali_id, nome, cognome, email) VALUES (3, 3, 'Michela', 'Sicuranza', 'mich@example.com');

-- RICETTE
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (1, 'Carbonara', 'Primo', 'images/carbo.png', '20 minuti', 'Italia', 'Inizia cuocendo la pasta in abbondante acqua salata. Nel frattempo, taglia il guanciale a cubetti e fallo rosolare in una padella fino a che non diventa croccante. In una ciotola, sbatti le uova con il pecorino e il parmigiano, aggiungendo un po’ di sale e pepe a piacere. Quando la pasta è pronta, scolala e mescolala con il guanciale. Aggiungi il composto di uova e formaggio, mescolando velocemente per creare una crema liscia. Servi subito con una spolverata di pepe.');
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (2, 'Amatriciana', 'Primo', 'images/amatr.png', '20 minuti', 'Italia', 'Cuoci la pasta in acqua salata. Nel frattempo, taglia il guanciale a fette e rosolalo in una padella fino a che non diventa croccante. Aggiungi i pomodori pelati schiacciati e lascia cuocere per qualche minuto, aggiungendo un po’ di sale e pepe a piacere. Quando la pasta è pronta, scolala e uniscila al sugo, mescolando bene. Servi con una generosa spolverata di pecorino.');
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (3, 'Cacio e pepe', 'Primo', 'images/cape.png', '20 minuti', 'Italia', 'Cuoci la pasta in acqua salata. In una padella, tostare il pepe fino a renderlo aromatico. Scola la pasta al dente, conservando un po’ dell’acqua di cottura. Nella padella con il pepe, aggiungi un po’ di acqua di cottura della pasta e mescola bene. Unisci il pecorino grattugiato e mescola fino a ottenere una crema liscia. Aggiungi la pasta e amalgama bene, utilizzando l’acqua di cottura per regolare la consistenza. Servi subito, con una spolverata di pecorino.');
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (4, 'Gricia', 'Primo', 'images/gri.png', '20 minuti', 'Italia', 'Cuoci la pasta in acqua salata. Nel frattempo, taglia il guanciale a cubetti e fallo rosolare in una padella fino a che non diventa croccante. Scola la pasta al dente e uniscila al guanciale, mescolando bene. Aggiungi il pecorino e il parmigiano grattugiati, mescolando fino a ottenere una crema liscia, utilizzando un po’ dell’acqua di cottura della pasta per ottenere la giusta consistenza. Aggiungi un po’ di sale e pepe a piacere, e servi subito con una spolverata di pecorino.');
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (5, 'Pollo alle mandorle', 'Secondo', 'images/polloman.png', '20 minuti', 'Cina', 'Taglia il petto di pollo a pezzetti e infarinali leggermente. Scalda un po’ di olio in una padella e rosola il pollo infarinato fino a che non diventa dorato. Unisci le mandorle pelate e lascia che si tostino leggermente. Aggiungi la salsa di soia e la salsa teriyaki, mescolando bene. Lascia cuocere ancora qualche minuto per far amalgamare i sapori. Servi il pollo caldo con riso o verdure a piacere.');
INSERT INTO ricetta (id, nome, categoria, img, tempo_di_preparazione, provenienza, procedimento) VALUES (6, 'Ravioli al vapore', 'Secondo', 'images/rav.png', '20 minuti', 'Cina', 'Prepara la pasta per i ravioli usando la farina e l’acqua, impastando fino a ottenere un composto omogeneo. Stendi la pasta e crea dei dischi. In una ciotola, mescola la carne macinata con un po’ di salsa di soia e salsa teriyaki per condire il ripieno. Fai dei piccoli fagottini con la pasta, mettendo il ripieno al centro. Cuoci i ravioli al vapore fino a che non sono morbidi e pronti da servire.');

-- INGREDIENTI
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (1, 'pasta', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (2, 'uova', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (3, 'guanciale', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (4, 'pecorino', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (5, 'parmigiano', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (6, 'sale', 'q.b.', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (7, 'pepe', 'q.b.', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (8, 'petto di pollo', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (9, 'farina', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (10, 'acqua', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (11, 'cipollotto', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (12, 'salsa di soia', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (13, 'mandorle pelate', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (14, 'salsa teriyaki', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (15, 'pomodori pelati', '400gr', '4 persone');
INSERT INTO ingrediente (id, nome, quantità, dose) VALUES (16, 'macinato', '400gr', '4 persone');

-- Associazione tra ricette e ingredienti
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (1, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (1, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (1, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (1, 4);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (2, 1);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (3, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (3, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (3, 4);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (4, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (4, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (4, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (4, 4);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (5, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (5, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (5, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (5, 4);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 4);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 5);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (6, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 4);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 5);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (7, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (8, 5);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (9, 5);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (9, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (10, 1);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (10, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (10, 3);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (10, 4);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (10, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (11, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (12, 5);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (12, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (13, 5);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (14, 5);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (14, 6);

INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (15, 2);
INSERT INTO ingrediente_ricette (ingredienti_id, ricette_id) VALUES (16, 6);

-- Associazione tra utenti e ricette
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (1, 1);
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (2, 2);
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (3, 3);
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (2, 4);
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (1, 5);
INSERT INTO utente_ricette (utente_id, ricette_id) VALUES (3, 6);

-- Aggiorna il counter degli id delle varie tabelle
SELECT setval('credenziali_seq', (SELECT MAX(id) FROM credenziali));
SELECT setval('utente_seq', (SELECT MAX(id) FROM utente));
SELECT setval('ricetta_seq', (SELECT MAX(id) FROM ricetta));
SELECT setval('ingrediente_seq', (SELECT MAX(id) FROM ingrediente));