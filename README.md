# FinalProjV2
Danielle Nkem Ebende - 101218592

Functionlity
- Member stuff 1, 2, 3,
- Trainer stuff 2

Youtube: https://youtu.be/F-VpC0y30yM

Assumptions:
- For Trainers and admins, they can's be registered like a member they have to be added in by an existing admin staff.
- Maximum of 3 activites for routine per person
- The code crashes when the member_id keys dont align when registering as a member. So you'll have to register 3 times so it aligns well. Kind quirky behaviour.

Tools:
    ALTER SEQUENCE trainers_trainer_id_seq RESTART WITH 1;
    ALTER SEQUENCE members_member_id_seq RESTART WITH 1;
    delete from members where member_id = ;
    select * from members;
