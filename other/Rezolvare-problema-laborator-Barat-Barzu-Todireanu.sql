/*
  Propunere rezolvare problema sali examinare
  Autori:
    Barat Maria Andra
    Barzu Gabriel Catalin
    Todireanu Denis Laurentiu
*/

SET SERVEROUTPUT ON;

select * from studenti;
select * from sali;
select * from specializari;
select * from discipline;
select * from STUD_SALA_EXAMINARE;
select * from PLANIFICARE_EXAMENE;

alter table sali add abreviere_spec VARCHAR2(10) DEFAULT NULL;
alter table sali add locuri_ocupate INTEGER DEFAULT 0;
alter table specializari add repartizata INTEGER DEFAULT 0;
alter table studenti add repartizat INTEGER DEFAULT 0;
--curatare db
update sali set abreviere_spec = NULL;
update sali set locuri_ocupate = 0;
update specializari set repartizata = 0;
update studenti set repartizat = 0;
delete from STUD_SALA_EXAMINARE;

--versiunea unde pun toate salile cu acelasi numar direct
declare
  --cursor parcurge studenti
  cursor c_studenti is
    select id_stud, abreviere_spec, an_studii, repartizat from studenti;
  --cursor parcurgere studenti si materii
  cursor c_materii_studentii is
    select abreviere_spec, count(*) as nr_stud from studenti
    group by abreviere_spec, AN_STUDII
    order by 2 desc;
  --cursor parcurgere lista sali
  cursor c_sali is
    select nume_sala, nr_locuri, abreviere_spec, locuri_ocupate from sali
    order by 2 desc;
  --cursor pentru parcurgerea materiilor nerepartizate
  cursor c_materii is
    select abreviere_spec, repartizata from specializari;
  --variabila numar total studenti
  v_nr_total_studenti INTEGER ;
  --variabila numar total studenti
  v_nr_total_locuri_sali INTEGER ;
  --variabila temporara pentru a optine materia
  v_nume_spec_temp_studenti VARCHAR2(10);
  --variabila temporara pentru a optine nr de stundeti de la fiecare materie
  v_nr_spec_temp_studenti INTEGER;
  --variabila pentru id-ul examenului
  v_id_examen INTEGER;
begin
  --curatare db
  update sali set abreviere_spec = NULL;
  update sali set locuri_ocupate = 0;
  update specializari set repartizata = 0;
  update studenti set repartizat = 0;
  delete from STUD_SALA_EXAMINARE;
  
  --verificam daca nr studenti > nr locuri in sali atunci nu se pot repartiza toti
  select count(*) into v_nr_total_studenti from studenti;
  select sum(nr_locuri) into v_nr_total_locuri_sali from sali;
  if (v_nr_total_studenti > v_nr_total_locuri_sali) then
    dbms_output.put_line('Numarul total de studenti este mai mare decat numarul total de locuri in sali');
    return;
  end if;
  
  --adaugare doar materiile care incap exact intr-o sala de curs
  for val_materii_studentii in c_materii_studentii loop
    for val_sali in c_sali loop
      if (val_materii_studentii.nr_stud = val_sali.nr_locuri and val_sali.abreviere_spec is null) then
        update sali set abreviere_spec = val_materii_studentii.abreviere_spec where nume_sala = val_sali.nume_sala ;
        update specializari set repartizata = 1 where abreviere_spec = val_materii_studentii.abreviere_spec ;
      end if;
    end loop;
  end loop;
  
  --folosind un algorith greedy vom repartiza materiile pe clase
  for val_materii in c_materii loop
    if (val_materii.repartizata = 0 ) then
      --obtinem materiile nerepartizate dupa prima runda
      v_nume_spec_temp_studenti := val_materii.abreviere_spec;
      select count(*) into v_nr_spec_temp_studenti from studenti where abreviere_spec = v_nume_spec_temp_studenti group by abreviere_spec, AN_STUDII ;
      --parcurgem pt materia aleasa si o asezam in toate clasele actualizant tablea
      for val_sali in c_sali loop
        if (val_sali.abreviere_spec is null) then
          if (v_nr_spec_temp_studenti = 0) then
            exit;
          end if;
          update sali set abreviere_spec = v_nume_spec_temp_studenti where nume_sala = val_sali.nume_sala;
          update specializari set repartizata = 1 where abreviere_spec = v_nume_spec_temp_studenti;
          v_nr_spec_temp_studenti := v_nr_spec_temp_studenti - val_sali.nr_locuri;
        end if;
      end loop;
    end if;
  end loop;
  
  --vom parcurge salile iar pe baza aprevierilor vom repartiza stundetii
  for val_studenti in c_studenti loop
    if (val_studenti.repartizat = 0) then
      --repartizam elevul
      for val_sali in c_sali loop
        if(val_sali.locuri_ocupate < val_sali.nr_locuri and val_sali.abreviere_spec is not null and val_studenti.abreviere_spec = val_sali.abreviere_spec) then
          --adaugam studentul in lista aia, ii pun 1 pe repartizat, mergem mai departe
          update sali set locuri_ocupate = val_sali.locuri_ocupate + 1 where nume_sala = val_sali.nume_sala;
          update studenti set repartizat = 1 where id_stud = val_studenti.id_stud;
          select id_examen into v_id_examen from planificare_examene where abreviere_spec = val_sali.abreviere_spec and an_studii = val_studenti.an_studii;
          insert into STUD_SALA_EXAMINARE (id_stud, id_examen, nume_sala) values (val_studenti.id_stud, v_id_examen, val_sali.nume_sala);
          exit;
        end if;
      end loop;
    end if;
  end loop;
end;
/

--verificare
select * from STUD_SALA_EXAMINARE;