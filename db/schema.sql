
    create table CheckOperator (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table ContactJournal (
        ContactJournalId bigint not null auto_increment,
        Date datetime not null,
        Description longtext,
        CreatedBy bigint not null,
        ContactType varchar(255) not null,
        DietTreatmentId bigint not null,
        primary key (ContactJournalId)
    );

    create table ContactType (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table DietParameter (
        DietParameterTemplateId bigint not null,
        Start datetime,
        primary key (DietParameterTemplateId)
    );

    create table DietParameterSet (
        DietParameterSetId bigint not null auto_increment,
        Name varchar(255) not null unique,
        primary key (DietParameterSetId)
    );

    create table DietParameterTemplate (
        DietParameterTemplateId bigint not null auto_increment,
        CheckOperator varchar(255) not null,
        Duration integer,
        Value varchar(255),
        ParameterDefinitionUnitId bigint not null,
        DietParameterType varchar(255) not null,
        ParameterDefinitionId bigint not null,
        DietParameterSetId bigint,
        primary key (DietParameterTemplateId)
    );

    create table DietParameterType (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table DietPlan (
        DietPlanId bigint not null auto_increment,
        Name varchar(255) not null,
        CreatedOn datetime not null,
        PlanType varchar(255) not null,
        DietTreatmentId bigint not null,
        Creator bigint not null,
        primary key (DietPlanId)
    );

    create table DietPlanDietParameter (
        DietPlanId bigint not null,
        DietParameterId bigint not null unique,
        primary key (DietPlanId, DietParameterId)
    );

    create table DietTreatment (
        DietTreatmentId bigint not null auto_increment,
        Start datetime not null,
        Duration integer not null,
        Name varchar(255) not null,
        ShortDescription varchar(255),
        TreatmentState varchar(255) not null,
        PatientId bigint not null,
        primary key (DietTreatmentId)
    );

    create table DietTreatmentDietParameter (
        DietTreatmentId bigint not null,
        DietParameterId bigint not null unique,
        primary key (DietTreatmentId, DietParameterId)
    );

    create table DietTreatmentPatientState (
        DietTreatmentId bigint not null,
        PatientStateId bigint not null,
        primary key (PatientStateId, DietTreatmentId)
    );

    create table DietTreatmentSystemUser (
        DietTreatmentSystemUserId bigint not null auto_increment,
        SystemUserId bigint not null,
        Function varchar(255) not null,
        DietTreatmentId bigint not null,
        primary key (DietTreatmentSystemUserId)
    );

    create table FamilyAnamnesis (
        FamilyAnamnesisId bigint not null auto_increment,
        Person varchar(255) not null,
        PatientId bigint not null,
        primary key (FamilyAnamnesisId)
    );

    create table FamilyAnamnesisIllness (
        FamilyAnamnesisId bigint not null,
        IllnessId bigint not null,
        primary key (FamilyAnamnesisId, IllnessId)
    );

    create table FamilyStatus (
        Status varchar(255) not null,
        primary key (Status)
    );

    create table Gender (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table Illness (
        IllnessId bigint not null auto_increment,
        Name varchar(255) not null,
        primary key (IllnessId)
    );

    create table LaborParameter (
        LaborParameterId bigint not null auto_increment,
        Value varchar(255) not null,
        ParameterDefinitionUnitId bigint not null,
        CheckOperatorId varchar(255) not null,
        ParameterDefinitionId bigint not null,
        LaborReportId bigint,
        primary key (LaborParameterId)
    );

    create table LaborReport (
        LaborReportId bigint not null auto_increment,
        Date datetime not null,
        Notice longtext,
        Creator bigint not null,
        LaborReportTypeId bigint,
        PatientId bigint not null,
        primary key (LaborReportId)
    );

    create table LaborReportType (
        LaborReportTypeId bigint not null auto_increment,
        Name varchar(255) not null,
        primary key (LaborReportTypeId)
    );

    create table LaborReportTypeParameterDefinition (
        LaborReportTypeId bigint not null,
        ParameterDefinitionId bigint not null unique,
        primary key (LaborReportTypeId, ParameterDefinitionId)
    );

    create table Meal (
        MealId bigint not null auto_increment,
        Code varchar(255) not null,
        Name varchar(255) not null,
        TimeSpanId bigint not null,
        Idx integer,
        primary key (MealId)
    );

    create table MealDietParameter (
        MealId bigint not null,
        DietParameterId bigint not null unique,
        primary key (MealId, DietParameterId)
    );

    create table MealLine (
        MealLineId bigint not null auto_increment,
        Quantity float not null,
        Info longtext,
        recipe bigint not null,
        unit bigint not null,
        ParentMealLineId bigint,
        MealId bigint not null,
        Idx integer,
        primary key (MealLineId)
    );

    create table NutrimentParameter (
        NutrimentParameterId bigint not null auto_increment,
        Value varchar(255) not null,
        ParameterDefinitionId bigint not null,
        ParameterDefinitionUnitId bigint,
        RecipeId bigint not null,
        primary key (NutrimentParameterId)
    );

    create table NutritionProtocol (
        DietPlanId bigint not null,
        Date datetime not null,
        Contact varchar(255),
        Notice longtext,
        DietTreatmentId bigint not null,
        primary key (DietPlanId)
    );

    create table ParameterDefinition (
        ParameterDefinitionId bigint not null auto_increment,
        Name varchar(255) not null unique,
        CheckPattern varchar(255),
        primary key (ParameterDefinitionId)
    );

    create table ParameterDefinitionDataType (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table ParameterDefinitionUnit (
        ParameterDefinitionUnitId bigint not null auto_increment,
        Name varchar(255) not null unique,
        Type varchar(255) not null,
        primary key (ParameterDefinitionUnitId)
    );

    create table ParameterDefinitionUnits (
        ParameterDefinitionId bigint not null,
        ParameterDefinitionUnitId bigint not null,
        primary key (ParameterDefinitionId, ParameterDefinitionUnitId)
    );

    create table Patient (
        PatientId bigint not null auto_increment,
        InsuranceNumber varchar(255),
        Forename varchar(255) not null,
        Lastname varchar(255) not null,
        Username varchar(255),
        Password varchar(255),
        Title varchar(255),
        Street varchar(255),
        Zip varchar(255),
        Place varchar(255),
        Country varchar(255),
        Birthday datetime,
        Job varchar(255),
        Religion varchar(255),
        Regime longtext,
        Notice longtext,
        Gender varchar(255) not null,
        FamilyStatus varchar(255),
        primary key (PatientId)
    );

    create table PatientIllness (
        PatientId bigint not null,
        IllnessId bigint not null,
        primary key (PatientId, IllnessId)
    );

    create table PatientLike (
        PatientLikeId bigint not null auto_increment,
        PatientId bigint not null,
        BlsPattern varchar(255) not null,
        Grade bigint not null,
        Notice varchar(255),
        primary key (PatientLikeId)
    );

    create table PatientLikeGrade (
        PatientLikeGradeId bigint not null,
        Name varchar(255) not null,
        primary key (PatientLikeGradeId)
    );

    create table PatientState (
        PatientStateId bigint not null auto_increment,
        Date datetime not null,
        Anamnesis longtext,
        Weight integer,
        WeightPercentile float,
        Height integer,
        HeightPercentile float,
        Compliance integer,
        Motivation integer,
        Type varchar(255) not null,
        SystemUserId bigint not null,
        PatientId bigint not null,
        primary key (PatientStateId)
    );

    create table PatientStateLaborReports (
        PatientStateId bigint not null,
        LaborReportId bigint not null,
        primary key (PatientStateId, LaborReportId)
    );

    create table PatientStateType (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table PlanType (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table Recipe (
        RecipeId bigint not null auto_increment,
        Name varchar(255),
        BlsCode varchar(255),
        Difficulty integer not null,
        Description longtext,
        Benefits longtext,
        CookInstructions longtext,
        Amount float,
        ParameterDefinitionUnitId bigint,
        primary key (RecipeId)
    );

    create table RecipeIngredient (
        RecipeIngredientId bigint not null auto_increment,
        Amount float,
        IngredientId bigint,
        RecipeId bigint not null,
        primary key (RecipeIngredientId)
    );

    create table SystemUser (
        SystemUserId bigint not null auto_increment,
        Username varchar(255) not null unique,
        Password varchar(255) not null,
        Name varchar(255) not null,
        Email varchar(255) not null unique,
        DirectDial varchar(255),
        Department varchar(255),
        Job varchar(255),
        primary key (SystemUserId)
    );

    create table SystemUserFunction (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table SystemUserRight (
        SystemUserId bigint not null,
        UserRightId varchar(255) not null,
        primary key (SystemUserId, UserRightId)
    );

    create table TimeSpan (
        TimeSpanId bigint not null auto_increment,
        Start datetime not null,
        Duration integer not null,
        DietPlanId bigint not null,
        primary key (TimeSpanId)
    );

    create table TimeSpanDietParameter (
        TimeSpanId bigint not null,
        DietParameterId bigint not null unique,
        primary key (TimeSpanId, DietParameterId)
    );

    create table TreatmentState (
        Name varchar(255) not null,
        primary key (Name)
    );

    create table UserRight (
        Name varchar(255) not null,
        primary key (Name)
    );

    alter table ContactJournal 
        add index FK54C20B178D4F3D12 (ContactType), 
        add constraint FK54C20B178D4F3D12 
        foreign key (ContactType) 
        references ContactType (Name);

    alter table ContactJournal 
        add index FK54C20B17501CA4C1 (DietTreatmentId), 
        add constraint FK54C20B17501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table ContactJournal 
        add index FK54C20B17D283D1DB (CreatedBy), 
        add constraint FK54C20B17D283D1DB 
        foreign key (CreatedBy) 
        references SystemUser (SystemUserId);

    alter table DietParameter 
        add index FKDE1D29F55DB59177 (DietParameterTemplateId), 
        add constraint FKDE1D29F55DB59177 
        foreign key (DietParameterTemplateId) 
        references DietParameterTemplate (DietParameterTemplateId);

    alter table DietParameterTemplate 
        add index FK177958FA2C14277 (DietParameterSetId), 
        add constraint FK177958FA2C14277 
        foreign key (DietParameterSetId) 
        references DietParameterSet (DietParameterSetId);

    alter table DietParameterTemplate 
        add index FK177958F232A8D9C (DietParameterType), 
        add constraint FK177958F232A8D9C 
        foreign key (DietParameterType) 
        references DietParameterType (Name);

    alter table DietParameterTemplate 
        add index FK177958F6E3C21F1 (ParameterDefinitionId), 
        add constraint FK177958F6E3C21F1 
        foreign key (ParameterDefinitionId) 
        references ParameterDefinition (ParameterDefinitionId);

    alter table DietParameterTemplate 
        add index FK177958FE3E89D19 (ParameterDefinitionUnitId), 
        add constraint FK177958FE3E89D19 
        foreign key (ParameterDefinitionUnitId) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table DietParameterTemplate 
        add index FK177958FD29C5A36 (CheckOperator), 
        add constraint FK177958FD29C5A36 
        foreign key (CheckOperator) 
        references CheckOperator (Name);

    alter table DietPlan 
        add index FKFD20555D501CA4C1 (DietTreatmentId), 
        add constraint FKFD20555D501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table DietPlan 
        add index FKFD20555DEC27E0C8 (Creator), 
        add constraint FKFD20555DEC27E0C8 
        foreign key (Creator) 
        references SystemUser (SystemUserId);

    alter table DietPlan 
        add index FKFD20555D54B30668 (PlanType), 
        add constraint FKFD20555D54B30668 
        foreign key (PlanType) 
        references PlanType (Name);

    alter table DietPlanDietParameter 
        add index FKE68133B865527EB7 (DietPlanId), 
        add constraint FKE68133B865527EB7 
        foreign key (DietPlanId) 
        references DietPlan (DietPlanId);

    alter table DietPlanDietParameter 
        add index FKE68133B8444512E3 (DietParameterId), 
        add constraint FKE68133B8444512E3 
        foreign key (DietParameterId) 
        references DietParameter (DietParameterTemplateId);

    alter table DietTreatment 
        add index FK65D7EEC45952B14 (TreatmentState), 
        add constraint FK65D7EEC45952B14 
        foreign key (TreatmentState) 
        references TreatmentState (Name);

    alter table DietTreatment 
        add index FK65D7EEC4A7608723 (PatientId), 
        add constraint FK65D7EEC4A7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table DietTreatmentDietParameter 
        add index FKCE659B1501CA4C1 (DietTreatmentId), 
        add constraint FKCE659B1501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table DietTreatmentDietParameter 
        add index FKCE659B1444512E3 (DietParameterId), 
        add constraint FKCE659B1444512E3 
        foreign key (DietParameterId) 
        references DietParameter (DietParameterTemplateId);

    alter table DietTreatmentPatientState 
        add index FK9CB64170501CA4C1 (DietTreatmentId), 
        add constraint FK9CB64170501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table DietTreatmentPatientState 
        add index FK9CB64170C53650D5 (PatientStateId), 
        add constraint FK9CB64170C53650D5 
        foreign key (PatientStateId) 
        references PatientState (PatientStateId);

    alter table DietTreatmentSystemUser 
        add index FK7F83487EBFC865B1 (SystemUserId), 
        add constraint FK7F83487EBFC865B1 
        foreign key (SystemUserId) 
        references SystemUser (SystemUserId);

    alter table DietTreatmentSystemUser 
        add index FK7F83487E501CA4C1 (DietTreatmentId), 
        add constraint FK7F83487E501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table DietTreatmentSystemUser 
        add index FK7F83487E71A6506C (Function), 
        add constraint FK7F83487E71A6506C 
        foreign key (Function) 
        references SystemUserFunction (Name);

    alter table FamilyAnamnesis 
        add index FK377CD329A7608723 (PatientId), 
        add constraint FK377CD329A7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table FamilyAnamnesisIllness 
        add index FKB427BA17F40A20CB (FamilyAnamnesisId), 
        add constraint FKB427BA17F40A20CB 
        foreign key (FamilyAnamnesisId) 
        references FamilyAnamnesis (FamilyAnamnesisId);

    alter table FamilyAnamnesisIllness 
        add index FKB427BA1711924959 (IllnessId), 
        add constraint FKB427BA1711924959 
        foreign key (IllnessId) 
        references Illness (IllnessId);

    alter table LaborParameter 
        add index FKA34D557937BC9541 (LaborReportId), 
        add constraint FKA34D557937BC9541 
        foreign key (LaborReportId) 
        references LaborReport (LaborReportId);

    alter table LaborParameter 
        add index FKA34D55796E3C21F1 (ParameterDefinitionId), 
        add constraint FKA34D55796E3C21F1 
        foreign key (ParameterDefinitionId) 
        references ParameterDefinition (ParameterDefinitionId);

    alter table LaborParameter 
        add index FKA34D55793591AC51 (CheckOperatorId), 
        add constraint FKA34D55793591AC51 
        foreign key (CheckOperatorId) 
        references CheckOperator (Name);

    alter table LaborParameter 
        add index FKA34D5579E3E89D19 (ParameterDefinitionUnitId), 
        add constraint FKA34D5579E3E89D19 
        foreign key (ParameterDefinitionUnitId) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table LaborReport 
        add index FKB0C585644547BE55 (LaborReportTypeId), 
        add constraint FKB0C585644547BE55 
        foreign key (LaborReportTypeId) 
        references LaborReportType (LaborReportTypeId);

    alter table LaborReport 
        add index FKB0C58564A7608723 (PatientId), 
        add constraint FKB0C58564A7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table LaborReport 
        add index FKB0C58564EC27E0C8 (Creator), 
        add constraint FKB0C58564EC27E0C8 
        foreign key (Creator) 
        references SystemUser (SystemUserId);

    alter table LaborReportTypeParameterDefinition 
        add index FK7CB4F23E4547BE55 (LaborReportTypeId), 
        add constraint FK7CB4F23E4547BE55 
        foreign key (LaborReportTypeId) 
        references LaborReportType (LaborReportTypeId);

    alter table LaborReportTypeParameterDefinition 
        add index FK7CB4F23E6E3C21F1 (ParameterDefinitionId), 
        add constraint FK7CB4F23E6E3C21F1 
        foreign key (ParameterDefinitionId) 
        references ParameterDefinition (ParameterDefinitionId);

    alter table Meal 
        add index FK2487E35FB4052B (TimeSpanId), 
        add constraint FK2487E35FB4052B 
        foreign key (TimeSpanId) 
        references TimeSpan (TimeSpanId);

    alter table MealDietParameter 
        add index FK7575CAF2444512E3 (DietParameterId), 
        add constraint FK7575CAF2444512E3 
        foreign key (DietParameterId) 
        references DietParameter (DietParameterTemplateId);

    alter table MealDietParameter 
        add index FK7575CAF21636B763 (MealId), 
        add constraint FK7575CAF21636B763 
        foreign key (MealId) 
        references Meal (MealId);

    alter table MealLine 
        add index FKC92C02776ED73A1E (recipe), 
        add constraint FKC92C02776ED73A1E 
        foreign key (recipe) 
        references Recipe (RecipeId);

    alter table MealLine 
        add index FKC92C02774DA9F442 (unit), 
        add constraint FKC92C02774DA9F442 
        foreign key (unit) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table MealLine 
        add index FKC92C02775992B695 (ParentMealLineId), 
        add constraint FKC92C02775992B695 
        foreign key (ParentMealLineId) 
        references MealLine (MealLineId);

    alter table MealLine 
        add index FKC92C02771636B763 (MealId), 
        add constraint FKC92C02771636B763 
        foreign key (MealId) 
        references Meal (MealId);

    alter table NutrimentParameter 
        add index FKCE37D0076E3C21F1 (ParameterDefinitionId), 
        add constraint FKCE37D0076E3C21F1 
        foreign key (ParameterDefinitionId) 
        references ParameterDefinition (ParameterDefinitionId);

    alter table NutrimentParameter 
        add index FKCE37D007E3E89D19 (ParameterDefinitionUnitId), 
        add constraint FKCE37D007E3E89D19 
        foreign key (ParameterDefinitionUnitId) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table NutrimentParameter 
        add index FKCE37D00776981BB9 (RecipeId), 
        add constraint FKCE37D00776981BB9 
        foreign key (RecipeId) 
        references Recipe (RecipeId);

    alter table NutritionProtocol 
        add index FKC3CA9630501CA4C1 (DietTreatmentId), 
        add constraint FKC3CA9630501CA4C1 
        foreign key (DietTreatmentId) 
        references DietTreatment (DietTreatmentId);

    alter table NutritionProtocol 
        add index FKC3CA963065527EB7 (DietPlanId), 
        add constraint FKC3CA963065527EB7 
        foreign key (DietPlanId) 
        references DietPlan (DietPlanId);

    alter table ParameterDefinitionUnit 
        add index FKC1D6DD60513EB178 (Type), 
        add constraint FKC1D6DD60513EB178 
        foreign key (Type) 
        references ParameterDefinitionDataType (Name);

    alter table ParameterDefinitionUnits 
        add index FK7904CF136E3C21F1 (ParameterDefinitionId), 
        add constraint FK7904CF136E3C21F1 
        foreign key (ParameterDefinitionId) 
        references ParameterDefinition (ParameterDefinitionId);

    alter table ParameterDefinitionUnits 
        add index FK7904CF13E3E89D19 (ParameterDefinitionUnitId), 
        add constraint FK7904CF13E3E89D19 
        foreign key (ParameterDefinitionUnitId) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table Patient 
        add index FK340C82E549567484 (Gender), 
        add constraint FK340C82E549567484 
        foreign key (Gender) 
        references Gender (Name);

    alter table Patient 
        add index FK340C82E527DED24E (FamilyStatus), 
        add constraint FK340C82E527DED24E 
        foreign key (FamilyStatus) 
        references FamilyStatus (Status);

    alter table PatientIllness 
        add index FK7FC21EDBA7608723 (PatientId), 
        add constraint FK7FC21EDBA7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table PatientIllness 
        add index FK7FC21EDB11924959 (IllnessId), 
        add constraint FK7FC21EDB11924959 
        foreign key (IllnessId) 
        references Illness (IllnessId);

    alter table PatientLike 
        add index FK82BAAC1CA7608723 (PatientId), 
        add constraint FK82BAAC1CA7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table PatientLike 
        add index FK82BAAC1C2800554 (Grade), 
        add constraint FK82BAAC1C2800554 
        foreign key (Grade) 
        references PatientLikeGrade (PatientLikeGradeId);

    alter table PatientState 
        add index FKD50258ACBFC865B1 (SystemUserId), 
        add constraint FKD50258ACBFC865B1 
        foreign key (SystemUserId) 
        references SystemUser (SystemUserId);

    alter table PatientState 
        add index FKD50258ACA7608723 (PatientId), 
        add constraint FKD50258ACA7608723 
        foreign key (PatientId) 
        references Patient (PatientId);

    alter table PatientState 
        add index FKD50258AC8B95A5A2 (Type), 
        add constraint FKD50258AC8B95A5A2 
        foreign key (Type) 
        references PatientStateType (Name);

    alter table PatientStateLaborReports 
        add index FK1431DE3B37BC9541 (LaborReportId), 
        add constraint FK1431DE3B37BC9541 
        foreign key (LaborReportId) 
        references LaborReport (LaborReportId);

    alter table PatientStateLaborReports 
        add index FK1431DE3BC53650D5 (PatientStateId), 
        add constraint FK1431DE3BC53650D5 
        foreign key (PatientStateId) 
        references PatientState (PatientStateId);

    alter table Recipe 
        add index FK91AB41AEE3E89D19 (ParameterDefinitionUnitId), 
        add constraint FK91AB41AEE3E89D19 
        foreign key (ParameterDefinitionUnitId) 
        references ParameterDefinitionUnit (ParameterDefinitionUnitId);

    alter table RecipeIngredient 
        add index FK6743CE1F776E333C (IngredientId), 
        add constraint FK6743CE1F776E333C 
        foreign key (IngredientId) 
        references Recipe (RecipeId);

    alter table RecipeIngredient 
        add index FK6743CE1F76981BB9 (RecipeId), 
        add constraint FK6743CE1F76981BB9 
        foreign key (RecipeId) 
        references Recipe (RecipeId);

    alter table SystemUserRight 
        add index FKD1C4F782BFC865B1 (SystemUserId), 
        add constraint FKD1C4F782BFC865B1 
        foreign key (SystemUserId) 
        references SystemUser (SystemUserId);

    alter table SystemUserRight 
        add index FKD1C4F7822C5188DB (UserRightId), 
        add constraint FKD1C4F7822C5188DB 
        foreign key (UserRightId) 
        references UserRight (Name);

    alter table TimeSpan 
        add index FK8807F3D765527EB7 (DietPlanId), 
        add constraint FK8807F3D765527EB7 
        foreign key (DietPlanId) 
        references DietPlan (DietPlanId);

    alter table TimeSpanDietParameter 
        add index FK111C6B7E5FB4052B (TimeSpanId), 
        add constraint FK111C6B7E5FB4052B 
        foreign key (TimeSpanId) 
        references TimeSpan (TimeSpanId);

    alter table TimeSpanDietParameter 
        add index FK111C6B7E444512E3 (DietParameterId), 
        add constraint FK111C6B7E444512E3 
        foreign key (DietParameterId) 
        references DietParameter (DietParameterTemplateId);
