%% Stations.txt methods
clc;
clear;
close all;

data = fopen('Stations.txt','r');
for title = 1:4
    titles{title} = fscanf(data, '%s', 1);
end

artist = cell([1,3]);
hours = zeros([1,3]);
t_up = hours;
t_down = hours;

for count = 1:3
    artist{count} = fscanf(data, '%s', 1);
    hours(count)= fscanf(data, '%f', 1);
    t_up(count) = fscanf(data, '%i', 1);
    t_down(count) = fscanf(data, '%i', 1);
end
fclose(data);

fprintf('%s\t%s\t%s\n',artist{1:3})
fprintf('%.1f\t%.1f\t%.1f\n',hours(1:3))
fprintf('%i\t%i\t%i\n', t_up(1:3))
fprintf('%i\t%i\t%i\n', t_down(1:3))

%% Altering Strings
clc;
clear;
close all;

mine = 'My Love Mine all Mine';

% Task: Change all 'Mine' to 'Yours'
words = split(mine);

% alter words depending on their value
for count = 1:5
    if strcmp(words{count}, 'Mine') == 1
        words{count} = "Yours";
    elseif strcmp(words{count}, 'My') == 1
        words{count} = "His";
    end
    % Add spaces in between words
    words{count} = strcat(words{count}, " ");
end

% Combine the alteration into one string
title_new = strcat(words{1:end})
