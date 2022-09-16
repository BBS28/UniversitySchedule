package com.shchehlov.universityschedule.constant;

public class ValidationMessageConstant {
    private ValidationMessageConstant() {
    }

    public static final String CLASSROOM_NAME_NOT_EMPTY = "Classroom name can't be empty";
    public static final String GROUP_NAME_NOT_EMPTY = "Group name can't be empty";
    public static final String LECTURE_NAME_NOT_EMPTY = "Lecture name can't be empty";
    public static final String FIRST_NAME_NOT_EMPTY = "First name can't be empty";
    public static final String LAST_NAME_NOT_EMPTY = "Last name can't be empty";

    public static final String CLASSROOM_NAME_SIZE_CONSTRAINT = "Classroom name can't be more than 100 characters";
    public static final String GROUP_NAME_SIZE_CONSTRAINT = "Group name can't be more than 255 characters";
    public static final String LECTURE_NAME_SIZE_CONSTRAINT = "Lecture name can't be more than 255 characters";
    public static final String FIRST_NAME_SIZE_CONSTRAINT = "First name can't be more than 100 characters";
    public static final String LAST_NAME_SIZE_CONSTRAINT = "Last name can't be more than 100 characters";
    public static final String EMAIL_SIZE_CONSTRAINT = "Email can't be more than 255 characters";
    public static final String EMAIL_VALID = "Email address must be valid";
    public static final String PASSWORD_REGEXP = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String PASSWORD_REGEXP_MESSAGE = "Entered password should be min 8 symbols, 1 capital letter, 1 number";











}
