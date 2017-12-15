insert into Conseiller values ('Conseiller1','password1','Potter','Harry');
insert into Conseiller values ('Conseiller2','password2','Whisley','Ron');
insert into Conseiller values ('Conseiller3','password3','Granger','Hermione');
insert into Conseiller values ('1','1','Bunny','Bugs');

insert into Client values(seq_client.nextval,'SARKOZY','Nicolas','10 rue de larnaque','75005','paris','0126597820','arnaqueur@devieux.com','Conseiller1');
insert into Client values(seq_client.nextval,'HOLLANDE','Francois','10 rue de la mollesse','75005','paris','0226597820','zerocharisme@flan.com','Conseiller1');
insert into Client values(seq_client.nextval,'MACRON','Emmanuel','10 rue de largent','75005','paris','0326597820','pourlesriches@jetprivee.com','Conseiller2');
insert into Client values(seq_client.nextval,'MELANCHON','JL','10 rue de la revolte','75005','paris','0426597820','insoumis@revolution.com','Conseiller1');
insert into Client values(seq_client.nextval,'LEPEN','Marine','10 rue de la racaille','75005','paris','0526597820','pourzerzer@france.com','Conseiller2');

insert into Compte values (seq_compte.nextval,5875,sysdate, 'courant', 1000, 0, 1);
insert into Compte values (seq_compte.nextval,10000,sysdate, 'epargne', 0, 0.5, 2);
insert into Compte values (seq_compte.nextval,60000,sysdate, 'epargne', 0, 0.5, 1);
insert into Compte values (seq_compte.nextval,1000,sysdate, 'courant', 1000, 0, 3);
insert into Compte values (seq_compte.nextval,5875,sysdate, 'courant', 1000, 0, 4);
insert into Compte values (seq_compte.nextval,20000,sysdate, 'epargne', 0, 0.5, 4);

insert into Virement values (seq_virement.nextval,212,sysdate, 'Conseiller1');

insert into Compte_Virement values (1001,1);
insert into Compte_Virement values (1002,1);

commit;