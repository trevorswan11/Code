%% houssekeeping and load question/output data
clc;
clear;
close all;
opts = delimitedTextImportOptions("NumVariables", 1);

% Specify range and delimiter
opts.DataLines = [1, Inf];
opts.Delimiter = "?";

% Specify column names and types
opts.VariableNames = "VarName1";
opts.VariableTypes = "string";

% Specify file level properties
opts.ExtraColumnsRule = "ignore";
opts.EmptyLineRule = "read";

% Specify variable properties
opts = setvaropts(opts, "VarName1", "WhitespaceRule", "preserve");
opts = setvaropts(opts, "VarName1", "EmptyFieldRule", "auto");

% Import the data
questions = readmatrix("questions.csv", opts);
score_output = readmatrix("score_outputs.csv", opts);
custom_text = readmatrix("custom_text.csv", opts);

% questions = readmatrix(".csv", opts); % place quesiton file here
% score_output = readmatrix(".csv", opts); % place output data here
% custom_text = readmatrix(".csv", opts); % place custom txt file here
% custom_text = " "; % use this if you dont want a disclaimer 

% question array can have n questions, but it must be one-dimensional
size_q = length(questions);
if mod(numel(score_output),7) ~= 0
    error('I told you to keep your number of responses at exactly 7...')
end

%% reset average data if requested
load('score_data.mat','scores','avg_score')
fprintf('Hello Trevor,\n')
reset = input('Type in the password to reset the score/average data,\nor press <strong>ENTER</strong> to skip: ','s');
if reset == "574631"
    avg_score = [];
    scores = [];
    clc;
    fprintf('<strong>The score data has been reset and the average and score is set to 77 percent of the number of questions.</strong>\n')
    pause(2)
    clc;
else
    clc;
    fprintf('The test will continue as normal.\n')
    pause(1)
    clc;
end

%% load score and average data

% in the event of a score data reset, a 'nice' score of 77% of total is filled in as
% the default score and average. This can and should be changed to the
% creator's score
if isempty(scores) == 1
    scores = 0.77*size_q;
    avg_score = 0.77*size_q;
end
old_avg = avg_score;
clear avg_score;

%% prompt user name
name = [];
while isempty(name) == 1
    clc;
    fprintf(['Welcome to the SKAT purity test... in MATLAB!!!\nHonesty is the best policy... ' ...
        'your name data will not be stored anywhere,\nbut your score will be recorded to find an ' ...
        'average among participants.\n\n'])

    fprintf(['<strong>Caution: This is not a bucket list. Completion of all items on this test ' ...
        'will likely result in death... or worse. </strong> \n\n'])


    name = input('Please enter your name or alias: ','s');
end

%% prompt the user to begin the test
begin = "a";
while isempty(begin) == 0
    clc;
    fprintf(['%s,\nWelcome to the SKAT purity test... in MATLAB!!!\nHonesty is the best policy... ' ...
        'your name data will not be stored anywhere,\nbut your score will be recorded to find an ' ...
        'average among participants.\n'],name)

    fprintf(['<strong>Caution: This is not a bucket list. Completion of all items on this test ' ...
        'will likely result in death... or worse. </strong> \n'])

    fprintf(['Respond <strong>y</strong> or <strong>n</strong> for every question.\n' ...
        '%s\n'],custom_text(1))

    fprintf(['\n<strong>DISCLAIMER: The answers you put for each question are not stored anywhere,\nas they are deleted as'...
        ' soon as the next question is asked.\nIf, at any time, you do not feel comfortable answering these questions,' ...
        '\npress CTRL+C and the code will stop immediately.\n</strong>'])

    begin = input('\nPress <strong>ENTER</strong> to continue...','s');
end
%% asking questions
score_counter = 0;

