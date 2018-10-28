create or replace function f_available_positions2 (
    prog_abbrev_ master_progs.prog_abbreviation%type) 
    return boolean 
is
    v_n_of_available_positions INTEGER ;        
begin
    select n_of_positions - n_of_filled_positions
    into v_n_of_available_positions
    from master_progs
    where prog_abbreviation = prog_abbrev_;
    
    if v_n_of_available_positions > 0 then 
        return true ;
    else
        return false ;
    end if ;    
end ;
/
    


create or replace procedure p_admission2
as
    cursor c_applicants is 
        select applicants.*, grades_avg * .6 + dissertation_avg * .4 as admission_avg
            from applicants order by grades_avg * .6 + dissertation_avg * .4 desc ;
            
begin
    -- we setup the filled positions on zero
    update master_progs set n_of_filled_positions = 0 ;
    
    -- set all the applicants accepted programme on NULL
    update applicants set prog_abbreviation_accepted = NULL ;

    -- arrange the applications and loop through each row (using a cursor)
    for rec_applicant in c_applicants loop
       
       -- here is the current applicant
       dbms_output.put_line(rec_applicant.applicant_name || ', admission avg: ' || 
            rec_applicant.admission_avg) ;
       
       -- convert the six option attributes into a record set 
       for rec_opt in (select rec_applicant.prog1_abbreviation as prog, 1 as option_no from dual union
            select rec_applicant.prog2_abbreviation, 2 from dual union
            select rec_applicant.prog3_abbreviation, 3  from dual union
            select rec_applicant.prog4_abbreviation, 4  from dual union
            select rec_applicant.prog5_abbreviation, 5 from dual union
            select rec_applicant.prog6_abbreviation, 6  from dual 
                order by 2) loop
            -- we process the i-th option of the current applicant
            --  dbms_output.put_line(rec_opt.option_no || ') ' || 
            --        rec_opt.prog) ;
            
            
            -- ... but fist we'll test if the current option is filled (not null)
            if rec_opt.prog is null then 
                exit ;
            end if ;
            
            -- we will try to assign the current applicant to this option
            --  (rec_opt.prog) provided there are still available places
            
            -- test if there is al least an available place for the current option 
            -- programme
            
            if f_available_positions2(rec_opt.prog)  then 
                -- we can assign the applicants to her/his current option
                update applicants
                set prog_abbreviation_accepted = rec_opt.prog
                where applicant_id = rec_applicant.applicant_id ;
                
                -- increase the number of filled positions
                update master_progs
                set n_of_filled_positions = n_of_filled_positions + 1
                where prog_abbreviation = rec_opt.prog ;
                
                -- avoid looping through remaining options of the current
                --  applicant
                exit ;
                
            end if ;
                  
       end loop ;
       
    end loop ;

end ;
/
