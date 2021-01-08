package com.blog.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE("400", " Invalid Input Value"),
    INVALID_TYPE_VALUE("400", " Invalid Type Value"),
    ENTITY_NOT_FOUND("401", " Entity Not Found"),
    HANDLE_ACCESS_DENIED("403", "Access is Denied"),
    METHOD_NOT_ALLOWED("405", " Invalid Input Value"),
    NULL_VALUE("406", "NULL_VALUE"),
    INTERNAL_SERVER_ERROR("500", "Server Error"),
    UN_AUTHORIZED("501", "AccessToken check"),
    RESOURCE_NOT_FOUND("404", "RESOURCE NOT FOUND"),

    // user
    VALUE_DUPLICATE("1004", "This is a duplicate"),
    EXPIRE_TIME("1005", "Certification has expired."),
    DIFFERENT_NUMBER("1006", "The authentication number is different"),
    USER_NOT_FOUND("1007", "there is no User"),
    USER_ALLREADY_SIGNUP("1008", "USER_ALLREADY_SIGNUP"),
    DO_NOT_TWOROOM_LEADER("1009", "YOU CANNOT BE THE LEADER OF MORE THAN ONE ROOM"),
    DO_NOT_LEADER("1010", "There is no leader who is a leader."),
    DO_NOT_ROOM("1011", "There is no room"),
    ALREADY_JOINED_DEPARTMENT_OR_FRIEND_GROUP("1012", "Already Joined Department or FiendGroup"),
    PASSWORD_IS_DIFFERENT("1013", "Password is different."),
    IMAGE_NOT_FOUND("1014", "there is no IMAGE"),
    SIGN_UP_FAILED("1015", "sign up failed"),
    NAME_AND_SECURTIY_NUMBER_DIFFERENT(
            "1016", "The name and social security number are different."),

    // calendar
    CALENDAR_NODATA("1200", "There is no calendar data."),
    FAILED_TO_GENERATE_NIGHT_WORK_SCHEDULE("1201", "Failed to generate night work schedule."),
    FAILED_TO_GENERATE_DAY_WORK_SCHEDULE("1202", "Failed to generate day work schedule."),
    FAILED_TO_GENERATE_EVENING_WORK_SCHEDULE("1203", "Failed to generate evening work schedule."),

    // file
    FILE_NOT_CONVERT("1400", "File converting failed."),

    // workRequest

    WORK_REQUSET_NODATA("1300", "There is no work request."),
    WORK_REQUSET_READ("1301", "There is workRequest Read."),

    // auth
    LOGIN_ID_DUPLICATION("1100", "LoginId is Duplication"),
    DOMAIN_DUPLICATION("1101", "domain is Duplication"),
    MAIL_NOT_FOUND("1102", "There is no mail."),
    AUTHENTICATION_FAILED("11000", "authentication failed"),

    // api
    API_EXECUTION_FAILED("12000", "Api execution failed"),

    // schedule
    DUPLICATE_SCHDULE_ERROR("13000", "duplicate schdule"),
    DUPLICATE_PHONE_NUMBER_ERROR("13001", "duplicate phone number"),
    DUPLICATE_RESERVATION_ERROR("13002", "duplicate reservation"),
    DUPLICATE_DEVICE_TOKEN_ERROR("13003", "duplicate device token"),

    EXIST_ENTITY("14000", "exist entity"),

    // payment
    PAYMENT_AMOUNT_DIFFERENT_ERROR("15000", "Payment amount is different"),
    PAYMENT_APPROVAL_FAILED_ERROR("15001", "Failed to payment approval"),
    PAYMENT_REQUEST_FAILED_ERROR("15002", "Failed to payment request"),
    PAYMENT_APPROVAL_DATA_CONVERT_FAILED_ERROR("15003", "Failed to convert payment approval data"),
    PAYMENT_REQUEST_DATA_SAVE_FAILED_ERROR("15004", "Failed to save payment reqeust"),
    PAYMENT_APPROVAL_DATA_SAVE_FAILED_ERROR("15005", "Failed to save payment approval"),
    PAYMENT_CANCEL_FAILED_ERROR("15006", "Request to cancel payment failed"),

    // worktime
    WORK_START_TIME("16000", "Not working time"),

    // reservation
    HAS_RESERVATION("17000", "has Reservation");

    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }
}