% this loop is almost foolproof, It will only break if something other than
% a single letter is entered. It can handle any number of questions.
for question_num = 1:size_q
    clc;
    response = "a";
    fprintf(['Respond <strong>y</strong> or <strong>n</strong> for every item you have done. ' ...
        '%s\n'],custom_text(1))
    fprintf('%i. %s? ',question_num,questions(question_num))
    while ((response ~= "y" && response ~= "n") && (response ~= "Y" && response ~= "N"))
        clc;
        fprintf(['Respond only with <strong>y</strong> or <strong>n</strong> for this question. ' ...
            '%s\n'],custom_text(1))
        fprintf('%i. %s? ',question_num,questions(question_num))
        response = input('<strong>y/n</strong> ','s');
        if (isempty(response) == 1) || (response == "nn" || response == "yy" || response == "NN" || response == "YY")
            while isempty(response) == 1
                clc;
                fprintf(['Respond only with <strong>y</strong> or <strong>n</strong> for this question. ' ...
                    '%s\n'],custom_text(1))
                fprintf('%i. %s? ',question_num,questions(question_num))
                response = input('<strong>y/n</strong> ','s');
            end
        end
    end
    if response == "y" || response == "Y"
        score_counter = score_counter + 1;
    end
    clear response;
end

%% score calculator and final display
clc;
your_score = size_q - score_counter;
fprintf('\nCongragulations %s, you have finished the bean purity test!\nYour score is: <strong>%i/%i</strong>.\n',name,your_score,size_q)

% this conditional works based on percentage of question count, so any
% number of questions can be asked and yield an appropriate output
if your_score == size_q
    fprintf('%s',score_output(1))
elseif your_score >= 0.9*size_q
    fprintf('%s',score_output(2))
elseif your_score >= .75*size_q
    fprintf('%s',score_output(3))
elseif your_score >= .60*size_q
    fprintf('%s',score_output(4))
elseif your_score >= .45*size_q
    fprintf('%s',score_output(5))
elseif your_score >= .30*size_q
    fprintf('%s',score_output(6))
else
    fprintf('%s',score_output(7))
end

%% score data
num_scores = size(scores,2);
scores(num_scores + 1) = your_score;
avg_score = mean(scores);
response = "a";

% the following pertains to displaying information regarding test
% population
while (response ~= "y" && response ~= "n") && (response ~= "Y" && response ~= "N")
    response = input('\nWould you like to see the average score among participants? <strong> y/n </strong> ','s');
    if (response ~= "y" && response ~= "n") && (response ~= "Y" && response ~= "N")
        while (response ~= "y" && response ~= "n") && (response ~= "Y" && response ~= "N")
            clc;
            fprintf('\nCongragulations %s, you have finished the bean purity test!\nYour score is: <strong>%i/%i</strong>.\n',name,your_score,size_q)
            if your_score == size_q
                fprintf('%s',score_output(1))
            elseif your_score >= 0.9*size_q
                fprintf('%s',score_output(2))
            elseif your_score >= .75*size_q
                fprintf('%s',score_output(3))
            elseif your_score >= .60*size_q
                fprintf('%s',score_output(4))
            elseif your_score >= .45*size_q
                fprintf('%s',score_output(5))
            elseif your_score >= .30*size_q
                fprintf('%s',score_output(6))
            else
                fprintf('%s',score_output(7))
            end
            response = input('\nWould you like to see the average score among participants? <strong> y/n </strong> ','s');
            if response == "y" || response == "n" || response == "Y" || response == "N"
                break
            end
        end
    end

end

% this condional displays information about the average, if requested
if response == 'y' || response == 'Y'
    fprintf('\nThe average score among participants is a %.1f.',avg_score)
    if avg_score < old_avg
        fprintf('\nCongrats! You lowered the average!')
    elseif avg_score > old_avg
        fprintf('\nWow look at you raising the average!')
    else
        fprintf('\nYour score did <strong>absolutley nothing</strong> to the avereage... How tragic')
    end
else
    fprintf("\nSuit yourself. It's probably best if you don't know.")
end

% as promised, the following saves the score and avg, then deletes peronal data
save('score_data.mat','scores','avg_score')
clear;
pause(1)
fprintf('\n\n<strong>Any and all personal information has been cleared!</strong>\n\nHave a great day!\n')
