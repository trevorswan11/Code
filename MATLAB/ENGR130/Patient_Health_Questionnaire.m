%% load/alter/save question/response items
clear;
% clc;
close all;

GAD_PHQ = ["Feeling nervous, anxious, or on edge." "Little interest or pleasure in doing things."; 
    "Not being able to stop or control worrying." "Feeling down, depressed, or hopeless";
    "Worrying too much about different things." "Trouble falling/staying asleep, sleeping too much."; 
    "Trouble relaxing." "Feeling tired or having little energy.";
    "Being so restless that it's hard to sit still." "Poor appetite or overeating."; 
    "Becoming easily annoyed or irritable." "Feeling bad about yourself, or that you are a failure, or have let yourself or your family down.";
    "Feeling afraid as if something awful might happen." "Trouble concentrating on things, such as reading the newspaper or watching TV.";
    "" "Moving or speaking so slowly/quickly that other people could have noticed.";
    "" "Thoughts that you would be better off dead or of hurting yourself in some way."];
save('Patient_Health_Questionnaire.mat','GAD_PHQ')
load('health_data.mat')

%% GAD Questionnaire

scores_GAD = zeros(1,7);
for GAD_Q = 1:7
    % clc;
    fprintf(['The following pertain to the GAD portion of this assessment.\nPlease indicate your responses to the following based on this scale:\n' ...
        '<strong>0</strong> - Not at all\n<strong>1</strong> - Moderately annoyed\n<strong>2</strong> - Noticably Perturbed\n' ...
        '<strong>3</strong> - Distressed\n\n'])
    fprintf('Over the last <strong>day</strong>, how much were you bothered by the following problem?\n%i. <strong>%s</strong>\n',GAD_Q,GAD_PHQ(GAD_Q,1))
    scores_GAD(GAD_Q) = input('Indicate your response <strong>(0-3)</strong> here: ');
    
end

col_idx_GAD = size(responses_GAD,2) + 1;
responses_GAD(1,col_idx_GAD) = col_idx_GAD;
responses_GAD(2,col_idx_GAD) = sum(scores_GAD);

if sum(scores_GAD) > 0
    % clc;
    fprintf(['The following pertain to the GAD portion of this assessment.\nHow difficult have these problems made it for you to do your work, ' ...
        'take care of things at home, or get along with other people?\nPlease indicate your response based on this scale:\n'])
    fprintf(['<strong>0</strong> - Not difficult at all\n<strong>1</strong> - Somewhat difficult\n<strong>2</strong> - Very difficult\n' ...
        '<strong>3</strong> - Extremely difficult\n\n'])
    responses_GAD(3,col_idx_GAD) = input('Indicate your response <strong>(0-3)</strong> here: ');
end

%% PHQ Portion

scores_PHQ = zeros(1,9);
for PHQ_Q = 1:9
    % clc;
    fprintf(['The following pertain to the PHQ portion of this assessment.\nPlease indicate your responses to the following based on this scale:\n' ...
        '<strong>0</strong> - Not at all\n<strong>1</strong> - Moderately annoyed\n<strong>2</strong> - Noticably perturbed\n' ...
        '<strong>3</strong> - Distressed\n\n'])
    fprintf('Over the last <strong>day</strong>, how often have you been bothered by the following problem?\n%i. <strong>%s</strong>\n',PHQ_Q,GAD_PHQ(PHQ_Q,2))
    scores_PHQ(PHQ_Q) = input('Indicate your response <strong>(0-3)</strong> here: ');
end

col_idx_PHQ = size(responses_PHQ,2) + 1;
responses_PHQ(1,col_idx_PHQ) = col_idx_PHQ;
responses_PHQ(2,col_idx_PHQ) = sum(scores_PHQ);

if sum(scores_PHQ) > 0
    % clc;
    fprintf(['The following pertain to the PHQ portion of this assessment.\nHow difficult have these problems made it for you to do your work, ' ...
        'take care of things at home, or get along with other people?\nPlease indicate your response based on this scale:\n'])
    fprintf(['<strong>0</strong> - Not difficult at all\n<strong>1</strong> - Somewhat difficult\n<strong>2</strong> - Very difficult\n' ...
        '<strong>3</strong> - Extremely difficult\n\n'])
    responses_PHQ(3,col_idx_PHQ) = input('Indicate your response <strong>(0-3)</strong> here: ');
end

%% provide user with conclusive data 
clc;
GAD_diff_num = responses_GAD(3,col_idx_GAD);
PHQ_diff_num = responses_PHQ(3,col_idx_PHQ);

if GAD_diff_num == 0
    GAD_difficult = "not difficult at all";
elseif GAD_diff_num == 1
    GAD_difficult = "somewhat difficult";
elseif GAD_diff_num == 2
    GAD_difficult = "very difficult";
elseif GAD_diff_num == 3
    GAD_difficult = "extremely difficult";
end

if PHQ_diff_num == 0
    PHQ_difficult = "not difficult at all";
elseif PHQ_diff_num == 1
    PHQ_difficult = "somewhat difficult";
elseif PHQ_diff_num == 2
    PHQ_difficult = "very difficult";
elseif PHQ_diff_num == 3
    PHQ_difficult = "extremely difficult";
end

fprintf(['Trevor,\nThank you for completing the GAD Assessment and PHQ.\nYour data has been plotted to show changes over time.\n\n' ...
    'Your GAD score today was a <strong>%i/21</strong>.\nYou indicated that these problems make completing everyday tasks ' ...
    '<strong>%s</strong>.\n\n'],sum(scores_GAD),GAD_difficult)
fprintf(['Your PHQ score today was a <strong>%i/27</strong>.\nYou indicated ' ...
    'that these problems make completing everyday tasks <strong>%s</strong>.\n'],sum(scores_PHQ),PHQ_difficult)
save('health_data.mat','responses_GAD','responses_PHQ')

%% plot the users data
close all;
hold on
x_data = responses_GAD(1,:);
y_data_GAD = responses_GAD(2,:);
y_data_PHQ = responses_PHQ(2,:);
GAD_difficult_data = responses_GAD(3,:);
PHQ_difficult_data = responses_PHQ(3,:);
plot(x_data, y_data_GAD, 'r-', x_data, y_data_PHQ, 'b-')
plot(x_data, GAD_difficult_data, 'm*', x_data, PHQ_difficult_data, 'g*')

day = responses_GAD(1,end);
today_GAD = responses_GAD(2,end);
today_PHQ = responses_PHQ(2,end);
plot(day, today_GAD, 'ko', day, today_PHQ, 'ko')

xlabel('Number of Times Taken')
ylabel('Score')
title("Plot of Trevor's GAD and PHQ Scores Over Time")
legend('GAD data','PHQ data','GAD difficulty', 'PHQ difficulty',"Today's points")
xlim([0,day+1])
ylim([0,30])

