clear all; clc; close all

f_id = fopen('Pipeline Data.txt','r'); % Open file for reading

label = fscanf(f_id,'%s',6);           % Read First line - Column Labels
units = fscanf(f_id,'%s',6);           % Read Second Line - Column Units

k = 0;                                 % Set counter to 0
while (fscanf(f_id, '%s', 1) ~= '#')   %Check for end of file
    k = k + 1;
    Vel(k) = fscanf(f_id,'%f',1);      % Fluid Velocity
    Den(k) = fscanf(f_id,'%f',1);      % Fluid Density
    NPS(k) = fscanf(f_id,'%f',1);      % Pipe NPS
    Sch(k) = fscanf(f_id,'%f',1);      % Pipe Schedule
    Stiff  = fscanf(f_id,'%s',1);      % pipe stiffness
    if Stiff(1:4) == 'Stif'            % Identify if "Stiff" pipe
        STF(k) = 1;                    
    elseif Stiff(1:4) == 'Med_'        % Identify if "Med_Stiff"
        STF(k) = 2;
    elseif Stiff(1:4) == 'Medi'        % Identify if "Medium"
        STF(k) = 3;
    else                               % Set to 0 if nothing matches
        STF(k) = 0;
    end
end
